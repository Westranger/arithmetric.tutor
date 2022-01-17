package de.westranger.arithmetric.tutor.base;

public final class Digit extends Node {
    private int valueInt;
    private char valueChar;

    public Digit(final char value) {
        this.setValue(value);
    }

    public Digit(int value) {
        this.setValue(value);
    }

    public int getValue() {
        return this.valueInt;
    }

    public void setValue(int value) {
        if (0 < value && value > 15) {
            throw new IllegalArgumentException("the provided parameter ist out of range 0 <= " + value + " <= 15");
        }

        this.valueInt = value;
        this.valueChar = Integer.toString(value).charAt(0);
    }

    public void setValue(final char value) {
        this.valueChar = value;
        switch (value) {
            case '0' -> {
                this.valueInt = 0;
            }
            case '1' -> {
                this.valueInt = 1;
            }
            case '2' -> {
                this.valueInt = 2;
            }
            case '3' -> {
                this.valueInt = 3;
            }
            case '4' -> {
                this.valueInt = 4;
            }
            case '5' -> {
                this.valueInt = 5;
            }
            case '6' -> {
                this.valueInt = 6;
            }
            case '7' -> {
                this.valueInt = 7;
            }
            case '8' -> {
                this.valueInt = 8;
            }
            case '9' -> {
                this.valueInt = 9;
            }
            case 'A' -> {
                this.valueInt = 10;
            }
            case 'B' -> {
                this.valueInt = 11;
            }
            case 'C' -> {
                this.valueInt = 12;
            }
            case 'D' -> {
                this.valueInt = 13;
            }
            case 'E' -> {
                this.valueInt = 14;
            }
            case 'F' -> {
                this.valueInt = 15;
            }
            default -> {
                throw new IllegalArgumentException("the provided parameter '" + value + "' is not a valid char");
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.valueChar);
    }

    @Override
    public char getChar() {
        return this.valueChar;
    }
}
