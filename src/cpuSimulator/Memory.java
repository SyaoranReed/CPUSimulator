package cpuSimulator;

import java.util.Iterator;

import javax.imageio.ImageTypeSpecifier;

public class Memory implements Iterable<String> {

	String[] memory;
	public Memory(int size){
		memory = new String[size];
	}
	
	//Returns the word located in the memory location pointed by the address.
	public String retrieve(String address) {
		
		//Since an index of an array is integer type, the binary address
		//must be converted to its integer value.
		int memoryIndex = Binary.parseBinaryToInt(address);
		
		return memory[memoryIndex];
	}
	
	//Writes a word to the memory location pointed by the address.
	public void write(String word, String address) {
		int memoryIndex = Binary.parseBinaryToInt(address);
		memory[memoryIndex] = word;
	}
	/**
	 * 	Return a iterator to go over the values of the memory.
	 */
	@Override
	public Iterator<String> iterator() {
		return new Iterator() {
			private int i=0;
			@Override
			public boolean hasNext() {
				if (i < memory.length) {
					return true;
				}
				return false;
				
			}

			@Override
			public Object next() {
				return memory[i++];
			}
			
		};
	}
}
