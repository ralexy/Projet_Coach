package com.example.alexy.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alexy.coach.R;

public class HistoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        ecouteRetourMenu();
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
}
