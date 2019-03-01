package com.example.alexy.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.alexy.coach.R;
import com.example.alexy.coach.controleur.Controle;
import com.example.alexy.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    // Propriétés
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle = Controle.getInstance(null);
        ecouteRetourAccueil();
        creerListe();
    }

    /**
     * Evénement clic sur le bouton de retour vers l'accuel
     */
    private void ecouteRetourAccueil(){
        ((Button)findViewById(R.id.btnAccueil2)).setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Création de la liste adapter
     */
    private void creerListe(){
        ArrayList<Profil> liste = controle.getLesProfils();
        Collections.sort(liste, Collections.<Profil>reverseOrder());
        if(liste!=null){
            ListView listView = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, liste);
            listView.setAdapter(adapter);
        }
    }

    /**
     * Demande à l'activity Calcul d'afficher un profil
     * @param profil
     */
    public void afficheProfil(Profil profil){
        controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
