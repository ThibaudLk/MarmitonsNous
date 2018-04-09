package fr.eni.ecole.marmitonsnous.beans;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class Etape {

    private int idEtape;
    private String nom;
    private int idRecette;

    public Etape() {
    }

    public Etape(int idEtape, String nom, int idRecette) {
        this.idEtape = idEtape;
        this.nom = nom;
        this.idRecette = idRecette;
    }

    public Etape(String nom, int idRecette) {
        this.nom = nom;
        this.idRecette = idRecette;
    }

    public Etape(String nom) {
        this.nom = nom;
    }

    public int getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }
}