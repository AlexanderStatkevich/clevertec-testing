package com.statkevich.receipttask.service;

import com.statkevich.receipttask.dao.sql.SqlDiscountCardDao;
import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.domain.SaleType;
import com.statkevich.receipttask.dto.OrderDto;
import com.statkevich.receipttask.dto.PositionDto;
import com.statkevich.receipttask.dto.ReceiptDto;
import com.statkevich.receipttask.dto.ReceiptRow;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OrderServiceTest {


    private static final String SRC = "jdbc:h2:mem:test;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1";
    private static final DataSource dataSource = initDataSource();
    private final SqlDiscountCardDao cardDao = new SqlDiscountCardDao(dataSource);
    private final DiscountCardService discountCardService = new DiscountCardService(cardDao);
    private final OrderService orderService = new OrderService(discountCardService);
    private final OrderDto orderDto;

    public OrderServiceTest() {
        orderDto = new OrderDto(List.of(
                new PositionDto(new CommonProduct(1L, "Name1", BigDecimal.valueOf(5), Set.of(SaleType.TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS)), 6),
                new PositionDto(new CommonProduct(2L, "Name2", BigDecimal.valueOf(10), Set.of()), 3)), "1111");
    }

    private static DataSource initDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(SRC);
        return dataSource;
    }

    @BeforeEach
    void init() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = """
                    CREATE TABLE discount_cards (
                    card_number varchar(255),
                    discount    decimal(2,3));
                                            
                    INSERT INTO discount_cards (card_number, discount)
                    VALUES
                    ('1111', 0.03),
                    ('2222', 0),
                    ('3333', 0.05);""";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testOrder() {
        ReceiptDto receiptDto = orderService.processingOrder(orderDto);
        ReceiptDto receiptDtoExpected = new ReceiptDto(List.of(
                new ReceiptRow(6, "Name1", BigDecimal.valueOf(5), BigDecimal.TEN, BigDecimal.valueOf(27), BigDecimal.valueOf(3)),
                new ReceiptRow(3, "Name2", BigDecimal.valueOf(10), BigDecimal.valueOf(3), BigDecimal.valueOf(29.1), BigDecimal.valueOf(0.9))), BigDecimal.valueOf(56.1));

        assertEquals(receiptDtoExpected, receiptDto);
    }

    @AfterEach
    void comletion() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = """
                    DROP TABLE discount_cards""";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
