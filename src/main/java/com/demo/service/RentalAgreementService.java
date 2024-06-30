package main.java.com.demo.service;

import main.java.com.demo.model.Brand;
import main.java.com.demo.model.RentalAgreement;
import main.java.com.demo.model.Tool;
import main.java.com.demo.model.ToolType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class RentalAgreementService {

    public RentalAgreementService() {
    }

    private boolean isHoliday(Month month, int dayOfMonth, DayOfWeek dayOfWeek) {
        return (month == Month.JULY && dayOfMonth == 4 && isWeekday(dayOfWeek)) ||
                (month == Month.JULY && dayOfMonth == 3 && dayOfWeek == DayOfWeek.FRIDAY) ||
                (month == Month.JULY && dayOfMonth == 5 && dayOfWeek == DayOfWeek.MONDAY) ||
                (month == Month.SEPTEMBER && dayOfMonth <= 7 && dayOfWeek == DayOfWeek.MONDAY);
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY ||
                dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY ||
                dayOfWeek == DayOfWeek.FRIDAY;
    }

    public int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        int chargeDays = 0;
        LocalDate date = checkoutDate.plusDays(1);
        while (!date.isAfter(dueDate)) {
            if (holidayCharge && isHoliday(date.getMonth(), date.getDayOfMonth(), date.getDayOfWeek())) {
                chargeDays++;
            } else if (weekendCharge && isWeekend(date.getDayOfWeek()) && !isHoliday(date.getMonth(), date.getDayOfMonth(), date.getDayOfWeek())) {
                chargeDays++;
            } else if (weekdayCharge && isWeekday(date.getDayOfWeek()) && !isHoliday(date.getMonth(), date.getDayOfMonth(), date.getDayOfWeek())) {
                chargeDays++;
            }
            date = date.plusDays(1);
        }
        return chargeDays;
    }

    public double calculatePreDiscountCharge(int chargeDays, double dailyRentalCharge) {
        return chargeDays * dailyRentalCharge;
    }

    public double calculateDiscountAmount(int discountPercent, double preDiscountCharge) {
        return (discountPercent / 100.0) * preDiscountCharge;
    }

    public double calculateFinalCharge(double preDiscountCharge, double discountAmount) {
        return preDiscountCharge - discountAmount;
    }

    public RentalAgreement generateRentalAgreement(Tool tool, String checkoutDate, int rentalDayCount, int discountPercent) {
        String toolCode = tool.getToolCode();
        ToolType toolType = tool.getToolType();
        Brand toolBrand = tool.getBrand();
        LocalDate parsedCheckoutDate = LocalDate.parse(checkoutDate, RentalAgreement.getDateFormatter());
        LocalDate dueDate = parsedCheckoutDate.plusDays(rentalDayCount);
        double dailyRentalCharge = tool.getToolType().getDailyCharge();
        int chargeDays = calculateChargeDays(parsedCheckoutDate, dueDate, toolType.isWeekdayCharge(), toolType.isWeekendCharge(), toolType.isHolidayCharge());
        double preDiscountCharge = calculatePreDiscountCharge(chargeDays, dailyRentalCharge);
        double discountAmount = calculateDiscountAmount(discountPercent, preDiscountCharge);
        double finalCharge = calculateFinalCharge(preDiscountCharge, discountAmount);

        return new RentalAgreement(
                toolCode,
                toolType,
                toolBrand,
                rentalDayCount,
                parsedCheckoutDate,
                dueDate,
                dailyRentalCharge,
                chargeDays,
                preDiscountCharge,
                discountPercent,
                discountAmount,
                finalCharge);
    }


}
