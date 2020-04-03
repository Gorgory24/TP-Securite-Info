package com.example.tp_scurit_info;
public class Atbash {

	public Atbash() {
		
	}
	
	private String crypt(String message) {
		String final_message = "";
		
		for (int i = 0; i < message.length(); i++) {
			char current_letter = message.charAt(i);
			
			// On r�cup�re le caract�re courant
			int position = message.charAt(i);

			// On va chercher son oppos� dans la table ASCII
			int opposite = 255 - position;

			// Et selon si c'est un caract�re sp�cial, on affiche sa valeur hexad�cimal ou le caract�re directement:
			if (ExtendedAscii.isSpecialCharacter(opposite))
				final_message += ExtendedAscii.getHex(opposite);
			else
				final_message += new String(Character.toChars(opposite)); //(char)(opposite);  //ExtendedAscii.getChar(opposite, 0);

		}
		
		return final_message;
	}
	
	public String crypt(String message, boolean useAlphabet) {
		String final_message = "";
		
		if (useAlphabet) {
			message = message.toUpperCase();
			
			for (int i = 0; i < message.length(); i++) {
				char current_letter = message.charAt(i);
				
				// On r�cup�re le caract�re courant
				int position = Alphabet.getPosition(current_letter);

				// On va chercher son oppos� dans l'alphabet
				int opposite = 25 - position;

				final_message += Alphabet.getLetter(opposite);
			}
			
			return final_message;
		}
		
		return crypt(message);
		
	}
}
