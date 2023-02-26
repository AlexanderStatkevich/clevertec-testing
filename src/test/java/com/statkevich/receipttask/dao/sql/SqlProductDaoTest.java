package com.statkevich.receipttask.dao.sql;

class SqlProductDaoTest {

//    Невозможно протестировать метод get и getByKeys у Products из за особенностей работы базы H2 с типом данных Array.
//    В перспективе лучше использовать testContainers во избежание проблем с совместимостью баз данных.

//    private static final String SRC = "jdbc:h2:mem:test;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1";
//    private static final DataSource dataSource = initDataSource();
//
//    private final SqlProductDao productDao = new SqlProductDao(dataSource);
//
//    private static DataSource initDataSource() {
//        JdbcDataSource dataSource = new JdbcDataSource();
//        dataSource.setURL(SRC);
//        return dataSource;
//    }

//    @BeforeEach
//    void init() {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            String sql = """
//                    CREATE TABLE products (
//                    id         bigint,
//                    name       varchar(255),
//                    price      decimal,
//                    sale_types VARCHAR (255) ARRAY
//                    );
//                    INSERT INTO
//                    products(id, name, price, sale_types)
//                    VALUES(1,'Milk',2.0,ARRAY ['TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS']),
//                    (2,'Bread',3.0, ARRAY ['TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS']),
//                    (3,'Meat',15.0, ARRAY ['TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS']);""";
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


//    @Test
//    void getByExistedKeyThenReturnExistedProduct() {
//    CommonProduct commonProduct = CommonProduct.builder()
//                .setId(1L)
//                .setName("Milk")
//                .setPrice(BigDecimal.valueOf(2.0))
//                .setSaleType(Set.of())
//                .build();
//        assertEquals(commonProduct, productDao.get(1L));
//    }

//    @Test
//    void getByAllExistedKeysThenReturnAllExistedProducts() {
//        CommonProduct commonProduct1 = CommonProduct.builder()
//                .setId(1L)
//                .setName("Milk")
//                .setPrice(BigDecimal.valueOf(2.0))
//                .setSaleType(Set.of())
//                .build();
//        CommonProduct commonProduct2 = CommonProduct.builder()
//                .setId(2L)
//                .setName("Bread")
//                .setPrice(BigDecimal.valueOf(3.0))
//                .setSaleType(Set.of(SaleType.TEN_PERCENT_OFF_FOR_MORE_THAN_FIVE_PRODUCTS))
//                .build();
//        CommonProduct commonProduct3 = CommonProduct.builder()
//                .setId(3L)
//                .setName("Meat")
//                .setPrice(BigDecimal.valueOf(15.0))
//                .setSaleType(Set.of())
//                .build();
//        List<Long> ids = List.of(1L,2L,3L);
//        List<CommonProduct> commonProductList = List.of(commonProduct1,commonProduct2,commonProduct3);
//        assertEquals(commonProductList, productDao.getByKeys(ids));
//    }

//    @AfterEach
//    void comletion() {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//            String sql = """
//                    DROP TABLE products""";
//            statement.executeUpdate(sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
