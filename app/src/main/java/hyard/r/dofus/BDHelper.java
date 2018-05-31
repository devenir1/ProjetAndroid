package hyard.r.dofus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 28/05/2018.
 */

public class BDHelper extends SQLiteOpenHelper{

    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public BDHelper(Context context, String name, CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    /**
     * Creation de table
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        String reqClasse = "create table classed (idClasse int, nom text, difficulte int, description text, dieu text)";
        String reqMetier = "create table metier (idMetier int, nomMetier text, descriptionMetier text, level int)";
        String reqDofus = "create table dofus (idDofus, nomDofus, levelDofus, descriptionDofus, EffetDofus, descripEffet)";
        db.execSQL(reqClasse);
        db.execSQL(reqMetier);
        db.execSQL(reqDofus);
    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
