package re.javafx.example.CopyWindow;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by henry on 10.03.17.
 */
public class CopyWindow extends GridPane{

    private TextField copyInputField;
    private Label copiedTextLabel;
    private Button copyBtn;

    public CopyWindow(){
        initCopyWindow();
    }

    private void initCopyWindow(){
        this.getStylesheets().add("CopyWindowStyles.css");
        copyInputField = new TextField();
        copiedTextLabel = new Label();
        copyBtn = new Button("Copy!");
        GridPane.setHalignment(copyInputField, HPos.LEFT);
        GridPane.setHalignment(copiedTextLabel, HPos.LEFT);
        GridPane.setHalignment(copyBtn, HPos.RIGHT);
        this.add(copyInputField, 0, 0);
        this.add(copiedTextLabel, 0, 1);
        this.add(copyBtn, 1, 0);
        this.setRowSpan(copyBtn,2);

        copiedTextLabel.setId("copiedTextLabel");
        copyInputField.setId("copyInputField");
        copyBtn.setId("copyBtn");


        copyBtn.setOnAction(event -> onCopy());
    }

    private void onCopy(){
        copiedTextLabel.setText(copyInputField.getText());
    }

}
