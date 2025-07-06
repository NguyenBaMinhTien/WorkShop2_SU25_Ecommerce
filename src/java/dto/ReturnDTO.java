/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author DELL
 */
public class ReturnDTO {
        private int returnID;
    private int invoiceID;
    private String reason;
    private String status;

    public ReturnDTO() {}

    public ReturnDTO(int returnID, int invoiceID, String reason, String status) {
        this.returnID = returnID;
        this.invoiceID = invoiceID;
        this.reason = reason;
        this.status = status;
    }

    public int getReturnID() {
        return returnID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

}
