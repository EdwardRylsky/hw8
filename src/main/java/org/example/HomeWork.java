package org.example;


import java.util.ArrayList;
import java.util.List;

public class HomeWork {
    private final static double COMMISSION = 0.01;
    private final static int MAX_NUMBER_OF_ACTIONS_FOR_GET_PROFIT = 100000;
    private final static int MAX_NUMBER_OF_ACTIONS_FOR_GET_LEAVE_ORDER = 300000;

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу
     * <a href="https://acm.timus.ru/problem.aspx?space=1&num=1316">https://acm.timus.ru/problem.aspx?space=1&num=1316</a>
     */
    public Double getProfit(List<String> actionList) {
        if (actionList == null) {
            throw new IllegalArgumentException("ActionList can't be null");
        }

        if (actionList.size() > MAX_NUMBER_OF_ACTIONS_FOR_GET_PROFIT) {
            throw new IllegalArgumentException("ActionList have to contains less or equal " + MAX_NUMBER_OF_ACTIONS_FOR_GET_PROFIT + " actions");
        }

        ProfitActionHandler profitActionHandler = new ProfitActionHandler();

        int numberOfDeal = 0;

        for (String action : actionList) {
            String[] s = action.split(" ");

            switch (s[0]) {
                case ProfitActionHandler.BID:
                    profitActionHandler.handleBidAction(s);
                    break;
                case ProfitActionHandler.DEL:
                    profitActionHandler.handleDelAction(s);
                    break;
                case ProfitActionHandler.SALE:
                    numberOfDeal += profitActionHandler.handleSaleAction(s);
                    break;
                default: throw new IllegalArgumentException("Unknown action " + s[0]);
            }


        }
        return numberOfDeal * COMMISSION;
    }

    /**
     * <h1>Задание 2.</h1>
     * Решить задачу <br/>
     * <a href="https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1">https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1</a><br/>
     */
    public List<Integer> getLeaveOrder(List<String> actionList) {
        if (actionList == null) {
            throw new IllegalArgumentException("ActionList can't be null");
        }

        if (actionList.size() > MAX_NUMBER_OF_ACTIONS_FOR_GET_LEAVE_ORDER) {
            throw new IllegalArgumentException("ActionList have to contains less or equal " + MAX_NUMBER_OF_ACTIONS_FOR_GET_LEAVE_ORDER + " actions");
        }

        LeaveOrderActionHandler leaveOrderActionHandler = new LeaveOrderActionHandler();

        ArrayList<Integer> result = new ArrayList<>();

        for (String action : actionList) {
            String[] s = action.split(" ");

            switch (s[0]) {
                case LeaveOrderActionHandler.ADD:
                    leaveOrderActionHandler.handleAddAction(s);
                    break;
                case LeaveOrderActionHandler.NEXT:
                    result.add(leaveOrderActionHandler.handleNextAction(s));
                    break;
                default: throw new IllegalArgumentException("Unknown action " + s[0]);
            }
        }

        return result;
    }
}