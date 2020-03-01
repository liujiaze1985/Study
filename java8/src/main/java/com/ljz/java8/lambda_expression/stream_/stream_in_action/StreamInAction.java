package com.ljz.java8.lambda_expression.stream_.stream_in_action;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Copyright © 2019年11月23日 LiuJiaZe. All rights reserved.
 *
 * @Project: java8
 * @Package: com.ljz.java8.lambda_expression.stream_.stream_in_action
 * @Description: 综合练习
 * @author: LiuJiaZe
 * @date: 2019年11月23日 18:07
 * @version: V1.0
 */
public class StreamInAction {
    /**
     * 1. Find all transactions in the year 2011 and sort them by value (small to high).
     * 2. What are all the unique cities where the traders work?
     * 3. Find all traders from Cambridge and sort them by name.
     * 4. Return a string of all traders’ names sorted alphabetically.
     * 5. Are any traders based in Milan?
     * 6. Print all transactions’ values from the traders living in Cambridge.
     * 7. What’s the highest value of all the transactions?
     * 8. Find the transaction with the smallest value.
     */
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> result = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(result);
        // 2. What are all the unique cities where the traders work?
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
        //3. Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        //4. Return a string of all traders’ names sorted alphabetically.
        String value = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(value);
        //5. Are any traders based in Milan?
        //5.1
        boolean liveInMilan1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        //5.2
        boolean liveInMilan2 = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println(liveInMilan1);
        System.out.println(liveInMilan2);
        //6. Print all transactions’ values from the traders living in Cambridge.
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);


        //7. What’s the highest value of all the transactions?
        Integer maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        System.out.println(maxValue);

        //8. Find the transaction with the smallest value.
        Integer minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min).get();
        System.out.println(minValue);


    }
}
