package dto;
import java.util.Date;

public class WarrantyDTO {

    private int warrantyID;
    private int productID; // ← sửa từ String thành int
    private int durationDays;
    private String terms;
    private String status;
    private Date expiryDate;

    public WarrantyDTO() {
    }

    public WarrantyDTO(int warrantyID, int productID, int durationDays, String terms, String status, Date expiryDate) {
        this.warrantyID = warrantyID;
        this.productID = productID;
        this.durationDays = durationDays;
        this.terms = terms;
        this.status = status;
        this.expiryDate = expiryDate;
    }

    public int getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(int warrantyID) {
        this.warrantyID = warrantyID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}