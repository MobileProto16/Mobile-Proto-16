package com.example;

/**
 * Created by abrahams on 6/12/16.
 */
public class CheckingAccount extends Account {

    public CheckingAccount(long amount) {
        super(amount);
    }

    public String toString() {
        return "Checking " + super.toString();
    }

    public void withdraw(long amount) {
        setAmount(getAmount() - amount);
    }
}
