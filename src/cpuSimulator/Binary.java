package cpuSimulator;

public class Binary {
	
	public static void main(String[] args) {
		int number = -16383;
		System.out.println(toBinaryString(number));
		System.out.println(Integer.toBinaryString(number));
	}
	
	/**
	 * Devuelve en un string value en binario (32bits)
	 * @param value
	 * @return
	 */
	public static String toBinaryString(long value){
		String s = "";
		for(int i= 31; i >= 0; i--){
			if ((value & (1 << i)) != 0) {
				s += 1;
			}
			else {
				s += 0;
			}
		}
		return s;
	}
	
	/**
	 * Devuelve en un string value en binario (15bits)
	 * @param value
	 * @return
	 */
	public static String toBinaryString(int value){
		String s = "";
		for(int i= 14; i >= 0; i--){
			if ((value & (1 << i)) != 0) {
				s += 1;
			}
			else {
				s += 0;
			}
		}
		return s;
	}
	
	public static int parseBinaryToInt(String binaryNumber) {
		
		int number;
		
		if(binaryNumber.charAt(0) == '1') {
			
			String positiveBinaryNumber = invertSign(binaryNumber);
			number = Integer.parseInt(positiveBinaryNumber, 2);
			number*= -1;
		} else {
			number = Integer.parseInt(binaryNumber, 2);
		}
			
		
		
		return number;
		
	}
	
	
	
	public static String addBinaryNumbers(String binaryNumber1, String binaryNumber2) {
		
		String sum = "";
		int bitWiseSum = 0;
		int carry = 0;
		int bit1, bit2;
		
		char[] binaryNumber1Bits = binaryNumber1.toCharArray();
		char[] binaryNumber2Bits = binaryNumber2.toCharArray();
		
		for(int i = 15; i >= 0; i--) {
			bit1 = (binaryNumber1Bits[i] == '1') ? 1 : 0;
			bit2 = (binaryNumber2Bits[i] == '1') ? 1 : 0;
			
			bitWiseSum = bit1 + bit2 + carry;
			
			switch(bitWiseSum) {
				case 0: 
					sum = 0 + sum;
					carry = 0;
					break;
					
				case 1: 
					sum = 1 + sum;
					carry = 0;
					break;
					
				case 2: 
					sum = 0 + sum;
					carry = 1;
					break;
				case 3: 
					sum = 1 + sum;
					carry = 1;
					break;
						
			}
			bitWiseSum = 0;		
		}
		return sum;
	}
	
	
	
	//Returns a string with each original bit flipped to its opposite. For ex: original = "0100", flipped = "1011"
		public static String bitWiseNot(String binaryString) {
			String negatedBinaryString = "";
			for(char bit : binaryString.toCharArray()) 
				negatedBinaryString += (bit == '1') ? '0' : '1';
				
			return negatedBinaryString;
			
		}
		
	public static String bitWiseOr(String binaryString1, String binaryString2) {
		String orString = "";
		
		char[] binaryString1Chars = binaryString1.toCharArray();
		char[] binaryString2Chars = binaryString2.toCharArray();
		
		for(int i = 0; i < 16; i++) 
			orString += (binaryString1Chars[i] == '1' || binaryString2Chars[i] == '1') ? '1' : '0';
			
		return orString;
	}
	
	
	public static String bitWiseAnd(String binaryString1, String binaryString2) {
		String andString = "";
		
		char[] binaryString1Chars = binaryString1.toCharArray();
		char[] binaryString2Chars = binaryString2.toCharArray();
		
		for(int i = 0; i < 16; i++) 
			andString += (binaryString1Chars[i] == '1' && binaryString2Chars[i] == '1') ? '1' : '0';
			
		return andString;
		
	}
	

	//Inverts the sign of a binary  in 2's complement representation. So, if the number is positive, it becomes negative, and viseversa.
	public static String invertSign(String binaryNumber) {
		String negatedBinaryNumber = bitWiseNot(binaryNumber);
		String positiveBinaryNumber = addBinaryNumbers(negatedBinaryNumber, "0000000000000001");
		return positiveBinaryNumber;
	}
	
	
	
	
}
