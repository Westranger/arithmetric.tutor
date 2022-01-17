package de.westranger.arithmetric.tutor;

import de.westranger.arithmetric.tutor.base.Node;
import de.westranger.arithmetric.tutor.operator.Operator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class Equation {

    private final List<Node> list;

    public Equation() {
        this.list = new LinkedList<>();
    }

    public boolean addNode(final Node node) {

        this.list.add(node);
        return true;
    }

    public Optional<Node> removeLastNode() {
        if (!this.list.isEmpty()) {
            return Optional.of(this.list.remove(this.list.size() - 1));
        }
        return Optional.empty();
    }

    List<Node> getEquation() {
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (Node node : this.list) {
            sb.append(node.getChar());
        }
        return sb.toString();
    }
}
