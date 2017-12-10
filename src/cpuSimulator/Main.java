package cpuSimulator;

public class Main {

	
	public static void main(String[] args) {
		
		
		final int memorySize = 32768; //2^15
		
		Memory instructionMemory = new Memory(memorySize);
		Memory dataMemory= new Memory(memorySize);
		
		//Leer el archivo y almacenar el programa en la memoria de intrucciones.
		
		CPU cpu = new CPU(instructionMemory, dataMemory);
		cpu.startProgram();

	}

}
