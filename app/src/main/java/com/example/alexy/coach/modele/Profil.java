package com.example.alexy.coach.modele;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alexy ROUSSEAU on 10/01/2019
 */

public class Profil implements Serializable {
    private static final Integer minFemme = 15; // maigre si en-dessous
    private static final Integer maxFemme = 30; // gros si au-dessus
    private static final Integer minHomme = 10; // maigre si en-dessous
    private static final Integer maxHomme = 25; // gros si au-dessus

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;
    private Date dateMesure;

    /**
     * Constructeur
     * @param poids le poids en kg
     * @param taille la taille encm
     * @param age
     * @param sexe 0 pour femme 1 pour homme
     */
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;

        this.calculIMG();
        this.resultIMG();
    }

    /**
     * Retourne le poids du profil
     * @return
     */
    public Integer getPoids() { return poids; }

    /**
     * Retourne la taille du profil
     * @return
     */
    public Integer getTaille() { return taille; }

    /**
     * Retourne l'âge du profil
     * @return
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Retourne le sexe du profil
     * @return
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * Retourne l'image calculée, adaptée au profil
     * @return
     */
    public float getImg() { return img; }

    /**
     * Retourne le message personnalisé déterminé par les variables du profil
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retourne la date de création de l'objet
     * @return Date la date
     */
    public Date getDateMesure() { return dateMesure; }

    /**
     * Calcule l'IMG du profil
     */
    public void calculIMG() {
        float tailleM = ((float)taille / 100);
        this.img = (float) (1.2 * (poids / (tailleM*tailleM)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * Calcule le message personnalisé au profil selon son IMG
     */
    private void resultIMG() {
        // Si c'est une femme
        if(this.sexe == 0) {
            if(this.img < 15) {
                this.message = "trop faible";
            }
            else if(this.img > 30) {
                this.message = "trop élevé";
            }
            else{
                this.message = "normal";
            }
        }
        else{
            if(this.img < 10) {
                this.message = "trop maigre";
            }
            else if(this.img > 25) {
                this.message = "trop élevé";
            }
            else{
                this.message = "normal";
            }
        }
    }
}
