package cn.com.cloudstar.rightcloud.framework.test.t001study.jdbctest;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * 参数的原信息测试
 */
public class ParameterMetaTest {

	public static void main(String[] args) throws SQLException {
		// 测试从数据库中读取数据
		//第二个参数传java.sql.Date或java.util.Date都可以测试成功
		Object[] params = new Object[] {100L};
		read("select * from sys_m_user where user_sid = ?", params);
	}


	/**
	 * 从数据库中读取数据
	 * @param sql
	 * @param params
	 * @throws SQLException
	 */
	static void read(String sql, Object[] params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			//获取参数原信息
			ParameterMetaData pmd = ps.getParameterMetaData();
			//通过获取参数个数来提高代码灵活性
			int count = pmd.getParameterCount();
			System.out.println("参数个数:" + count);

			//打印数据库参数信息,MySQL的输出并不准确
			for(int i = 1; i <= count; i ++) {
//				System.out.print(pmd.getParameterClassName(i) + "\t");
//				System.out.print(pmd.getParameterType(i) + "\t");
//				System.out.println(pmd.getParameterTypeName(i));
				ps.setObject(i, params[i - 1]);
			}

			//执行查询操作
			rs = ps.executeQuery();

//			while(rs.next()) {
//				System.out.println(rs.getObject("user_sid"));
//			}

			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				//获得所有列的数目及实际列数
				int columnCount=rsmd.getColumnCount();
				System.out.println("columnCount="+columnCount);

				for(int i = 1 ; i <= rsmd.getColumnCount(); i++){
					System.out.println("获得列"+i+"的字段名称:"+rsmd.getColumnName(i));
					System.out.println("获得列"+i+"的字段值:"+rs.getString(i));
					System.out.println("获得列"+i+"的类型,返回SqlType中的编号:"+rsmd.getColumnType(i));
					System.out.println("获得列"+i+"的数据类型名:"+rsmd.getColumnTypeName(i));
					System.out.println("获得列"+i+"所在的Catalog名字:"+rsmd.getCatalogName(i));
					System.out.println("获得列"+i+"对应数据类型的类:"+rsmd.getColumnClassName(i));
					System.out.println("获得列"+i+"在数据库中类型的最大字符个数:"+rsmd.getColumnDisplaySize(i));
					System.out.println("获得列"+i+"的默认的列的标题:"+rsmd.getColumnLabel(i));
					System.out.println("获得列"+i+"的模式:"+rsmd.getSchemaName(i));
					System.out.println("获得列"+i+"类型的精确度(类型的长度):"+rsmd.getPrecision(i));
					System.out.println("获得列"+i+"小数点后的位数:"+rsmd.getScale(i));
					System.out.println("获得列"+i+"对应的表名:" + rsmd.getTableName(i));
					System.out.println("获得列"+i+"是否自动递增:"+rsmd.isAutoIncrement(i));
					System.out.println("获得列"+i+"在数据库中是否为货币型:"+rsmd.isCurrency(i));
					System.out.println("获得列"+i+"是否为空:"+rsmd.isNullable(i));
					System.out.println("获得列"+i+"是否为只读:"+rsmd.isReadOnly(i));
					System.out.println("获得列"+i+"能否出现在where中:"+rsmd.isSearchable(i));
				}
			}





		} finally {
			JdbcUtils.free(rs, ps, conn);
		}

	}
}