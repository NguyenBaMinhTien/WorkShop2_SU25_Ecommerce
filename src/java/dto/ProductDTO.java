package dto;

import java.sql.Date;

public class ProductDTO {
    private int productID;
    private String name;
    private int categoryID;
    private double price;
    private int quantity;
    private String sellerID;
    private String status;

    // Thông tin khuyến mãi
    private String promotionName;
    private double discountPercent;
    private Date startDate;
    private Date endDate;
    private String promoID;
    private double discountedPrice;

    public ProductDTO() {
    }

    public ProductDTO(int productID, String name, int categoryID, double price, int quantity, String sellerID, String status, Date startDate, Date endDate) {
        this.productID = productID;
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProductDTO(int productID, String name, double price, String promoID, String promotionName, double discountPercent,double discountedPrice, Date startDate, Date endDate,String status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.status = status;
        this.promotionName = promotionName;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promoID = promoID;
        this.discountedPrice = discountedPrice;
    }

    public ProductDTO(String name, int categoryID, double price, int quantity, String sellerID, String status) {
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.status = status;
    }

    public ProductDTO(int productID, String name, int categoryID, double price, int quantity, String sellerID, String status) {
        this.productID = productID;
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.status = status;
    }

    
    
    

    // Constructor đầy đủ
    public ProductDTO(int productID, String name, int categoryID, double price, int quantity,
                      String sellerID, String status,
                      String promotionName, double discountPercent, Date startDate, Date endDate) {
        this.productID = productID;
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
        this.quantity = quantity;
        this.sellerID = sellerID;
        this.status = status;
        this.promotionName = promotionName;
        this.discountPercent = discountPercent;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    

    // Getter - Setter
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Tính giá sau khi giảm
    public double getDiscountedPrice() {
        if (discountPercent > 0) {
            return price * (1 - discountPercent / 100.0);
        } else {
            return price;
        }
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", categoryID=" + categoryID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sellerID='" + sellerID + '\'' +
                ", status='" + status + '\'' +
                ", promotionName='" + promotionName + '\'' +
                ", discountPercent=" + discountPercent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
