package re.javafx.example.BallBounce;

import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * Created by henry on 15.03.17.
 */
public class BallBounce extends StackPane{
    Circle flyingCircle;
    Timeline timeline;

    public BallBounce(){

        flyingCircle = new Circle(8);

    }

}
