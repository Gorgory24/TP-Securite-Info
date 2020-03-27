package com.example.tp_scurit_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.*;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView MesACoder;
    TextView Resultat;
    TextView Clef;
    Cesar cesar = new Cesar();
    Atbash atbash = new Atbash();
    Hill hill = new Hill();
    Vigenere vigenere = new Vigenere();
    /*Button AtBashBut;
    Button CésarBut;
    Button VigenèreBut;
    Button PolybeBut;
    Button PlayfairBut;
    Button HillBut;
    Button ParalléloBut;*/

    ListView Listcrypto;
    String CodeList[] = {"AtBash", "César", "Vigenère", "Hill", "Parallélogramme"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Listcrypto = (ListView)findViewById(R.id.ListCrypto);
        Clef = (TextView)findViewById(R.id.Clef);
        ArrayAdapter<String> CodeAdadapter = new ArrayAdapter<String>(this,R.layout.activity_list_view,R.id.textView, CodeList);
        Listcrypto.setAdapter(CodeAdadapter);
        Listcrypto.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("-----------------------------------");
                Object item = Listcrypto.getItemAtPosition(i);
                System.out.println(item);
                String it = item.toString();
                System.out.println(it);
                System.out.println("-----------------------------------");
                switch (it){
                    case "Parallélogramme":
                        ParalléloCode();
                        break;
                    case "AtBash":
                        String a = atbash.crypt(MesACoder.getText().toString());
                        System.out.println(MesACoder.toString());
                        Resultat.setText("MesCoder avec AtBash : " + a);
                        System.out.println(Resultat.getText().toString());
                        break;
                    case "César":
                        String c = cesar.crypt(MesACoder.getText().toString(),Integer.parseInt(Clef.getText().toString()));
                        Resultat.setText("MesCoder en césar avec une clef de " + Integer.parseInt(Clef.getText().toString()) + " est : " + c);
                        System.out.println(Resultat.getText().toString());
                        break;
                    case "Vigenère":
                        String v = vigenere.crypt(MesACoder.getText().toString(),Clef.getText().toString());
                        Resultat.setText("MesCoder avec Vigenère avec la clef " + Clef.getText().toString() + " est : " + v);
                        System.out.println(Resultat.getText().toString());
                        break;
                    case "Hill":
                        String clef = Clef.getText().toString();
                        String h = hill.crypt(MesACoder.getText().toString(),2, clef.charAt(0), clef.charAt(1), clef.charAt(2), clef.charAt(3));
                        Resultat.setText("MesCoder avec Hill avec la clef " + Clef.getText().toString() + " et m = 2 donne : " + h);
                        System.out.println(Resultat.getText().toString());
                        break;
                    default:
                        System.out.println("Waiting for code..");
                }
            }
        });

        MesACoder = (TextView)findViewById(R.id.MesACoder);
        Resultat = (TextView)findViewById(R.id.Coder);
    }


    public void ParalléloCode() {  //Fonction de cryptage créer par nous même qui utilisera une clef à 4 chiffre pour crypter le code

        String coder = "";  //Message coder après passage dans le crypteur
        int Droite = 0;
        int Gauche = 0;
        String clef = Clef.getText().toString();
        while(clef.length() <4) {   //Il faut une clef de minimum taille 4
            Random ran = new Random();
            clef += ran.nextInt(10);
            System.out.println(clef);
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
        Resultat.setText(coder + " clef : "+ clef);
        //System.out.println("Le message est : " + message);
        System.out.println(coder);
    }

    /*public void ReverseParalléloCode() {  //Fonction de décryptage créer par nous même qui utilisera une clef à 4 chiffre pour crypter le code
        String coder = "";  //Message decoder après passage dans le crypteur
        int Droite = 0;
        int Gauche = 0;
        String clef = Clef.getText().toString();

        for (int i = 0; i < Clef.length(); i++) { //Parcours la clef composé de chiffre afin de faire des calculs sur la clef pour obtenir le chiffrement de César approprié pour les lettres au position pair et impair
            if (i % 2 == 0) {
                Droite += (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position pair
                Gauche -= (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position impair
            } else {
                Droite -= (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position pair
                Gauche += (int) clef.charAt(i); //Chiffrement de César obtenu a partir de la clef pour les lettres à position impair
            }
        }

        String message = MesACoder.getText().toString();
        char x;                     //lettre actuel lors de la lecture du message
        int a = clef.charAt(0);     //1er lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int b = clef.charAt(1);     //2eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int c = clef.charAt(2);     //3eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int d = clef.charAt(3);     //4eme lettre a ajouter apres la lettre coder pour ajouter des lettres inutiles en opérant avec x
        int compt = 0;
        for (int i = 0; i < message.length(); i++) {
            x = message.charAt(i);
            if (i%5 == 0) {
                if (compt%2 == 0) {
                    //coder = coder + (char) ((x + Droite)%26) + (char) ((x * a)%26) + (char) ((x + b)%26) + (char) ((x * c)%26) + (char) ((x + d)%26);
                    //coder = coder + (char) ((x + Droite) % 26 + 65) + (char) ((x * a) % 26 + 65) + (char) ((x + b) % 26 + 65) + (char) ((x * c) % 26 + 65) + (char) ((x + d) % 26 + 65);
                    coder = coder + (char) (x - 65 - Droite);
                    compt++;
                } else {
                    //coder = coder + (char) ((x + Gauche)%26) + (char) ((x - a)%26) + (char) ((x * b)%26) + (char) ((x - c)%26) + (char) ((x * d)%26);
                    //coder = coder + (char) ((x + Gauche) % 26 + 65) + (char) ((x - a) % 26 + 65) + (char) ((x * b) % 26 + 65) + (char) ((x - c) % 26 + 65) + (char) ((x * d) % 26 + 65);
                    coder = coder + (char) (x - 65 - Gauche);
                    compt++;
                }
            }
        }
        Resultat.setText(coder);
        //System.out.println("Le message est : " + message);
        System.out.println(coder);
    }*/
}
