package com.example.tp_scurit_info;
public class Cesar {

	public Cesar() {
		
	}
	
	private String crypt(String message, int offset) {
		String message_final = "";

		for(int i = 0; i < message.length(); i++)
		{
			int character = (int)message.charAt(i);
			int next = ExtendedAscii.getChar(character, offset);

			if (ExtendedAscii.isSpecialCharacter(next)) 
				message_final += ExtendedAscii.getHex(character);

			else
				message_final += ExtendedAscii.getString(next); //(char)next;
			
		}

		return message_final;
	}
	
	public String crypt(String message, int offset, boolean useAlphabet) {
		String message_final = "";

		if (useAlphabet) {
			
			message = message.toUpperCase();
			
			for(int i = 0; i < message.length(); i++)
			{
				if (Alphabet.isSymbole(message.charAt(i))) 
					message_final += message.charAt(i);
				
				else {
					char next = Alphabet.add(Alphabet.getLetter((int)message.charAt(i)), offset);
					message_final += next;
				}
			}

			return message_final;
		}
		
		return crypt(message,offset);
	}
	
	private String decrypt(String message, int offset) {
		String message_final = "";

		for(int i = 0; i < message.length(); i++)
		{
			int character = (int)message.charAt(i);
			int next = ExtendedAscii.getChar(character, -offset);

			if (ExtendedAscii.isSpecialCharacter(next)) 
				message_final += ExtendedAscii.getHex(character);
			else
				message_final += ExtendedAscii.getString(next); //(char)next;
		}

		return message_final;
	}
	
	public String decrypt(String message, int offset, boolean useAlphabet) {
		String message_final = "";
		
		if (useAlphabet) {
			message = message.toUpperCase();
			
			for(int i = 0; i < message.length(); i++)
			{
				if (Alphabet.isSymbole(message.charAt(i))) 
					message_final += message.charAt(i);
				
				else {
					char next = Alphabet.substract(Alphabet.getLetter((int)message.charAt(i)), offset);
					message_final += next;
				}
			}
		}
		
		return decrypt(message,offset);
	}
	
}
