package calculator;

public class MemoryButton extends CalculatorButton{
    String value;

    public MemoryButton(CalculatorChip calculatorChip, String value) {
        
        super(calculatorChip);
        super.setText(value); // set the value of the button
        this.value = value;
    }
}
