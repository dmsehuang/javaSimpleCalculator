package calculator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CalculatorChipTest {
    CalculatorChip calculatorChip;

    @Before
    public void setUp() throws Exception {
        this.calculatorChip = new CalculatorChip();
    }

    @Test
    public void testClear() {
        this.calculatorChip.numberBuffer = "45";
        this.calculatorChip.clear();
        assertEquals("0", this.calculatorChip.numberBuffer);
    }

    @Test
    public void testAllClear() {
        this.calculatorChip.operandList.add(new Float(101.111));
        this.calculatorChip.operandList.add(new Float(1.23));
        this.calculatorChip.operator = "+";
        this.calculatorChip.allClear();
        assertEquals("0", this.calculatorChip.numberBuffer);
        assertEquals(null, this.calculatorChip.operator);
        assertEquals(true, this.calculatorChip.operandList.isEmpty());
    }

    @Test
    public void testMemClear() {
        this.calculatorChip.memory = (float)999.99;
        this.calculatorChip.memClear();
        assertEquals(0, this.calculatorChip.memory,0.00001);
    }

//    @Test
//    public void testMemRead() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMemPlus() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMemMinus() {
//        fail("Not yet implemented");
//    }
//
    @Test
    public void testDigit() {
        assertEquals("8", this.calculatorChip.digit(8));
        assertEquals("8", this.calculatorChip.numberBuffer);
        this.calculatorChip.clear();
        assertEquals("0", this.calculatorChip.numberBuffer);
        this.calculatorChip.digit(4);
        assertEquals("45", this.calculatorChip.digit(5));
        assertEquals("45", this.calculatorChip.numberBuffer);
    }

    @Test
    public void testDecimalPoint() {
        assertEquals("0.", this.calculatorChip.decimalPoint());
        assertEquals("0.1", this.calculatorChip.digit(1));
        this.calculatorChip.clear();
        assertEquals("1", this.calculatorChip.digit(1));
        assertEquals("12", this.calculatorChip.digit(2));
        assertEquals("12.", this.calculatorChip.decimalPoint());
        assertEquals("12.5", this.calculatorChip.digit(5));
    }

    @Test
    public void testPushIntoOperandList() {
        this.calculatorChip.pushIntoOperandList(168);
        assertEquals(1, this.calculatorChip.operandList.size());
        this.calculatorChip.pushIntoOperandList(11);
        assertEquals(2, this.calculatorChip.operandList.size());
        this.calculatorChip.pushIntoOperandList((float)3.14159);
        this.calculatorChip.pushIntoOperandList((float)5.6);
        // if more than two operands are pushed into the array list, remove the older operand
        assertEquals(2, this.calculatorChip.operandList.size());
        // check if the operands are the newest ones
        assertEquals(3.14159, this.calculatorChip.operandList.get(0).floatValue(), 0.000001);
        assertEquals(5.6, this.calculatorChip.operandList.get(1).floatValue(), 0.000001);
    }

    @Test
    public void testNewestOperand() {
        this.calculatorChip.pushIntoOperandList(168);
        this.calculatorChip.pushIntoOperandList(11);
        this.calculatorChip.pushIntoOperandList((float)3.14159);
        this.calculatorChip.pushIntoOperandList((float)1.2);
        assertEquals(1.2, this.calculatorChip.operandList.get(1).floatValue(), 0.000001);
    }

    @Test
    public void testAdd() {
        // if calculator operand list is empty initially
        this.calculatorChip.numberBuffer = "3.1415";
        assertEquals("3.1415", this.calculatorChip.add());
        assertEquals(3.1415, this.calculatorChip.newestOperand(), 0.000001);
        this.calculatorChip.numberBuffer = "5.0";
        assertEquals("5.0", this.calculatorChip.add());
        assertEquals(5, this.calculatorChip.newestOperand(), 0.000001);
        assertEquals(2, this.calculatorChip.operandList.size());
    }

//    @Test
//    public void testSubtract() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testMultiply() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testDivide() {
//        fail("Not yet implemented");
//    }

    @Test
    public void testEquals() {
        // 45 + 139 = 184.0
        this.calculatorChip.digit(4);
        this.calculatorChip.digit(5);
        this.calculatorChip.add();
        this.calculatorChip.digit(1);
        this.calculatorChip.digit(3);
        this.calculatorChip.digit(9);
        assertEquals("184.0", this.calculatorChip.equals());
        
        // 1.25 + 10. = 11.25
        this.calculatorChip.allClear();
        this.calculatorChip.digit(1);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(2);
        this.calculatorChip.digit(5);
        this.calculatorChip.add();
        this.calculatorChip.digit(1);
        this.calculatorChip.digit(0);
        this.calculatorChip.decimalPoint();
        assertEquals("11.25", this.calculatorChip.equals());
        
        // .5 + 21 = 21.5
        this.calculatorChip.allClear();
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(5);
        this.calculatorChip.add();
        this.calculatorChip.digit(2);
        this.calculatorChip.digit(1);
        this.calculatorChip.decimalPoint();
        assertEquals("21.5", this.calculatorChip.equals());
        
        // 5 += 10.0 = 15.0 = 20.0
        this.calculatorChip.allClear();
        this.calculatorChip.digit(5);
        this.calculatorChip.add();
        assertEquals("10.0", this.calculatorChip.equals());
        assertEquals("15.0", this.calculatorChip.equals());
        assertEquals("20.0", this.calculatorChip.equals());
    }
//
//    @Test
//    public void testSqrt() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testPercent() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testInvert() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testChangeSign() {
//        fail("Not yet implemented");
//    }

}
