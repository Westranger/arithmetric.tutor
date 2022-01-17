package de.westranger.arithmetric.tutor.operation;

import static org.junit.jupiter.api.Assertions.*;

import de.westranger.arithmetric.tutor.base.Number;
import org.junit.jupiter.api.Assertions;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

class AdditionTest {

    @org.junit.jupiter.api.Test
    void emptyNumbers() throws OperationNotSupportedException {
        Number numA = new Number("11", 10);
        Number numB = new Number("22", 8);

        numA.clear();
        numB.clear();

        List<Number> numbers = new ArrayList<>(2);
        numbers.add(numA);
        numbers.add(numB);

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            Addition add = new Addition(numbers);
        });
    }

    @org.junit.jupiter.api.Test
    void NoNumberInList() throws OperationNotSupportedException {
        List<Number> numbers = new ArrayList<>(2);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Addition add = new Addition(numbers);
        });
    }

    @org.junit.jupiter.api.Test
    void baseDoNotMatch() throws OperationNotSupportedException {
        Number numA = new Number("11", 10);
        Number numB = new Number("22", 8);

        List<Number> numbers = new ArrayList<>(2);
        numbers.add(numA);
        numbers.add(numB);

        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> {
            Addition add = new Addition(numbers);
        });
    }

    @org.junit.jupiter.api.Test
    void exampleCalculationC() throws OperationNotSupportedException {
        Number numA = new Number("1", 10);
        Number numB = new Number("23", 10);
        Number numC = new Number("456", 10);

        List<Number> numbers = new ArrayList<>(3);
        numbers.add(numA);
        numbers.add(numB);
        numbers.add(numC);

        Addition add = new Addition(numbers);

        assertTrue(add.hasNextStep());
        assertFalse(add.hasPrevStep());

        add.nextStep();
        assertEquals("1+3+6=10", add.getCurrentStep().toString());
        assertEquals(" 0\n1 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("2+5+1=8", add.getCurrentStep().toString());
        assertEquals("80\n1 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("4=4", add.getCurrentStep().toString());
        assertEquals("480\n 1 ", add.getStepResult().toString());
    }

    @org.junit.jupiter.api.Test
    void exampleCalculationA() throws OperationNotSupportedException {
        Number numA = new Number("12345", 10);
        Number numB = new Number("23456", 10);
        Number numC = new Number("34567", 10);

        List<Number> numbers = new ArrayList<>(3);
        numbers.add(numA);
        numbers.add(numB);
        numbers.add(numC);

        Addition add = new Addition(numbers);

        System.out.println("we need to add two numbers");
        System.out.println(numA);
        System.out.println(numB);
        System.out.println(numC);

        assertTrue(add.hasNextStep());
        assertFalse(add.hasPrevStep());

        add.nextStep();
        assertEquals("5+6+7=18", add.getCurrentStep().toString());
        assertEquals(" 8\n1 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("4+5+6+1=16", add.getCurrentStep().toString());
        assertEquals(" 68\n11 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("3+4+5+1=13", add.getCurrentStep().toString());
        assertEquals(" 368\n111 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("2+3+4+1=10", add.getCurrentStep().toString());
        assertEquals(" 0368\n1111 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("1+2+3+1=7", add.getCurrentStep().toString());
        assertEquals("70368\n1111 ", add.getStepResult().toString());

        assertFalse(add.hasNextStep());
        assertTrue(add.hasPrevStep());

        add.prevStep();
        assertEquals("2+3+4+1=10", add.getCurrentStep().toString());
        assertEquals(" 0368\n1111 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("3+4+5+1=13", add.getCurrentStep().toString());
        assertEquals("  368\n 111 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("4+5+6+1=16", add.getCurrentStep().toString());
        assertEquals("   68\n  11 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("5+6+7=18", add.getCurrentStep().toString());
        assertEquals("    8\n   1 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        assertFalse(add.hasPrevStep());
    }

    @org.junit.jupiter.api.Test
    void exampleCalculationB() throws OperationNotSupportedException {
        Number num01 = new Number("999", 10);
        Number num02 = new Number("1999", 10);
        Number num03 = new Number("2999", 10);
        Number num04 = new Number("3999", 10);
        Number num05 = new Number("4999", 10);
        Number num06 = new Number("5999", 10);
        Number num07 = new Number("6999", 10);
        Number num08 = new Number("7999", 10);
        Number num09 = new Number("8999", 10);
        Number num10 = new Number("9999", 10);
        Number num11 = new Number("10999", 10);
        Number num12 = new Number("11999", 10);

        List<Number> numbers = new ArrayList<>(12);
        numbers.add(num01);
        numbers.add(num02);
        numbers.add(num03);
        numbers.add(num04);
        numbers.add(num05);
        numbers.add(num06);
        numbers.add(num07);
        numbers.add(num08);
        numbers.add(num09);
        numbers.add(num10);
        numbers.add(num11);
        numbers.add(num12);

        Addition add = new Addition(numbers);

        assertTrue(add.hasNextStep());
        assertFalse(add.hasPrevStep());

        add.nextStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9=108", add.getCurrentStep().toString());
        assertEquals("  8\n10 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9+0=108", add.getCurrentStep().toString());
        assertEquals("  88\n110 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9+1=109", add.getCurrentStep().toString());
        assertEquals("  988\n1110 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("1+2+3+4+5+6+7+8+9+0+1+1=47", add.getCurrentStep().toString());
        assertEquals(" 7988\n5110 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        add.nextStep();
        assertEquals("1+1+5=7", add.getCurrentStep().toString());
        assertEquals("77988\n5110 ", add.getStepResult().toString());

        assertFalse(add.hasNextStep());
        assertTrue(add.hasPrevStep());

        add.prevStep();
        assertEquals("1+2+3+4+5+6+7+8+9+0+1+1=47", add.getCurrentStep().toString());
        assertEquals(" 7988\n5110 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9+1=109", add.getCurrentStep().toString());
        assertEquals("  988\n1110 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9+0=108", add.getCurrentStep().toString());
        assertEquals("   88\n 110 ", add.getStepResult().toString());

        assertTrue(add.hasPrevStep());
        add.prevStep();
        assertEquals("9+9+9+9+9+9+9+9+9+9+9+9=108", add.getCurrentStep().toString());
        assertEquals("    8\n  10 ", add.getStepResult().toString());

        assertTrue(add.hasNextStep());
        assertFalse(add.hasPrevStep());
    }
}