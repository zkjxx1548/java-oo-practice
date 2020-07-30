package com.twu;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
}
