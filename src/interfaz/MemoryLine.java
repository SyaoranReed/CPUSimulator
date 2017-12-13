package interfaz;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MemoryLine {
	private static MemoryLine selected = null;
	private StringProperty address;
	private StringProperty word;
	private StringProperty decimalWord;
	private boolean isSelected;
	
	public MemoryLine(String address) {
		this.address = new SimpleStringProperty(address);
		this.word = new SimpleStringProperty("");
		this.decimalWord = new SimpleStringProperty("");
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
	
	 
	public String getWord() {
		return this.word.get();
	}

	public void setWord(String word) {
		this.word.setValue(word);
	}
	
	public StringProperty wordProperty() {
		return this.word;
	}
	
	public String getDecimalWord() {
		return this.decimalWord.getValue();
	}
	
	public void setDecimalWord(String value) {
		decimalWord.set(value);
	}
	
	public StringProperty decimalWordProperty() {
		return decimalWord;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean state) {
		MemoryLine.selected = this;
		this.isSelected = state;
	}
	
	public static void clearSelected() {
		if (MemoryLine.selected != null) {
			MemoryLine.selected.setSelected(false);
		}
	}
}
