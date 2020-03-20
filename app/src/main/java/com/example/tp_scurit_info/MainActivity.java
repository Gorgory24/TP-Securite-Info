package com.example.tp_scurit_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView MesACoder;
    TextView Resultat;
    TextView Clef;
    Button AtBashBut;
    Button CésarBut;
    Button VigenèreBut;
    Button PolybeBut;
    Button PlayfairBut;
    Button HillBut;
    Button ParalléloBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MesACoder = (TextView)findViewById(R.id.MesACoder);
        Resultat = (TextView)findViewById(R.id.Coder);
        Clef = (TextView)findViewById(R.id.clef);
        AtBashBut = (Button)findViewById(R.id.AtbashButton);
        CésarBut = (Button)findViewById(R.id.CésarButton);
        VigenèreBut = (Button)findViewById(R.id.VigenèreButton);
        PolybeBut = (Button)findViewById(R.id.PolybeButton);
        PlayfairBut = (Button)findViewById(R.id.PlayfairButton);
        HillBut = (Button)findViewById(R.id.HillButton);
        ParalléloBut = (Button)findViewById(R.id.ParalléloButton);
    }


    public void ParalléloCode(View v) {  //Fonction de cryptage créer par nous même qui utilisera une clef à 4 chiffre pour crypter le code

        String coder = "";  //Message coder après passage dans le crypteur
        int Droite = 0;
        int Gauche = 0;
        String clef = Clef.getText().toString();
        while(clef.length() <4) {   //Il faut une clef de minimum taille 4
            clef += 2;
        }

        for (int i = 0; i < Clef.length(); i++) { //Parcours la clef composé de chiffre afin de faire des calculs sur la clef pour obtenir le chiffrement de César approprié pour les lettres au position pair et impair
            if (i % 2 == 0) {
                Droite -= (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position pair
                Gauche += (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position impair
            } else {
                Droite += (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position pair
                Gauche -= (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position impair
            }
        }

        String message = MesACoder.getText().toString();
        System.out.println(" -------------------- HERE -------------------");
        char x;                     //lettre actuel lors de la lecture du message
        int a = clef.charAt(0);     //1er lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int b = clef.charAt(1);     //2eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int c = clef.charAt(2);     //3eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int d = clef.charAt(3);     //4eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x

        for (int i = 0; i < message.length(); i++) {
            x = message.charAt(i);
            if (i % 2 == 0) {
                //coder = coder + (char) ((x + Droite)%26) + (char) ((x * a)%26) + (char) ((x + b)%26) + (char) ((x * c)%26) + (char) ((x + d)%26);
                coder = coder + (char) ((x + Droite)%26+65) + (char) ((x * a)%26+65) + (char) ((x + b)%26+65) + (char) ((x * c)%26+65) + (char) ((x + d)%26+65);
            } else {
                //coder = coder + (char) ((x + Gauche)%26) + (char) ((x - a)%26) + (char) ((x * b)%26) + (char) ((x - c)%26) + (char) ((x * d)%26);
                coder = coder + (char) ((x + Gauche)%26+65) + (char) ((x - a)%26+65) + (char) ((x * b)%26+65) + (char) ((x - c)%26+65) + (char) ((x * d)%26+65);
            }
        }
        Resultat.setText(coder);
        //System.out.println("Le message est : " + message);
        System.out.println(coder);
    }

    public void Test(View v) {
        System.out.println("HEEREREREREERRERERE");
    }
}
