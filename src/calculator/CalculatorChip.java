package calculator;

import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;;

public class CalculatorChip {
    // reference to the controller's text field
    JTextField display;
    // memory used by memory operation
    float memory; 
    // two operand registers
    ArrayList<Float> operandList;
    // one operator registers
    String operator;
    
    /* constructor for calculator chip */
    public CalculatorChip(){
        this.display = null;
        this.memory = 0;
        this.operandList = new ArrayList<Float>();
        this.operator = null;
    }
    
    /* clear all the numbers */
    public String clear(){
        return "";
    }
    
    /* clear all the memory */
    public String memClear(){
        this.memory = 0;
        return "";
    } 
    
    /* read data from the memory and display it */
    public String memRead(){
        this.memory = Float.parseFloat(this.display.getText());
        return display.getText();
    } 
    
    /* memory add number and displays it */
    public String memPlus(){
        this.memory = this.memory + Float.parseFloat(this.display.getText());
        return Float.toString(this.memory);
    } 
    
    /* memory subtract number and displays it */
    public String memMinus(){
        this.memory = this.memory - Float.parseFloat(this.display.getText());
        return Float.toString(this.memory);
    }

    /* return a digit */
    public String digit(int digit){
        return Integer.toString(digit);
    }
    
    /* return a decimal point */
    public String decimalPoint(){
        return ".";
    }
    
    /* change the operand and operator array list when needed */
    public void updateOperandRegister(float operand){
        if(this.operandList.size() >= 2){
            this.operandList.remove(0); // remove the first element
        }
        this.operandList.add(new Float(operand)); // put the operand into the array list
    }
    
    /* when user press '+', display won't change while the operand/operator array list changes */
    public String add(){
        float operand = Float.valueOf(this.display.getText()); 
        this.updateOperandRegister(operand);
        operator = "+";
        return this.display.getText();
    } 
    
    /* when user press '-', display won't change while the operand/operator array list changes */
    public String subtract(){
        float operand = Float.valueOf(this.display.getText()); 
        this.updateOperandRegister(operand);
        operator = "-";
        return this.display.getText();
    }
    
    /* when user press '*', display won't change while the operand/operator array list changes */
    public String multiply(){
        float operand = Float.valueOf(this.display.getText()); 
        this.updateOperandRegister(operand);
        operator = "*";
        return this.display.getText(); 
    } 
    
    /* when user press '/', display won't change while the operand/operator array list changes */
    public String divide(){
        float operand = Float.valueOf(this.display.getText()); 
        this.updateOperandRegister(operand);
        operator = "/";
        return this.display.getText();
    } 
    
    /* when user press '=' */
    public String equals(){
        if(this.operandList.size() == 0){
            return "0";
        }
        if(operator == null){
            // return the last element in the operand
            return this.operandList.get(this.operandList.size() - 1).toString();
        }
        else{
            // both operand size and operator are not null
            float cal = 0;
            float operand1 = 0;
            float operand2 = 0;
            if(this.operandList.size() == 1){
                operand1 = this.operandList.get(0).floatValue();
                operand2 = operand1;
            }else{
                operand1 = this.operandList.get(0).floatValue();
                operand2 = this.operandList.get(1).floatValue();
            }
            // +, -, *, / requires two operands
            if(operator.equals("+")){
                cal = operand1 + operand2;
            }
            if(operator.equals("+")){
                cal = operand1 + operand2;
            }
            if(operator.equals("-")){
                cal = operand1 - operand2;
            }
            if(operator.equals("*")){
                cal = operand1 * operand2;
            }
            if(operator.equals("/")){
                if(operand2 == 0){
                    return "Error";
                }
                cal = operand1 / operand2;
            }
            // sqrt requires one operand, here the last one: operand2
            if(operator.equals("sqrt")){
                return this.sqrt();
            }
            this.updateOperandRegister(cal);
            return new Float(cal).toString();
        }
    }
    
    /* when user press 'sqrt' */
    public String sqrt(){
        float operand = this.operandList.get(this.operandList.size() - 1);
        float cal = (float)Math.sqrt((double)operand);
        this.updateOperandRegister(cal);
        return new Float(cal).toString();
    } 
    
    /* when user press '%' */
    public String percent(){
        float operand = this.operandList.get(this.operandList.size() - 1);
        float cal = operand/100;
        this.updateOperandRegister(cal);
        return new Float(cal).toString();
    } 
    
    /* when user press '1/x' */
    public String invert(){
        float operand = this.operandList.get(this.operandList.size() - 1);
        if(operand == 0){
            return "Error";
        }
        float cal = 1/operand;
        this.updateOperandRegister(cal);
        return new Float(cal).toString();
    } 
    
    /* when user press '+/-' */
    public String changeSign(){
        float operand = this.operandList.get(this.operandList.size() - 1);
        float cal = -1 * operand;
        this.updateOperandRegister(cal);
        return new Float(cal).toString();
    }
    
}
