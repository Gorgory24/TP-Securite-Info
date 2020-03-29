
public class Atbash {

	public Atbash() {
		
	}
	
	private String crypt(String message) {
		String final_message = "";
		
		for (int i = 0; i < message.length(); i++) {
			char current_letter = message.charAt(i);
			
			// On récupère le caractère courant
			int position = message.charAt(i);

			// On va chercher son opposé dans la table ASCII
			int opposite = 255 - position;

			// Et selon si c'est un caractère spécial, on affiche sa valeur hexadécimal ou le caractère directement:
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
				
				// On récupère le caractère courant
				int position = Alphabet.getPosition(current_letter);

				// On va chercher son opposé dans l'alphabet
				int opposite = 25 - position;

				final_message += Alphabet.getLetter(opposite);
			}
			
			return final_message;
		}
		
		return crypt(message);
		
	}
}
