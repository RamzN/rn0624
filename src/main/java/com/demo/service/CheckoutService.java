package main.java.com.demo.service;

import main.java.com.demo.model.Brand;
import main.java.com.demo.model.RentalAgreement;
import main.java.com.demo.model.Tool;
import main.java.com.demo.model.ToolType;

import java.util.HashMap;
import java.util.Map;

public class CheckoutService {

    private final Map<String, Tool> availableTools;

    public CheckoutService() {
        this.availableTools = new HashMap<>();
        this.addTool(new Tool("CHNS", ToolType.CHAINSAW, Brand.STIHL));
        this.addTool(new Tool("LADW", ToolType.LADDER, Brand.WERNER));
        this.addTool(new Tool("JAKD", ToolType.JACKHAMMER, Brand.DEWALT));
        this.addTool(new Tool("JAKR", ToolType.JACKHAMMER, Brand.RIDGID));
    }

    private void addTool(Tool tool) {
        this.availableTools.put(tool.getToolCode(), tool);
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, String checkoutDate) throws IllegalArgumentException {
        if (rentalDayCount < 1) {
            throw new IllegalArgumentException("Rental day count must be greater than 0");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0-100");
        }
        Tool tool = this.availableTools.get(toolCode);
        return (new RentalAgreementService()).generateRentalAgreement(tool, checkoutDate, rentalDayCount, discountPercent);
    }

}
