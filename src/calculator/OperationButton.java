package calculator;

public class OperationButton extends CalculatorButton{
    String value;

    public OperationButton(CalculatorChip calculatorChip, String value) {  
        super(calculatorChip);
        super.setText(value); // set the value of the button
        this.value = value;
    }
}
