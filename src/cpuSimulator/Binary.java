package cpuSimulator;

public class Binary {
	
	public static void main(String[] args) {
		System.out.println(toBinaryString(4));
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
	 * Devuelve en un string value en binario (16bits)
	 * @param value
	 * @return
	 */
	public static String toBinaryString(int value){
		String s = "";
		for(int i= 15; i >= 0; i--){
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
