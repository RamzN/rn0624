package main.java.com.demo.model;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RentalAgreement {

    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("M/d/yy");
    }

    public static NumberFormat getCurrencyFormatter() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormatter.setRoundingMode(RoundingMode.HALF_UP);
        return currencyFormatter;
    }

    private final String toolCode;
    private final ToolType toolType;
    private final Brand toolBrand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public RentalAgreement(String toolCode, ToolType toolType, Brand toolBrand, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge, int discountPercent, double discountAmount, double finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void printValuesToConsole() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Tool code: " + this.toolCode + "\n" +
                "Tool type: " + this.toolType.toString() + "\n" +
                "Tool brand: " + this.toolBrand.toString() + "\n" +
                "Rental days: " + this.rentalDays + "\n" +
                "Check out date: " + this.checkoutDate.format(getDateFormatter()) + "\n" +
                "Due date: " + this.dueDate.format(getDateFormatter()) + "\n" +
                "Daily rental charge: " + getCurrencyFormatter().format(this.dailyRentalCharge) + "\n" +
                "Charge days: " + this.chargeDays + "\n" +
                "Pre-discount charge: " + getCurrencyFormatter().format(this.preDiscountCharge) + "\n" +
                "Discount percent: " + this.discountPercent + "%\n" +
                "Discount amount: " + getCurrencyFormatter().format(this.discountAmount) + "\n" +
                "Final charge: " + getCurrencyFormatter().format(this.finalCharge) + "\n";
    }
}
