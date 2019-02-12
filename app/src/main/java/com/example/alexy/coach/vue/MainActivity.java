package com.example.alexy.coach.vue;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexy.coach.R;
import com.example.alexy.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    // propriétés
    private TextView txtPoids;
    private TextView txtTaille;
    private TextView txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgPoids;

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
        controle = Controle.getInstance();
    }

    /**
     * initialisation des liens avec les objets graphiques
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids) ;
        txtTaille = (EditText) findViewById(R.id.txtTaille) ;
        txtAge = (EditText) findViewById(R.id.txtAge) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        lblIMG = (TextView) findViewById(R.id.lblIMG) ;
        imgPoids = (ImageView) findViewById(R.id.imgPoids) ;
        ecouteCalcul();
    }

    /**
     * Methode qui permet d'écouter le clic sur le bouton calcul
     * @return void
     */
    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalculer)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                //Log.d("message", "click ok sur le bouton Calcul ***********************");

                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;
                // Récupération des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch(Exception e) {
                    e.printStackTrace();
                }

                if(rdHomme.isChecked()) {
                    sexe = 1;
                }
                // Contrôle des données saisies
                if(poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affichage de l'IMG, du message et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        // Création du profil et récupération des informations
       this.controle.creerProfil(poids, taille, age, sexe);
       float img = this.controle.getImg();
       String message = this.controle.getMessage();

       // Affichage
        if(message == "normal") {
            imgPoids.setImageResource(R.drawable.ic_normal);
            lblIMG.setTextColor(Color.GREEN);
        } else if(message == "trop faible") {
            imgPoids.setImageResource(R.drawable.ic_thin);
            lblIMG.setTextColor(Color.RED);
        } else {
            imgPoids.setImageResource(R.drawable.ic_grass);
            lblIMG.setTextColor(Color.RED);
        }

        lblIMG.setText(String.format("%.01f", img) +" IMG "+ message);
    }
}
