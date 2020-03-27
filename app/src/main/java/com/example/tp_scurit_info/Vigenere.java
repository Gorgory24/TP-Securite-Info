package com.example.tp_scurit_info;
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
	
	public String crypt(String message, String cle) {
		
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
	
	public String decrypt(String message, String cle) {
			
			String final_message = "";
			String final_cle = createKey(message,cle);
			
			for (int i = 0; i < message.length(); i++) {
				
				int letter_decale = ((int)message.charAt(i) - (int)final_cle.charAt(i));			
				
				final_message += ExtendedAscii.getString(letter_decale);
				
			}
			
			return final_message;
			
		}
	
}
