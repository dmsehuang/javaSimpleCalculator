package calculator;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public abstract class CalculatorButton extends JButton implements MouseListener{
    CalculatorChip calculatorChip;
    public static final int DEFAULT_WIDTH = 60;
	public static final int DEFAULT_HEIGHT = 60;
	private Color defaultColor;
	private Color mouseOverColor;
	
    
    // constructor
    public CalculatorButton(CalculatorChip calculatorChip, Color defaultColor, Color mouseOverColor){
        this.calculatorChip = calculatorChip;
      
		setOpaque(true);
		setPreferredSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		setBackground(defaultColor);
		 
		this.defaultColor = defaultColor;
		 
		this.mouseOverColor = mouseOverColor;
		 
		addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) { }
    
    public void mousePressed(MouseEvent e) { }
     
    public void mouseReleased(MouseEvent e) { }
     
     
     
    public void mouseEntered(MouseEvent e) { 
     
    if(e.getSource()==this) {  this.setBackground(this.mouseOverColor); }
     
    }
     
     
    public void mouseExited(MouseEvent e) { 
     
    if(e.getSource()==this) { this.setBackground(this.defaultColor); }
    }
    abstract String onClick(ActionEvent e);
    
}
