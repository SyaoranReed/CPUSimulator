package cpuSimulator;

import java.util.Iterator;

public class CPU {
	
	public final static String DEFAULT_VALUE = "0000000000000000";
	
	//An A-instruction have this format 111a cccc ccdd djjj.
	
	//Registers contained by the CPU.
	String A; //Nota: Si se usa A para acceder a M[A], deben usarse los 15 bits menos significativos, pues el
	//tamaño de la memoria es de solo 32K.
	String D;
	String PC;
	public Memory instructionMemory, dataMemory;
	ALU alu;
	
	String programLines; //Binary representation of the number of lines of the program to execute.
	
	
	public CPU(Memory instructionMemory, Memory dataMemory) {
		this.instructionMemory = instructionMemory;
		this.dataMemory = dataMemory;
		PC = DEFAULT_VALUE;
		A = DEFAULT_VALUE;
		D = DEFAULT_VALUE;
		alu = new ALU(this);
		
	}
	
	public void startProgram(int numberOfInstructions) {
		
		while(Binary.parseBinaryToInt(PC) < numberOfInstructions) {
			stepForward();
		}
		
	}
	
	public String fetchData() {
		return dataMemory.retrieve(A);
	}
	
	public String fetchInstruction() {
		return instructionMemory.retrieve(PC);
	}

	public void stepForward() {
		String instructionToExecute = fetchInstruction();
		executeInstruction(instructionToExecute);
	}
	
	private void executeInstruction(String instruction) {
		
		
		if(instruction.charAt(0) == '0') { //if it is an A-Instruction
			A = instruction; //The instructions is interpreted as a 16-bit value and stored in A-register.
			PC = Binary.addBinaryNumbers(PC, "0000000000000001");
		}
		else if(instruction.charAt(0) == '1') { //if it is an C-Instruction
			
			//An C-instruction have this format 111a cccc ccdd djjj. Where acccccc it's needed to make the computation,
			//the ddd to determinate the destination of the computation, and the jjj to determinate the jump condition
			//in case a jump had to be executed.
			
			String comp = instruction.substring(3, 10);
			String dest = instruction.substring(10, 13);
			String jmp = instruction.substring(13, 16);
			String computation = compute(comp);
			
			destinate(dest, computation);
			String M = dataMemory.retrieve(A);
			if( jump(jmp, computation) ) 
				PC = A; //Modifying PC register so it points to the next instruction.
			else 
				PC = Binary.addBinaryNumbers(PC, "0000000000000001"); //If a jump is not required, then continue with the next instruction.(PC++)
			
		}
	}

	//Asks to the ALU to compute something.
	private String compute(String acBits) {
		return alu.compute(acBits);
	}
		

	//Determinates destination register where the computation is going to be stored.
	private void destinate(String dBits, String computation) {
		//dBits is a string with the format d1d2d3, where d1, d2 and d3 is associated with 
		//A, D and M respectively .
		
		if(dBits.charAt(0) == '1') A = computation; 
		if(dBits.charAt(1) == '1') D = computation;
		if(dBits.charAt(2) == '1') dataMemory.write(computation, A);
		
	}
	
	//Evaluates if a jump must be executed.
	private boolean jump(String jBits, String computation) {
		
		//jBits is a string with the format j1j2j3, where j1, j2 and j3 are associated respectively with
		//computation lesser, equal, and greater than zero conditions that need to be true for the jump to happen.
		
		boolean jump = false;
		
		int comp = Binary.parseBinaryToInt(computation); //Integer representation of the computation.
		
		if(jBits.equals("001") && comp > 0) jump = true; //JGT
		else if(jBits.equals("010") && comp == 0) jump = true; //JEQ
		else if(jBits.equals("011") && (comp > 0 || comp == 0)) jump = true; //JGE
		else if(jBits.equals("100") && comp < 0) jump = true; //JLT
		else if(jBits.equals("101") && comp != 0) jump = true; //JNE
		else if(jBits.equals("110") && (comp < 0 || comp == 0)) jump = true; //JLE
		else if(jBits.equals("111")) jump = true; 
		
		
		return jump;
		
		
	}

	public String getA() {
		return A;
	}

	public String getD() {
		return D;
	}

	public String getPC() {
		return PC;
	}
	
	public Iterator<String> instructionIterator(){
		return this.instructionMemory.iterator();
	}
	
	public Iterator<String> memoryIterator(){
		return this.dataMemory.iterator();
	}
	
	
}
