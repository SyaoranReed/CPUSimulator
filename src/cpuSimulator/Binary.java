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
}
