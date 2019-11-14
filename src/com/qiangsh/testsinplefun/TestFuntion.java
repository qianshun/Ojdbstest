package com.qiangsh.testsinplefun;

import com.qiangsh.utils.JDBCUtils;
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestFuntion {
    @Test
    public void testProcedure(){
        Connection conn =null;
        ResultSet rs =null;
        CallableStatement cs=null;
        String sql="{?=call OJDBCTEST_PKG.getename(?)}";
        try {
            conn = JDBCUtils.getConnection();
            cs=conn.prepareCall(sql);

            //注册三个输出
            cs.registerOutParameter(1, OracleTypes.VARCHAR);
            cs.setInt(2,7844); //设置输入参数
            //执行存储过程
            cs.execute();
            //得到返回结果
            String ename = cs.getString(1);
            System.out.println("查询结果是" + ename); //设置输入参数);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,cs,rs);
        }

    }
}
