package hyard.r.dofus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

/**
 * Created by admin on 28/05/2018.
 */

public class GestionBD {

    /**
     * Propriétés
     */
    private SQLiteDatabase maBase;
    private BDHelper monBDHelper;

    /**
     * Constructeur
     * @param context
     */
    public GestionBD(Context context){
        monBDHelper = new BDHelper(context, "base", null, 1);
    }

    /**
     * Ouverture de base de données
     */
    public void open(){
        maBase = monBDHelper.getWritableDatabase();
    }

    /**
     * Fermeture de base de données
     */
    public void close(){
        maBase.close();
    }

    /*******************************************************CLASSE*/
    /**
     * Ajout d'une classe (dans l'interface)
     * @param classed
     * @return
     */
    public long ajouteClasse(ClasseD classed){
        ContentValues v = new ContentValues();
        v.put("idClasse", classed.getIdClasse());
        v.put("nom", classed.getNom());
        v.put("difficulte", classed.getDifficulte());
        v.put("description", classed.getDescription());
        v.put("dieu", classed.getDieu());
        return maBase.insert("classed", null, v);
    }

    /**
     * Suppression d'une classe
     */
    public void supprimeClasse(){
        maBase.delete("classed", null, null);
    }

    public ArrayList<String> donneChaineClasse(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select idClasse, nom, difficulte, description, dieu from classed order by nom", null);
        while(c.moveToNext())
            liste.add(c.getString(0)+"_"+c.getString(1));
        if(liste == null){
            liste.add("erreur de bdd!");
        }
        return liste;
    }

    /**
     * Afficher les classes
     * @return
     */
    public ArrayList<String> donneLesClasses(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c = maBase.rawQuery("select nom from classed order by nom", null);
        while(c.moveToNext())
            liste.add(c.getString(0));
        if(liste == null){
            liste.add("erreur de bd ! ");
        }
        return liste;
    }

    /**
     * Affiche une classe
     * @param choix
     * @return
     */
    public ClasseD donneUneClasse(String choix){
        ClasseD laClasse;
        String laRequete = "select idClasse, nom, difficulte, description, dieu from classed where nom like'"+choix+"'";
        Cursor c = maBase.rawQuery(laRequete, null);
        if(c.moveToNext()){
            laClasse = new ClasseD(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4));
        }
        else{
            laClasse = new ClasseD (0, "erreur bdd", 0, "erreur bdd", "erreur bdd");
        }
        return laClasse;
    }

    /******************************************************METIER

    /**
     * Ajout d'un métier (dans l'interface)
     * @param metier
     * @return
     */
    public long ajouteMetier(Metier metier){
        ContentValues v = new ContentValues();
        v.put("idMetier", metier.getIdMetier());
        v.put("nomMetier", metier.getNomMetier());
        v.put("descriptionMetier", metier.getDescriptionMetier());
        v.put("level", metier.getLevel());
        return maBase.insert("metier", null, v);
    }

    /**
     * Suppression d'un métier
     */
    public void supprimeMetier(){
        maBase.delete("metier", null, null);
    }

    public ArrayList<String> donneChaineMetier(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c2 = maBase.rawQuery("select idMetier, nomMetier, descriptionMetier, level from metier order by nomMetier", null);
        while(c2.moveToNext())
            liste.add(c2.getString(0)+"_"+c2.getString(1));
        if(liste == null){
            liste.add("erreur de bdd!");
        }
        return liste;
    }

    /**
     * Afficher les métiers
     * @return
     */
    public ArrayList<String> donneLesMetiers(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c2 = maBase.rawQuery("select nomMetier from metier order by nomMetier", null);
        while(c2.moveToNext())
            liste.add(c2.getString(0));
        if(liste == null){
            liste.add("erreur de bd ! ");
        }
        return liste;
    }

    /**
     * Affiche un metier
     * @param choix
     * @return
     */
    public Metier donneUnMetier(String choix){
        Metier leMetier;
        String laRequete2 = "select idMetier, nomMetier, descriptionMetier, level from metier where nomMetier like'"+choix+"'";
        Cursor c2 = maBase.rawQuery(laRequete2, null);
        if(c2.moveToNext()){
            leMetier = new Metier(c2.getInt(0), c2.getString(1), c2.getString(2), c2.getInt(3));
        }
        else{
            leMetier = new Metier (0, "erreur bdd", "erreur bdd", 0);
        }
        return leMetier;
    }

    /**********************************************DOFUS
    /**
     * Ajout d'un dofus (dans l'interface)
     * @param dofus
     * @return
     */
    public long ajouteDofus(Dofus dofus){
        ContentValues v = new ContentValues();
        v.put("idDofus", dofus.getIdDofus());
        v.put("nomDofus", dofus.getNomDofus());
        v.put("levelDofus", dofus.getLevelDofus());
        v.put("descriptionDofus", dofus.getDescriptionDofus());
        v.put("effetDofus", dofus.getEffetDofus());
        v.put("descripEffet", dofus.getDescripEffet());
        return maBase.insert("dofus", null, v);
    }

    /**
     * Suppression d'un dofus
     */
    public void supprimeDofus(){
        maBase.delete("dofus", null, null);
    }

    public ArrayList<String> donneChaineDofus(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c3 = maBase.rawQuery("select idDofus, nomDofus, levelDofus, descriptionDofus, effetDofus, descripEffet from dofus order by nomDofus", null);
        while(c3.moveToNext())
            liste.add(c3.getString(0)+"_"+c3.getString(1));
        if(liste == null){
            liste.add("erreur de bdd!");
        }
        return liste;
    }

    /**
     * Afficher les dofus
     * @return
     */
    public ArrayList<String> donneLesDofus(){
        ArrayList<String> liste = new ArrayList<String>();
        Cursor c3 = maBase.rawQuery("select nomDofus from dofus order by nomDofus", null);
        while(c3.moveToNext())
            liste.add(c3.getString(0));
        if(liste == null){
            liste.add("erreur de bd ! ");
        }
        return liste;
    }

    /**
     * Affiche une classe
     * @param choix
     * @return
     */
    public Dofus donneUnDofus(String choix){
        Dofus leDofus;
        String laRequete = "select idDofus, nomDofus, levelDofus, descriptionDofus, effetDofus, descripEffet from dofus where nomDofus like'"+choix+"'";
        Cursor c3 = maBase.rawQuery(laRequete, null);
        if(c3.moveToNext()){
            leDofus = new Dofus(c3.getInt(0), c3.getString(1), c3.getInt(2), c3.getString(3), c3.getString(4), c3.getString(5));
        }
        else{
            leDofus = new Dofus (0, "erreur bdd", 0, "erreur bdd", "erreur bdd", "erreur bdd");
        }
        return leDofus;
    }

}
