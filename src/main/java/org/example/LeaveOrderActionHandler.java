package org.example;

public class LeaveOrderActionHandler {
    public final static String ADD = "+";
    public final static String NEXT = "?";
    private final static int MIN_PARAMETER_VALUE = 0;
    private final static int MAX_PARAMETER_VALUE = 1_000_000_000;
    private final Treap<Integer> bidTreap = new Treap<>();
    private int lastFoundedNumber = 0;

    public void handleAddAction(String[] action) {
        int parameter = getParameter(action[1]);
        checkParameter(parameter);
        if (lastFoundedNumber > 0) {
            parameter = (parameter + lastFoundedNumber) % MAX_PARAMETER_VALUE;
            lastFoundedNumber = 0;
        }
        if (!bidTreap.search(parameter)) {
            bidTreap.add(parameter);
        }
    }

    public int handleNextAction(String[] action) {
        int parameter = getParameter(action[1]);
        checkParameter(parameter);

        Treap.Node<Integer>[] split = bidTreap.split(parameter > 1 ? parameter - 1 : parameter);
        lastFoundedNumber = split != null && split[1] != null ? split[1].key : -1;
        return lastFoundedNumber;
    }

    private int getParameter(String s) {
        return Integer.parseInt(s);
    }

    private void checkParameter(int parameter) {
        if (parameter < MIN_PARAMETER_VALUE) {
            throw new IllegalArgumentException("Parameter have to be greater or equal than " + MIN_PARAMETER_VALUE);
        }
        if (parameter > MAX_PARAMETER_VALUE) {
            throw new IllegalArgumentException("Parameter have to be less or equal than " + MAX_PARAMETER_VALUE);
        }
    }
}
