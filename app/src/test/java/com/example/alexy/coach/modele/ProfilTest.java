package com.example.alexy.coach.modele;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    // Création d'un profil : femme de 67kg, 1.65m, 35 ans
    private Profil profil = new Profil(67, 165, 35, 0);

    // Création de l'image correspondante
    private float img = (float)32.2;

    // Message correspondant
    private String message = "trop élevé";

    @Test
    public void getImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message, profil.getMessage());
    }
}