import java.util.ArrayList;

public class DES {

	private final String BINARY_0_8BIT = "00000000";
	private final String BINARY_0_4BIT = "0000";
	
	private final int[] INITIAL_PERMUTATION = { 58,50,42,34,26,18,10,2,
												60,52,44,36,28,20,12,4,
												62,54,46,38,30,22,14,6,
												64,56,48,40,32,24,16,8,
												57,49,41,33,25,17,9,1,
												59,51,43,35,27,19,11,3,
												61,53,45,37,29,21,13,5,
												63,55,47,39,31,23,15,7
											  };
	
	// Permutation en fin de sortie du schéma de Feistel (Voir lien Constande du DES Wikipedia):
	private final int[] PERMUTATION = { 16,7,20,21,
										29,12,28,17,
										1,15,23,26,
										5,18,31,10,
										2,8,24,14,
										32,27,3,9,
										19,13,30,6,
										22,11,4,25
									  };
	
	private final int[] PC_1 = {57,49,41,33,25,17,9,
								1,58,50,42,34,26,18,
								10,2,59,51,43,35,27,
								19,11,3,60,52,44,36,
								63,55,47,39,31,23,15,
								7,62,54,46,38,30,22,
								14,6,61,53,45,37,29,
								21,13,5,28,20,12,4
							   };
	
	private final int[] PC_2 = { 14,17,11,24,1,5,
								 3,28,15,6,21,10,
								 23,19,12,4,26,8,
								 16,7,27,20,13,2,
								 41,52,31,37,47,55,
								 30,40,51,45,33,48,
								 44,49,39,56,34,53,
								 46,42,50,36,29,32
							   };
	
	private final int[] E = { 31,1,2,3,4,5,
							  4,5,6,7,8,9,
							  8,9,10,11,12,13,
							  12,13,14,15,16,17,
							  16,17,18,19,20,21,
							  20,21,22,23,24,25,
							  24,25,26,27,28,29,
							  28,29,30,31,32,1
							};
	
	private final int[][] S1 = {
			{14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},
			{0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},
			{4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},
			{15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}
	};
	
	private final int[][] S2 = { 
			{15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},
			{3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},
			{0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},
			{13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}
	};
	
	private final int[][] S3 = {
			{10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
			{13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
			{13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
			{1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}
	};
	
	private final int[][] S4 = {
			{7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},
			{13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},
			{10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},
			{3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}
	};
	
	private final int[][] S5 = {
			{2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},
			{14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},
			{4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},
			{11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}
	};
	
	private final int[][] S6 = {
			{12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},
			{10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},
			{9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},
			{4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}
	};
	
	private final int[][] S7 = {
			{4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},
			{13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},
			{1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},
			{6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}
	};
	
	private final int[][] S8 = {
			{13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},
			{1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},
			{7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},
			{2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}
	};
	
	private final int[] FINAL_PERMUTATION = { 40,8,48,16,56,24,64,32,
											  39,7,47,15,55,23,63,31,
											  38,6,46,14,54,22,62,30,
											  37,5,45,13,53,21,61,29,
											  36,4,44,12,52,20,60,28,
											  35,3,43,11,51,19,59,27,
											  34,2,42,10,50,18,58,26,
											  33,1,41,9,49,17,57,25
											};
	
	private final ArrayList<int[][]> S = new ArrayList<int[][]>();

	public DES() {
		// Facilité d'utilisation si toutes les boites de substitutions sont dans une liste:
		S.add(S1); S.add(S2); S.add(S3); S.add(S4);
		S.add(S5); S.add(S6); S.add(S7); S.add(S8);
	}
	
	// -----------  Helper -----------------
	
	private void printList(String messageBase, ArrayList<String> list, boolean horizontal) {
		
		System.out.println("--Begin Trace list--");
	
		System.out.print(messageBase);
		if (horizontal) {
			for(int i = 0; i < list.size(); i++)
				System.out.print("" + list.get(i) + " ");
			System.out.print("\n");
		}
		
		else
		{
			for(int i = 0; i < list.size(); i++)
				System.out.println("index: " + i+ " --> " + list.get(i));
		}
		
		System.out.println("-- End Trace list --");
	}
	
	private String transformArrayListToString(ArrayList<String> list) {
		String result = "";
		
		for(int i = 0 ; i < list.size(); i++)
			result += list.get(i);
		
		return result;
	}
	
	private ArrayList<String> transformStringToArrayList(String string, int decoupage) {
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i=0; i<string.length(); i+=decoupage)
			result.add(string.substring(i, Math.min(string.length(), i + decoupage)));
        
		return result;
	}
	
	// ----------- Converters (Bin, Hex, Int, ...) ---------------------
	
	private String convertCharToBinary(char character) {
		String binary_string = Integer.toBinaryString(character);

		for(int i =0; i < (8 - binary_string.length()); i++) {
			binary_string = "0".concat(binary_string);
		}
		
		return binary_string;
	}
	
	private String convertIntToBinary(int n, int nb_bits) {
		String result = "";
		
		if (n == 0)
			return BINARY_0_4BIT;
		
		while(n > 0) {
			result = ((n%2) == 0 ? "0" : "1") + result;
			n /= 2;
		}
		
		while (result.length() < nb_bits)
			result = "0".concat(result);
		
		return result;
	}
	
	private String convertIntToBinary(long n, int nb_bits) {
		String result = "";
		
		if (n == 0)
			return BINARY_0_4BIT;
		
		while(n > 0) {
			result = ((n%2) == 0 ? "0" : "1") + result;
			n /= 2;
		}
		
		while (result.length() < nb_bits)
			result = "0".concat(result);
		
		return result;
	}
		
	private String convertHexToBinary(char hex) {
		switch(hex) {
			case 'A': return convertIntToBinary(10,4);
			case 'B': return convertIntToBinary(11,4);
			case 'C': return convertIntToBinary(12,4);
			case 'D': return convertIntToBinary(13,4);
			case 'E': return convertIntToBinary(14,4);
			case 'F': return convertIntToBinary(15,4);
			default: return convertIntToBinary(Integer.parseInt(""+hex),4);
		}
	}
	
	// ----------------- Create Data --------------------------
	
	private ArrayList<String> fillBinary(String message) {
		ArrayList<String> list_binary = new ArrayList<String>();
		
		for (int i = 0 ; i < message.length(); i++) {
			list_binary.add(convertCharToBinary(message.charAt(i)));
		}
		
		while(list_binary.size() < 8)
			list_binary.add(BINARY_0_8BIT);
		
		return list_binary;
	}
	
	private ArrayList<String> fillKey(String cle) {
		ArrayList<String> list_binary = new ArrayList<String>();
		for(int i = 0 ; i < cle.length(); i++)
			list_binary.add(convertHexToBinary(cle.charAt(i)));
		
		return list_binary;
	}
	
	private ArrayList<String> fillShiftKeys(ArrayList<String> list) {
		String result = "";
		
		for(int i = 1; i < 16; i++) {
			
			int offset = (i == 1 || i == 2 || i == 9 || i == 16) ? 1 : 2;
			
			String currentL = list.get(i-1);
			long currentInt = Integer.parseInt(currentL,2);
			
			long cyclicShift = cyclicShift(currentInt,offset,true,28);
			result = convertIntToBinary(cyclicShift,28);
			result = result.substring(1,result.length());
			
			// Gestion cas particulier: 
			if (result.length() == 27) {
				String getLastCharacter = ""+list.get(i-1).charAt(offset+1);
				result =  getLastCharacter.concat(result);
			}
			if (result.length() == 29)
				result = result.substring(1);
			
			//System.out.println(""+i+": " + result + "(" + result.length() + " bits) | décalage: " + offset);
			
			list.add(result);
		}
		
		return list;
	}
	
	
	// ------------------ Permutation ------------------
	
	private String doPermutation(String cle, int[] tableau_permutation) {
		
		String result = "";
		
		for (int i = 0; i < tableau_permutation.length; i++) {
			int index = tableau_permutation[i]-1;

			result += ""+cle.charAt(index);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param number The number you want to shift
	 * @param offset How much you want to shift this number
	 * @param leftSide Do a cyclic shift by the left? put false to shift to the right
	 * @return 
	 */
	private long cyclicShift(long number, int offset, boolean leftSide, int nbTotalDigit) {
		if (leftSide) 
			return (number << offset) | (number >> (nbTotalDigit - offset));
		
		return (number >>> offset) | (number << (nbTotalDigit - offset));
		
	}
		
	// On doit passer la permutation initial en paramètre: 
	private ArrayList<String>[] separationKey(String cle) {
		ArrayList<String> L = new ArrayList<String>();
		ArrayList<String> R = new ArrayList<String>();
		
		String L0 = "";
		for(int i = 0 ; i < 28; i++)
			L0 += cle.charAt(i);
			
		L.add(L0);
		
		String R0 = "";
		for(int i = cle.length()-28; i < cle.length(); i++)
			R0 += cle.charAt(i);
		
		R.add(R0);

		L = fillShiftKeys(L);
		R = fillShiftKeys(R);
		
		ArrayList<String>[] result = new ArrayList[2];
		result[0] = L;
		result[1] = R;
		
		return result;
	}
	
	private final String XOR(String bin1, String bin2) {
		
		String result = "";
		for(int i=0; i < bin1.length(); i++) {
			char c1 = bin1.charAt(i);
			char c2 = bin2.charAt(i);
			
			if (c1 == c2 && c2 == '1')
				result += "0";
			else if (c1 != c2)
				result += "1";
			else
				result += "0";
		}
		
		return result;
	}
	
	private String confusionFunction(String key, String right_part) {
		String result = "";
		
		String expansion_D = doPermutation(right_part,E);
		System.out.println("Expansion D: " + expansion_D + " ("+expansion_D.length() + " bits)");
		System.out.println("Clé confusion: " + key + " | " + key.length());
		
		// Calcul du XOR entre l'expansion de D et la clé courante, puis découpage en bloc de 6 bits:
	    String B = XOR(expansion_D,key);
	    //System.out.println("B: " + B);
	    
	    ArrayList<String> B_final = transformStringToArrayList(B,6);
	    printList("B_final: ", B_final,false);
		
	    // -- Passage des substitutions {S1, S2, ... S8} --
	    for(int i=0; i < 8; i++) {
	    	String current_B = B_final.get(i);
	    	
	    	// On récupère le 1er character et le dernier ainsi que les éléments au milieu de manière distinct: 
	    	String first = ""+current_B.charAt(0);
	    	String last = ""+current_B.charAt(current_B.length()-1);
	    	String middle = current_B.substring(1,current_B.length()-1);
	    	//System.out.println(" First: " + first + " | Middle: " + middle + " | Last: " + last);
	    	
	    	// On converti en entier la concaténation du premier et dernier char, de même pour le nombre binaire au milieu:
	    	int first_and_last = Integer.parseInt(first + last,2);
	    	int middle_integer = Integer.parseInt(middle,2);
	    	//System.out.println("After convert to decimal: First/Last " + first_and_last + " mid: " + middle_integer);
	    	
	    	int s_value = S.get(i)[first_and_last][middle_integer];
	    	//System.out.println("Value of S: " + s_value);
	    	
	    	result += convertIntToBinary(s_value,4);
	    }
	    
	    // Dernière permutation: 
	    result = doPermutation(result,PERMUTATION);

	    return result;
	}
	
	private ArrayList<String> feistel(ArrayList<String> list_keys, String message) {
		final int MESSAGE_HALF_SIZE = 32;
		
		ArrayList<String> all = new ArrayList<String>(); // Contient l'assemblage des parties gauches et droite
		ArrayList<String> G = new ArrayList<String>(); // Uniquement la partie gauche
		ArrayList<String> D = new ArrayList<String>(); // Uniquement la partie droite
		
		String G0 = "";
		for(int i = 0 ; i < MESSAGE_HALF_SIZE; i++)
			G0 += message.charAt(i);
		G.add(G0);
		
		String D0 = "";
		for(int i = message.length()-MESSAGE_HALF_SIZE; i < message.length(); i++)
			D0 += message.charAt(i);
		D.add(D0);
		
		for (int i = 1; i < 15; i++) {
			String G_courant = D.get(i-1);
			G.add(G_courant);
			
			String D_courant = confusionFunction(list_keys.get(i),D.get(i-1));
			D.add(D_courant);
		}
		
		// Assemblage des parties gauches et droite: 
		for(int i=0; i < 15; i++)
			all.add(""+G.get(i) + D.get(i));
			
		// On effectue la permutation finale sur le dernier element: 
		String last_element = all.get(D.size()-1);
		last_element = doPermutation(all.get(all.size()-1),FINAL_PERMUTATION);
		all.remove(D.size()-1); // On enlève le dernier élément sans permutation
		all.add(last_element); // Pour le remplacer par celui qui à subit la permutation finale.

		return all;
	}
	
	
	public String crypt(String message, String cle) {
		ArrayList<String> list_binary_message = fillBinary(message);
		
		// -- Diversification de la clé --
		ArrayList<String> list_binary_cle = fillKey(cle);
		ArrayList<String> L = new ArrayList<String>();
		ArrayList<String> R = new ArrayList<String>();
		ArrayList<String> listKeyFinal = new ArrayList<String>();
		// ---- 
		
		printList("Message en binaire: ", list_binary_message,true);
		printList("Clé en binaire: ", list_binary_cle,true);
		
		String initial_permutation_key = doPermutation(transformArrayListToString(list_binary_cle), PC_1); 
		
		System.out.println("Initial Permutation cle: " + initial_permutation_key);
		
		L = separationKey(initial_permutation_key)[0];
		R = separationKey(initial_permutation_key)[1];
		
		// Etape finale de diversification de la clé: 
		for (int i = 0 ; i < 15; i ++) {
			String current_assemble_key = L.get(i) + R.get(i);
			listKeyFinal.add(doPermutation(current_assemble_key,PC_2));
		}
		
		String initial_permutation_message = doPermutation(transformArrayListToString(list_binary_message), INITIAL_PERMUTATION);
		System.out.println("Initial Permutation Message: " + initial_permutation_message);
		
		// Passage dans le schéma de Feistel:
		ArrayList<String> result = feistel(listKeyFinal,initial_permutation_message);
		
		return result.get(result.size()-1);
	}

}
