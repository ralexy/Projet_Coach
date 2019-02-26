package com.example.alexy.coach.modele;

import android.util.Log;

import com.example.alexy.coach.controleur.Controle;
import com.example.alexy.coach.outils.AccesHTTP;
import com.example.alexy.coach.outils.AsyncResponse;
import com.example.alexy.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
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

                case "tous":
                    try {
                        JSONArray info = new JSONArray(message[1]);
                        ArrayList <Profil> lesProfils = controle.getLesProfils();

                        for(int i = 0; i < info.length(); i++) {
                            Integer poids = info.getJSONObject(i).getInt("poids");
                            Integer taille = info.getJSONObject(i).getInt("taille");
                            Integer age = info.getJSONObject(i).getInt("age");
                            Integer sexe = info.getJSONObject(i).getInt("sexe");
                            Date datemesure = MesOutils.convertStringToDate(info.getJSONObject(i).getString("datemesure"), "yyyy-MM-dd HH:mm:ss");

                            lesProfils.add(new Profil(datemesure, poids, taille, age, sexe));
                        }

                        controle.setLesProfils(lesProfils);

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
