package com.statkevich.receipttask.dto;

import java.math.BigDecimal;
import java.util.Objects;

public record ReceiptRow(int quantity, String productName, BigDecimal price, BigDecimal salePercentage,
                         BigDecimal totalRow, BigDecimal saleAmount) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptRow that = (ReceiptRow) o;
        return quantity == that.quantity
                && Objects.equals(productName, that.productName)
                && Objects.equals(price, that.price)
                && (salePercentage.compareTo(that.salePercentage) == 0)
                && (totalRow.compareTo(that.totalRow) == 0)
                && (saleAmount.compareTo(that.saleAmount) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, productName, price, salePercentage, totalRow, saleAmount);
    }
}
