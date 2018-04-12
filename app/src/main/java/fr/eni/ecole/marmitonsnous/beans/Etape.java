package fr.eni.ecole.marmitonsnous.beans;

import java.io.Serializable;

/**
 * Created by mmalenfant2016 on 09/04/2018.
 */

public class Etape implements Serializable {

    private int idEtape;
    private String nom;
    private int numero;
    private int idRecette;

    public Etape() {
    }

    public Etape(int idEtape, String nom, int numero, int idRecette) {
        this.idEtape = idEtape;
        this.nom = nom;
        this.numero = numero;
        this.idRecette = idRecette;
    }

    public Etape(String nom, int numero) {
        this.nom = nom;
        this.numero = numero;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
