package com.colin.app.config.database;

import org.hibernate.dialect.MySQLDialect;

public class CustomMySqlDialect extends MySQLDialect {

    @Override
    public boolean dropConstraints() {
        return false;
    }
}
