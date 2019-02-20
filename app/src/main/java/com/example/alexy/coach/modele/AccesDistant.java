package com.example.alexy.coach.modele;

import android.util.Log;

import com.example.alexy.coach.controleur.Controle;
import com.example.alexy.coach.outils.AccesHTTP;
import com.example.alexy.coach.outils.AsyncResponse;
import com.example.alexy.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR = "http://192.168.1.6/coach/serveurcoach.php";
    private static Controle controle;

    public AccesDistant() {
        AccesDistant.controle = controle.getInstance(null);
    }

    @Override
    public void processFinish(String output) {
        Log.d("serveur", "******************" + output);

        String[] message = output.split("%");

        if(message.length >= 1) {
            switch (message[0]) {
                case "enreg":
                    break;

                case "dernier":
                    try {
                        JSONObject info = new JSONObject(message[1]);

                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Date datemesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd HH:mm:ss");

                        Profil profil = new Profil(datemesure, poids, taille, age, sexe);

                        controle.setProfil(profil);
                    } catch (JSONException e) {
                        Log.d("erreur", "conversion JSON impossible " + e.toString());
                    }
                    break;

                case "Erreur !":
                    break;
            }
        }
    }

    /**
     * Méthode permettant d'envoyer une requête HTTP à notre serveur de traitements
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;

            // On ajoute les paramètres à notre requête HTTP
            accesDonnees.addParam("operation", operation);
            accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

            accesDonnees.execute(SERVERADDR);
    }
}
