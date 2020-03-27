package com.example.tp_scurit_info;
import java.util.ArrayList;

public class Hill {
	
	private final String LETTER_RARE = "q";
	
	public Hill() {
		
	}
	
	private ArrayList<String> separeMessage(String message, int groupe) {
		ArrayList<String> liste_final = new ArrayList<String>();
		
		String groupe_courant = "";
		
		if (message.length()%groupe != 0)
			message += LETTER_RARE;
		
		int i = 0;
		while (i < message.length()) {
			
			groupe_courant += message.charAt(i);
			i += 1;
			
			if (i%groupe == 0) {
				liste_final.add(groupe_courant);
				groupe_courant = "";
			}
			
		}
		
		return liste_final;
		
	}
	
	private ArrayList<int[]> rangGroupe(ArrayList<String> liste_groupe) {
		ArrayList<int[]> liste_rang = new ArrayList<int[]>();
		
		for(int i = 0 ; i < liste_groupe.size(); i++) {
			int size = liste_groupe.get(i).length();
			int[] my_group = new int[size];
			
			for(int j = 0; j < size; j++) 
				my_group[j] = (int)liste_groupe.get(i).charAt(j);

			liste_rang.add(new int[] {my_group[0], my_group[1]});
			
		}
		
		return liste_rang;
	}
	
	
	private int determinant(int a, int b, int c, int d) {
		return ((a * d) - (b*c));
	}
	
	private int[] combinaisonLineaire(int x1, int x2, int a, int b, int c, int d) {
		int[] result = new int[2];
		
		result[0] = (a*x1) + (b*x2);
		result[1] = (c*x1) + (d*x2);
		
		return result;
	}
	
	private int matriceInverse(int a, int b, int c, int d) {
		int det = determinant(a,b,c,d);
		
		int calcul = 1;
		int k = 0;
		
		while ((calcul%11) != 0) {
			calcul = 1 + (26*k);
			
			if (calcul%11 == 0)
				return (int)(calcul/11);
			
			k += 1;
		}
		
		// Cas si rien � �t� trouv�: (Mais c'est cens� ne jamais arriv� � cause du while au dessus)
		return -1;
	}
	
	
	public String crypt(String message, int groupe, int a, int b, int c, int d) {
		String final_message = "";
		
		if (determinant(a,b,c,d)%2 == 0 && determinant(a,b,c,d)%13 == 0 )
			return "Erreur: [Impossible de decrypter!]";
		
		ArrayList<String> liste_groupe = separeMessage(message,groupe);
		ArrayList<int[]> liste_rang = rangGroupe(liste_groupe);
		
		ArrayList<int[]> liste_combinaison = new ArrayList<int[]>();
		
		for(int i = 0; i < liste_rang.size(); i++) {
			int[] rang = liste_rang.get(i);
			
			int[] combinaison = combinaisonLineaire(rang[0],rang[1],a,b,c,d);
			liste_combinaison.add(new int[] {combinaison[0]%256, combinaison[1]%256});
			
		}
		
		for (int i = 0; i < liste_combinaison.size(); i++) {
			int[] combi = liste_combinaison.get(i);
			final_message += ExtendedAscii.getChar(combi[0], 0) + "" + ExtendedAscii.getChar(combi[1], 0);
		}
		
		return final_message;
	}
	
	public String decrypt(String message, int a, int b, int c, int d) {
		int m = matriceInverse(a,b,c,d);
		
		int[] mA1 = new int[] { m*d, m*-b, m*-c, m*a };
		
		ArrayList<String> message_bloc = separeMessage(message, 2);
		
		String final_message = "";
		
		for (int i = 0; i < message_bloc.size(); i++) {
			String bloc_courant = message_bloc.get(i);
			
			int[] bloc_matrice = new int[] { (int)bloc_courant.charAt(0), (int)bloc_courant.charAt(1) };
			
			int[] final_matrice = new int[] { (mA1[0]*bloc_matrice[0]) + (mA1[1]*bloc_matrice[1]), (mA1[2]*bloc_matrice[0]) + (mA1[3]*bloc_matrice[1]) };
			final_matrice[0] %= 256;
			final_matrice[1] %= 256;
			
			final_message += ExtendedAscii.getChar(final_matrice[0], 0) + "" + ExtendedAscii.getChar(final_matrice[1], 0);

		}
		
		return final_message;
	}

}
