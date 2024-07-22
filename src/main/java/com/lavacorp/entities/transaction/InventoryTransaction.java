package com.lavacorp.entities.transaction;

import com.lavacorp.entities.item.Item;
import org.jdbi.v3.core.mapper.Nested;

import java.time.LocalDateTime;

public record InventoryTransaction(@Nested TransactionType transactionType, Item item, LocalDateTime timestamp, int count) {
}
