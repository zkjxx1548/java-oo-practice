package com.twu;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Manager {
    public void viewEvent(List<Event> events) {
        AtomicInteger i = new AtomicInteger(1);
        events.stream()
                .sorted((o1, o2) -> o2.getHeat() - o1.getHeat())
                .forEach(event -> {
                    System.out.println(String.format("排名：%d，描述：%s，热度：%d。",
                            i.getAndIncrement(), event.getDescribe(), event.getHeat()));
                    event.setRanking(i.get() - 1);
                });
    }

    public void addEvent(List<Event> events, String eventString) {
        if (!events.stream()
                .map(event -> event.getDescribe())
                .collect(Collectors.toList()).contains(eventString)
        ) {
            events.add(new Event(0, eventString, 0));
        }
    }

    public void addSuperEvent(List<Event> events, String superEventString) {
        Event superEvent = events.stream()
                .filter(event -> Objects.equals(event.getDescribe(), superEventString))
                .findFirst()
                .orElse(new Event());
        superEvent.setSuperEvent(true);
    }
}
