package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StockTest {
    private static final BackToStockService backToStockService = new BackToStockServiceImpl();
    private static final Product harryPotter = new Product("1", ProductCategory.BOOKS);
    private static final Product ibuprophen = new Product("2", ProductCategory.MEDICAL);
    private static final User john = new User("John", true, 50);
    private static final User phill = new User("Phill", false, 40);
    private static final User kile = new User("Kile", false, 72);
    private static final User sofia = new User("Sofia", false, 50);
    private static final User bread = new User("Bread", true, 40);

    @BeforeAll
    static void beforeAll() {
        backToStockService.subscribe(john, harryPotter);
        backToStockService.subscribe(john, harryPotter);
        backToStockService.subscribe(phill, harryPotter);
        backToStockService.subscribe(phill, harryPotter);
        backToStockService.subscribe(kile, harryPotter);
        backToStockService.subscribe(kile, harryPotter);
        backToStockService.subscribe(sofia, ibuprophen);
        backToStockService.subscribe(sofia, ibuprophen);
        backToStockService.subscribe(bread, ibuprophen);
        backToStockService.subscribe(bread, ibuprophen);
        backToStockService.subscribe(kile, ibuprophen);
        backToStockService.subscribe(kile, ibuprophen);
    }

    @Test
    void stock_Ok() {
        Stock stock = new Stock(backToStockService);
        List<User> actualUsersOrderBook = stock.addProduct(harryPotter, 1);
        List<User> expectedUsersOrderBook = List.of(john, john, kile);
        assertEquals(expectedUsersOrderBook, actualUsersOrderBook);
        List<User> actualUsersOrderMedical = stock.addProduct(ibuprophen, 2);
        List<User> expectedUsersOrderMedical = List.of(kile, kile, bread, bread, sofia, sofia);
        assertEquals(expectedUsersOrderMedical, actualUsersOrderMedical);
    }
}