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
        
        // 6 + 3, clear, 2 should return 8
        this.calculatorChip.digit(6);
        this.calculatorChip.add();
        this.calculatorChip.digit(3);
        this.calculatorChip.clear();
        this.calculatorChip.digit(2);
        assertEquals("8.0", this.calculatorChip.equals());
    }

    @Test
    public void testAllClear() {
        this.calculatorChip.operand1 = (float)101.111;
        this.calculatorChip.operand2 = (float)1.23;
        this.calculatorChip.operator = "+";
        this.calculatorChip.allClear();
        assertEquals("0", this.calculatorChip.numberBuffer);
        assertEquals(null, this.calculatorChip.operator);
        assertEquals(0, this.calculatorChip.operand1, 0.00000001);
        assertEquals(0, this.calculatorChip.operand2, 0.00000001);
        
        // 6 + 3, all clear, 2 should return 2
        this.calculatorChip.digit(6);
        this.calculatorChip.add();
        this.calculatorChip.digit(3);
        this.calculatorChip.allClear();
        this.calculatorChip.digit(2);
        assertEquals("2", this.calculatorChip.equals());
    }

    @Test
    public void testMemClear() {
        this.calculatorChip.memory = (float)999.99;
        this.calculatorChip.memClear();
        assertEquals(0, this.calculatorChip.memory,0.00001);
    }

    @Test
    public void testMemRead() {
        this.calculatorChip.digit(6);
        // at first memory is 0
        assertEquals("0.0",this.calculatorChip.memRead());
        this.calculatorChip.digit(9);
        // read the data from memory
        assertEquals("9.0",this.calculatorChip.memPlus());
        this.calculatorChip.digit(5);
        // the data still the same
        assertEquals("9.0",this.calculatorChip.memRead());
    }

    @Test
    public void testMemPlus() {
        this.calculatorChip.digit(6);
        assertEquals("6.0", this.calculatorChip.memPlus());
        this.calculatorChip.digit(5);
        assertEquals("11.0", this.calculatorChip.memPlus());
        this.calculatorChip.memClear();
        this.calculatorChip.digit(1);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(2);
        this.calculatorChip.digit(5);
        assertEquals("1.25", this.calculatorChip.memPlus());
    }

    @Test
    public void testMemMinus() {
        this.calculatorChip.digit(6);
        assertEquals("-6.0", this.calculatorChip.memMinus());
        // also test plus here
        this.calculatorChip.digit(5);
        assertEquals("-1.0", this.calculatorChip.memPlus());
        this.calculatorChip.memClear();
        this.calculatorChip.digit(1);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(2);
        this.calculatorChip.digit(5);
        assertEquals("-1.25", this.calculatorChip.memMinus());
        this.calculatorChip.clear();
        assertEquals("-1.25", this.calculatorChip.memRead());
        assertEquals("0.0", this.calculatorChip.memMinus());
    }

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
    public void testAdd() {
        // if calculator operand list is empty initially
        this.calculatorChip.numberBuffer = "3.1415";
        assertEquals("3.1415", this.calculatorChip.add());
        assertEquals(3.1415, this.calculatorChip.operand1, 0.000001);
        
        // 9 + 6, when user enter + again
        this.calculatorChip.allClear();
        this.calculatorChip.digit(9);
        this.calculatorChip.add();
        this.calculatorChip.digit(6);
        assertEquals("15.0", this.calculatorChip.add());
        this.calculatorChip.digit(3);
        assertEquals("18.0", this.calculatorChip.add());
        this.calculatorChip.digit(8);
        assertEquals("26.0", this.calculatorChip.equals());
        // a new calculation
        this.calculatorChip.digit(10);
        assertEquals("10.0", this.calculatorChip.add());
    }

    @Test
    public void testSubtract() {
        // if calculator operand list is empty initially
        this.calculatorChip.numberBuffer = "3.1415";
        assertEquals("3.1415", this.calculatorChip.subtract());
        assertEquals(3.1415, this.calculatorChip.operand1, 0.000001);
        
        // 9 - 6, when user enter - again
        this.calculatorChip.allClear();
        this.calculatorChip.digit(9);
        this.calculatorChip.subtract();
        this.calculatorChip.digit(6);
        assertEquals("3.0", this.calculatorChip.subtract());
        this.calculatorChip.digit(3);
        assertEquals("0.0", this.calculatorChip.subtract());
    }

    @Test
    public void testMultiply() {
        // if calculator operand list is empty initially
        this.calculatorChip.numberBuffer = "3.1415";
        assertEquals("3.1415", this.calculatorChip.multiply());
        assertEquals(3.1415, this.calculatorChip.operand1, 0.000001);
        
        // 9 * 6, when user enter * again
        this.calculatorChip.allClear();
        this.calculatorChip.digit(9);
        this.calculatorChip.multiply();
        this.calculatorChip.digit(6);
        assertEquals("54.0", this.calculatorChip.multiply());
        this.calculatorChip.digit(2);
        assertEquals("108.0", this.calculatorChip.subtract());
    }

    @Test
    public void testDivide() {
     // if calculator operand list is empty initially
        this.calculatorChip.numberBuffer = "3.1415";
        assertEquals("3.1415", this.calculatorChip.divide());
        assertEquals(3.1415, this.calculatorChip.operand1, 0.000001);
        
        // 9 / 6, when user enter / again
        this.calculatorChip.allClear();
        this.calculatorChip.digit(9);
        this.calculatorChip.divide();
        this.calculatorChip.digit(6);
        assertEquals("1.5", this.calculatorChip.divide());
        this.calculatorChip.digit(0);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(5);
        assertEquals("3.0", this.calculatorChip.divide());
    }

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
        
        // 9 / 0 should return Error
        this.calculatorChip.allClear();
        this.calculatorChip.digit(9);
        this.calculatorChip.divide();
        this.calculatorChip.digit(0);
        assertEquals("Error", this.calculatorChip.equals());
        
        /* special cases */
        // 5 += 10.0 = 15.0 = 20.0
        this.calculatorChip.allClear();
        this.calculatorChip.digit(5);
        this.calculatorChip.add();
        assertEquals("10.0", this.calculatorChip.equals());
        assertEquals("15.0", this.calculatorChip.equals());
        assertEquals("20.0", this.calculatorChip.equals());
        
        // 8 + 10 = 18.0 = 28.0 = 38.0
//        this.calculatorChip.allClear();
//        this.calculatorChip.digit(8);
//        this.calculatorChip.add();
//        this.calculatorChip.digit(10);
//        assertEquals("18.0", this.calculatorChip.equals());
//        assertEquals("28.0", this.calculatorChip.equals());
//        assertEquals("38.0", this.calculatorChip.equals());
    }

    @Test
    public void testSqrt() {
        this.calculatorChip.digit(1);
        this.calculatorChip.digit(6);
        assertEquals("4.0", this.calculatorChip.sqrt());
        assertEquals("2.0", this.calculatorChip.sqrt());
    }

    @Test
    public void testPercent() {
        this.calculatorChip.digit(4);
        this.calculatorChip.digit(5);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(1);
        this.calculatorChip.digit(6);
        assertEquals("0.4516", this.calculatorChip.percent());
    }

    @Test
    public void testInvert() {
        this.calculatorChip.digit(0);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(5);
        assertEquals("2.0", this.calculatorChip.invert());
        
        this.calculatorChip.allClear();
        this.calculatorChip.digit(2);
        assertEquals("0.5", this.calculatorChip.invert());
        
        // invert 0 return Error
        this.calculatorChip.allClear();
        this.calculatorChip.digit(0);
        assertEquals("Error", this.calculatorChip.invert());
    }

    @Test
    public void testChangeSign() {
        this.calculatorChip.digit(0);
        this.calculatorChip.decimalPoint();
        this.calculatorChip.digit(5);
        assertEquals("-0.5", this.calculatorChip.changeSign());
        assertEquals("0.5", this.calculatorChip.changeSign());
    }

}
