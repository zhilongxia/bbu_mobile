package bbu_mobile.com;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class MySqlTest {

	public static void main(String[] args) {
		Connection conn = null;
		try {

			// 加载mysql驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 连接localhost上的mysql,并指定使用test数据库，用户名为root,密码为***
			conn = DriverManager.getConnection("jdbc:mysql://localhost/order_wear", "blue_bird", "123456");
			if (!conn.isClosed()) {
				System.out.println("数据库连接成功！"); // 验证是否连接成功
			}

			Statement statement = conn.createStatement();
			// 查询数据
			ResultSet rs = statement.executeQuery("select * from product_main");

			// 输出结果集（类似.net中的DataSet/DataTable）
			while (rs.next()) {
				System.out.println("id=" + rs.getInt("id") + ",name=" + rs.getString("seNo"));
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
