package de.westranger.arithmetric.tutor.base;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public final class Number extends ArrayList<TableCell> {

    private int base;

    public Number(final int base) {
        super();
        if (base < 1 && base > 16) {
            throw new IllegalArgumentException("base mut be within range  1 <= base <= 16 but was " + base);
        }
        this.base = base;
    }

    public Number(final String number, final int base) {
        super(number.length());
        if (number == null) {
            throw new IllegalArgumentException("argument to number must not be null");
        }

        if (number.isEmpty()) {
            throw new IllegalArgumentException("argument to number must not be empty");
        }

        if (base < 1 && base > 16) {
            throw new IllegalArgumentException("base mut be within range  1 <= base <= 16 but was " + base);
        }
        this.base = base;

        try {
            number.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("argument to number must be UTF-8 encoded");
        }

        if (!number.matches("\\p{Digit}+")) {
            throw new IllegalArgumentException("argument to number must ne digits only");
        }

        for (int i = 0; i < number.length(); i++) {
            this.add(0,new TableCell(new Digit(number.charAt(i))));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sbA = new StringBuilder();
        final StringBuilder sbB = new StringBuilder();
        boolean carryPresent = false;

        for (int i = this.size() - 1; i >= 0; i--) {
            final Optional<Digit> value = this.get(i).getValue();
            if (value.isPresent()) {
                sbA.append(value.get().getChar());
            } else {
                sbA.append(' ');
            }

            final Optional<Digit> carry = this.get(i).getCarry();
            if (carry.isPresent()) {
                sbB.append(carry.get().getChar());
                carryPresent = true;
            } else {
                sbB.append(' ');
            }
        }

        if (carryPresent) {
            sbA.append('\n');
            sbA.append(sbB);
        }
        return sbA.toString();
    }

    public int getBase() {
        return base;
    }
}
