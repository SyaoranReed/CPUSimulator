package interfaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemoryLine {
	private StringProperty address;
	private StringProperty instruction;
	private boolean isSelected;
	
	public MemoryLine(String address, String instruction) {
		this.address = new SimpleStringProperty(address);
		this.instruction = new SimpleStringProperty(instruction);
		isSelected = false;
	}

	public String getAddress() {
		return this.address.get();
	}

	public void setAddress(String address) {
		this.address.setValue(address);
	}
	
	public StringProperty addressProperty() {
		return this.address;
	}
	
	 
	public String getInstruction() {
		return this.instruction.get();
	}

	public void setInstruction(String instruction) {
		this.instruction.setValue(instruction);
	}
	
	public StringProperty instructionProperty() {
		return this.instruction;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void changeState() {
		this.isSelected = !isSelected;
	}
}
