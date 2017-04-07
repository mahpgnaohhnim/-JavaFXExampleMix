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
 * Created by henry on 20.01.17.
 */
public class EasyWindow extends BorderPane{

    private Label centerText = new Label();
    Button btnSouthBig, btnNorthSmall, btnWestRed, btnEastGreen;

    public EasyWindow(){

        configCenterText();
        configSouthButton();
        configEastButton();
        configWestButton();
        configNorthButton();
        setCenter(centerText);
        setBottom(btnSouthBig);
        setRight(btnEastGreen);
        setLeft(btnWestRed);
        setTop(btnNorthSmall);

        configButtons();

    }

    private void configCenterText(){
        centerText.setText("EasyWindow");
        centerText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 22));
    }

    private void configSouthButton(){
        btnSouthBig = new Button("BIG");
        btnSouthBig.setStyle("-fx-font-size: 22; -fx-font-weight: bold");
    }

    private void configEastButton(){
        btnEastGreen = new Button("green");
        btnEastGreen.setStyle("-fx-text-fill: green");

    }

    private void configWestButton(){
        btnWestRed = new Button("red");
        btnWestRed.setStyle("-fx-text-fill: red");

    }

    private void configNorthButton(){
        btnNorthSmall = new Button("small");
        btnNorthSmall.setStyle("-fx-font-size: 12");
    }

    private void configButtons(){

        BorderPane.setAlignment(btnSouthBig, Pos.CENTER);
        BorderPane.setAlignment(btnEastGreen, Pos.CENTER);
        BorderPane.setAlignment(btnWestRed, Pos.CENTER);
        BorderPane.setAlignment(btnNorthSmall, Pos.CENTER);

        List<Button> btnList = new ArrayList<Button>();
        btnList.add(btnEastGreen);
        btnList.add(btnWestRed);
        btnList.add(btnSouthBig);
        btnList.add(btnNorthSmall);
        for(Button keyBtn:btnList){
            keyBtn.setOnAction(e -> onButton(keyBtn));
        }
    }

    public void onButton(Button keyBtn){
        centerText.setStyle(keyBtn.getStyle());
    }



}
