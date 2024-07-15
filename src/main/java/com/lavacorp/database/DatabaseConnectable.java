package com.lavacorp.database;

public interface DatabaseConnectable {
    void connect(String filepath);
    void close();
}
