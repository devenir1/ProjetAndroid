package hyard.r.dofus;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 28/05/2018.
 */

public class TraitementJSONClasseD extends AsyncTask<String, Void, Boolean> {

    /**
     * Propriétés
     */
    private List<ClasseD> lesClasses = new ArrayList<ClasseD>();
    private Context context;
    private JSONObject jObj = null;
    private URL url;
    private HttpURLConnection connexion;
    private GestionBD sgbd;

    /**
     * Constructeur
     *
     * @param context
     */
    public TraitementJSONClasseD(Context context) {
        this.context = context;
        sgbd = new GestionBD(context);
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        Log.i("doInBackground", "le départ : ");
        sgbd.open();
        try {
            url = new URL(urls[0]);
            Log.i("doInBackground", "le fichier distant : " + urls[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Boolean result = false;

        try {
            String ficClasse;

            //Récupération du contenu du fichier json sur serveur
            ficClasse = lectureFichierDistant();
            Log.i("doInBackground", "le fichier distant : " + ficClasse);

            //Transformation du fichier obtenu en objet JSON
            JSONObject jsonClasse = parseClasse(ficClasse);

            //Traitement de l'objet JSON pour obtenir des instances de Personnages
            Log.i("doInBackground", "le fichier json : " + jsonClasse.toString());
            recClasse(jsonClasse);
            Log.i("doInBackground", "nombre de classes : " + lesClasses.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les classes : ");
            long id;
            for (ClasseD classeD : lesClasses) {
                message.append(classeD.getIdClasse());
                message.append(" : ");
                message.append(classeD.getNom());
                message.append(" : ");
                message.append(classeD.getDifficulte());
                message.append(" : ");
                message.append(classeD.getDescription());
                message.append(" : ");
                message.append(classeD.getDieu());
                message.append("\n");
                num++;
                id = sgbd.ajouteClasse(classeD);
            }
            Log.i("doInBackground", "message : " + message);
            sgbd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void recClasse(JSONObject jsonClasse) {

        JSONArray listeClasse = null;
        try {
            listeClasse = jsonClasse.getJSONArray("classed");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recupClasse", "erreurJSArray");
        }

        for (int i = 0; i < listeClasse.length(); i++) {
            JSONObject nuplet = null;
            String nom, description, dieu;
            int idClasse, difficulte;
            Long id;
            ClasseD classeD;
            try {
                nuplet = listeClasse.getJSONObject(i);
                idClasse = nuplet.getInt("idClasse");
                nom = nuplet.getString("nom");
                difficulte = nuplet.getInt("difficulte");
                description = nuplet.getString("description");
                dieu = nuplet.getString("dieu");

                classeD = new ClasseD(idClasse, nom, difficulte, description, dieu);
                lesClasses.add(classeD);
                //id = sgbd.ajouteClasse(classeD);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject parseClasse(String textClasses) {
        if (textClasses != null) {
            try {
                jObj = new JSONObject(textClasses);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("parseClasse", "erreurJSObj");
            }
            return jObj;
        }
        return null;
    }

    private String lectureFichierDistant() {
        StringBuilder builder = new StringBuilder();

        //adresse du serveur (modification pour passage en production
        try {
            connexion = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public List<ClasseD> getLesClasses() {
        return lesClasses;
    }

    public String getLesNoms() {
        String liste = "";
        for (ClasseD classeD : lesClasses) {
            liste += classeD.getNom() + "\n";
        }
        return liste;
    }
}
