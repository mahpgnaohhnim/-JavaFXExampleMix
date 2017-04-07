package re.javafx.example.ToDoList;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by henry on 12.03.17.
 */
public class ToDoListEx extends VBox{
    private HBox hBox;
    private Button addToDoBtn, removeToDoBtn;
    private TextField toDoInputField;
    private ListView<HBox> toDoItemList;
    private ObservableList<HBox> observableToDoList;

    public ToDoListEx(){
        addToDoBtn = new Button("Add");
        removeToDoBtn = new Button("Remove");
        toDoInputField = new TextField();
        toDoItemList = new ListView<>();
        observableToDoList = FXCollections.observableArrayList();
        hBox = new HBox(toDoInputField,addToDoBtn,removeToDoBtn);
        toDoItemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        hBox.setId("HBox");
        addToDoBtn.setId("addToDoBtn");
        removeToDoBtn.setId("removeToDoBtn");
        toDoInputField.setId("toDoInputField");

        addToDoBtn.setOnAction(e -> onAdd());
        addToDoBtn.disableProperty().bind(Bindings.isEmpty(toDoInputField.textProperty()));

        removeToDoBtn.setOnAction(e -> onRemove());
        removeToDoBtn.disableProperty().bind(Bindings.isEmpty(observableToDoList));

        this.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                KeyCode keyPressed = event.getCode();
                onKeyConfig(keyPressed);
            }
        });

        this.getChildren().add(toDoItemList);
        this.getChildren().add(hBox);
    }

    private void onAdd(){
        String toDoInputText = toDoInputField.getText();
        if(!toDoInputText.equals("")){

            observableToDoList.add(createNiceEntryByText(toDoInputText));
            updateToDoList();
            toDoInputField.setText("");
        }
    }

    private void onRemove(){
        if(observableToDoList.size() > 0){
            ObservableList <HBox> selectedItem = toDoItemList.getSelectionModel().getSelectedItems();
            if(selectedItem.size() > 0){
                observableToDoList.removeAll(selectedItem);
                updateToDoList();
            }
        }
    }

    private void updateToDoList(){
        toDoItemList.setItems(observableToDoList);
    }

    private HBox createNiceEntryByText(String text){
        ImageView toDoItemIcon;
        toDoItemIcon = new ImageView(new Image("task_icon.png"));

        Label toDoListItemLabel = new Label();
        toDoListItemLabel.setText(text);
        toDoListItemLabel.setPadding(new Insets(8, 0, 0, 0));

        HBox itemHBox = new HBox(toDoItemIcon, toDoListItemLabel);

        return itemHBox;
    }

    public void onKeyConfig(KeyCode keyCode){
        if(keyCode == KeyCode.DELETE){
            onRemove();
        }

        if(keyCode == KeyCode.ENTER){
            onAdd();
        }
    }

}
