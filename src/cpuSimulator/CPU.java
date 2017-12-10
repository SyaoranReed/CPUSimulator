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
	
	public String fetchData(String address) {
		return dataMemory.retrieve(address);
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
			jump(instruction.substring(13, 16));
			
			
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
		// TODO Auto-generated method stub
		
	}
	
	//Determinates the jump condition and Modifies PC register so it points to the next instruction.
	private void jump(String jBits) {
		// TODO Auto-generated method stub
		
	}


	
}
