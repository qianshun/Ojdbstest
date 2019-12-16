package com.qiangsh.testprocedure;

import com.qiangsh.utils.JDBCUtils;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestProWithTableType {
    public static void main(String[] args) {
        Connection conn =null; //数据库连接
        CallableStatement cs=null; //提供调用存储过程的方法
        ResultSet rs=null;
        String sql="{call OJDBCTEST_PKG.TESTTABLETYPE(?)}";//sql语句
        try {

            Object[] so1 = {011,02,03};
            Object[] so2 = {111,12,13};
            conn = JDBCUtils.getConnection();
            StructDescriptor IDS = StructDescriptor.createDescriptor("IDS",conn);
            STRUCT s1 = new STRUCT(IDS,conn,so1);
            STRUCT s2 = new STRUCT(IDS,conn,so2);
            STRUCT[] idsARRAY = {s1,s2};
            ArrayDescriptor IDS_table = ArrayDescriptor.createDescriptor("IDS_TABLE",conn);
            ARRAY IDS_tables = new ARRAY(IDS_table,conn,idsARRAY);

            cs=conn.prepareCall(sql); //预处理
            cs.setArray(1,IDS_tables);
            cs.executeUpdate();
//            conn.rollback();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,cs,rs);
        }


    }
}
