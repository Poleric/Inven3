package com.lavacorp.inven3.dao;

import com.lavacorp.inven3.dao.generic.NamedDaoTests;
import com.lavacorp.inven3.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

public class SupplierDaoTests extends NamedDaoTests<Supplier, SupplierDao> {

    @Autowired
    public SupplierDaoTests(SupplierDao dao) {
        super(dao);
    }

}
