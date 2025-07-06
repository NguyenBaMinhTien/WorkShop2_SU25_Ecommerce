/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author DELL
 */
public class InvoiceDTO {
    private int invoiceID;
    private String userID;
    private String name;
    private String productID;
    private String quantity;
    private double totalAmount;
    private String status;
    private String createdDate;

    public InvoiceDTO() {}

    

    public InvoiceDTO(int invoiceID, String userID, String name, String productID, String quantity, double totalAmount, String status, String createdDate) {
        this.invoiceID = invoiceID;
        this.userID = userID;
        this.name = name;
        this.productID = productID;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdDate = createdDate;
    }
    
    public int getInvoiceID() { return invoiceID; }
    public String getUserID() { return userID; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public String getCreatedDate() { return createdDate; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
}
