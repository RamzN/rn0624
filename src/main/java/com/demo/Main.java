package main.java.com.demo;

import main.java.com.demo.model.RentalAgreement;
import main.java.com.demo.service.CheckoutService;

public class Main {
    public static void main(String[] args) {
        CheckoutService checkoutService = new CheckoutService();
        RentalAgreement rentalAgreement = checkoutService.checkout("JAKR", 9, 10, "7/2/15");
        rentalAgreement.printValuesToConsole();
    }
}
