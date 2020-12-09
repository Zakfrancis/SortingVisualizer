package javafxpack;
import java.util.concurrent.TimeUnit;

import algorithms.Sorting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class MainController {
	
	@FXML
	private HBox root;
	
	@FXML
	private Slider slider;
	
	@FXML
	private Text statusText;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
	private boolean statusFlag = false;
	private Sorting s;
	
	public MainController(){
		s=new Sorting();
	}
	
	public void initialize(){
		slider.valueProperty().addListener(new ChangeListener<Number>() {
		    @Override
		    public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
		    	
				s.resize((int)slider.getValue());
				updateList();
		        
		    }
		});
		updateList();
		
		choiceBox.getItems().addAll("Bubble Sort", "Insertion Sort", "Quick Sort");
		choiceBox.getSelectionModel().select(0);
	}
	
	public void randomize(ActionEvent event){
		s.randomize();
		updateList();
	}
	
	public void toggleStatusText() {
		if(statusFlag == false) {
			statusText.setText("Running");
			statusText.setFill(Color.LIMEGREEN);
			statusFlag = true;
		}else {
			statusText.setText("Stopped");
			statusText.setFill(Color.RED);
			statusFlag = false;
		}
	}
	
	public void bubbleSort(){
		toggleStatusText();
		s.allSort(this,0);
	}
	
	public void insertionSort() {
		toggleStatusText();
		s.allSort(this,1);
	}
	
	public void quickSort() {
		toggleStatusText();
		s.allSort(this, 2);
	}
	
	public void sortSwitch(ActionEvent event) {
		if("Bubble Sort".equals(choiceBox.getValue())) {
			bubbleSort();
		}
		else if("Insertion Sort".equals(choiceBox.getValue())) {
			insertionSort();
		}
		else if("Quick Sort".equals(choiceBox.getValue())) {
			quickSort();
		}
	}
	
	public void updateList(){
		double spacing = 100.0/(double)s.getList().size();
		root.getChildren().clear();
		root.setSpacing(spacing);
		for(int i=0;i<s.getList().size();i++){
			root.getChildren().add(new Rectangle((1/(double)s.getList().size()*root.getMaxWidth())-(2*spacing),((double)s.getList().get(i)/(double)s.getMaxNum())*root.getMaxHeight()));
		}
	}
}
