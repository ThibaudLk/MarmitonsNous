package fr.eni.ecole.marmitonsnous.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ecole.marmitonsnous.beans.Etape;
import fr.eni.ecole.marmitonsnous.beans.Ingredient;
import fr.eni.ecole.marmitonsnous.provider.GestionBddHelper;

/**
 * Created by mmalenfant2016 on 10/04/2018.
 */

class IngredientDAO {

    //Représente la connexion.
    private static SQLiteDatabase db;
    private GestionBddHelper helper;

    public static final String TABLE_INGREDIENT = "ingredient";
    public static final String COL_ID_INGREDIENT = "idIngredient";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_NUMERO = "numero";
    public static final String COL_ID_RECETTE = "idRecette";

    public static final String SQL_CREATE_TABLE =
            " CREATE TABLE IF NOT EXISTS "
                    + TABLE_INGREDIENT + " ("
                    + COL_ID_INGREDIENT
                    + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + COL_DESCRIPTION
                    +" VARCHAR(500), "
                    + COL_NUMERO
                    + " INT, "
                    + "FOREIGN KEY ("+ COL_ID_RECETTE +") REFERENCES " +
                    RecetteDAO.TABLE_RECETTE + "(" + RecetteDAO.COL_ID_RECETTE+ "))";

    public static final String SQL_DROP_TABLE =
            " DROP TABLE IF EXISTS "
                    + TABLE_INGREDIENT;

    public IngredientDAO(Context context)
    {
        helper = new GestionBddHelper(context);
        db = helper.getWritableDatabase();
    }

    public static long insert(Ingredient ingredient)
    {
        ContentValues values = new ContentValues();
        values.put(COL_DESCRIPTION, ingredient.getDescription());
        values.put(COL_NUMERO, ingredient.getNumero());
        values.put(COL_ID_RECETTE, ingredient.getIdRecette());
        return db.insert(TABLE_INGREDIENT,null,values);
    }

    public long update(Ingredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(COL_DESCRIPTION, ingredient.getDescription());
        values.put(COL_NUMERO, ingredient.getNumero());
        values.put(COL_ID_RECETTE, ingredient.getIdRecette());

        String whereClause = COL_ID_INGREDIENT + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                Integer.toString(ingredient.getIdRecette())
        };

        return db.update(TABLE_INGREDIENT, values, whereClause, whereArgs);
    }

    public Ingredient selectById(String id){
        Ingredient truc =null;
        //Tableau des colonnes
        String[] tableColumns = new String[] {
                COL_ID_INGREDIENT
        };

        //Where
        String whereClause = COL_ID_INGREDIENT + " = ? ";

        //Where args
        String[] whereArgs = new String[] {
                id
        };

        Cursor c = db.query(TABLE_INGREDIENT,
                tableColumns,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        if(c.moveToFirst()) {
            truc = this.getIngredient(c);
        }
        c.close();


        return truc;
    }

    public List<Ingredient> selectAll(){
        List<Ingredient> listIngredient = new ArrayList<Ingredient>();

        String[] tableColumns = new String[] {
                COL_ID_INGREDIENT,
                COL_DESCRIPTION,
                COL_NUMERO,
                COL_ID_RECETTE,
        };

        Cursor c = db.query(TABLE_INGREDIENT,
                tableColumns,
                null,
                null,
                null,
                null,
                null);


        while (c.moveToNext()) {
            Ingredient ingredient = this.getIngredient(c);
            listIngredient.add(ingredient);
        };

        c.close();

        return listIngredient;
    }

    private Ingredient getIngredient(Cursor c){

        Ingredient ingredient = new Ingredient(c.getInt(c.getColumnIndex(COL_ID_INGREDIENT.trim())),
                c.getString(c.getColumnIndex(COL_DESCRIPTION)),
                c.getInt(c.getColumnIndex(COL_NUMERO)),
                c.getInt(c.getColumnIndex(COL_ID_RECETTE))
        );

        return ingredient;
    }

    public void close(){
        if(db != null) {
            //on ferme l'accès à la BDD
            db.close();
        }
    }
}
