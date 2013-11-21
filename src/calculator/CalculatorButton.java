package calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class CalculatorButton extends JButton{
    CalculatorChip calculatorChip;
    
    // constructor
    public CalculatorButton(CalculatorChip calculatorChip){
        this.calculatorChip = calculatorChip;
    }
    
    abstract String onClick(ActionEvent e);
}
