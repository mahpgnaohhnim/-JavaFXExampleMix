package re.javafx.example.ToDoList;

import javafx.application.Platform;
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
import javafx.stage.FileChooser;
import javafx.stage.Window;
import re.javafx.example.starter.azubi.FxExampleStarter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by henry on 13.03.17.
 */
public class ToDoListDX extends VBox{

    private HBox hBox;
    private Button addToDoBtn, removeToDoBtn, saveToDoListBtn, loadToDoListBtn;
    private TextField toDoInputField;
    private ListView<HBox> toDoItemList;
    private ObservableList<HBox> observableToDoList;

    public ToDoListDX(){
        addToDoBtn = new Button("Add");
        removeToDoBtn = new Button("Remove");
        saveToDoListBtn = new Button("Save");
        loadToDoListBtn = new Button("Load");
        toDoInputField = new TextField();
        toDoItemList = new ListView<>();
        observableToDoList = FXCollections.observableArrayList();
        hBox = new HBox(toDoInputField,addToDoBtn,removeToDoBtn, saveToDoListBtn, loadToDoListBtn);
        toDoItemList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        hBox.setId("HBox");
        addToDoBtn.setId("addToDoBtn");
        removeToDoBtn.setId("removeToDoBtn");
        toDoInputField.setId("toDoInputField");

        addToDoBtn.setOnAction(e -> onAdd());
        addToDoBtn.disableProperty().bind(Bindings.isEmpty(toDoInputField.textProperty()));

        removeToDoBtn.setOnAction(e -> onRemove());
        removeToDoBtn.disableProperty().bind(Bindings.isEmpty(observableToDoList));

        saveToDoListBtn.setOnAction(e -> onSaveList());
        loadToDoListBtn.setOnAction(e -> onLoadList());

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

            URL selectedImageURL = getLoadImageURL();
            observableToDoList.add(createNiceEntryByText(toDoInputText, selectedImageURL));
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

    private HBox createNiceEntryByText(String text, URL imageURL){


        ImageView toDoItemIcon;

        if(imageURL != null){
            toDoItemIcon = new ImageView(new Image(imageURL.toExternalForm()));
        }else{
            toDoItemIcon = new ImageView(new Image("task_icon.png"));
        }
        toDoItemIcon.setFitHeight(30);
        toDoItemIcon.setFitWidth(30);


        Label toDoListItemLabel = new Label();
        toDoListItemLabel.setText(text);
        toDoListItemLabel.setPadding(new Insets(8, 0, 0, 0));

        HBox itemHBox = new HBox(toDoItemIcon, toDoListItemLabel);

        return itemHBox;
    }

    private void onKeyConfig(KeyCode keyCode){
        if(keyCode == KeyCode.DELETE){
            onRemove();
        }

        if(keyCode == KeyCode.ENTER){
            onAdd();
        }
    }

    private void  onSaveList(){
        FileChooser fileChooser = new FileChooser();
        File toDoListFile = fileChooser.showSaveDialog(this.getScene().getWindow());

        if(toDoListFile != null){
            try{
                BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(toDoListFile));
                for(HBox hBoxItem: toDoItemList.getItems()){
                    final Label itemLabel = (Label) hBoxItem.getChildren().get(1);
                    final ImageView imgView = (ImageView) hBoxItem.getChildren().get(0);
                    String imgURL = imgView.getImage().impl_getUrl();
                    String dataToWrite = imgURL + ";" + itemLabel.getText() + "\n";
                    outStream.write(dataToWrite.getBytes());
                }
                outStream.close();
            }catch(IOException e){
                System.err.println(e.getMessage());
            }
        }

    }

    private void onLoadList(){
        FileChooser fileChooser = new FileChooser();
        File toDoListFile = fileChooser.showOpenDialog(this.getScene().getWindow());


        if(toDoListFile != null){
            try{
                BufferedReader inputStream = new BufferedReader(new FileReader(toDoListFile));
                String readData;
                observableToDoList.removeAll(observableToDoList);
                while((readData = inputStream.readLine()) != null){
                    URL imgURL = new URL(readData.split(";")[0]);
                    String toDoText = readData.split(";")[1];
                    observableToDoList.add(createNiceEntryByText(toDoText, imgURL));
                }

            }catch(IOException e){
                System.err.println(e.getMessage());
            }

            updateToDoList();
        }
    }

    private URL getLoadImageURL(){

        URL selectedImageURL = null;
        File selectedImageFile = null;


        if(showMsgBox("Bild Auswählen?", "Hinweis", 2) == 1){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            selectedImageFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        }




        if(selectedImageFile != null){
            try{
                selectedImageURL = selectedImageFile.toURI().toURL();

            }catch(MalformedURLException e){
                System.err.print(e.getMessage());
            }
        }
        return selectedImageURL;
    }

    private int showMsgBox(String strMessage, String strTitle, int iOptions){
        Alert alert = null;

        if(iOptions == 2){
            alert = new Alert(Alert.AlertType.INFORMATION, strMessage, ButtonType.OK, ButtonType.CANCEL);
        }else{
            alert = new Alert(Alert.AlertType.INFORMATION, strMessage);
        }

        if(strTitle.equals("")){
            strTitle = "Hinweis";
        }

        alert.setHeaderText("");
        alert.setTitle(strTitle);

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            return 1;
        }else {
            return 0;
        }
    }





}
