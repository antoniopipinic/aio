package helper.components;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class WarningLabel extends Label {
    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), this);
    private final FadeTransition ftIn = new FadeTransition(Duration.millis(200), this);

    public WarningLabel() {
        super();
        super.setVisible(false);
        super.setAlignment(Pos.CENTER);
        super.setTextFill(Color.rgb(255,0,0));
    }

    public WarningLabel(String s) {
        super(s);
        super.setVisible(false);
        super.setAlignment(Pos.CENTER);
        super.setTextFill(Color.rgb(255,0,0));
    }


    public void setIdleState(){
        super.setVisible(false);
    }

    public void showError(){
        super.setVisible(true);
        ftIn.setFromValue(0);
        ftIn.setToValue(1);
        scaleTransition.setByX(0.4);
        scaleTransition.setByY(0.4);
        scaleTransition.setInterpolator(Interpolator.EASE_BOTH);
        //Let animation run 2 times
        scaleTransition.setCycleCount(4);
        // Reverse direction
        scaleTransition.setAutoReverse(true);
        // Play the Animation
        ftIn.play();
        scaleTransition.play();
    }
}
