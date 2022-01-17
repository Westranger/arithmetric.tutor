package de.westranger.arithmetric.tutor.operation;

import de.westranger.arithmetric.tutor.Equation;
import de.westranger.arithmetric.tutor.Table;
import de.westranger.arithmetric.tutor.base.Number;

import javax.naming.OperationNotSupportedException;

public abstract class Operation {

    abstract Table getCurrentTable() throws OperationNotSupportedException;

    abstract boolean hasNextStep();

    abstract boolean hasPrevStep();

    abstract void nextStep() throws OperationNotSupportedException;

    abstract void prevStep() throws OperationNotSupportedException;

    abstract Equation getCurrentStep();

    abstract Number getStepResult();

}
