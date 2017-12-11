package interfaz;

import java.io.File;
import java.util.Iterator;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InterfaceController {
	@FXML
	private TextField aRegField;
	@FXML
	private TextField dRegField;
	@FXML
	private TextField cpRegField;
	@FXML
	private 	TableView<MemoryLine> instructionTable;
	@FXML
	private 	TableView<MemoryLine> dataTable;
	private Stage ownerWindows;
	
	ObservableList<MemoryLine> observableValue = FXCollections.observableArrayList(	
			new MemoryLine("1", "1110 1012 1002 1110"),
			new MemoryLine("2", "1110 1012 1002 1120"),
			new MemoryLine("3", "1110 1012 1002 1110"),
			new MemoryLine("4", "1110 1012 1002 1110")
			);

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		TableColumn column1 = instructionTable.getColumns().get(0);
		column1.setCellValueFactory(new PropertyValueFactory("address"));
		TableColumn column2 = instructionTable.getColumns().get(1);
		column2.setCellValueFactory(new PropertyValueFactory("instruction"));
		setCellFactoryTableView(column1);
		setCellFactoryTableView(column2);
		instructionTable.setItems(observableValue);
		instructionTable.setFocusModel(null);
		observableValue.get(0).changeState();
		
	}
	
	private void setCellFactoryTableView(TableColumn column1) {
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

	public void setOwner(Stage stage) {
		this.ownerWindows = stage;
	}
	
	@FXML
	private void loadCodeOnAction() {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(ownerWindows);
		
		
	}
	
	@FXML
	private void executeOnAction() {
		
	}
	
	@FXML
	private void oneStepOnAction() {
		observableValue.get(2).changeState();
		instructionTable.refresh();
		
	}
}