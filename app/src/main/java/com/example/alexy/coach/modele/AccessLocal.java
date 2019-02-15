package com.example.alexy.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.alexy.coach.outils.MesOutils;
import com.example.alexy.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccessLocal {
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accessBD;
    private SQLiteDatabase bd;
    private String req;

    public AccessLocal(Context contexte) {
        accessBD = new MySQLiteOpenHelper(contexte, nomBase, versionBase);
    }

    /**
     * Méthode permettant d'ajouter un profil dans la DB
     * @param profil
     */
    public void ajout(Profil profil) {
        bd = accessBD.getWritableDatabase();

        req = "INSERT INTO profil (datemesure, poids, taille, age, sexe) ";
        req += "VALUES(\"" + profil.getDateMesure() + "\", \"" + profil.getPoids() + "\", \"" + profil.getTaille() + "\", \"" + profil.getAge() + "\", \"" + profil.getSexe() + "\")";

        bd.execSQL(req);
    }

    /**
     * Méthode permettant de récupérer le dernier profil enregistré
     */
    public Profil recupDernier() {
        Profil profil = null;

        bd = accessBD.getReadableDatabase();

        req = "SELECT datemesure, poids, taille, age, sexe FROM profil";

        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();

        // Récupération des informations du profil si on a bien une ligne dans notre curseur
        if(!curseur.isAfterLast()) {
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));

            profil = new Profil(dateMesure, curseur.getInt(1), curseur.getInt(2), curseur.getInt(3), curseur.getInt(4));

            Log.d("DATE ****", "" + dateMesure);

        }
        // Fermeture du curseur
        curseur.close();

        return profil;
    }
}