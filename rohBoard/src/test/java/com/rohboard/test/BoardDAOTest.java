package com.rohboard.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohboard.domain.BoardVO;
import com.rohboard.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;
	
	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setCategory(101);
		board.setTitle("제목 입력 테스트");
		board.setContent("내용 입력 테스트");
		board.setWriter("user01");
		
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception {
		System.out.println(dao.read(2));
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(3);
		board.setTitle("제목 수정 테스트");
		
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(2);
	}
	
	@Test
	public void testListAll() throws Exception {
		System.out.println(dao.listAll().toString());
	}
}
