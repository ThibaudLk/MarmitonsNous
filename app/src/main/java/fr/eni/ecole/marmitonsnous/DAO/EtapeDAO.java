package fr.eni.ecole.marmitonsnous.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.marmitonsnous.beans.Etape;
import fr.eni.ecole.marmitonsnous.provider.GestionBddHelper;

/**
 * Created by mmalenfant2016 on 10/04/2018.
 */

public class EtapeDAO {

    //Représente la connexion.
    private static SQLiteDatabase db;
    private GestionBddHelper helper;

    public static final String TABLE_ETAPE = "etape";
    public static final String COL_ID_ETAPE = "idEtape";
    public static final String COL_NOM = "nom";
    public static final String COL_NUMERO = "numero";
    public static final String COL_ID_RECETTE = "idRecette";

    public static final String SQL_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS "
                    + TABLE_ETAPE + " ("
                    + COL_ID_ETAPE
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + COL_NOM
                    + " VARCHAR(80), "
                    + COL_NUMERO
                    + " INT(2), "
                    + COL_ID_RECETTE
                    +" INT, "
                    + "FOREIGN KEY ("+ COL_ID_RECETTE +") REFERENCES " +
                    RecetteDAO.TABLE_RECETTE + "(" + RecetteDAO.COL_ID_RECETTE+ "))";

    public static final String SQL_DROP_TABLE =
            " DROP TABLE IF EXISTS "
                    + TABLE_ETAPE;

    public EtapeDAO(Context context)
    {
        helper = new GestionBddHelper(context);
        db = helper.getWritableDatabase();
    }

    public long insert(Etape etape)
    {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, etape.getNom());
        values.put(COL_NUMERO, etape.getNumero());
        values.put(COL_ID_RECETTE, etape.getIdRecette());
        return db.insert(TABLE_ETAPE,null,values);
    }

    public long update(Etape etape) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, etape.getNom());
        values.put(COL_NUMERO, etape.getNumero());
        values.put(COL_ID_RECETTE, etape.getIdRecette());

        String whereClause = COL_ID_ETAPE + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                Integer.toString(etape.getIdRecette())
        };

        return db.update(TABLE_ETAPE, values, whereClause, whereArgs);
    }

    public Etape selectById(String id){
        Etape truc =null;
        //Tableau des colonnes
        String[] tableColumns = new String[] {
                COL_ID_RECETTE
        };

        //Where
        String whereClause = COL_ID_ETAPE + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                id
        };

        Cursor c = db.query(TABLE_ETAPE,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        if(c.moveToFirst()) {
            truc = this.getEtape(c);
        }
        c.close();


        return truc;
    }

    public List<Etape> selectAll(){
        List<Etape> listEtape = new ArrayList<Etape>();

        String[] tableColumns = new String[] {
                COL_ID_ETAPE,
                COL_NOM,
                COL_NUMERO,
                COL_ID_RECETTE,
        };

        Cursor c = db.query(TABLE_ETAPE,
                tableColumns,
                null,
                null,
                null,
                null,
                null);


        while (c.moveToNext()) {
            Etape etape = this.getEtape(c);
            listEtape.add(etape);
        };

        c.close();

        return listEtape;
    }

    public List<Etape> selectAllByIdRecette(int idRecette){
        List<Etape> listEtape = new ArrayList<Etape>();

        String[] tableColumns = new String[] {
                COL_ID_ETAPE,
                COL_NOM,
                COL_NUMERO,
                COL_ID_RECETTE,
        };

        //Where
        String whereClause = COL_ID_RECETTE + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                String.valueOf(idRecette)
        };

        Cursor c = db.query(TABLE_ETAPE,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                COL_NUMERO);


        while (c.moveToNext()) {
            Etape etape = this.getEtape(c);
            listEtape.add(etape);
        };

        c.close();

        return listEtape;
    }

    private Etape getEtape(Cursor c){

        Etape etape = new Etape(c.getInt(c.getColumnIndex(COL_ID_ETAPE.trim())),
                c.getString(c.getColumnIndex(COL_NOM)),
                c.getInt(c.getColumnIndex(COL_NUMERO)),
                c.getInt(c.getColumnIndex(COL_ID_RECETTE))
        );

        return etape;
    }

    public void close(){
        if(db != null) {
            //on ferme l'accès à la BDD
            db.close();
        }
    }
}
