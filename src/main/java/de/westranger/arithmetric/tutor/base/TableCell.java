package de.westranger.arithmetric.tutor.base;

import java.util.Optional;

public final class TableCell {
    public Digit value;
    public Digit carry;

    public TableCell() {
        this(null, null);
    }

    public TableCell(final Digit value) {
        this(value, null);
    }

    public TableCell(final Digit value, final Digit carry) {
        this.value = value;
        this.carry = carry;
    }

    public void setValue(final Digit value) {
        this.value = value;
    }

    public void setCarry(final Digit carry) {
        this.carry = carry;
    }

    public Optional<Digit> getValue() {
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(value);
    }

    public Optional<Digit> getCarry() {
        if (carry == null) {
            return Optional.empty();
        }

        return Optional.of(carry);
    }
}
