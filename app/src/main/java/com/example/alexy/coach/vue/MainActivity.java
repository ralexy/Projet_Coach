package com.example.alexy.coach.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.alexy.coach.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }

    public void creerMenu() {
        ecouteMenu((ImageButton) findViewById(R.id.btnMonImg), CalculActivity.class);
        //ecouteMenu((ImageButton) findViewById(R.id.btnMonHistorique), CalculActivity.class);
    }

    /**
     * Ouvrir l'activity correspondante
     * @param button
     * @param classe
     */
    public void ecouteMenu(ImageButton button, final Class classe) {
        button.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Log.d("success", "success");
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }
}
