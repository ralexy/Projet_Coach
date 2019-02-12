package com.example.alexy.coach.controleur;

import android.content.Context;

import com.example.alexy.coach.modele.Profil;
import com.example.alexy.coach.outils.Serializer;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil = null;
    private static String nomFic = "saveprofil";

    /**
     * Contructeur privé pour ne pas l'instancier (Pattern Singleton)
     */
    private Controle() {
        super();
    }

    /**
     * Permet de retourner l'instance du contrôleur (Pattern Singleton), l'invoque au 1er appel
     * @return
     */
    public static final Controle getInstance(Context contexte) {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            Controle.recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int poids, int taille, int age, int sexe, Context contexte) {
        this.profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(this.nomFic, this.profil, contexte);

    }

    /**
     * Retourne la méthode getImg() l'instance de Profil
     * @return
     */
    public float getImg() {
        return this.profil.getImg();
    }

    /**
     * Retourne la méthode getMessage() de l'instance de Profil
     * @return
     */
    public String getMessage() {
        return this.profil.getMessage();
    }

    /**
     * Méthode permettant de retourner la taille d'une personne,
     * Retourne 0 si aucune information n'a été stockée
     */
    public int getTaille() {
        if(!this.profil.getTaille().equals(null)) {
           return this.profil.getTaille();
        }

        return 0;
    }

    /**
     * Méthode permettant de retourner le poids d'une personne,
     * Retourne 0 si aucune information n'a été stockée
     */
    public int getPoids() {
        if(!this.profil.getPoids().equals(null)) {
            return this.profil.getPoids();
        }

        return 0;
    }

    /**
     * Méthode permettant de retourner l'âge d'une personne,
     * Retourne 0 si aucune information n'a été stockée
     */
    public int getAge() {
        if(!this.profil.getAge().equals(null)) {
            return this.profil.getAge();
        }

        return 0;
    }

    /**
     * Méthode permettant de retourner le sexe d'une personne,
     * Retourne 0 si aucune information n'a été stockée
     */
    public int getSexe() {
        if(!this.profil.getSexe().equals(null)) {
            return this.profil.getSexe();
        }

        return 0;
    }

    /**
     * Méthode qui récupère les données sérializées précédemment saisies
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        Controle.profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }
}
