package com.example.alexy.coach.controleur;

import com.example.alexy.coach.modele.Profil;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil = null;

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
    public static final Controle getInstance() {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
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
    public void creerProfil(int poids, int taille, int age, int sexe) {
        this.profil = new Profil(poids, taille, age, sexe);
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
}
