package org.example.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int MAX = 10;
    private static final int MIN = 1;

    public static void main(String[] args) {

        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put("1", new Account(700_000, "1"));
        accounts.put("2", new Account(200_000, "2"));
        accounts.put("3", new Account(300_000, "3"));
        accounts.put("4", new Account(500_000, "4"));
        accounts.put("5", new Account(100_000, "5"));
        accounts.put("6", new Account(150_000, "6"));
        accounts.put("7", new Account(250_000, "7"));
        accounts.put("8", new Account(350_000, "8"));
        accounts.put("9", new Account(400_000, "9"));
        accounts.put("10", new Account(650_000, "10"));


        Random random = new Random();
        Bank bank = new Bank(accounts);
        List<Thread> threads = new ArrayList<>();
        System.out.println(bank.getSumAllAccounts());

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {

                for (int j = 0; j < 100; j++) {
                    int randomOne = random.nextInt(MAX - MIN + 1) + MIN;
                    int randomTwo = random.nextInt(MAX - MIN + 1) + MIN;
                    int moneyRandom = random.nextInt(40_000 - 5_000 + 1) + 5_000;
                    int money = 50_000;
                    try {
                        if (j >= 15 && j <= 20) {
                            bank.transfer(String.valueOf(randomOne), String.valueOf(randomTwo), money);
                        }
                        bank.transfer(String.valueOf(randomOne), String.valueOf(randomTwo), moneyRandom);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }));
        }
        threads.forEach(Thread::start);

        try {
            waitingForThreadsToFinish(threads);
            bank.setSumAllAccounts(0);
            System.out.println(bank.getSumAllAccounts());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    public static void waitingForThreadsToFinish(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads){
            thread.join();
        }
    }
}
