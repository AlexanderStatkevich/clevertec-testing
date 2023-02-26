package com.statkevich.receipttask.service;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.statkevich.receipttask.testutil.model.DiscountCardTestBuilder.aCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountCardServiceTest {

    @Mock
    private DiscountCardDao discountCardDao;

    private DiscountCardService discountCardService;

    @BeforeEach
    void setUp() {
        discountCardService = new DiscountCardService(discountCardDao);
    }

    @Test
    @DisplayName("Get return correct entity")
    void checkGetMethodReturningCorrectEntity() {
        DiscountCard expected = aCard().build();
        when(discountCardDao.get("1111")).thenReturn(expected);
        DiscountCard actual = discountCardService.get("1111");

        assertThat(actual).isEqualTo(expected);
    }
}
