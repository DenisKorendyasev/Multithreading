package org.example.transactions;

import java.util.Comparator;
import java.util.Map;
import java.util.Random;

public class Bank implements Comparator<Account> {

    private static final int SUSPICIOUS_TRANSFER = 50_000;
    private Map<String, Account> accounts;
    private final Random random = new Random();
    private long sumAllAccounts = 0;


    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account accountOne = accounts.get(fromAccountNum);
        Account accountTwo = accounts.get(toAccountNum);
        if (accounts.containsKey(fromAccountNum) && accounts.containsKey(toAccountNum))
        {
            synchronized (compare(accountOne, accountTwo) > 0 ? accountOne : accountTwo) {
                synchronized (compare(accountOne, accountTwo) > 0 ? accountTwo : accountOne)
                {
                    if ((accountOne.getMoney() - amount) >= 0 && !accountOne.isBlocked() && !accountTwo.isBlocked())
                    {
                        accountOne.withdrawalOfMoney(amount);
                        accountTwo.addingFundsYourAccount(amount);
                    } else {
                        System.out.println("Один из счетов заблокирован");
                    }
                }
            }
            if (amount >= SUSPICIOUS_TRANSFER)
            {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accountOne.setBlocked(true);
                    accountTwo.setBlocked(true);
                }
            }
        }
    }
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        setSumAllAccounts(0);
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
            sumAllAccounts += account.getValue().getMoney();
        }
        return sumAllAccounts;
    }

    public void setSumAllAccounts(long sumAllAccounts) {
        this.sumAllAccounts = sumAllAccounts;
    }

    @Override
    public int compare(Account o1, Account o2) {
        return o1.getAccNumber().compareTo(o2.getAccNumber());
    }
}