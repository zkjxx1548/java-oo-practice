package com.twu;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    public void buyEventChangePosition(List<Event> events, Event originEvent, String eventAndPrice) {
        String[] buyEventInfoArr = eventAndPrice.split("-");
        int buyEventPrice = Integer.parseInt(buyEventInfoArr[1]);
        Event buyEvent = events.stream()
                .filter(event -> Objects.equals(event.getDescribe(), buyEventInfoArr[0]))
                .findFirst()
                .orElse(new Event());
        events.remove(buyEvent);
        originEvent.setDescribe(buyEvent.getDescribe());
        originEvent.setPrice(buyEventPrice);
        events.stream()
                .skip(buyEvent.getRanking())
                .forEach(event -> event.setRanking(event.getRanking() - 1));
    }

    public void addEvent(List<Event> events, String eventString) {
        if (!events.stream()
                .map(Event::getDescribe)
                .collect(Collectors.toList()).contains(eventString)
        ) {
            events.add(new Event(0, eventString, 0));
        }
    }
}
