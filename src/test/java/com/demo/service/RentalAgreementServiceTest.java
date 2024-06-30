package test.java.com.demo.service;

import main.java.com.demo.model.Brand;
import main.java.com.demo.model.RentalAgreement;
import main.java.com.demo.model.Tool;
import main.java.com.demo.model.ToolType;
import main.java.com.demo.service.RentalAgreementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RentalAgreementServiceTest {

    private RentalAgreementService rentalAgreementService;

    @BeforeEach
    public void setUp() {
        rentalAgreementService = new RentalAgreementService();
    }

    @Test
    public void testChargeDaysNone() {
        LocalDate start = LocalDate.parse("1/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("1/10/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, false,false);
        assertEquals(0, chargeDays);
    }

    @Test
    public void testChargeDaysWeekdays() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,false);
        assertEquals(20, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,false);
        assertEquals(21, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,false);
        assertEquals(20, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,false);
        assertEquals(19, chargeDays);
    }

    @Test
    public void testChargeDaysWeekdaysAndWeekends() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,false);
        assertEquals(28, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,false);
        assertEquals(29, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,false);
        assertEquals(29, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,false);
        assertEquals(29, chargeDays);
    }

    @Test
    public void testChargeDaysWeekdaysAndHolidays() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,true);
        assertEquals(21, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,true);
        assertEquals(22, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,true);
        assertEquals(21, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, false,true);
        assertEquals(20, chargeDays);
    }

    @Test
    public void testChargeDaysWeekdaysAndWeekendsAndHolidays() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,true);
        assertEquals(29, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,true);
        assertEquals(30, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,true);
        assertEquals(30, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, true, true,true);
        assertEquals(30, chargeDays);
    }

    @Test
    public void testChargeDaysWeekends() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,false);
        assertEquals(8, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,false);
        assertEquals(8, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,false);
        assertEquals(9, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,false);
        assertEquals(10, chargeDays);
    }

    @Test
    public void testChargeDaysWeekendsAndHolidays() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,true);
        assertEquals(9, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,true);
        assertEquals(9, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,true);
        assertEquals(10, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, true,true);
        assertEquals(11, chargeDays);
    }

    @Test
    public void testChargeDaysHolidays() {
        LocalDate start = LocalDate.parse("9/1/20", RentalAgreement.getDateFormatter());
        LocalDate end = LocalDate.parse("9/30/20", RentalAgreement.getDateFormatter());
        int chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, false,true);
        assertEquals(1, chargeDays);

        start = LocalDate.parse("7/1/20", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/20", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, false,true);
        assertEquals(1, chargeDays);

        start = LocalDate.parse("7/1/21", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/21", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, false,true);
        assertEquals(1, chargeDays);

        start = LocalDate.parse("7/1/22", RentalAgreement.getDateFormatter());
        end = LocalDate.parse("7/31/22", RentalAgreement.getDateFormatter());
        chargeDays = rentalAgreementService.calculateChargeDays(start, end, false, false,true);
        assertEquals(1, chargeDays);
    }

    @Test
    public void testPreDiscountCharge() {
        double preDiscountCharge = rentalAgreementService.calculatePreDiscountCharge(4, 2.00);
        assertEquals(8.00, preDiscountCharge);

        preDiscountCharge = rentalAgreementService.calculatePreDiscountCharge(4, 1.99);
        assertEquals(7.96, preDiscountCharge);

        preDiscountCharge = rentalAgreementService.calculatePreDiscountCharge(0, 1.99);
        assertEquals(0.0, preDiscountCharge);

        preDiscountCharge = rentalAgreementService.calculatePreDiscountCharge(1, 1.99);
        assertEquals(1.99, preDiscountCharge);
    }

    @Test
    public void testDiscountAmount() {
        double discountAmount = rentalAgreementService.calculateDiscountAmount(10, 1.00);
        assertEquals(0.10, discountAmount);

        discountAmount = rentalAgreementService.calculateDiscountAmount(0, 10.00);
        assertEquals(0.0, discountAmount);

        discountAmount = rentalAgreementService.calculateDiscountAmount(1, 10.00);
        assertEquals(0.10, discountAmount);

        discountAmount = rentalAgreementService.calculateDiscountAmount(20, 50.00);
        assertEquals(10.00, discountAmount);
    }

    @Test
    public void testFinalCharge() {
        double finalCharge = rentalAgreementService.calculateFinalCharge(50.00, 10.00);
        assertEquals(40.00, finalCharge);
    }

    @Test
    public void testGenerateRentalAgreement() {
        Tool tool = new Tool("LADS", ToolType.LADDER, Brand.STIHL);
        String checkoutDate = "10/1/20";
        int rentalDayCount = 1;
        int discountPercent = 10;

        RentalAgreement rentalAgreement = rentalAgreementService.generateRentalAgreement(tool, checkoutDate, rentalDayCount, discountPercent);
        String expected = "Tool code: LADS\n" +
                "Tool type: Ladder\n" +
                "Tool brand: Stihl\n" +
                "Rental days: 1\n" +
                "Check out date: 10/1/20\n" +
                "Due date: 10/2/20\n" +
                "Daily rental charge: $1.99\n" +
                "Charge days: 1\n" +
                "Pre-discount charge: $1.99\n" +
                "Discount percent: 10%\n" +
                "Discount amount: $0.20\n" +
                "Final charge: $1.79\n";

        assertEquals(expected, rentalAgreement.toString());
    }
}
