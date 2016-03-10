package com.edu.util;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/3/7
 * Description:
 * <author>           <time>             <version>        <desc>
 * 黄双双           2016/3/7              00000001        创建文件
 */
public class MysqlDialect implements Dialect {
    @Override
    public String pageSql(String sql, Page page) {

        StringBuffer querySql =
                new StringBuffer("SELECT * FROM ( ").append(sql).append(" ) AS COUNT_PAGE")
                        .append(" LIMIT ").append( page.getStart() ).append(" , ").append(page.getPageSize());
        return querySql.toString();
    }
}