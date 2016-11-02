package com.example.iem.apptub.classes;

import java.util.List;

/**
 * Created by iem on 21/10/2016.
 */

public class Ligne {
    private String nom;
    private List<Arret> arrets;

    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public List<Arret> getArrets(){
        return arrets;
    }

    public void setArrets(List<Arret> arrets){
        if(this.arrets != null)
            this.arrets.clear();
        this.arrets.addAll(arrets);
    }

}
