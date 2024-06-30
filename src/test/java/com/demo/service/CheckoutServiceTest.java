package test.java.com.demo.service;

import main.java.com.demo.model.RentalAgreement;
import main.java.com.demo.service.CheckoutService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    private static CheckoutService checkoutService;

    @BeforeAll
    public static void setUp() {
        checkoutService = new CheckoutService();
    }

    @Test
    public void testDiscountOutOfRange(){
        String toolCode = "JAKR";
        String checkoutDate = "9/3/15";
        int rentalDays = 5;
        int discount = 101;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("Discount percent must be between 0-100"));
    }

    @Test
    public void testRentalDaysLessThanOne(){
        String toolCode = "JAKR";
        String checkoutDate = "9/3/15";
        int rentalDays = 0;
        int discount = 10;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        });
        assertTrue(exception.getMessage().equalsIgnoreCase("Rental day count must be greater than 0"));
    }

    @Test
    public void testWeekdayWeekendChargeOnlyNoIndependenceDayCharge(){
        String toolCode = "LADW";
        String checkoutDate = "7/2/20";
        int rentalDays = 3;
        int discount = 10;

        RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        String expected = "Tool code: LADW\n" +
                "Tool type: Ladder\n" +
                "Tool brand: Werner\n" +
                "Rental days: 3\n" +
                "Check out date: 7/2/20\n" +
                "Due date: 7/5/20\n" +
                "Daily rental charge: $1.99\n" +
                "Charge days: 2\n" +
                "Pre-discount charge: $3.98\n" +
                "Discount percent: 10%\n" +
                "Discount amount: $0.40\n" +
                "Final charge: $3.58\n";

        assertEquals(expected, rentalAgreement.toString());
    }

    @Test
    public void testWeekdayIndependenceDayChargeNoWeekendCharge(){
        String toolCode = "CHNS";
        String checkoutDate = "7/2/15";
        int rentalDays = 5;
        int discount = 25;

        RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        String expected = "Tool code: CHNS\n" +
                "Tool type: Chainsaw\n" +
                "Tool brand: Stihl\n" +
                "Rental days: 5\n" +
                "Check out date: 7/2/15\n" +
                "Due date: 7/7/15\n" +
                "Daily rental charge: $1.49\n" +
                "Charge days: 3\n" +
                "Pre-discount charge: $4.47\n" +
                "Discount percent: 25%\n" +
                "Discount amount: $1.12\n" +
                "Final charge: $3.35\n";

        assertEquals(expected, rentalAgreement.toString());
    }

    @Test
    public void testWeekdayChargeNoWeekendLaborDayCharge(){
        String toolCode = "JAKD";
        String checkoutDate = "9/3/15";
        int rentalDays = 6;
        int discount = 0;

        RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        String expected = "Tool code: JAKD\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: DeWalt\n" +
                "Rental days: 6\n" +
                "Check out date: 9/3/15\n" +
                "Due date: 9/9/15\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 3\n" +
                "Pre-discount charge: $8.97\n" +
                "Discount percent: 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $8.97\n";

        assertEquals(expected, rentalAgreement.toString());
    }

    @Test
    public void testWeekdayChargeNoWeekendIndependenceDayCharge1(){
        String toolCode = "JAKR";
        String checkoutDate = "7/2/15";
        int rentalDays = 9;
        int discount = 0;

        RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        String expected = "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 9\n" +
                "Check out date: 7/2/15\n" +
                "Due date: 7/11/15\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 5\n" +
                "Pre-discount charge: $14.95\n" +
                "Discount percent: 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $14.95\n";

        assertEquals(expected, rentalAgreement.toString());
    }

    @Test
    public void testWeekdayChargeNoWeekendIndependenceDayCharge2(){
        String toolCode = "JAKR";
        String checkoutDate = "7/2/20";
        int rentalDays = 4;
        int discount = 50;

        RentalAgreement rentalAgreement = checkoutService.checkout(toolCode, rentalDays, discount, checkoutDate);
        String expected = "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 4\n" +
                "Check out date: 7/2/20\n" +
                "Due date: 7/6/20\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 1\n" +
                "Pre-discount charge: $2.99\n" +
                "Discount percent: 50%\n" +
                "Discount amount: $1.50\n" +
                "Final charge: $1.50\n";

        assertEquals(expected, rentalAgreement.toString());
    }
}
