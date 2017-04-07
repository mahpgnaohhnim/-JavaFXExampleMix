package re.javafx.example.starter.azubi;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by henry on 14.03.17.
 */
public class Chess extends GridPane{

    public Chess(){
        this.setStyle("-fx-background-color: grey");

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Rectangle rect = new Rectangle(30,30);
                if((i+j)%2 == 0){
                    rect.setFill(Color.BLACK);
                }else{
                    rect.setFill(Color.WHITE);
                }
                this.add(rect, i,j);
            }
        }


    }

}
