package com.qiangsh.testconnection;

import java.sql.Connection;
import com.qiangsh.utils.JDBCUtils;

public class TestConnection {
    public static void main(String[] args) {
        Connection conn=JDBCUtils.getConnection();
        System.out.println(conn);
        JDBCUtils.free(conn, null, null);
    }
}
