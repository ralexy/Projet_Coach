package com.example.alexy.coach.controleur;

import android.content.Context;

import com.example.alexy.coach.modele.AccesDistant;
import com.example.alexy.coach.modele.AccessLocal;
import com.example.alexy.coach.modele.Profil;
import com.example.alexy.coach.outils.Serializer;
import com.example.alexy.coach.vue.MainActivity;

import org.json.JSONArray;

import java.util.Date;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil = null;
    private static String nomFic = "saveprofil";
    private static AccessLocal accesLocal;
    private static AccesDistant accesDistant = new AccesDistant();
    private static Context contexte;

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
        if(contexte != null) {
            Controle.contexte = contexte;
        }
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            Controle.accesLocal = new AccessLocal(contexte);
            Controle.contexte = contexte;
            //profil = accesLocal.recupDernier();
            //Controle.recupSerialize(contexte);

            accesDistant = new AccesDistant();
            accesDistant.envoi("dernier", new JSONArray());

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
        profil = new Profil(new Date(), poids, taille, age, sexe);

        // Sauvegarde de notre profil par sérialisation
        //Serializer.serialize(nomFic, profil, contexte);

        // Ajout de notre profil dans la DB SQLite
        //accesLocal.ajout(profil);
        accesDistant.envoi("enreg", profil.convertToJSONArray());
    }

    /**
     * Retourne la méthode getImg() l'instance de Profil
     * @return
     */
    public float getImg() {
        return profil.getImg();
    }

    /**
     * Retourne la méthode getMessage() de l'instance de Profil
     * @return String le message
     */
    public String getMessage() {
        if(profil == null) {
            return null;
        } else {
            return profil.getMessage();
        }
    }

    /**
     * Méthode permettant de retourner la taille d'une personne,
     */
    public Integer getTaille() {
        if(profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }

    /**
     * Méthode permettant de retourner le poids d'une personne,
     */
    public Integer getPoids() {
        if(profil == null) {
            return null;
        } else {
            return profil.getPoids();
        }
    }

    /**
     * Méthode permettant de retourner l'âge d'une personne,
     */
    public Integer getAge() {
        if(profil == null) {
            return null;
        } else{
            return profil.getAge();
        }
    }

    /**
     * Méthode permettant de retourner le sexe d'une personne,
     */
    public Integer getSexe() {
        if(profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }

    /**
     * Méthode qui récupère les données sérializées précédemment saisies
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        Controle.profil = (Profil) Serializer.deSerialize(nomFic, contexte);
    }

    /**
     * Setter profil
     * @param profil le Profil à définir
     */
    public void setProfil(Profil profil) {
        Controle.profil = profil;
        ((MainActivity)contexte).recupProfil();
    }
}
