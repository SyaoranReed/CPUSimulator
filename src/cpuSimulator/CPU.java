package cpuSimulator;


public class CPU {
	
	final static String FIRST_INSTRUCTION_ADDRESS = "0000000000000000";
	
	//An A-instruction have this format 111a cccc ccdd djjj.
	
	//Registers contained by the CPU.
	String A; //Nota: Si se usa A para acceder a M[A], deben usarse los 15 bits menos significativos, pues el
	//tamaño de la memoria es de solo 32K.
	String D;
	String PC;
	Memory instructionMemory, dataMemory;
	
	String programLines; //Binary representation of the number of lines of the program to execute.
	
	
	public CPU(Memory instructionMemory, Memory dataMemory) {
		
	}
	
	public void startProgram() {
		//Mientras queden instrucciones por ejecutar {
			stepForward();
		//}
		
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
		
		if(instruction.charAt(0) == '0') //if it is an A-Instruction
			A = instruction; //The instructions is interpreted as a 16-bit value and stored in A-register.
		
		else if(instruction.charAt(0) == '1') { //if it is an C-Instruction
			
			//An C-instruction have this format 111a cccc ccdd djjj. Where acccccc it's needed to make the computation,
			//the ddd to determinate the destination of the computation, and the jjj to determinate the jump condition
			//in case a jump had to be executed.
			String computation = compute(instruction.substring(3, 9));
			destinate(instruction.substring(10, 12), computation);
			if( jump(instruction.substring(13, 16), computation) ) 
				PC = A; //Modifying PC register so it points to the next instruction.
			else 
				//PC++; //If a jump is not required, then continue with the next instruction.
			
			
		}
	}

	//Determinates what type of computation has to be made.
	private String compute(String cBits) {
		
		//Nota: En caso de transformar la computación con desde enteros a binario con el método Integer.toBinaryString()
		//hay que concatenarle 0's en caso de que el número alcance a representarse con menos de 16 bits.
		//por ejemplo, si transformamos 4 a binario, quedará un string "100", por lo que hay que agregarle 
		//ceros a la izquierda para que queden 16 bits.
		
		String computation =  "hola";
		return computation;
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
		
		int comp = Integer.parseInt(computation, 10); //Integer representation of the computation.
		
		if(jBits.equals("001") && comp > 0) jump = true; //JGT
		else if(jBits.equals("010") && comp == 0) jump = true; //JEQ
		else if(jBits.equals("011") && (comp > 0 || comp == 0) jump = true; //JGE
		else if(jBits.equals("100") && comp < 0) jump = true; //JLT
		else if(jBits.equals("101") && comp != 0) jump = true; //JNE
		else if(jBits.equals("110") && (comp < 0 || comp == 0)) jump = true; //JLE
		else if(jBits.equals("111")) jump = true; 
		
		
		return jump;
		
		
	}
	
	


	
}
