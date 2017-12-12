package cpuSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Program{
	public static final int memorySize = 32768; //2^15
	public static void main(String[] args) {
		
		Memory instructionMemory = new Memory(memorySize);
		Memory dataMemory= new Memory(memorySize);
		
		
		CPU cpu = new CPU(instructionMemory, dataMemory);
		
		//cpu.startProgram(cantidadDeInstrucciones);	    
	}
	
	/**
	 * Create a CPU and load the instruction from the file in the path directory
	 * @param path Direction to file with the machine code.
	 * @return CPU
	 * @throws IOException 
	 */
	public static CPU createCPU(String path) throws IOException {
		
		Memory instructionMemory = new Memory(memorySize);
		Memory dataMemory= new Memory(memorySize);
		loadInstruction(path, instructionMemory);
		CPU cpu = new CPU(instructionMemory, dataMemory);
		
		return cpu;
	}
	
	public static void loadInstruction(String path, Memory instructionMemory) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException("Don't exist" + path);
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		try {
			int i = 0;
			while((line = bufferedReader.readLine()) != null) {
				instructionMemory.write(line, Binary.toBinaryString(i++));
			}
		} catch (IOException e) {
			
			throw new IOException("I/O error");
		}
	}


}