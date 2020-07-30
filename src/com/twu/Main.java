package com.twu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void homePage(Manager manager, User user, List<Event> events) {
        System.out.println("请选择权限：");
        System.out.println("1.管理员");
        System.out.println("2.普通用户");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                managerPage(manager, user, events);
                break;
            case 2:
                userPage(manager, user, events);
                break;
        }

    }

    public static void managerPage(Manager manager, User user,List<Event> events) {
        System.out.println("请选择你需要的操作：");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.添加热搜");
        System.out.println("3.添加超级热搜");
        System.out.println("4.退出");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                manager.viewEvent(events);
                managerPage(manager, user, events);
                break;
            case 2:
                addEventManagerPage(manager, events);
                managerPage(manager, user, events);
                break;
            case 3:
                addSuperEventPage(manager, events);
                managerPage(manager, user, events);
                break;
            case 4:
                homePage(manager, user, events);
                break;
        }
        scanner.close();
    }


    public static void userPage(Manager manager, User user, List<Event> events) {
        System.out.println("请选择你需要的操作：");
        System.out.println("1.查看热搜排行榜");
        System.out.println("2.给热搜事件投票");
        System.out.println("3.购买热搜");
        System.out.println("4.添加热搜");
        System.out.println("5.退出");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                user.viewEvent(events);
                userPage(manager, user, events);
                break;
            case 2:
                voteEventPage(user, events);
                userPage(manager, user, events);
                break;
            case 3:
                buyEventPage(user, events);
                userPage(manager, user, events);
                break;
            case 4:
                addEventUserPage(user, events);
                userPage(manager, user, events);
                break;
            case 5:
                homePage(manager, user, events);
                break;
        }
        scanner.close();
    }

    public static void addEventManagerPage(Manager manager, List<Event> events) {
        System.out.println("请输入想添加的热搜信息，如：eleven");
        Scanner scanner = new Scanner(System.in);
        String eventString  = scanner.nextLine();
        manager.addEvent(events, eventString);
    }

    public static void addSuperEventPage(Manager manager, List<Event> events) {
        System.out.println("请输入想添加的超级热搜信息，如：eleven");
        Scanner scanner = new Scanner(System.in);
        String superEventString  = scanner.nextLine();
        manager.addSuperEvent(events, superEventString);
    }

    public static void addEventUserPage(User user, List<Event> events) {
        System.out.println("请输入想添加的热搜信息，如：eleven");
        Scanner scanner = new Scanner(System.in);
        String eventString  = scanner.nextLine();
        user.addEvent(events, eventString);
    }

    public static void voteEventPage(User user, List<Event> events) {
        System.out.println("请输入投票信息，总共不能超过10票，如：eleven-2,one-8");
        Scanner scanner = new Scanner(System.in);
        String voteString  = scanner.nextLine();
        user.voteEvent(events, voteString);
    }

    public static void buyEventPage(User user, List<Event> events) {
        System.out.println("请输入购买热搜信息，需要购买的热搜的排名，如：3");
        Scanner scanner = new Scanner(System.in);
        String buyEventString  = scanner.nextLine();
        Event originEvent = user.getBuyOriginEvent(events, buyEventString);
        if(originEvent.getPrice() == 0) {
            System.out.println("该排名上的热搜没有人购买，任意价格即可买到该位热搜，请输入事件和价格，如：nine-500");
        } else {
            System.out.println(String.format("该排名上的热搜有人购买，价格为%d，需要花高于当前价格的钱即可买到该位热搜，请输入事" +
                    "件和价格，如：nine-500", originEvent.getPrice()));
        }
        String eventAndPrice = scanner.nextLine();
        user.buyEventChangePosition(events, originEvent, eventAndPrice);
    }
}
