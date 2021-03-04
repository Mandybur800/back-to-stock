package com.example.comparator;

import com.example.User;

public class RestfulComparator implements java.util.Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        if (o1.getAge() > 70 && o2.getAge() > 70) {
            return 1;
        } else if (o1.getAge() > 70) {
            return -1;
        } else if (o2.getAge() > 70) {
            return 1;
        } else if (o1.isPremium() && o2.isPremium()) {
            return 1;
        } else if (o1.isPremium()) {
            return -1;
        } else if (o2.isPremium()) {
            return 1;
        }
        return 0;
    }
}
