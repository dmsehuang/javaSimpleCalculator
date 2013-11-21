package calculator;

import java.awt.event.ActionEvent;

public class NumberButton extends CalculatorButton{
    String value;

    public NumberButton(CalculatorChip calculatorChip, String value) {  
        super(calculatorChip);
        super.setText(value); // set the value of the button
        this.value = value;
    }
    
    public String onClick(ActionEvent e){
        return "";
    }
}
