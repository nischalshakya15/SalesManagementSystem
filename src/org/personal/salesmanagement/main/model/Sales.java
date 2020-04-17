package org.personal.salesmanagement.main.model;

import org.personal.salesmanagement.main.constants.Constants;

import java.io.Serializable;

public class Sales implements Serializable {

    private String goodsId;

    private String description;

    private Double pricePerUnit;

    private Integer quantity;

    private Double totalSales;

    public Sales() {

    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getDescription() {
        return description;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public Sales(String goodsId, String description, Double pricePerUnit, Integer quantity, Double totalSales) {
        this.goodsId = goodsId.trim();
        this.description = description.trim();
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return goodsId + Constants.DELIMITER + description + Constants.DELIMITER + pricePerUnit +
                Constants.DELIMITER + quantity + Constants.DELIMITER + totalSales + Constants.NEW_LINE;
    }

    public static String getHeaders() {
        return Constants.GOODS_ID + Constants.DELIMITER + Constants.DESCRIPTION + Constants.DELIMITER +
                Constants.PRICE_PER_UNIT + Constants.DELIMITER + Constants.QUANTITY + Constants.DELIMITER + Constants.TOTAL_SALES + Constants.NEW_LINE;
    }
}
