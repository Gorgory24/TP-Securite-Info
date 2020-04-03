package com.example.tp_scurit_info;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public RSA() {

    }

    public String crypt(String message) {
        String final_message = "";
        Random random = new Random();
        int p = random.nextInt(27)+3;
        int q = random.nextInt(27)+3;
        for (int i = 2; i < p; i++) {
            if (p % i == 0 || p<0) {
                p = random.nextInt(27)+3;
                i = 2;
            }
        }
        for (int i = 2; i < q; i++) {
            while (q == p) {
                q = random.nextInt(27)+3;
            }
            if (q % i == 0 || q<0) {
                q = random.nextInt(27)+3;
                i = 2;
            }
        }

        int n = p * q;
        int fi = (p - 1) * (q - 1);
        System.out.println("p = "+p+" q = "+q);
        int e = random.nextInt(fi-2)+2;
        for(int i = 2; i<e; i++){
            if(e%i == 0){
                e = random.nextInt(fi-2)+2;
                i = 2;
            }
        }

        int calcul = 1;
        long d = 1;
        int k = 0;
        while (calcul%e != 0) {
            calcul = 1 + (fi * k);
            if (calcul%e == 0 && d != e){
                d = calcul/e;
            }
            k += 1;
        }

        String ascii_message = "";
        for (int i = 0; i<message.length(); i++){
            ascii_message = ascii_message + (int) message.charAt(i);
        }

        System.out.println("Ascii_message = " + ascii_message);
        String temp_message = String.valueOf(ascii_message.charAt(0));
        BigInteger modpow;
        BigInteger a1;
        BigInteger a2;
        BigInteger a3;

        for (int i = 1; i<ascii_message.length(); i++){
            temp_message = temp_message + ascii_message.charAt(i);
            if(Integer.parseInt(temp_message) > n){
                temp_message = temp_message.substring(0,temp_message.length()-1);
                a1 = new BigInteger(temp_message);
                a2 = new BigInteger(String.valueOf(e));
                a3 = new BigInteger(String.valueOf(n));
                modpow = a1.modPow(a2,a3);
                System.out.println("modpow : " + modpow);
                final_message = final_message + modpow;
                temp_message = String.valueOf(ascii_message.charAt(i));
            }
            else if(ascii_message.length() == i+1){
                a1 = new BigInteger(temp_message);
                a2 = new BigInteger(String.valueOf(e));
                a3 = new BigInteger(String.valueOf(n));
                modpow = a1.modPow(a2,a3);
                final_message = final_message + modpow;
            }
            System.out.println("temp_message = " + temp_message);
        }
        System.out.println(final_message);
        return final_message + " e = "+e+" d = "+d+" n = "+n;
    }

    public String decrypt(String message, String clef){
        String final_message = "";
        String d = "";
        String n = "";
        int isN = 0;
        char k;
        BigInteger a1;
        BigInteger a2;
        BigInteger a3;
        BigInteger modpow;

        for (int i = 0; i<clef.length(); i++){
            k = clef.charAt(i);
            if (k == ' '){
                isN = 1;
            }
            if(isN == 1 && clef.charAt(i) != ' ') {
                n = n + clef.charAt(i);
            }
            else if (isN == 0 && clef.charAt(i) != ' '){
                d = d + clef.charAt(i);
            }
        }

        String temp_message = String.valueOf(message.charAt(0));
        System.out.println("temp_message : " + temp_message);

        for (int i = 1; i<message.length(); i++){
            temp_message = temp_message + message.charAt(i);
            if(Integer.parseInt(temp_message) > Integer.parseInt(n)){
                temp_message = temp_message.substring(0,temp_message.length()-1);
                a1 = new BigInteger(temp_message);
                a2 = new BigInteger(d);
                a3 = new BigInteger(n);
                modpow = a1.modPow(a2,a3);
                final_message = final_message + modpow;
                temp_message = String.valueOf(message.charAt(i));
                System.out.println("temp_message : " + temp_message);
                System.out.println("final_message : "+ final_message);
            }
            else if(message.length() == i+1){
                a1 = new BigInteger(temp_message);
                a2 = new BigInteger(d);
                a3 = new BigInteger(n);
                modpow = a1.modPow(a2,a3);
                final_message = final_message + modpow;
            }
        }

        return final_message;
    }
}

