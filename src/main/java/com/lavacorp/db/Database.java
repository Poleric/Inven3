package com.lavacorp.db;

public interface Database {
    void connect();
    void close();
    void init();
}
