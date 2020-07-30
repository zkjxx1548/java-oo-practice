package com.twu;

import java.util.Objects;

public class Event {
    int ranking;
    String describe;
    int heat;
    int price = 0;
    boolean superEvent = false;

    public Event() {
    }

    public Event(int ranking, String describe, int heat) {
        this.ranking = ranking;
        this.describe = describe;
        this.heat = heat;
    }

    public Event(int ranking, String describe, int heat, int price) {
        this.ranking = ranking;
        this.describe = describe;
        this.heat = heat;
        this.price = price;
    }

    public Event(int ranking, String describe, int heat, int price, boolean superEvent) {
        this.ranking = ranking;
        this.describe = describe;
        this.heat = heat;
        this.price = price;
        this.superEvent = superEvent;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSuperEvent() {
        return superEvent;
    }

    public void setSuperEvent(boolean superEvent) {
        this.superEvent = superEvent;
    }

    @Override
    public String toString() {
        return "Event{" +
                "ranking=" + ranking +
                ", describe='" + describe + '\'' +
                ", heat=" + heat +
                ", price=" + price +
                ", superEvent=" + superEvent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return ranking == event.ranking &&
                heat == event.heat &&
                price == event.price &&
                superEvent == event.superEvent &&
                Objects.equals(describe, event.describe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ranking, describe, heat, price, superEvent);
    }
}
