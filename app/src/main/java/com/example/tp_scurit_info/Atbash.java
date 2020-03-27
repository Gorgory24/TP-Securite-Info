
public class Atbash {

	public Atbash() {
		
	}
	
	public String crypt(String message) {
		String final_message = "";
		
		for (int i = 0; i < message.length(); i++) {
			char current_letter = message.charAt(i);
			
			// On récupère le caractère courant
			int position = ExtendedAscii.getChar(current_letter, 0);
			
			// On va chercher son opposé dans la table ASCII
			int opposite = 256 - position;
			//System.out.println("Opposite: " + opposite + " | " + (char)opposite);
			
			// Et selon si c'est un caractère spécial, on affiche sa valeur hexadécimal ou le caractère directement:
			if (ExtendedAscii.isSpecialCharacter(opposite))
				final_message += ExtendedAscii.getHex(opposite);
			else
				final_message += ExtendedAscii.getAscii(opposite);  //ExtendedAscii.getChar(opposite, 0);

		}
		
		return final_message;
	}
}
