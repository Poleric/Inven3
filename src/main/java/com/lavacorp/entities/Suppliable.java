package com.lavacorp.entities;

import java.util.List;

public interface Suppliable {
    List<Supplier> getSuppliers();
    void addSupplier(Supplier supplier);
    void removeSupplier(Supplier supplier);
}
