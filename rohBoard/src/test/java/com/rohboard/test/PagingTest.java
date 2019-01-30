package com.rohboard.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;
import com.rohboard.domain.PageMaker;
import com.rohboard.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class PagingTest {

	@Inject
	private BoardDAO dao;
	
	@Test
	public void testPaging() throws Exception {
		
		Criteria cri = new Criteria();
		cri.setPage(500);
		cri.setPerPageNum(99);
		
		PageMaker pn = new PageMaker();
		pn.setCri(cri);
		pn.setTotalCount(dao.countPaging(cri));
		
		System.out.println("endPage: " + pn.getEndPage());
		System.out.println("startPage: " + pn.getStartPage());
		
		System.out.println((int) Math.ceil(3/10.0)*10);
	}
	
}
