package com.lavacorp.db.dao;

import com.lavacorp.db.dao.generic.DaoTest;
import com.lavacorp.models.*;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StockDaoTests extends DaoTest<Stock, StockDao> {
    public static final Stock[] DATA = {
            Stock.builder()
                    .id(1)
                    .item(ItemDaoTests.DATA[0])
                    .supplier(SupplierDaoTests.DATA[0])
                    .location(LocationDaoTests.DATA[1])
                    .quantity(3)
                    .status(StockStatus.OK)
                    .build(),
            Stock.builder()
                    .id(2)
                    .item(ItemDaoTests.DATA[1])
                    .supplier(SupplierDaoTests.DATA[0])
                    .location(LocationDaoTests.DATA[3])
                    .quantity(5)
                    .status(StockStatus.OK)
                    .build(),
            Stock.builder()
                    .id(3)
                    .item(ItemDaoTests.DATA[1])
                    .supplier(SupplierDaoTests.DATA[0])
                    .location(LocationDaoTests.DATA[0])
                    .quantity(20)
                    .status(StockStatus.OK)
                    .build(),
            Stock.builder()
                    .id(4)
                    .item(ItemDaoTests.DATA[3])
                    .supplier(SupplierDaoTests.DATA[0])
                    .location(LocationDaoTests.DATA[5])
                    .quantity(2)
                    .status(StockStatus.OK)
                    .build()
    };

    public StockDaoTests() {
        super(StockDao.class, DATA, null);
    }

    @BeforeAll
    static void setUp() {
        CategoryDaoTests categoryDaoTests = new CategoryDaoTests();

        categoryDaoTests.beforeEach();
        categoryDaoTests.getData().forEach(categoryDaoTests.dao::create);
        categoryDaoTests.afterEach();

        ItemDaoTests itemDaoTests = new ItemDaoTests();

        itemDaoTests.beforeEach();
        itemDaoTests.getData().forEach(itemDaoTests.dao::create);
        itemDaoTests.afterEach();

        SupplierDaoTests supplierDaoTests = new SupplierDaoTests();

        supplierDaoTests.beforeEach();
        supplierDaoTests.getData().forEach(supplierDaoTests.dao::create);
        supplierDaoTests.afterEach();

        LocationDaoTests locationDaoTests = new LocationDaoTests();
        locationDaoTests.beforeEach();
        locationDaoTests.getData().forEach(locationDaoTests.dao::create);
        locationDaoTests.afterEach();
    }

    public Stream<Item> getItemData() {
        assumeArrayNotEmpty(ItemDaoTests.DATA);
        return Stream.of(ItemDaoTests.DATA);
    }

    public Stream<Supplier> getSupplierData() {
        assumeArrayNotEmpty(SupplierDaoTests.DATA);
        return Stream.of(SupplierDaoTests.DATA);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getItemData")
    public void testRetrieveByItemId(Item item) {
        assertNotNull(item.getId());

        List<Stock> expected = getData().filter((stock) -> stock.getItem().equals(item)).toList();
        List<Stock> actual = dao.retrieveByItemId(item.getId());

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @Order(1)
    @MethodSource("getSupplierData")
    public void testRetrieveBySupplierId(Supplier supplier) {
        assertNotNull(supplier.getId());

        List<Stock> expected = getData().filter((stock) -> {
            assertNotNull(stock.getSupplier());
            return stock.getSupplier().equals(supplier);
        }).toList();
        List<Stock> actual = dao.retrieveBySupplierId(supplier.getId());

        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void testIncreaseStock() {
        Stock expected = DATA[0];
        int quantity = expected.getQuantity();
        final int DIFF = quantity - 1;

        assertTrue(DIFF > 0);
        assertNotNull(expected.getId());

        dao.increaseStock(expected.getId(), DIFF);
        quantity += DIFF;

        Stock actual = dao.retrieve(expected.getId());
        assertEquals(quantity, actual.getQuantity());

        handle.rollback();
    }

    @Test
    @Order(2)
    public void testDecreaseStock() {
        Stock expected = DATA[0];
        int quantity = expected.getQuantity();
        final int DIFF = quantity - 1;

        assertTrue(DIFF > 0);
        assertNotNull(expected.getId());

        dao.decreaseStock(expected.getId(), DIFF);
        quantity -= DIFF;

        Stock actual = dao.retrieve(expected.getId());
        assertEquals(quantity, actual.getQuantity());

        handle.rollback();
    }

    @Test
    @Order(2)
    public void testRaiseOnOverDecreaseStock() {
        Stock expected = DATA[0];
        int quantity = expected.getQuantity();
        final int DIFF = quantity + 1;

        assertTrue(DIFF > 0);
        assertNotNull(expected.getId());

        UnableToExecuteStatementException exc = assertThrows(UnableToExecuteStatementException.class, () -> dao.decreaseStock(expected.getId(), DIFF));
        assertTrue(exc.getMessage().contains("SQLITE_CONSTRAINT_CHECK"));

        Stock actual = dao.retrieve(expected.getId());
        assertEquals(expected.getQuantity(), actual.getQuantity());
    }
}
