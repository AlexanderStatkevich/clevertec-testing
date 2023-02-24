package com.statkevich.receipttask.calculation;

import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullCostCalculatorTest {

    private final Long ID = 1L;
    private final int QUANTITY = 5;
    private final String NAME = "name";
    private final BigDecimal PRICE = BigDecimal.TEN;
    private final BigDecimal TOTAL_ROW = PRICE.multiply(BigDecimal.valueOf(QUANTITY));

    private final FullCostCalculator fullCostCalculator;
    private final CommonProduct product;

    public FullCostCalculatorTest() {
        this.fullCostCalculator = new FullCostCalculator();
        this.product = new CommonProduct(ID, NAME, PRICE, null);
    }

    @Test
    void checkCalculate() {
        PositionDto positionDto = new PositionDto(product, QUANTITY);
        ReceiptRow receiptRow = fullCostCalculator.calculate(positionDto);
        assertEquals(new ReceiptRow(QUANTITY, NAME, PRICE, BigDecimal.ZERO, TOTAL_ROW, BigDecimal.ZERO), receiptRow);
    }
}