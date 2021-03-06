package com.rohboard.test;

import java.sql.Connection;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SqlSessionFactoryTest {

	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		System.out.println("sqlSessionFactory: " + sqlSessionFactory);
		
//		try - with
		try (SqlSession session = sqlSessionFactory.openSession()) {
			
			System.out.println("session: " + session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
