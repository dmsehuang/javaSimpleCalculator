package calculator;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Calculator extends JFrame{
    JTextField display;
    CalculatorChip calculatorChip;
    public static final String appName = "Calculator";
	public static final int DEFAULT_WIDTH = 550;
	public static final int DEFAULT_HEIGHT = 680;
	
	private static final int BUTTON_GAP = 5;
	

    /**
     * @param args
     */
    public static void main(String[] args) {
        new Calculator().createGui();
    }
    

    public void createGui(){
        CalculatorChip calculatorChip = new CalculatorChip();
        this.calculatorChip = calculatorChip;
        
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
        OperationButton buttonDiv = new OperationButton(calculatorChip, "\u00F7");
        OperationButton buttonPer = new OperationButton(calculatorChip, "%");
        OperationButton buttonInv = new OperationButton(calculatorChip, "1/x");
        OperationButton buttonSqrt = new OperationButton(calculatorChip, "\u221A");
        OperationButton buttonEql = new OperationButton(calculatorChip, "=");
        
        OperationButton buttonClear = new OperationButton(calculatorChip, "C");
        OperationButton buttonAllClear = new OperationButton(calculatorChip, "AC");
        
        MemoryButton buttonMC = new MemoryButton(calculatorChip, "MC");
        MemoryButton buttonMR = new MemoryButton(calculatorChip, "MR");
        MemoryButton buttonMAdd = new MemoryButton(calculatorChip, "M+");
        MemoryButton buttonMMinus = new MemoryButton(calculatorChip, "M-");
        
        JTextField display = new JTextField();
        display.setText("0");
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);
		display.setBackground(Color.WHITE);
		
        this.display = display;
        
        JPanel pMem = new JPanel();
        pMem.setLayout(new GridLayout(5,5, BUTTON_GAP, BUTTON_GAP)); 
        pMem.add(buttonMC);
        pMem.add(buttonMR);
        pMem.add(buttonMAdd);
        pMem.add(buttonMMinus);
        pMem.add(buttonSqrt);
        pMem.add(button7);
        pMem.add(button8);
        pMem.add(button9);
        pMem.add(buttonDiv);
        pMem.add(buttonPer);
        pMem.add(button4);
        pMem.add(button5);
        pMem.add(button6);
        pMem.add(buttonMul);
        pMem.add(buttonInv);
        pMem.add(button1);
        pMem.add(button2);
        pMem.add(button3);
        pMem.add(buttonSub);
        pMem.add(buttonEql);
        pMem.add(button0);
        pMem.add(buttonDot);
        pMem.add(buttonSign);
        pMem.add(buttonAdd);
       
        JPanel pNorth = new JPanel();
        pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));
        pNorth.add(display);
        
        JPanel pCenter = new JPanel();
        pCenter.setLayout(new  GridLayout(1,2, BUTTON_GAP, BUTTON_GAP)); 
      
        pCenter.add(buttonClear);
        pCenter.add(buttonAllClear);
       
        setLayout(new BorderLayout());
        add(pNorth, BorderLayout.NORTH);
        add(pCenter, BorderLayout.EAST);
        add(pMem, BorderLayout.SOUTH);
        

        pack();
        setVisible(true);
        
        
        // add number listener
        button1.addActionListener(new NumberButtonListener());
        button2.addActionListener(new NumberButtonListener());
        button3.addActionListener(new NumberButtonListener());
        button4.addActionListener(new NumberButtonListener());
        button5.addActionListener(new NumberButtonListener());
        button6.addActionListener(new NumberButtonListener());
        button7.addActionListener(new NumberButtonListener());
        button8.addActionListener(new NumberButtonListener());
        button9.addActionListener(new NumberButtonListener());
        button0.addActionListener(new NumberButtonListener());
        // add dot listener
        buttonDot.addActionListener(new DotListener());
        // add operation listener
        buttonSign.addActionListener(new OperationListener());
        buttonAdd.addActionListener(new OperationListener());
        buttonSub.addActionListener(new OperationListener());
        buttonMul.addActionListener(new OperationListener());
        buttonDiv.addActionListener(new OperationListener());
        buttonPer.addActionListener(new OperationListener());
        buttonInv.addActionListener(new OperationListener());
        buttonEql.addActionListener(new OperationListener());
        buttonSqrt.addActionListener(new OperationListener());
        // clear and all clear
        buttonClear.addActionListener(new OperationListener());
        buttonAllClear.addActionListener(new OperationListener());
        
        // add memory operation listener
        buttonMC.addActionListener(new MemoryListener());
        buttonMR.addActionListener(new MemoryListener());
        buttonMAdd.addActionListener(new MemoryListener());
        buttonMMinus.addActionListener(new MemoryListener());
    }
    
    class NumberButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String s = calculatorChip.digit(Integer.parseInt(e.getActionCommand()));
            display.setText(s);
        }
    }
    
    class DotListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String s = calculatorChip.decimalPoint();
            display.setText(s);
        }
    }
    
    class MemoryListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            String s = "";
            if(cmd.equals("MC")){
                s = calculatorChip.memClear();
            }else if(cmd.equals("MR")){
                s = calculatorChip.memRead();
            }else if(cmd.equals("M+")){
                s = calculatorChip.memPlus();
            }else if(cmd.equals("M-")){
                s = calculatorChip.memMinus();
            }
            display.setText(s);
        }
    }
    
    class OperationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            String s = "";
            if(cmd.equals("+")){
                s = calculatorChip.add();
            }else if(cmd.equals("-")){
                s = calculatorChip.subtract();
            }else if(cmd.equals("*")){
                s = calculatorChip.multiply();
            }else if(cmd.equals("\u00F7")){
                s = calculatorChip.divide();
            }else if(cmd.equals("\u221A")){
                s = calculatorChip.sqrt();
            }else if(cmd.equals("%")){
                s = calculatorChip.percent();
            }else if(cmd.equals("1/x")){
                s = calculatorChip.invert();
            }else if(cmd.equals("+/-")){
                s = calculatorChip.changeSign();
            }else if(cmd.equals("=")){
                s = calculatorChip.equals();
            }else if(cmd.equals("C")){
                s = calculatorChip.clear();
            }else if(cmd.equals("AC")){
                s = calculatorChip.allClear();
            }
            display.setText(s);
        }
    }
}
