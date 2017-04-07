package re.javafx.example.EasyWindow;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by henry on 10.02.17.
 */
public class EasyWindowEx extends BorderPane{

    private Label centerText = new Label();
    private Button btnSouthBig, btnNorthSmall, btnWestRed, btnEastGreen;

    public EasyWindowEx(){

        initElements();
        configButtons();

    }

    private void initElements(){
        btnSouthBig = new Button("BIG");
        btnEastGreen = new Button("green");
        btnWestRed = new Button("red");
        btnNorthSmall = new Button("small");

        BorderPane.setAlignment(btnSouthBig, Pos.CENTER);
        BorderPane.setAlignment(btnEastGreen, Pos.CENTER);
        BorderPane.setAlignment(btnWestRed, Pos.CENTER);
        BorderPane.setAlignment(btnNorthSmall, Pos.CENTER);

        setCenter(centerText);
        setBottom(btnSouthBig);
        setRight(btnEastGreen);
        setLeft(btnWestRed);
        setTop(btnNorthSmall);
        centerText.setText("EasyWindow");
        centerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));

        btnSouthBig.getStyleClass().add("big");
        btnNorthSmall.getStyleClass().add("small");
        btnWestRed.getStyleClass().add("red");
        btnEastGreen.getStyleClass().add("green");
    }




    private void configButtons(){

        List<Button> btnList = new ArrayList<Button>();
        btnList.add(btnEastGreen);
        btnList.add(btnWestRed);
        btnList.add(btnSouthBig);
        btnList.add(btnNorthSmall);
        for(Button keyBtn:btnList){
            keyBtn.setOnAction(event -> onButton(keyBtn));
        }
    }

    private void onButton(Button keyBtn){
        List<String> textStyleClassNames = centerText.getStyleClass();
        centerText.getStyleClass().removeAll(textStyleClassNames);
        List<String> styleClassNames= keyBtn.getStyleClass();
        for(String className:styleClassNames){
            if(className != "button"){
                centerText.getStyleClass().add(className);
            }
        }
    }


}
