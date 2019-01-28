package com.rohboard.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class SqlSessionTest {

	@Inject
	private SqlSession session;
	
	@Test
	public void testSqlSession() {
		System.out.println("sqlSession: " + session);
		
	}
	
}
