package breakout;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Score extends Text {

    private int value;

    public Score() {
        value = 0;
        setFont(new Font("Verdana", 24));
        updateDisplay();
    }

    public void updateDisplay() {
        setText("Score: " + String.valueOf(value));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        updateDisplay();
    }
}