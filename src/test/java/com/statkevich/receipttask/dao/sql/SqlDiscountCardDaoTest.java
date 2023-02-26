package com.statkevich.receipttask.dao.sql;

import com.statkevich.receipttask.domain.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.statkevich.receipttask.testutil.DataSourceUtil.getDataSource;
import static com.statkevich.receipttask.testutil.model.DiscountCardTestBuilder.aCard;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class SqlDiscountCardDaoTest {

    @Container
    private final PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>("postgres:15.1-alpine")
            .withDatabaseName("receiptDB")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("test.sql");


    private DataSource dataSource;
    private SqlDiscountCardDao cardDao;


    @BeforeEach
    void init() {
        dataSource = getDataSource(postgreSqlContainer);
        cardDao = new SqlDiscountCardDao(dataSource);

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = """
                    DELETE from discount_cards;
                                        
                            INSERT INTO discount_cards (card_number, discount)
                            VALUES ('0000', 0.0),
                                    ('1111', 0.03),
                            ('2222', 0),
                            ('3333', 0.05);""";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkGetMethodReturnAppropriateEntity() {
        DiscountCard actual = cardDao.get("3333");
        DiscountCard expected = aCard().withNumber("3333").withDiscount(BigDecimal.valueOf(0.05)).build();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkGetByKeysReturnAppropriateEntities() {
        DiscountCard expected1 = aCard().build();
        DiscountCard expected2 = aCard().withNumber("2222").withDiscount(BigDecimal.valueOf(0.0)).build();
        DiscountCard expected3 = aCard().withNumber("3333").withDiscount(BigDecimal.valueOf(0.05)).build();
        List<DiscountCard> expected = List.of(expected1, expected2, expected3);
        List<DiscountCard> actual = cardDao.getByKeys(List.of("1111", "2222", "3333"));

        assertThat(actual).isEqualTo(expected);
    }
}
