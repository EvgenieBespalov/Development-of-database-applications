package hibernate;

import org.hibernate.dialect.PostgreSQL9Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL9Dialect {

    public CustomPostgreSQLDialect () {
        registerFunction("fts", new PostgreSQLFTSFunction());
    }

}