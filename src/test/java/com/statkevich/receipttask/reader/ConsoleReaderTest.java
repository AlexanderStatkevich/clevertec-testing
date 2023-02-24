package com.statkevich.receipttask.reader;

import com.statkevich.receipttask.dto.InputPositionDto;
import com.statkevich.receipttask.dto.InputValuesDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleReaderTest {
    ConsoleReader consoleReader = new ConsoleReader();
    List<String> orderList = List.of("1-2", "2-3", "3-1", "card-1234");


    @Test
    void test() {
        InputValuesDto inputValuesDto = consoleReader.read(orderList);
        assertEquals(new InputValuesDto(List.of(
                new InputPositionDto(1L, 2),
                new InputPositionDto(2L, 3),
                new InputPositionDto(3L, 1)),
                "1234"), inputValuesDto);
    }

}
