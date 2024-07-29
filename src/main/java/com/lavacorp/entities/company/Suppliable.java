package com.lavacorp.entities.company;

import java.util.List;

public interface Suppliable {
    List<Supplier> getSuppliers();
    void addSupplier(Supplier supplier);
    void removeSupplier(Supplier supplier);
}
