package fr.eni.ecole.marmitonsnous.beans;

import java.util.List;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class Recette {

    private int idRecette;
    private String titre;
    private Float note;
    private int temps;
    private int nbrePersonne;
    private Float difficulte;
    private List<Etape> etapes;
    private List<Ingredient> ingredients;
    private int photo;

    public Recette() {
    }

    public Recette(int idRecette, String titre, Float note, int temps, int nbrePersonne, Float difficulte, int photo) {
        this.idRecette = idRecette;
        this.titre = titre;
        this.note = note;
        this.temps = temps;
        this.nbrePersonne = nbrePersonne;
        this.difficulte = difficulte;
        this.photo = photo;
    }

    public Recette(String titre, Float note, int temps, int nbrePersonne, Float difficulte, List<Etape> etapes, List<Ingredient> ingredients, int photo) {
        this.titre = titre;
        this.note = note;
        this.temps = temps;
        this.nbrePersonne = nbrePersonne;
        this.difficulte = difficulte;
        this.etapes = etapes;
        this.ingredients = ingredients;
        this.photo = photo;
    }

    public Recette(int idRecette, String titre, Float note, int temps, int nbrePersonne, Float difficulte, List<Etape> etapes, List<Ingredient> ingredients, int photo) {
        this.idRecette = idRecette;
        this.titre = titre;
        this.note = note;
        this.temps = temps;
        this.nbrePersonne = nbrePersonne;
        this.difficulte = difficulte;
        this.etapes = etapes;
        this.ingredients = ingredients;
        this.photo = photo;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public int getTemps() {
        return temps;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public int getNbrePersonne() {
        return nbrePersonne;
    }

    public void setNbrePersonne(int nbrePersonne) {
        this.nbrePersonne = nbrePersonne;
    }

    public Float getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(Float difficulte) {
        this.difficulte = difficulte;
    }

    public List<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<Etape> etapes) {
        this.etapes = etapes;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
