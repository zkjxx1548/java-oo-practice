package com.twu;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
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

    public void voteEvent(List<Event> events, String voteString) {
        String[] voteInfoArr = voteString.split(",");
        Arrays.stream(voteInfoArr)
                .map(s -> s.split("-"))
                .forEach(strings -> {
                    Event voteEvent = events.stream()
                            .filter(event -> Objects.equals(event.getDescribe(), strings[0]))
                            .findFirst()
                            .orElse(new Event());
                    if (voteEvent.isSuperEvent()) {
                        voteEvent.setHeat(voteEvent.getHeat() + Integer.parseInt(strings[1]) * 2);
                    } else {
                        voteEvent.setHeat(voteEvent.getHeat() + Integer.parseInt(strings[1]));
                    }
                });
    }

    public Event getBuyOriginEvent(List<Event> events, String buyRankingString) {
        int buyRanking = Integer.parseInt(buyRankingString);
        return events.stream()
                .sorted((o1, o2) -> o2.getHeat() - o1.getHeat())
                .skip(buyRanking - 1)
                .findFirst()
                .orElse(new Event());
    }
}
