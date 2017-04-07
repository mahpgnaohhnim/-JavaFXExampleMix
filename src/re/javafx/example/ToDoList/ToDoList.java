package re.javafx.example.ToDoList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Created by henry on 11.03.17.
 */
public class ToDoList extends VBox{
    private HBox hBox;
    private Button addToDoBtn, removeToDoBtn;
    private TextField toDoInputField;
    private ListView<String> toDoItemList;
    private ObservableList<String> observableToDoList;

    public ToDoList(){
        addToDoBtn = new Button("Add");
        removeToDoBtn = new Button("Remove");
        toDoInputField = new TextField();
        toDoItemList = new ListView<>();
        observableToDoList = FXCollections.observableArrayList();
        hBox = new HBox(toDoInputField,addToDoBtn,removeToDoBtn);

        hBox.setId("HBox");
        addToDoBtn.setId("addToDoBtn");
        removeToDoBtn.setId("removeToDoBtn");
        toDoInputField.setId("toDoInputField");

        addToDoBtn.setOnAction(e -> onAddBtn());
        removeToDoBtn.setOnAction(e -> onRemoveBtn());

        this.getChildren().add(toDoItemList);
        this.getChildren().add(hBox);
    }

    private void onAddBtn(){
        String itemToAdd = toDoInputField.getText();
        if(!itemToAdd.equals("")){
            observableToDoList.add(itemToAdd);
            updateToDoList();
            toDoInputField.setText("");
        }
    }

    private void onRemoveBtn(){
        if(observableToDoList.size() > 0){
            int selectedItem = toDoItemList.getSelectionModel().getSelectedIndex();
            if(selectedItem != -1){
                observableToDoList.remove(selectedItem);
                updateToDoList();
            }
        }
    }

    private void updateToDoList(){
        toDoItemList.setItems(observableToDoList);
    }
}
