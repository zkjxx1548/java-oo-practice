package com.twu;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, "one", 55));
        events.add(new Event(2, "two", 28));
        events.add(new Event(3, "three", 62));
        events.add(new Event(4, "four", 75));
        events.add(new Event(5, "five", 12));
        events.add(new Event(6, "six", 43));
        events.add(new Event(7, "seven", 6));
        events.add(new Event(8, "eight", 18));
        events.add(new Event(9, "nine", 2));
        events.add(new Event(10, "ten", 34));
        System.out.println(events);

        Manager manager = new Manager();
        User user = new User();
    }
}
