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

public class TraitementJSONDofus extends AsyncTask<String, Void, Boolean>{
    /**
     * Propriétés
     */
    private List<Dofus> lesDofus = new ArrayList<Dofus>();
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
    public TraitementJSONDofus(Context context) {
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
            String ficDofus;

            //Récupération du contenu du fichier json sur serveur
            ficDofus = lectureFichierDistant();
            Log.i("doInBackground", "le fichier distant : " + ficDofus);

            //Transformation du fichier obtenu en objet JSON
            JSONObject jsonDofus = parseDofus(ficDofus);

            //Traitement de l'objet JSON pour obtenir des instances de Dofus
            Log.i("doInBackground", "le fichier json : " + jsonDofus.toString());
            recDofus(jsonDofus);
            Log.i("doInBackground", "nombre de classes : " + lesDofus.size());
            int num = 1;
            StringBuilder message = new StringBuilder("les classes : ");
            long id;
            for (Dofus dofus : lesDofus) {
                message.append(dofus.getIdDofus());
                message.append(" : ");
                message.append(dofus.getNomDofus());
                message.append(" : ");
                message.append(dofus.getLevelDofus());
                message.append(" : ");
                message.append(dofus.getDescriptionDofus());
                message.append(" : ");
                message.append(dofus.getEffetDofus());
                message.append(" : ");
                message.append(dofus.getDescripEffet());
                message.append("\n");
                num++;
                id = sgbd.ajouteDofus(dofus);
            }
            Log.i("doInBackground", "message : " + message);
            sgbd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void recDofus(JSONObject jsonDofus) {

        JSONArray listeDofus = null;
        try {
            listeDofus = jsonDofus.getJSONArray("dofus");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("recupDofus", "erreurJSArray");
        }

        for (int i = 0; i < listeDofus.length(); i++) {
            JSONObject nuplet = null;
            String nomDofus, descriptionDofus, effetDofus, descripEffet;
            int idDofus, levelDofus;
            Long id;
            Dofus dofus;
            try {
                nuplet = listeDofus.getJSONObject(i);
                idDofus = nuplet.getInt("idDofus");
                nomDofus = nuplet.getString("nomDofus");
                levelDofus = nuplet.getInt("levelDofus");
                descriptionDofus = nuplet.getString("descriptionDofus");
                effetDofus = nuplet.getString("effetDofus");
                descripEffet = nuplet.getString("descripEffet");

                dofus = new Dofus(idDofus, nomDofus, levelDofus, descriptionDofus, effetDofus, descripEffet);
                lesDofus.add(dofus);
                //id = sgbd.ajouteClasse(classeD);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject parseDofus(String textDofus) {
        if (textDofus != null) {
            try {
                jObj = new JSONObject(textDofus);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("parseDofus", "erreurJSObj");
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

    public List<Dofus> getLesDofus() {
        return lesDofus;
    }

    public String getLesNoms() {
        String liste = "";
        for (Dofus dofus : lesDofus) {
            liste += dofus.getNomDofus() + "\n";
        }
        return liste;
    }
}
