package com.example;


public class MoneySaver {

    private long myMoney;
    private Account myAccount;
    private String name;


    public MoneySaver(String name, long money) {
        this.name = name;
        myMoney = money;
    }

    public String toString() {
        return name + ", " + "My balance is: "+ myMoney;
    }

    public String getName() {
        return name;
    }

    public void signUpForChecking(int amount) {
        myAccount = new CheckingAccount(amount);
        myMoney -= amount;
    }

    public Account getMyAccount() {
        return myAccount;
    }

    public void deposit(int amount) {
        myAccount.deposit(amount);
        myMoney -= amount;
    }

    public void withdraw(int amount) {
        ((CheckingAccount) myAccount).withdraw(amount);
        myMoney += amount;
    }

}
