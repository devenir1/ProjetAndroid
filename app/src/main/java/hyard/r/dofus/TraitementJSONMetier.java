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
 * Created by admin on 29/05/2018.
 */

public class TraitementJSONMetier extends AsyncTask<String, Void, Boolean> {

    /**
     * Propriétés
     */
    private List<Metier> lesMetiers = new ArrayList<Metier>();
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
    public TraitementJSONMetier(Context context) {
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
            String ficMetier;

            //Récupération du contenu du fichier json sur serveur
            ficMetier = lectureFichierDistant();
            Log.i("doInBackground", "le fichier distant : " + ficMetier);

            //Transformation du fichier obtenu en objet JSON
            JSONObject jsonMetier = parseClasse(ficMetier);

            //Traitement de l'objet JSON pour obtenir des instances de Personnages
            Log.i("doInBackground", "le fichier json : " + jsonMetier.toString());
            recMetier(jsonMetier);
            Log.i("doInBackground", "nombre de métier: " + lesMetiers.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les métiers : ");
            long id;
            for (Metier metier : lesMetiers) {
                message.append(metier.getIdMetier());
                message.append(" : ");
                message.append(metier.getNomMetier());
                message.append(" : ");
                message.append(metier.getDescriptionMetier());
                message.append(" : ");
                message.append(metier.getLevel());
                message.append("\n");
                num++;
                id = sgbd.ajouteMetier(metier);
            }
            Log.i("doInBackground", "message : " + message);
            sgbd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void recMetier(JSONObject jsonMetier) {

        JSONArray listeMetier = null;
        try {
            listeMetier = jsonMetier.getJSONArray("metier");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recupMetier", "erreurJSArray");
        }

        for (int i = 0; i < listeMetier.length(); i++) {
            JSONObject nuplet = null;
            String nomMetier, descriptionMetier;
            int idMetier, level;
            Long id;
            Metier metier;
            try {
                nuplet = listeMetier.getJSONObject(i);
                idMetier = nuplet.getInt("idMetier");
                nomMetier = nuplet.getString("nomMetier");
                descriptionMetier = nuplet.getString("descriptionMetier");
                level = nuplet.getInt("level");

                metier= new Metier(idMetier, nomMetier, descriptionMetier, level);
                lesMetiers.add(metier);
                //id = sgbd.ajouteClasse(classeD);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject parseClasse(String textMetiers) {
        if (textMetiers != null) {
            try {
                jObj = new JSONObject(textMetiers);
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

    public List<Metier> getLesMetiers() {
        return lesMetiers;
    }

    public String getLesNomsMetier() {
        String liste = "";
        for (Metier metier : lesMetiers) {
            liste += metier.getNomMetier() + "\n";
        }
        return liste;
    }

}
