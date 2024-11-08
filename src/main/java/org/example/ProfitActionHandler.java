package org.example;

public class ProfitActionHandler {
    public final static String BID = "BID";
    public final static String DEL = "DEL";
    public final static String SALE = "SALE";
    private final static double MIN_PRICE = 0.01;
    private final static double MAX_PRICE = 10000.00;
    private final static int MIN_NUMBER_OF_GOODS = 1;
    private final static int MAX_NUMBER_OF_GOODS = 100000;
    private final Treap<Double> bidTreap = new Treap<>();

    public void handleBidAction(String[] action) {
        double price = getPrice(action[1]);
        checkPrice(price);
        bidTreap.add(price);
    }

    public void handleDelAction(String[] action) {
        double price = getPrice(action[1]);
        checkPrice(price);
        bidTreap.remove(price);
    }

    public int handleSaleAction(String[] action) {
        int numberOfGoods = Integer.parseInt(action[2]);
        checkNumberOfGoods(numberOfGoods);

        double price = getPrice(action[1]);
        checkPrice(price);

        int numberOfSuitableBids = getNumberOfSuitableBids(price, bidTreap);

        return Math.min(numberOfSuitableBids, numberOfGoods);
    }
    private int getNumberOfSuitableBids(double price, Treap<Double> bidTreap) {
        // уменьшаем price на минимальную стоимость,
        // чтобы только во втором поддереве были подходящие заявки
        Treap.Node<Double>[] split = bidTreap.root != null ? bidTreap.split(price - MIN_PRICE) : null;
        return split != null && split[1] != null ? split[1].size : 0;
    }

    private void checkNumberOfGoods(int numberOfGoods) {
        if (numberOfGoods < MIN_NUMBER_OF_GOODS) {
            throw new IllegalArgumentException("Number of goods have to be greater or equal than " + MIN_NUMBER_OF_GOODS);
        }
        if (numberOfGoods > MAX_NUMBER_OF_GOODS) {
            throw new IllegalArgumentException("Number of goods have to be less or equal than " + MAX_NUMBER_OF_GOODS);
        }
    }
    private double getPrice(String s) {
        return Double.parseDouble(s);
    }

    private void checkPrice(double price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("Price have to be greater or equal than " + MIN_PRICE);
        }
        if (price > MAX_PRICE) {
            throw new IllegalArgumentException("Price have to be less or equal than " + MAX_PRICE);
        }
    }
}
