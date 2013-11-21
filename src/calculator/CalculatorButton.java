package calculator;

import javax.swing.*;

public abstract class CalculatorButton extends JButton{
    CalculatorChip calculatorChip;
    
    // constructor
    public CalculatorButton(CalculatorChip calculatorChip){
        this.calculatorChip = calculatorChip;
    }
}
