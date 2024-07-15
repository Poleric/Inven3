package com.lavacorp.modules.storage;

import com.lavacorp.Item;

import com.lavacorp.modules.DatabaseModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DatabaseStorage extends DatabaseModule implements Storage {
    private final static Logger LOGGER = LogManager.getLogger(DatabaseStorage.class);

    private final static String INIT_SCRIPTS = """
            CREATE TABLE IF NOT EXISTS Item (
                item_id   INTEGER PRIMARY KEY AUTOINCREMENT,
                item_name TEXT NOT NULL
            );
            
            CREATE TABLE IF NOT EXISTS Inventory (
                item_id INTEGER NOT NULL REFERENCES Item (item_id),
                count   INTEGER NOT NULL
            );
            """;

    @Override
    protected void initTables() {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(INIT_SCRIPTS);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void addItemInfo(@NotNull String ... itemNames) {
        final String CREATE_ITEM = "INSERT OR REPLACE INTO Item (item_name) VALUES (?);";

        try (PreparedStatement stmt = getConnection().prepareStatement(CREATE_ITEM)) {
            for (String itemName : itemNames) {
                stmt.setString(1, itemName);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteItemInfo(@NotNull int ... itemIds) {
        final String DELETE_ITEM = "DELETE FROM Item WHERE item_id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(DELETE_ITEM)) {
            for (int itemId : itemIds) {
                stmt.setInt(1, itemId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Item queryItemInfo(int itemId) {
        final String QUERY_ITEM = "SELECT item_id, item_name FROM Item WHERE item_id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(QUERY_ITEM)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return new Item(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        throw new NoSuchElementException("Item " + itemId + " is not found.");
    }

    @Override
    public ArrayList<Item> queryItemInfo(String itemName) {
        final String QUERY_ITEM = "SELECT item_id, item_name FROM Item WHERE item_name LIKE ?;";

        ArrayList<Item> results = new ArrayList<>();

        try (PreparedStatement stmt = getConnection().prepareStatement(QUERY_ITEM)) {
            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                results.add(new Item(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return results;
    }

    @Override
    public void addItem(int itemId, int quantity) {
        final String ADD_ITEM = "UPDATE Inventory SET count = count + ? WHERE item_id = ?";

        try (PreparedStatement stmt = getConnection().prepareStatement(ADD_ITEM)) {
            stmt.setInt(2, itemId);

            stmt.setInt(1, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void removeItem(int itemId, int quantity) {
        final String REMOVE_ITEM = "UPDATE Inventory SET count = count - ? WHERE item_id = ?;";

        // TODO: raise error when quantity exceed inventory
        try (PreparedStatement stmt = getConnection().prepareStatement(REMOVE_ITEM)) {
            stmt.setInt(2, itemId);

            stmt.setInt(1, quantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public int queryItemCount(int itemId) {
        final String QUERY_ITEM = "SELECT count FROM Inventory WHERE item_id = ?;";

        try (PreparedStatement stmt = getConnection().prepareStatement(QUERY_ITEM)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
                return rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        throw new NoSuchElementException("Item " + itemId + " is not stored in the inventory.");
    }
}
