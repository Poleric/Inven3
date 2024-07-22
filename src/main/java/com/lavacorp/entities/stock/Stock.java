package com.lavacorp.entities.stock;

import com.lavacorp.entities.company.Supplier;
import com.lavacorp.entities.item.Item;
import org.jetbrains.annotations.Nullable;

public class Stock {
    private int batchId;
    private Item item;
    private String location;
    private int quantity;

    @Nullable private Supplier supplier;
}
