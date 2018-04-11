package fr.eni.ecole.marmitonsnous.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import fr.eni.ecole.marmitonsnous.DAO.EtapeDAO;
import fr.eni.ecole.marmitonsnous.DAO.RecetteDAO;
import fr.eni.ecole.marmitonsnous.beans.Recette;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class GestionBddHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "MarmitonsNous.db";
    private final static int DATABASE_VERSION = 1;

    public GestionBddHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.v("truc", RecetteDAO.SQL_CREATE_TABLE);
        db.execSQL(RecetteDAO.SQL_CREATE_TABLE);
        db.execSQL(EtapeDAO.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RecetteDAO.SQL_DROP_TABLE);
        db.execSQL(EtapeDAO.SQL_DROP_TABLE);
        onCreate(db);
    }
}

