package com.lavacorp.db.dao;

import com.lavacorp.entities.Supplier;

public class SupplierTests extends DaoNamedTest<Supplier, SupplierDao> {
    static final Supplier[] DATA = {
            Supplier.builder()
                    .id(1)
                    .name("Samsen")
                    .address("Samseng Factory, Road Samseng")
                    .phoneNumber("0111 1111 1111")
                    .email("support@samseng.com").build(),
            Supplier.builder()
                    .id(2)
                    .name("Logitek")
                    .address("Lausanne, Switzerland")
                    .phoneNumber("+1 646-454-3200")
                    .email("support@logitek.com").build()
    };

    public SupplierTests() {
        super(SupplierDao.class, DATA, null);
    }
}
