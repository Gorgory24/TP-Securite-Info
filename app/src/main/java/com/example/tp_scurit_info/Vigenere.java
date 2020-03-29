
public class Vigenere {

	public Vigenere() {
		
	}
	
	
	
	public String createKey(String message, String cle) {
		String final_message = "";
		
		int caractere_courant_cle = 0;
		
		for (int i = 0 ; i < message.length(); i++) {
			
			char current_letter = message.charAt(i);
			
			if (ExtendedAscii.isSpecialCharacter(current_letter)) {
				final_message += current_letter;
			}
			
			else {
				final_message += cle.charAt(caractere_courant_cle%cle.length());
				caractere_courant_cle++;
			}
		}
		
		System.out.println("KEY FINAL= " + final_message);
		return final_message;
	}
	
	private String crypt(String message, String cle) {
		
		String final_message = "";
		String final_cle = createKey(message,cle);

		for (int i = 0; i < message.length(); i++) {
			
			int letter_decale = ((int)message.charAt(i) + (int)final_cle.charAt(i));		
			
			if (ExtendedAscii.isSpecialCharacter(letter_decale)) {
				final_message += ExtendedAscii.getHex(letter_decale);
			}
			else
				final_message += ExtendedAscii.getString(letter_decale);
		}
		
		return final_message;
		
	}
	
	public String crypt(String message, String cle, boolean useAlphabet) {
		String final_message = "";
		
		if (useAlphabet) {
			message = message.toUpperCase();
			String final_cle = createKey(message,cle);
			final_cle = final_cle.toUpperCase();
			
			for (int i = 0; i < message.length(); i++) {
				
				int current_letter = Alphabet.getPosition(message.charAt(i));
				int cle_letter = Alphabet.getPosition(final_cle.charAt(i));
				
				char letter_decale = (char)(current_letter + cle_letter);

				if (Alphabet.isSymbole((message.charAt(i))))
					final_message += message.charAt(i);
				else
					final_message +=Alphabet.getLetter(letter_decale);
			}
			
			return final_message;
		}
		
		return crypt(message,cle);
	}
	
	private String decrypt(String message, String cle) {
		String final_message = "";
		String final_cle = createKey(message,cle);
		
		for (int i = 0; i < message.length(); i++) {
			
			int letter_decale = ((int)message.charAt(i) - (int)final_cle.charAt(i));			
			final_message += ExtendedAscii.getString(letter_decale);
			
		}
		
		return final_message;
		
	}
	
	public String decrypt(String message, String cle, boolean useAlphabet) {
		
		String final_message = "";
		
		if (useAlphabet) {
			message = message.toUpperCase();
			String final_cle = createKey(message,cle);
			final_cle = final_cle.toUpperCase();
			
			for (int i = 0; i < message.length(); i++) {
				
				int current_letter = Alphabet.getPosition(message.charAt(i));
				int cle_letter = Alphabet.getPosition(final_cle.charAt(i));
				
				int letter_decale = (int)((current_letter - cle_letter)%26);
				
				if (letter_decale < 0)
					letter_decale += 26;
				
				if (Alphabet.isSymbole((message.charAt(i))))
					final_message += message.charAt(i);
				else
					final_message += Alphabet.getLetter(letter_decale);
			}
			
			return final_message;
		}
		
		return decrypt(message,cle);
		
	}
	
}
