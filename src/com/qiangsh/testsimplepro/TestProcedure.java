package com.qiangsh.testsimplepro;

import com.qiangsh.utils.JDBCUtils;
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestProcedure {
    @Test
    public void testProcedure(){
        Connection conn =null;
        ResultSet rs =null;
        CallableStatement cs=null;
        String sql="{call OJDBCTEST_PKG.SEARCHEMPBYEMPNO(?,?,?,?)}";
        try {
            conn = JDBCUtils.getConnection();
            cs=conn.prepareCall(sql);
            cs.setInt(1,7844); //设置输入参数
            //注册三个输出
            cs.registerOutParameter(2, OracleTypes.VARCHAR);
            cs.registerOutParameter(3, OracleTypes.VARCHAR);
            cs.registerOutParameter(4, OracleTypes.VARCHAR);
            //执行存储过程
            cs.execute();
            //得到返回结果
            String ename = cs.getString(2);
            String job=cs.getString(3);
            double sal=cs.getDouble(4);
            System.out.println(ename + "的工作是" + job);
            System.out.println(ename + "的薪水是" + sal);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,cs,rs);
        }

    }
}
