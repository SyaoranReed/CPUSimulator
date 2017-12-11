package cpuSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class Main {

	
	public static void main(String[] args) {
		
		
		final int memorySize = 32768; //2^15
		
		Memory instructionMemory = new Memory(memorySize);
		Memory dataMemory= new Memory(memorySize);
		
		
		
		
		
		CPU cpu = new CPU(instructionMemory, dataMemory);
		
		//cpu.startProgram(cantidadDeInstrucciones);
		
		
		
	    
	}
	
	
	public void loadInstruction(String pathname, Memory instructionMemory) throws IOException {
		File file = new File(pathname);
		if (!file.exists()) {
			throw new FileNotFoundException("Don't exist" + pathname);
		}
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		try {
			int i = 0;
			while((line = bufferedReader.readLine()) != null) {
				instructionMemory.write(line, Integer.toBinaryString(i++));
			}
		} catch (IOException e) {
			
			throw new IOException("I/O error");
		}
	}


}