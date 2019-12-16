package com.qiangsh.testprocedure;

import com.qiangsh.utils.JDBCUtils;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import oracle.sql.*;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestProWithTableTypeOut {

    public static void main(String[] args) {
        Connection conn =null; //数据库连接
        CallableStatement cs=null; //提供调用存储过程的方法
        ResultSet rs=null;
        String sql="{call OJDBCTEST_PKG.TESTTABLETYPE_OUT(?)}";//sql语句
        try {


            conn = JDBCUtils.getConnection();


            cs=conn.prepareCall(sql); //预处理
            cs.registerOutParameter(1, OracleTypes.ARRAY,"IDS_TABLE");
            cs.execute();
            ResultSet resultSet = cs.getArray(1).getResultSet();
            while(resultSet.next()){
                Datum[] oracleAttributes = ((STRUCT) resultSet.getObject(2)).getOracleAttributes();
                System.out.println("id1:"+oracleAttributes[0].intValue()+"id2:"+oracleAttributes[1].intValue()+"id3:"+oracleAttributes[2].intValue());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,cs,rs);
        }


    }
}
