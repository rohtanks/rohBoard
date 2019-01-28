package com.rohboard.test;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohboard.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() {
		System.out.println("datasource: " + ds);
		
//		기존
//		Connection con = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			
//			System.out.println(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
//		try - with
		try (Connection conn = ds.getConnection()) {
			System.out.println(conn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
