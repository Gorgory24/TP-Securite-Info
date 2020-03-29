
public class Cesar {

	public Cesar() {
		
	}
	
	public String crypt(String message, int offset) {
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
	
	public String decrypt(String message, int offset) {
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
	
}
