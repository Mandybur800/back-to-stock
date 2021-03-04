package com.example;

import com.example.comparator.PremiumComparator;
import com.example.comparator.RestfulComparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BackToStockServiceImpl implements BackToStockService {
    private final Map<Product, List<User>> subscribes = new HashMap<>();

    @Override
    public void subscribe(User user, Product product) {
        if (user == null || product == null || product.getCategory() == null) {
            throw new RuntimeException("Incorrect input! please enter not null values.");
        }
        if (subscribes.containsKey(product)) {
            subscribes.get(product).add(user);
            return;
        }
        List<User> users = new ArrayList<>();
        users.add(user);
        subscribes.put(product, users);
    }

    @Override
    public List<User> subscribedUsers(Product product) {
        if (product == null || product.getCategory() == null) {
            throw new RuntimeException("Incorrect input! please enter not null value.");
        }
        if (product.getCategory() == ProductCategory.MEDICAL) {
            return subscribes.get(product)
                    .stream()
                    .sorted(new RestfulComparator())
                    .collect(Collectors.toList());
        }
        return subscribes.get(product)
                .stream()
                .sorted(new PremiumComparator())
                .collect(Collectors.toList());
    }
}
