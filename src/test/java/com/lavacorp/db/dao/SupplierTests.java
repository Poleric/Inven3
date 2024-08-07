package com.lavacorp.db.dao;

import com.lavacorp.entities.company.Supplier;

public class SupplierTests extends DaoNamedTest<Supplier, SupplierDao> {
    static final Supplier[] DATA = {
            new Supplier(1, "Samseng", "Samseng factory, Road Samseng.", "0111 1111 1111", "support@samseng.com", null),
            new Supplier(2, "Logitek", "Lausanne, Switzerland", "+1 646-454-3200", "support@logitek.com", null),
    };

    public SupplierTests() {
        super(SupplierDao.class, DATA, null);
    }
}
