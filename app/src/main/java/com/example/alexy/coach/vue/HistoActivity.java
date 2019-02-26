package com.example.alexy.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.alexy.coach.R;
import com.example.alexy.coach.controleur.Controle;
import com.example.alexy.coach.modele.Profil;

import java.util.ArrayList;

public class HistoActivity extends AppCompatActivity {

    Controle controller;

    public HistoActivity(Controle controller) {
        this.controller = controller;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        ecouteRetourMenu();
        creerListe();
    }

    /**
     * Ouvrir l'activity correspondante
     */
    public void ecouteRetourMenu() {
        ((Button) findViewById(R.id.btnAccueil2)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void creerListe()
    {
        ArrayList <Profil> liste = controller.getLesProfils();

        if(liste != null) {
            ListView listView = findViewById(R.id.lstHisto);
            HistoListAdapter adapter;
        }
    }
}
