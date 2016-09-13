package com.example;

/**
 * Created by david on 9/4/16.
 */
public class MoneyTester {

    public static void main(String[] args) {
        Account a = new CheckingAccount(100);
        System.out.println(a);
        a.setAmount(20);
        System.out.println("New amount: " + a.getAmount());

        a = new CheckingAccount(100);
        System.out.println(a);
        a.setAmount(20);
        a.deposit(10);
        System.out.println("New amount: " + a.getAmount());

        Account small = new CheckingAccount(20);
        Account big = new CheckingAccount(30);
        System.out.println(Account.largerAccount(small, big));

        MoneySaver david = new MoneySaver("David", 100);
        david.signUpForChecking(50);  // put 50 dollars in a checking account
        System.out.println(david);
        System.out.println(david.getMyAccount());

        MoneySaver jim = new MoneySaver("Jim", 100);
        jim.signUpForChecking(30);
        CheckingAccount acc = (CheckingAccount) jim.getMyAccount();
        System.out.println(jim);
        System.out.println(acc);
        jim.deposit(40);
        System.out.println(jim);
        System.out.println(acc);
        jim.withdraw(60);
        System.out.println(jim);
        System.out.println(acc);
    }

}
