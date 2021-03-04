package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class BackToStockServiceImplTest {
    private static final BackToStockService backToStockService = new BackToStockServiceImpl();

    @Test
    void addingSubscribers_Ok() {
        Product harryPotter = new Product("1", ProductCategory.BOOKS);
        User john = new User("John", true, 50);
        User phill = new User("Phill", false, 40);
        User kile = new User("Kile", false, 72);
        backToStockService.subscribe(john, harryPotter);
        backToStockService.subscribe(phill, harryPotter);
        backToStockService.subscribe(kile, harryPotter);
        Product ibuprophen = new Product("2", ProductCategory.MEDICAL);
        User sofia = new User("Sofia", false, 50);
        User bread = new User("Bread", true, 40);
        backToStockService.subscribe(sofia, ibuprophen);
        backToStockService.subscribe(bread, ibuprophen);
        backToStockService.subscribe(kile, ibuprophen);
        List<User> expectedBooks = List.of(john, kile, phill);
        List<User> actualBooks = backToStockService.subscribedUsers(harryPotter);
        assertEquals(expectedBooks, actualBooks);
        List<User> expectedMedical = List.of(kile, bread, sofia);
        List<User> actualMedical = backToStockService.subscribedUsers(ibuprophen);
        assertEquals(expectedMedical, actualMedical);
    }

    @Test
    void subscribe_NotOk() {
        Product harryPotter = new Product("1", null);
        Product atlantic = new Product("2", ProductCategory.BOOKS);
        User john = new User("John", true, 50);
        User phill = new User("Phill", false, 40);
        User kile = new User("Kile", false, 72);
        assertThrows(RuntimeException.class, () -> backToStockService.subscribe(john, harryPotter));
        assertThrows(RuntimeException.class,() -> backToStockService.subscribe(null, null));
        assertThrows(RuntimeException.class,() -> backToStockService.subscribe(phill, null));
        assertThrows(RuntimeException.class,() -> backToStockService.subscribe(null, atlantic));
    }

    @Test
    void subscribedUsers_NotOk() {
        Product harryPotter = new Product("1", null);
        Product ibuprophen = null;
        assertThrows(RuntimeException.class,() -> backToStockService.subscribedUsers(harryPotter));
        assertThrows(RuntimeException.class,() -> backToStockService.subscribedUsers(ibuprophen));
    }

}