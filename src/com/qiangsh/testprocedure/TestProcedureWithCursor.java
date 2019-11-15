package com.qiangsh.testsimplepro;

import com.qiangsh.utils.JDBCUtils;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class TestProcedureWithCursor {
    public static void main(String[] args) {
        Connection conn =null; //数据库连接
        CallableStatement cs=null; //提供调用存储过程的方法
        ResultSet rs=null;
        String sql="{call OJDBCTEST_PKG.queryEmpList(?,?)}";//sql语句
        try {
            conn= JDBCUtils.getConnection();//获取链接
            cs=conn.prepareCall(sql); //预处理
            cs.setInt(1,7844); //设置第一个输入参数
            //注册输出参数
            cs.registerOutParameter(2, OracleTypes.CURSOR);
            //执行
            cs.execute();
            OracleCallableStatement ocs= (OracleCallableStatement) cs;
            rs=ocs.getCursor(2);
            while(rs.next()){
                System.out.print(rs.getInt("empno"+""));
                System.out.print(rs.getString("ename")+" ");
                System.out.print(rs.getString("JOB")+" ");
                System.out.print(rs.getInt("MGR")+" ");
                System.out.print(rs.getDate("HIREDATE")+" ");
                System.out.print(rs.getDouble("SAL")+" ");
                System.out.print(rs.getInt("COMM")+" ");
                System.out.print(rs.getInt("DEPTNO")+" ");
                System.out.println();
            }



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.free(conn,cs,rs);
        }

    }
}
