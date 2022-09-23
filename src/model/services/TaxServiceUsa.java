package model.services;

public class TaxServiceUsa implements TaxService{

    public double tax(double amount) {
        if (amount <= 100.0) {
            return amount * 0.30;
        } else {
            return amount * 0.25;
        }
    }
}
