package com.example;

public class FractionTester {

    public void runTests() {
        Fraction f1 = new Fraction(10,20);
        Fraction f2 = new Fraction(2,3);
        System.out.println(f1.add(f2) + " = 7/6");
        System.out.println(f1 + " = 10/20");
        System.out.println(f2 + " = 2/3");
        f1.simplify();
        System.out.println(f1 + " = 1/2");
        System.out.println(Fraction.gcd(9, 2) + " = 1");
        System.out.println(Fraction.gcd(24, 18) + " = 6");
    }

    public static void main(String[] args) {
        FractionTester tester = new FractionTester();
        tester.runTests();
    }
}
