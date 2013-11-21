package calculator;
import java.awt.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator extends JFrame{
    JTextField cal;

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Calculator().createGui();
    }
    

    public void createGui(){
        CalculatorChip calculatorChip = new CalculatorChip();
        
        NumberButton button1 = new NumberButton(calculatorChip, "1");
        NumberButton button2 = new NumberButton(calculatorChip, "2");
        NumberButton button3 = new NumberButton(calculatorChip, "3");
        NumberButton button4 = new NumberButton(calculatorChip, "4");
        NumberButton button5 = new NumberButton(calculatorChip, "5");
        NumberButton button6 = new NumberButton(calculatorChip, "6");
        NumberButton button7 = new NumberButton(calculatorChip, "7");
        NumberButton button8 = new NumberButton(calculatorChip, "8");
        NumberButton button9 = new NumberButton(calculatorChip, "9");
        NumberButton button0 = new NumberButton(calculatorChip, "0");
        NumberButton buttonDot = new NumberButton(calculatorChip, ".");
        
        OperationButton buttonSign = new OperationButton(calculatorChip, "+/-");
        OperationButton buttonAdd = new OperationButton(calculatorChip, "+");
        OperationButton buttonSub = new OperationButton(calculatorChip, "-");
        OperationButton buttonMul = new OperationButton(calculatorChip, "*");
        OperationButton buttonDiv = new OperationButton(calculatorChip, "/");
        OperationButton buttonPer = new OperationButton(calculatorChip, "%");
        OperationButton buttonInv = new OperationButton(calculatorChip, "1/x");
        OperationButton buttonEql = new OperationButton(calculatorChip, "=");
        
        OperationButton buttonClear = new OperationButton(calculatorChip, "C");
        
        MemoryButton buttonMC = new MemoryButton(calculatorChip, "MC");
        MemoryButton buttonMR = new MemoryButton(calculatorChip, "MR");
        MemoryButton buttonMAdd = new MemoryButton(calculatorChip, "M+");
        MemoryButton buttonMMinus = new MemoryButton(calculatorChip, "M-");
        
        JTextField display = new JTextField(15);
        display.setText("0");
        
        JPanel pMem = new JPanel();
        pMem.setLayout(new BoxLayout(pMem, BoxLayout.X_AXIS));
        pMem.add(buttonMC);
        pMem.add(buttonMR);
        pMem.add(buttonMAdd);
        pMem.add(buttonMMinus);
        
        JPanel pNorth = new JPanel();
        pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
        pNorth.add(display);
        pNorth.add(buttonClear);
        pNorth.add(pMem);
        
        JPanel pWest = new JPanel();
        pWest.setLayout(new GridLayout(4,4)); 
        pWest.add(button7);
        pWest.add(button8);
        pWest.add(button9);
        pWest.add(button4);
        pWest.add(button5);
        pWest.add(button6);
        pWest.add(button1);
        pWest.add(button2);
        pWest.add(button3);
        pWest.add(button0);
        pWest.add(buttonDot);
        
        JPanel pOperation = new JPanel();
        pOperation.setLayout(new GridLayout(4,2));
        pOperation.add(buttonSign);
        pOperation.add(buttonAdd);
        pOperation.add(buttonSub);
        pOperation.add(buttonMul);
        pOperation.add(buttonDiv);
        pOperation.add(buttonPer);
        pOperation.add(buttonInv);
        pOperation.add(buttonEql);
        
        JPanel pEast = new JPanel();
        pEast.add(pOperation);
        
        setLayout(new BorderLayout());
        add(pNorth, BorderLayout.NORTH);
        add(pWest, BorderLayout.WEST);
        add(pEast, BorderLayout.EAST);

        pack();
        setVisible(true);
    }
    
    class NumberButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            cal.setText("hhuijing");
        }
    }
}

class MyOkButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
        System.out.println("click");
    }
}

