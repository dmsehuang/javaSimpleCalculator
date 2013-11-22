package calculator;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class OperationButton extends CalculatorButton{
    String value;

    public OperationButton(CalculatorChip calculatorChip, String value) {  
        super(calculatorChip, new Color(173,255,47), new Color(255,215,0));
        super.setText(value); // set the value of the button
        this.value = value;
    }
    
    public String onClick(ActionEvent e){
        return "";
    }
}
