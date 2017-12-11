package cpuSimulator;




public class ALU {
	
	public static final String ZERO = "0101010";
	public static final String ONE	= "0111111";
	public static final String MINUS_ONE = "0111010";
	public static final String D_ = "0001100";
	public static final String A_ = "0110000";
	public static final String NOT_D = "0001101"; 
	public static final String NOT_A = "0110001";
	public static final String INVERTED_SIGN_D = "0001111";
	public static final String INVERTED_SIGN_A = "0110011";
	public static final String INCREMENTED_D = "0011111";
	public static final String INCREMENTED_A = "0110111";
	public static final String DECREMENTED_D = "0001110";
	public static final String DECREMENTED_A = "0110010";
	public static final String D_PLUS_A = "0000010";
	public static final String D_MINUS_A = "0010011";
	public static final String A_MINUS_D = "0000111";
	public static final String D_AND_A = "0000000";
	public static final String D_OR_A = "0010101";
	public static final String M_ = "1110000";
	public static final String NOT_M = "1110001";
	public static final String INVERTED_SIGN_M = "1110011";
	public static final String INCREMENTED_M = "1110111";
	public static final String DECREMENTED_M = "1110010";
	public static final String D_PLUS_M = "1000010";
	public static final String D_MINUS_M = "1010011";
	public static final String M_MINUS_D = "1000111";
	public static final String D_AND_M = "1000000";
	public static final String D_OR_M = "1010101";
	
	//Three constants that we use frequently: 0, 1, -1 in 2's complement representation.
	public static final String ZERO_NUMBER = "0000000000000000";
	public static final String ONE_NUMBER = "0000000000000001";
	public static final String MINUS_ONE_NUMBER = "1111111111111111";
	
	
	
	
	CPU cpu;
	
	//The CPU owns the ALU.
	public ALU(CPU cpu) {
		this.cpu = cpu;
	}
	
	public String compute(String acBits) {
		String computation = "";
		String A = cpu.A;
		String D = cpu.D;
		String M = cpu.dataMemory.retrieve(A);
		
		switch(acBits) {
			case ZERO:
				computation = ZERO_NUMBER; break;
				
			case ONE: 
				computation = ONE_NUMBER; break;
				
			case MINUS_ONE:
				computation = MINUS_ONE_NUMBER; break;
				
			case D_:
				computation = D; break; 
				
			case A_: 
				computation = A; break;
				
			case NOT_D:
				computation = Binary.bitWiseNot(D);break; 
				
			case NOT_A:
				computation = Binary.bitWiseNot(A);break; 
				
			case INVERTED_SIGN_D: 
				computation = Binary.invertSign(D); break;
				
			case INVERTED_SIGN_A: 
				computation = Binary.invertSign(A); break;
				
			case INCREMENTED_D: 
				computation = Binary.addBinaryNumbers(D, ONE_NUMBER);break;
			
			case INCREMENTED_A: 
				computation = Binary.addBinaryNumbers(A, ONE_NUMBER); break;
			
			case DECREMENTED_D:
				computation = Binary.addBinaryNumbers(D, MINUS_ONE_NUMBER); break;
				
			case DECREMENTED_A:
				computation = Binary.addBinaryNumbers(A, MINUS_ONE_NUMBER); break;
				
			case D_PLUS_A: 
				computation = Binary.addBinaryNumbers(D, A); break;
				
			case D_MINUS_A:
				computation = Binary.addBinaryNumbers(D, Binary.invertSign(A));break; 
				
			case A_MINUS_D:
				computation = Binary.addBinaryNumbers(A, Binary.invertSign(D)); break;
				
			case D_AND_A:
				computation = Binary.bitWiseAnd(D, A);break;
				
			case D_OR_A:
				computation = Binary.bitWiseOr(D, A);break;
				
			case M_:
				computation = M; break;
				
			case NOT_M:
				computation = Binary.bitWiseNot(M);break; 
			
			case INVERTED_SIGN_M: 
				computation = Binary.invertSign(M);break;
				
			case INCREMENTED_M: 
				computation = Binary.addBinaryNumbers(M, ONE_NUMBER);break; 
			
			case DECREMENTED_M:
				computation = Binary.addBinaryNumbers(M, MINUS_ONE_NUMBER);break; 
				
			case D_PLUS_M: 
				computation = Binary.addBinaryNumbers(D, M); break;
			
			case D_MINUS_M:
				computation = Binary.addBinaryNumbers(D, Binary.invertSign(M));break; 
				
			case M_MINUS_D:
				computation = Binary.addBinaryNumbers(M, Binary.invertSign(D)); break;
				
			case D_AND_M:
				computation = Binary.bitWiseAnd(D, M);break;
				
			case D_OR_M:
				computation = Binary.bitWiseOr(D, M);break;
				
		}
		
		cpu.A = A;
		cpu.D = D;
		cpu.dataMemory.write(M, A);
		
		return computation; 
		
	
	
	}

}
