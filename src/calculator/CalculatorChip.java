package calculator;

import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;

public class CalculatorChip {
    float memory; 
    String numberBuffer;
    boolean isResult; // if the display is a result, can't change it
    boolean afterEql; // if the display is after a equal key is pressed
    float operand1;
    float operand2;
    String operator;
    
    /* constructor for calculator chip */
    public CalculatorChip(){
        this.memory = 0;
        this.numberBuffer = "0";
        this.isResult = false;
        this.afterEql = false;
        this.operand1 = 0;
        this.operand2 = 0;
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
        this.operand1 = 0;
        this.operand2 = 0;
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
    
    /* when user press '+', display won't change while the operand/operator array list changes */
    public String add(){
        if(this.operator != null && !this.afterEql){
            // if previous calculation is not executed, execute here
            this.numberBuffer = this.equals();
            this.afterEql = false; // the equal key is not actually pressed
        }
        float operand = Float.parseFloat(this.numberBuffer);
        this.operand1 = operand;
        this.operator = "+";
        this.isResult = true;
        return new Float(operand).toString();
    } 
    
    /* when user press '-', display won't change while the operand/operator array list changes */
    public String subtract(){
        if(this.operator != null && !this.afterEql){
            // if previous calculation is not executed, execute here
            this.numberBuffer = this.equals();
            this.afterEql = false; // the equal key is not actually pressed
        }
        float operand = Float.parseFloat(this.numberBuffer);
        this.operand1 = operand;
        this.operator = "-";
        this.isResult = true;
        return new Float(operand).toString();
    }
    
    /* when user press '*', display won't change while the operand/operator array list changes */
    public String multiply(){
        if(this.operator != null && !this.afterEql){
            // if previous calculation is not executed, execute here
            this.numberBuffer = this.equals();
            this.afterEql = false; // the equal key is not actually pressed
        }
        float operand = Float.parseFloat(this.numberBuffer);
        this.operand1 = operand;
        this.operator = "*";
        this.isResult = true;
        return new Float(operand).toString();
    } 
    
    /* when user press '/', display won't change while the operand/operator array list changes */
    public String divide(){
        if(this.operator != null && !this.afterEql){
            // if previous calculation is not executed, execute here
            this.numberBuffer = this.equals();
            this.afterEql = false; // the equal key is not actually pressed
        }
        float operand = Float.parseFloat(this.numberBuffer);
        this.operand1 = operand;
        this.operator = "\u00F7";
        this.isResult = true;
        return new Float(operand).toString();
    } 
    
    /* when user press '=' */
    public String equals(){
        if(operator == null){
            return this.numberBuffer;
        }else{
            float operand = Float.parseFloat(this.numberBuffer);
            this.operand2 = operand;
            float cal = 0;
 
            // +, -, *, / requires two operands
            if(operator.equals("+")){
                cal = this.operand1 + this.operand2;
            }
            if(operator.equals("+")){
                cal = this.operand1 + this.operand2;
            }
            if(operator.equals("-")){
                cal = this.operand1 - this.operand2;
            }
            if(operator.equals("*")){
                cal = this.operand1 * this.operand2;
            }
            if(operator.equals("\u00F7")){
                if(this.operand2 == 0){
                    return "Error";
                }
                cal = this.operand1 / this.operand2;
            }
            this.numberBuffer = Float.toString(cal);
            this.isResult = true;
            this.afterEql = true;
            return Float.toString(cal);
        }
    }
    
    /* when user press 'sqrt' */
    public String sqrt(){
        float operand = Float.parseFloat(this.numberBuffer);
        float cal = (float)Math.sqrt((double)operand);
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    } 
    
    /* when user press '%' */
    public String percent(){
        float operand = Float.parseFloat(this.numberBuffer);
        float cal = operand/100;
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    } 
    
    /* when user press '1/x' */
    public String invert(){
        float operand = Float.parseFloat(this.numberBuffer);
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
        float operand = Float.parseFloat(this.numberBuffer);
        float cal = -1 * operand;
        this.numberBuffer = Float.toString(cal);
        this.isResult = true;
        return this.numberBuffer;
    }
    
}
