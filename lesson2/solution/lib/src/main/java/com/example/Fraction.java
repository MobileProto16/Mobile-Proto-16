package com.example;

public class Fraction {

    private int numerator;
    private int denominator;

    public Fraction(int num, int den) {
        numerator = num;
        denominator = den;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Fraction add(Fraction other) {
        int newNum = other.getDenominator() * numerator + denominator * other.getNumerator();
        int newDen = denominator * other.getDenominator();
        Fraction res = new Fraction(newNum, newDen);
        res.simplify();
        return res;
    }

    /**
     * Returns the greatest common divisor of two integers
     */
    public static int gcd(int m, int n) {
        int smaller = Math.min(m, n);
        int larger = Math.max(m, n);

        if (smaller == 0)
            return larger;

        return gcd(smaller, larger % smaller);
    }

    public void simplify() {
        int _gcd = gcd(numerator, denominator);
        if (_gcd != 0) {
            numerator = numerator / _gcd;
            denominator = denominator / _gcd;
        }
    }

    public String toString() {
        return Integer.toString(numerator) + "/" + Integer.toString(denominator);
    }

}