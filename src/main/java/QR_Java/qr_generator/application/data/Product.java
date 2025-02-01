package QR_Java.qr_generator.application.data;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class Product {
    @NotNull
    private String name;
    @NotNull
    private String batchNo;
    @NotNull
    private String serialNo;
    @NotNull
    private LocalDate expiryDate;
    @DecimalMin("0.0")
    private Double price;
    private String manufacturerName;
    public Product() {}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public String getSerialNo() {
        return serialNo;
    }
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getManufacturerName() {
        return manufacturerName;
    }
}
