package interfaz;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.lang.ref.Cleaner.Cleanable;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.Iterator;

import javax.print.attribute.standard.RequestingUserName;

import cpuSimulator.Binary;
import cpuSimulator.CPU;
import cpuSimulator.Program;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class InterfaceController {
	@FXML
	private Button playButton;
	@FXML
	private Button oneStepButton;
	@FXML
	private TextField aRegField;
	@FXML
	private TextField dRegField;
	@FXML
	private TextField pcRegField;
	@FXML
	private 	TableView<MemoryLine> instructionTable;
	@FXML
	private 	TableView<MemoryLine> dataTable;
	private Stage ownerWindows;
	
	private CPU programCPU;
	private boolean wasRead;
	private int numberOfInstrLoaded;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		//Prepare instructionTable
		TableColumn column1 = instructionTable.getColumns().get(0);
		column1.setCellValueFactory(new PropertyValueFactory("address"));
		TableColumn column2 = instructionTable.getColumns().get(1);
		column2.setCellValueFactory(new PropertyValueFactory("word"));
		setCellFactoryTableViewHighLine(column1);
		setCellFactoryTableViewHighLine(column2);
		instructionTable.setFocusModel(null);
		fill(instructionTable);
		
		//Prepare dataTable
		column1 = dataTable.getColumns().get(0);
		column1.setCellValueFactory(new PropertyValueFactory("address"));
		column2 = dataTable.getColumns().get(1);
		setCellFactoryEditingCell(column2);
		column2.setCellValueFactory(new PropertyValueFactory("word"));
		dataTable.getSelectionModel().setCellSelectionEnabled(true);
		fill(dataTable);
	}
	
	private void fill(TableView<MemoryLine> tableView) {
		ObservableList<MemoryLine> memory = FXCollections.observableArrayList();
		for (int i = 0; i < Program.memorySize; i++) {
			memory.add(i,new MemoryLine(String.valueOf(i)));
		}	
		tableView.setItems(memory);
	}
	
	private void setCellFactoryTableViewHighLine(TableColumn column1) {
		column1.setCellFactory((column) -> {
		    return new TableCell<MemoryLine, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty);

		            if (item == null || empty) {
		                setText(null);
		                setStyle("");
		            } 
		            else {                
		            		MemoryLine line = getTableRow().getItem();
		            		setText(item);
		                if (line != null && line.isSelected()) {
		                    setTextFill(Color.BLACK);
		                    setStyle("-fx-background-color: yellow");
		                }
		            }   
		        }
		    	};
		});		
	}
	
	private void setCellFactoryEditingCell(TableColumn column) {
		column.setEditable(true);
		column.setCellFactory(TextFieldTableCell.forTableColumn());
		
		column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MemoryLine, String>>() {
			@Override
			public void handle(CellEditEvent<MemoryLine, String> event) {
				String newValue = event.getNewValue();
				String binaryWord = Binary.parseDecimalStringToBinaryString(newValue);
				String binaryAddress = Binary.parseDecimalStringToBinaryString(event.getRowValue().getAddress());
				event.getRowValue().setWord(binaryFormat(binaryWord));
				programCPU.dataMemory.write(binaryWord, binaryAddress);
			}
		});
	}

	public void setOwner(Stage stage) {
		this.ownerWindows = stage;
	}
	
	@FXML
	private void loadCodeOnAction() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(ownerWindows);
		try {
			programCPU = Program.createCPU(file.getPath());
			prepareExecution(programCPU);
			
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error in the file");
			alert.setContentText("Ooops, there is an error to load the code!");
			alert.showAndWait();
			e.printStackTrace();
		}
		
		
	}
	
	private void prepareExecution(CPU cpu) {
		if (wasRead) {
			clean(instructionTable);
			clean(dataTable);
			MemoryLine.clearSelected();
		}
		else {
			playButton.setDisable(false);
			oneStepButton.setDisable(false);
		}
		aRegField.setText(cpu.DEFAULT_VALUE);
		dRegField.setText(cpu.DEFAULT_VALUE);
		pcRegField.setText(cpu.DEFAULT_VALUE);
		loadInstruction(cpu);
		instructionTable.refresh();
		wasRead = true;
	}

	private void loadInstruction(CPU cpu) {
		ObservableList<MemoryLine> observableList = instructionTable.getItems();
		Iterator<String> iterator = cpu.instructionIterator();
		int i = 0;
		while (iterator.hasNext()) {
			String instruction = (String) iterator.next();
			if (instruction == null) break;
			observableList.get(i++).setWord(binaryFormat(instruction));
		}
		numberOfInstrLoaded = i;
	}
	
	private String binaryFormat(String string) {
		String b0to4 = string.substring(0,4);
		String b4to8 = string.substring(4,8);
		String b8to12 = string.substring(8,12);
		String b12to16 = string.substring(12,16);
		return String.format("%s %s %s %s", b0to4,b4to8,b8to12,b12to16);
	}

	private void clean(TableView<MemoryLine> memoryView) {
		ObservableList<MemoryLine> observableList = memoryView.getItems();
		for (MemoryLine memoryLine : observableList) {
			memoryLine.setWord("");
		}
	}

	@FXML
	private void executeOnAction() {
		int flag=-1;
		while (flag != 0) {
			flag = executeStep();
		}
	}
	
	@FXML
	private void oneStepOnAction() {
		executeStep();
 	}

	private int executeStep() {
		ObservableList<MemoryLine> instructionList = instructionTable.getItems();
		ObservableList<MemoryLine> dataList = dataTable.getItems();
		
		MemoryLine line = instructionList.get(Binary.parseBinaryToInt(programCPU.getPC()));
		
		if(programCPU.dataMemory.retrieve(programCPU.getA()) != null) {
			dataList.get(Binary.parseBinaryToInt(programCPU.getA())).setWord(programCPU.dataMemory.retrieve(programCPU.getA()));
		}
		//Quita la seleccion a la instruccion anterior [Sirva para pintar el background]
		
		MemoryLine.clearSelected();
		//Selecciona la instruccion que se ejecuto
		line.setSelected(true);
		aRegField.setText(binaryFormat(programCPU.getA()));
		try {
		dRegField.setText(binaryFormat(programCPU.getD())); 
		pcRegField.setText(binaryFormat(programCPU.getPC()));
		
		instructionTable.refresh();
		if (Binary.parseBinaryToInt(programCPU.getPC()) == numberOfInstrLoaded) {
			return 0; // se ejecuto sin problemas
		}
		
			programCPU.stepForward();
		} catch (Exception e) {
			System.out.print("Likely you haven't added datas");
		}
		return 1;
	}
	
}