package com.example.alexy.coach.modele;

import android.util.Log;

import com.example.alexy.coach.outils.AccesHTTP;
import com.example.alexy.coach.outils.AsyncResponse;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR = "http://192.168.1.6/coach/serveurcoach.php";

    public AccesDistant() {
        super();
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
                    break;

                case "Erreur !":
                    break;
            }

            Log.d("RETOUR SERV", message[0]);
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
