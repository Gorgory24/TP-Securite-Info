package com.example.tp_scurit_info;
public class Alphabet {

	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final char[] SYMBOLES = {',',';','.','\'','/','{','}', '(',')', '?','!',' '};
	
	/**
	 * 
	 * @param code
	 * @return Return the character corresponding to a certain position
	 */
	public static final char getLetter(int code) {
		return ALPHABET.charAt(code%26);
	}
	
	/**
	 * 
	 * @param code
	 * @return Return a number which is the position of this letter in the alphabet
	 */
	public static final int getPosition(char code) {
		
		for (int i = 0 ; i < ALPHABET.length(); i++) {
			if (code == ALPHABET.charAt(i))
				return i;
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @param code
	 * @return Return true if a character is a symbole
	 */
	public static final boolean isSymbole(char code) {
		for (int i = 0; i < ALPHABET.length(); i++) {
			if (ALPHABET.charAt(i) == code)
				return false;
		}
		
		return true;
	}
	
	public static final char add(int letter1, int letter2) {
		return ALPHABET.charAt((letter1 + letter2)%26);
	}
	
	public static final char substract(int letter1, int letter2) {
		return ALPHABET.charAt((letter1 - letter2)%26);
	}

}
