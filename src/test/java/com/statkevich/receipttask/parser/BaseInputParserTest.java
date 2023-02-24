package com.statkevich.receipttask.parser;

class BaseInputParserTest {

//    Невозможно протестировать метод parse() у BaseInputParser из за особенностей работы базы H2 с типом данных Array.
//    В перспективе лучше использовать testContainers во избежание проблем с совместимостью баз данных.

//    private final BaseInputParser<String[]> consoleInputParser = new ConsoleInputParser();
//    private final BaseInputParser<Map<String, String[]>> webInputParser = new WebInputParser();
//
//    DataSource dataSource  = DataSourceHolder.getDataSource();
//    ProductDao productDao = new SqlProductDao(dataSource);
//
//    @Test
//    void consoleInputParserTest() {
//        String[] orderArray = new String[]{"1-2","card-1234"};
//        OrderDto orderDto = consoleInputParser.parse(orderArray);
//        CommonProduct product = productDao.get(1L);
//        assertEquals(new OrderDto(List.of(
//                new PositionDto(product,2)),
//                "1234"),orderDto);
//    }
//
//    @Test
//    void webInputParserTest() {
//        Map<String,String[]> orderMap = new HashMap<>();
//        orderMap.put("1",new String[] {"2"});
//        orderMap.put("card",new String[] {"1234"});
//        OrderDto orderDto = webInputParser.parse(orderMap);
//        CommonProduct product = productDao.get(1L);
//        assertEquals(new OrderDto(List.of(
//                new PositionDto(product,2)),
//                "1234"),orderDto);
//    }


}
