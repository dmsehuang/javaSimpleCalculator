package calculator;

import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;

public class CalculatorChip {
    float memory; 
    String numberBuffer;
    boolean isResult; // if the display is a result, can't change it
    ArrayList<Float> operandList; // at most two operands
    String operator;
    
    /* constructor for calculator chip */
    public CalculatorChip(){
        this.memory = 0;
        this.numberBuffer = "0";
        this.isResult = false;
        this.operandList = new ArrayList<Float>();
        this.operator = null;
    }
    
    /* clear method */
    public String clear(){
        // clear the number buffer
        this.numberBuffer = "0";
        return "0";
    }
    
    /* All clear method */
    public String allClear(){
        // clear the number buffer, operand list and operator
        this.numberBuffer = "0";
        this.operandList.clear();
        this.operator = null;
        return "0";
    }
    
    /* clear the memory */
    public String memClear(){
        this.memory = 0;
        return "0";
    }
    
    /* read data from the memory and display it */
    public String memRead(){
        String s = new Float(this.memory).toString();
        this.numberBuffer = s;
        this.isResult = true; // read from memory, can't change its value
        return this.numberBuffer;
    } 
    
    /* memory add number and displays it */
    public String memPlus(){
        this.memory = this.memory + Float.parseFloat(this.numberBuffer);
        this.numberBuffer = new Float(this.memory).toString();
        this.isResult = true; // read from memory, can't change its value
        return this.numberBuffer;
    } 
    
    /* memory subtract number and displays it */
    public String memMinus(){
        this.memory = this.memory - Float.parseFloat(this.numberBuffer);
        this.numberBuffer = new Float(this.memory).toString();
        this.isResult = true; // read from memory, can't change its value
        return this.numberBuffer;
    }

    /* return a digit */
    public String digit(int digit){
        if(this.isResult){
            // clear the result
            this.numberBuffer = Integer.toString(digit);
            this.isResult = false;
        }else{
            // not a result, append value to end, take care "0" case
            if(this.numberBuffer == null || this.numberBuffer.equals("0")){
                // initial number buffer should be removed
                this.numberBuffer = "";
            }
            this.numberBuffer += Integer.toString(digit);
        }
        return this.numberBuffer;
    }
    
    /* return a decimal point */
    public String decimalPoint(){
        if(this.isResult){
            this.numberBuffer = ".";
            this.isResult = false;
        }else{
            this.numberBuffer += ".";
        }
        return this.numberBuffer;
    }
    
    
    /* helper method 1 -- flush the number buffer and push value into the operand list */
    public void pushIntoOperandList(float operand){
        this.operandList.add(new Float(operand));
        if(this.operandList.size() > 2){
            this.operandList.remove(0); // remove the oldest operand if size is larger than 2
        }
    }
    
    /* helper method 2 -- return the newest operand */
    public float newestOperand(){
        if(this.operandList.isEmpty()){
            throw new RuntimeException();
        }
        // if operand list is not empty, return the last element in the list
        return this.operandList.get(this.operandList.size() - 1).floatValue();
    }
    
    /* when user press '+', display won't change while the operand/operator array list changes */
    public String add(){
        float operand = Float.parseFloat(this.numberBuffer);
        this.pushIntoOperandList(operand);
        this.operator = "+";
        this.isResult = true;
        return new Float(operand).toString();
    } 
    
    /* when user press '-', display won't change while the operand/operator array list changes */
    public String subtract(){
        float operand = Float.parseFloat(this.numberBuffer);
        this.pushIntoOperandList(operand);
        this.operator = "-";
        this.isResult = true;
        return new Float(operand).toString();
    }
    
    /* when user press '*', display won't change while the operand/operator array list changes */
    public String multiply(){
        float operand = Float.parseFloat(this.numberBuffer);
        this.pushIntoOperandList(operand);
        this.operator = "*";
        this.isResult = true;
        return new Float(operand).toString(); 
    } 
    
    /* when user press '/', display won't change while the operand/operator array list changes */
    public String divide(){
        float operand = Float.parseFloat(this.numberBuffer);
        this.pushIntoOperandList(operand);
        this.operator = "/";
        this.isResult = true;
        return new Float(operand).toString();
    } 
    
    /* when user press '=' */
    public String equals(){
        if(operator == null){
            return this.numberBuffer;
        }else{
            this.pushIntoOperandList(Float.parseFloat(this.numberBuffer));
            
            // both operand size and operator are not null
            float cal = 0;
            float operand1 = 0;
            float operand2 = 0;
            if(this.operandList.size() == 1){
                operand1 = this.newestOperand();
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
            this.numberBuffer = Float.toString(cal);
            this.isResult = true;
            return this.numberBuffer;
        }
    }
    
    /* when user press 'sqrt' */
    public String sqrt(){
        float operand = this.newestOperand();
        float cal = (float)Math.sqrt((double)operand);
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    } 
    
    /* when user press '%' */
    public String percent(){
        float operand = this.newestOperand();
        float cal = operand/100;
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    } 
    
    /* when user press '1/x' */
    public String invert(){
        float operand = this.newestOperand();
        if(operand == 0){
            return "Error";
        }
        float cal = 1/operand;
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    } 
    
    /* when user press '+/-' */
    public String changeSign(){
        float operand = this.newestOperand();
        float cal = -1 * operand;
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    }
    
}
