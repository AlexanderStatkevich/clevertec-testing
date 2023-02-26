package com.statkevich.receipttask.calculation;

import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.domain.SaleType;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCardDecoratorTest {

    private final Long ID = 1L;
    private final String NAME = "name";
    private final int QUANTITY = 5;
    private final BigDecimal SALE_PERCENTAGE_EXPECTED = BigDecimal.valueOf(5);
    private final BigDecimal SALE_MULTIPLIER = BigDecimal.ONE.subtract(SALE_PERCENTAGE_EXPECTED.multiply(BigDecimal.valueOf(0.01)));
    private final BigDecimal PRICE = BigDecimal.TEN;
    private final BigDecimal FULL_TOTAL_ROW = PRICE.multiply(BigDecimal.valueOf(QUANTITY));
    private final BigDecimal SALE_TOTAL_ROW = FULL_TOTAL_ROW.multiply(SALE_MULTIPLIER);
    private final BigDecimal SALE_AMOUNT_EXPECTED = FULL_TOTAL_ROW.subtract(SALE_TOTAL_ROW);

    private final DiscountCardDecorator discountCardDecorator;

    private final CommonProduct product;

    public DiscountCardDecoratorTest() {
        this.discountCardDecorator = new DiscountCardDecorator(new TenPercentOffForMoreThanFiveProducts(new FullCostCalculator()), new DiscountCard("1234", SALE_PERCENTAGE_EXPECTED.multiply(BigDecimal.valueOf(0.01))));
        this.product = new CommonProduct(ID, NAME, PRICE, Set.of(SaleType.TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS));
    }

    @Test
    void checkCalculate() {
        PositionDto positionDto = new PositionDto(product, QUANTITY);
        ReceiptRow receiptRow = discountCardDecorator.calculate(positionDto);
        assertEquals(new ReceiptRow(QUANTITY, NAME, PRICE, SALE_PERCENTAGE_EXPECTED, SALE_TOTAL_ROW, SALE_AMOUNT_EXPECTED), receiptRow);
    }


}
