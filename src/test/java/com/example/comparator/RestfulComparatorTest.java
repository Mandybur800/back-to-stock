package com.example.comparator;

import static org.junit.jupiter.api.Assertions.*;

import com.example.User;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

class RestfulComparatorTest {
    @Test
    void name() {
        Queue<User> users = new PriorityQueue<>(new RestfulComparator());
        User user1 = new User("John", true, 50);
        User user2 = new User("Phill", false, 40);
        User user3 = new User("Tod", false, 80);
        User user4 = new User("Bread", true, 40);
        User user5 = new User("Kile", false, 72);
        List<User> expected = List.of(user3, user5, user1, user4, user2);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        int i = 0;
        while (!users.isEmpty()){
            assertEquals(users.poll(), expected.get(i++));
        }
    }
}