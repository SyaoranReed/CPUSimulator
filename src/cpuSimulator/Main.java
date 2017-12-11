package cpuSimulator;

public class Main {

	
	public static void main(String[] args) {
		
		
		final int memorySize = 32768; //2^15
		
		Memory instructionMemory = new Memory(memorySize);
		Memory dataMemory= new Memory(memorySize);
		
		//Leer el archivo y almacenar el programa en la memoria de intrucciones.
		
		CPU cpu = new CPU(instructionMemory, dataMemory);
		cpu.startProgram();

		//System.out.println(Binary.toBinaryString(6));
		
		//System.out.println(Binary.parseBinaryToInt("0000000000000110"));
		
		/*
		
		String operand1 = "1110010000001010";
		String operand2 = "1111111111111111";
		System.out.println("Operand1 = " + Binary.parseBinaryToInt(operand1) + "   b: " + operand1);
		System.out.println("Operand2 = " + Binary.parseBinaryToInt(operand2) + "   b: " + operand2);
		
		String result =  Binary.addBinaryNumbers(operand1, operand2);
				
		System.out.println("Result = " + Binary.parseBinaryToInt(result) + "  b: " + result);*/
		
	}


}