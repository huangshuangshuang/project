package com.edu.util;

public class MysqlDialect implements Dialect {
    @Override
    public String pageSql(String sql, Page page) {

        StringBuffer querySql =
                new StringBuffer("SELECT * FROM ( ").append(sql).append(" ) AS COUNT_PAGE")
                        .append(" LIMIT ").append( page.getStart() ).append(" , ").append(page.getPageSize());
        return querySql.toString();
    }
}
