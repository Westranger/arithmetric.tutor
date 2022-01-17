package de.westranger.arithmetric.tutor.operator;

import de.westranger.arithmetric.tutor.base.Node;

public abstract class Operator extends Node {
    @Override
    public String toString() {
        return String.valueOf(this.getChar());
    }

}
