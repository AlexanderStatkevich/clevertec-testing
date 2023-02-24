package com.statkevich.receipttask.dao.sql;

import com.statkevich.receipttask.domain.DiscountCard;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlDiscountCardDaoTest {

    private static final String SRC = "jdbc:h2:mem:test;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1";
    private static final DataSource dataSource = initDataSource();
    private final SqlDiscountCardDao cardDao = new SqlDiscountCardDao(dataSource);

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
    void getByExistedKeyThenReturnExistedCard() {
        assertEquals(new DiscountCard("3333", BigDecimal.valueOf(0.05)), cardDao.get("3333"));
    }

    @Test
    void getByAllExistedKeysThenReturnAllExistedCards() {
        DiscountCard discountCard1 = new DiscountCard("1111", BigDecimal.valueOf(0.03));
        DiscountCard discountCard2 = new DiscountCard("2222", BigDecimal.valueOf(0));
        DiscountCard discountCard3 = new DiscountCard("3333", BigDecimal.valueOf(0.05));
        List<DiscountCard> cardList = List.of(discountCard1, discountCard2, discountCard3);
        assertEquals(cardList, cardDao.getByKeys(List.of("1111", "2222", "3333")));
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
