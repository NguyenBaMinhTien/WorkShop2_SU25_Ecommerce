/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author DELL
 */
public class DeliveryDTO {
    private int deliveryID;
    private int invoiceID;
    private String address;
    private String deliveryDate;
    private String status;

    public DeliveryDTO() {}

    public DeliveryDTO(int deliveryID, int invoiceID, String address, String deliveryDate, String status) {
        this.deliveryID = deliveryID;
        this.invoiceID = invoiceID;
        this.address = address;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getAddress() {
        return address;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

}
