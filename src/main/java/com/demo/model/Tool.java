package main.java.com.demo.model;

public class Tool {

    private String toolCode;
    private ToolType toolType;
    private Brand brand;

    public Tool(String toolCode, ToolType toolType, Brand brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Tool code: " + this.toolCode + "\n" +
                "Tool type: " + this.toolType.toString() + "\n" +
                "Tool brand: " + this.brand.toString() + "\n";
    }

}
