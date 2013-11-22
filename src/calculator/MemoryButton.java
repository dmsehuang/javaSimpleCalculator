package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class MemoryButton extends CalculatorButton{
    String value;

    public MemoryButton(CalculatorChip calculatorChip, String value) {
        
        super(calculatorChip, new Color(255,255,0), new Color(255,215,0));
        super.setText(value); // set the value of the button
        this.value = value;
    }

    public String onClick(ActionEvent e){
        return "";
    }
}
