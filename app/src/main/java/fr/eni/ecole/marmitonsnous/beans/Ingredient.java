package fr.eni.ecole.marmitonsnous.beans;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class Ingredient {

    private int idIngredient;
    private String description;
    private int numero;
    private int idRecette;

    public Ingredient() {
    }

    public Ingredient(int idIngredient, String description, int numero, int idRecette) {
        this.idIngredient = idIngredient;
        this.description = description;
        this.numero = numero;
        this.idRecette = idRecette;
    }

    public Ingredient(String description, int numero, int idRecette) {
        this.description = description;
        this.numero = numero;
        this.idRecette = idRecette;
    }

    public Ingredient(String description, int numero) {
        this.description = description;
        this.numero = numero;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }
}
