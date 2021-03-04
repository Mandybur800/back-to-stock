package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {
    private static final int MULTIPLIER = 3;
    private final Map<Product, Long> stock = new HashMap<>();
    private final BackToStockService backToStockService;

    public Stock(BackToStockService backToStockService) {
        this.backToStockService = backToStockService;
    }

    /**
     * @param product some product
     * @param amount of product was added
     * @return list of users, that need to notify
     */
    public List<User> addProduct(Product product, long amount) {
        if (stock.containsKey(product)) {
            amount += stock.get(product);
        }
        stock.put(product, amount);
        List<User> users = backToStockService.subscribedUsers(product);
        for (int i = 0; i < users.size() && i < amount * MULTIPLIER; i++) {
            users.get(i).notifyUser(product);
        }
        return amount * 3 > users.size() ? users : users.subList(0, (int) (amount * MULTIPLIER));
    }
}
