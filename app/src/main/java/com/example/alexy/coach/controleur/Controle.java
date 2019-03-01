package com.example.alexy.coach.controleur;

import android.content.Context;

import com.example.alexy.coach.modele.AccesDistant;
import com.example.alexy.coach.modele.Profil;
import com.example.alexy.coach.outils.Serializer;
import com.example.alexy.coach.vue.CalculActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by emds on 23/02/2018.
 */

public final class Controle {

    // propriétés
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    //    private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

    /**
     * Constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * Crée ou récupère l'instance déjà créée
     * @return l'instance de Conrtole
     */
    public static final Controle getInstance(Context context){
        if(context!=null) {
            Controle.context = context;
        }
        if(Controle.instance==null){
            Controle.instance = new Controle();
//            Controle.accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
//            Controle.profil = accesLocal.recupDernier();
//            accesDistant.envoi("dernier", new JSONArray());
            accesDistant.envoi("tous", new JSONArray());
//            recupSerialize(context);
        }
        return Controle.instance;
    }

    /**
     * Mémorisation du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        Profil unProfil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(unProfil);
//        accesLocal.ajout(profil);
        accesDistant.envoi("enreg", unProfil.convertToJSONArray());
//        Serializer.serialize(nomFic, profil, context);
    }

    /**
     * demande de suppression d'un profil
     * @param profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("del", profil.convertToJSONArray());
        lesProfils.remove(profil);
    }

    /**
     * getter
     * @return lesProfils
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * setter
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }

    /**
     * Récupère et retourne l'IMG du dernier profil si le profil existe
     * @return img
     */
    public Float getImg(){
        if(lesProfils.size()==0){
            return null;
        }else{
            return lesProfils.get(lesProfils.size()-1).getImg();
        }
    }

    /**
     * Récupère et retourne le message du dernier profil si le profil existe
     * @return message
     */
    public String getMessage(){
        if(lesProfils.size()==0){
            return null;
        }else{
            return lesProfils.get(lesProfils.size()-1).getMessage();
        }
    }

    /**
     * Récupère et retourne le poids du profil si le profil existe
     * @return poids
     */
    public Integer getPoids(){
        if(profil==null){
            return null;
        }else{
            return profil.getPoids();
        }
    }

    /**
     * Récupère et retourne la taille du profil si le profil existe
     * @return taille
     */
    public Integer getTaille(){
        if(profil==null){
            return null;
        }else{
            return profil.getTaille();
        }
    }

    /**
     * Récupère et retourne l'âge du profil si le profil existe
     * @return age
     */
    public Integer getAge(){
        if(profil==null){
            return null;
        }else{
            return profil.getAge();
        }
    }

    /**
     * Récupère et retourne le sexe du profil si le profil existe
     * @return sexe
     */
    public Integer getSexe(){
        if(profil==null){
            return null;
        }else{
            return profil.getSexe();
        }
    }

    /**
     * Valorise le profil
     * @param profil
     */
    public void setProfil(Profil profil){
        Controle.profil = profil;
//        ((CalculActivity)context).recupProfil();
    }

    /**
     * Valorise le profil avec l'objet précédemment sérialisé
     * @param context
     */
    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }
}
