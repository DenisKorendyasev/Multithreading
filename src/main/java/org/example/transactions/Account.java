package org.example.transactions;

public class Account {

    private long money;
    private String accNumber;
    private boolean isBlocked;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }
    public  void addingFundsYourAccount(long money)
    {
        if (money > 0 && !isBlocked) {
            this.money += money;
            System.out.println("Перевод прошёл");
        }
    }
    public void withdrawalOfMoney(long money) throws InterruptedException {

        if (money > 0 && (this.money - money) >= 0 && !isBlocked) {
            this.money -= money;
            System.out.println("Снятие прошло");
        }
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                ", accNumber='" + accNumber + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
