package fr.eni.ecole.marmitonsnous.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.marmitonsnous.beans.Ingredient;
import fr.eni.ecole.marmitonsnous.beans.Recette;
import fr.eni.ecole.marmitonsnous.provider.GestionBddHelper;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class RecetteDAO {

    //Représente la connexion.
    private SQLiteDatabase db;
    private GestionBddHelper helper;

    // nom des colonnes SQL
    final static String TABLE_RECETTE = "recette";
    final static String COL_ID_RECETTE = "idRecette";
    private final static String COL_TITRE = "titre";
    private final static String COL_NOTE = "note";
    private final static String COL_TEMPS = "temps";
    private final static String COL_NBREPERSONNE = "nbrepersonne";
    private final static String COL_DIFFICULTE = "difficulte";
    private final static String COL_ETAPE = "idEtape";
    private final static String COL_INGREDIENT = "idIngredient";
    private final static String COL_PHOTO = "photo";

    public static final String SQL_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS "
                    + TABLE_RECETTE + " ("
                    + COL_ID_RECETTE
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + COL_TITRE
                    +" VARCHAR(100), "
                    + COL_NOTE
                    + " FLOAT, "
                    + COL_TEMPS
                    + " INT(2), " +
                    COL_NBREPERSONNE +
                    " INT(2), " +
                    COL_DIFFICULTE +
                    " FLOAT, " +
                    COL_PHOTO +
                    " INT(20))";

    public static final String SQL_DROP_TABLE =
            " DROP TABLE IF EXISTS "
                    + TABLE_RECETTE;

    public RecetteDAO(Context context)
    {
        helper = new GestionBddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Recette recette)
    {
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, recette.getTitre());
        values.put(COL_NOTE, recette.getNote());
        values.put(COL_TEMPS,recette.getTemps());
        values.put(COL_NBREPERSONNE, recette.getNbrePersonne());
        values.put(COL_DIFFICULTE, recette.getDifficulte());
        /*for (Ingredient ingr : recette.getIngredients()) {
            values.put(COL_INGREDIENT, IngredientDAO.insert(ingr));
        }*/
/*        for(Etape etape : recette.getEtapes()) {
            values.put(COL_ETAPE,EtapeDAO.insert(etape));
        }*/
        /*values.put(COL_PHOTO, recette.getPhoto());*/
        return db.insert(TABLE_RECETTE,null,values);
    }

    public long update(Recette recette) {
        ContentValues values = new ContentValues();
        values.put(COL_TITRE, recette.getTitre());
        values.put(COL_NOTE, recette.getNote());
        values.put(COL_TEMPS,recette.getTemps());
        values.put(COL_NBREPERSONNE, recette.getNbrePersonne());
        values.put(COL_DIFFICULTE, recette.getDifficulte());
        for (Ingredient ingr : recette.getIngredients()) {
            values.put(COL_INGREDIENT, IngredientDAO.insert(ingr));
        }
        values.put(COL_PHOTO, recette.getPhoto());

        String whereClause = COL_ID_RECETTE + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                Integer.toString(recette.getIdRecette())
        };

        return db.update(TABLE_RECETTE, values, whereClause, whereArgs);
    }

    public Recette selectById(String id){
        Recette truc =null;
        //Tableau des colonnes
        String[] tableColumns = new String[] {
                COL_ID_RECETTE
        };

        //Where
        String whereClause = COL_ID_RECETTE + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                id
        };

        Cursor c = db.query(TABLE_RECETTE,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        if(c.moveToFirst()) {
            truc = this.getRecette(c);
        }
        c.close();


        return truc;
    }

    public List<Recette> selectAll(){
        List<Recette> listRecette = new ArrayList<Recette>();

        String[] tableColumns = new String[] {
                COL_ID_RECETTE,
                COL_TITRE,
                COL_NOTE,
                COL_TEMPS,
                COL_NBREPERSONNE,
                COL_DIFFICULTE,
                COL_ETAPE,
                COL_INGREDIENT,
                COL_PHOTO
        };

        Cursor c = db.query(TABLE_RECETTE,
                tableColumns,
                null,
                null,
                null,
                null,
                null);


        while (c.moveToNext()) {
            Recette recette = this.getRecette(c);
            listRecette.add(recette);
        };

        c.close();

        return listRecette;
    }

    private Recette getRecette(Cursor c){

        Recette recette = new Recette(c.getInt(c.getColumnIndex(COL_ID_RECETTE.trim())),
                c.getString(c.getColumnIndex(COL_TITRE)),
                c.getFloat(c.getColumnIndex(COL_NOTE)),
                c.getInt(c.getColumnIndex(COL_TEMPS)),
                c.getInt(c.getColumnIndex(COL_NBREPERSONNE)),
                c.getFloat(c.getColumnIndex(COL_DIFFICULTE)),
                c.getInt(c.getColumnIndex(COL_PHOTO))
        );

        return recette;
    }

    public void close(){
        if(db != null) {
            //on ferme l'accès à la BDD
            db.close();
        }
    }
}
