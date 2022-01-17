package de.westranger.arithmetric.tutor.operation;

import de.westranger.arithmetric.tutor.Equation;
import de.westranger.arithmetric.tutor.Table;
import de.westranger.arithmetric.tutor.base.Digit;
import de.westranger.arithmetric.tutor.base.Node;
import de.westranger.arithmetric.tutor.base.Number;
import de.westranger.arithmetric.tutor.base.TableCell;
import de.westranger.arithmetric.tutor.operator.OperatorAdd;
import de.westranger.arithmetric.tutor.operator.OperatorEquals;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Optional;

public final class Addition extends Operation {

    private final int base;
    private int idx;
    private int maxLen;
    private final Number currentResult;
    private Equation currentEquation;
    private final List<Number> numbers;

    public Addition(final List<Number> numbers) {
        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("list of numbers is empty");
        }

        this.base = numbers.get(0).getBase();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getBase() != this.base) {
                throw new IllegalStateException("bases of number do not match");
            }
        }

        this.idx = 0;
        this.maxLen = 0;
        this.currentResult = new Number(this.base);
        this.currentEquation = null;
        this.numbers = numbers;

        for (Number num : this.numbers) {
            this.maxLen = Math.max(this.maxLen, num.size());
        }
    }

    @Override
    Table getCurrentTable() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    @Override
    boolean hasNextStep() {
        return this.idx < this.maxLen;
    }

    @Override
    boolean hasPrevStep() {
        return this.idx - 1 > 0;
    }

    @Override
    void nextStep() throws OperationNotSupportedException {
        final Number columnResult = computeColumnResult(this.idx);
        final Equation eq = computeColumnEquation(this.idx, columnResult);

        // build result
        for (int i = 0; i < columnResult.size(); i++) {
            final TableCell tc = columnResult.get(i);
            if (this.idx + i + 1 > this.currentResult.size()) {
                this.currentResult.add(tc);
            } else {
                final TableCell tcc = this.currentResult.get(this.idx + i);
                if (tc.getValue().isPresent() && tcc.getValue().isEmpty()) {
                    tcc.setValue(tc.getValue().get());
                } else if (tc.getCarry().isPresent() && tcc.getCarry().isEmpty()) {
                    tcc.setCarry(tc.getCarry().get());
                } else if (tc.getCarry().isPresent() && tcc.getCarry().isPresent()) {
                    final int tmpCarry = tc.getCarry().get().getValue() + tcc.getCarry().get().getValue();
                    if (tmpCarry >= this.base) {
                        throw new OperationNotSupportedException("the current carry is lager than the the base and cannot be displayed anymore");
                    }

                    tcc.getCarry().get().setValue(tmpCarry);
                }
            }
        }

        this.currentEquation = eq;
        this.idx++;
    }

    private Equation computeColumnEquation(final int idx, final Number result) {
        final Equation eq = new Equation();
        for (Number num : this.numbers) {
            if (num.size() > idx) {
                final TableCell tc = num.get(idx);
                if (tc.getValue().isEmpty()) {
                    throw new IllegalStateException("number does not have a value which is required for this operation");
                }

                final Digit dig = tc.getValue().get();
                eq.addNode(dig);
                eq.addNode(new OperatorAdd());
            }
        }

        // add carry
        if (idx < this.currentResult.size()) {
            final TableCell tc = this.currentResult.get(idx);
            if (tc.getCarry().isPresent()) {
                eq.addNode(tc.getCarry().get());
                eq.addNode(new OperatorAdd());
            }
        }

        final Optional<Node> node = eq.removeLastNode();
        if (node.isEmpty()) {
            throw new IllegalStateException();
        }

        eq.addNode(new OperatorEquals());

        // build equation result
        for (int i = result.size() - 1; i >= 0; i--) {
            final TableCell tc = result.get(i);
            if (tc.getValue().isPresent()) {
                eq.addNode(tc.getValue().get());
            } else if (tc.getCarry().isPresent()) {
                eq.addNode(tc.getCarry().get());
            }
        }

        return eq;
    }

    private Number computeColumnResult(final int idx) {
        int result = 0;
        for (Number num : this.numbers) {
            if (num.size() > idx) {
                final TableCell tc = num.get(idx);
                if (tc.getValue().isEmpty()) {
                    throw new IllegalStateException("number does not have a value which is required for this operation");
                }

                final Digit dig = tc.getValue().get();
                result += dig.getValue();
            }
        }

        // add carry
        if (idx < this.currentResult.size()) {
            final TableCell tc = this.currentResult.get(idx);
            if (tc.getCarry().isPresent()) {
                result += tc.getCarry().get().getValue();
            }
        }

        final Number tmpResult = new Number(this.base);
        final String str = Integer.toString(result, this.base);

        for (int i = 0; i < str.length(); i++) {
            int tmp = Integer.parseInt(String.valueOf(str.charAt(str.length() - i - 1)));
            if (i == 0) {
                tmpResult.add(new TableCell(new Digit(tmp), null));
            } else {
                tmpResult.add(new TableCell(null, new Digit(tmp)));
            }
            result /= this.base;
        }
        return tmpResult;
    }

    @Override
    void prevStep() throws OperationNotSupportedException {
        this.idx--;
        final Number columnResultA = computeColumnResult(this.idx);
        final Number columnResultB = computeColumnResult(this.idx - 1);
        final Equation eq = computeColumnEquation(this.idx - 1, columnResultB);

        if (this.currentResult.get(this.idx).getValue().isPresent()) {
            this.currentResult.get(this.idx).setValue(null);
        }

        for (int i = 0; i < columnResultA.size(); i++) {
            if (i == 0) {
                this.currentResult.get(this.idx).setValue(null);
            } else {
                final Digit digA = this.currentResult.get(this.idx + i).getCarry().get();
                final Digit digB = columnResultA.get(i).getCarry().get();
                if (digA.getValue() - digB.getValue() <= 0) {
                    this.currentResult.get(this.idx + i).setCarry(null);
                } else {
                    digA.setValue(digA.getValue() - digB.getValue());
                }
            }
        }

        this.currentEquation = eq;
    }

    @Override
    Equation getCurrentStep() {
        return this.currentEquation;
    }

    @Override
    Number getStepResult() {
        return this.currentResult;
    }
}
