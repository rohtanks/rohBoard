package com.rohboard.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rohboard.domain.Criteria;
import com.rohboard.domain.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class CastingTest {

	@Test
	public void testCasting() throws Exception {
		// 다형성 이용하여 부모타입의 인스턴스 생성
		Criteria parent = new SearchCriteria();
		
		parent.setPage(2);
		parent.setPerPageNum(20);
		
		// 자식 메소드 사용 불가
		// parent.setSearchType("test");
		// parent.setKeyword("test keyword");
		
		System.out.println(parent.getPageStart());
		
		// 강제 형변환(casting)
		// 자식 타입이 부모 타입으로 변환되어 있는 경우 가능
		SearchCriteria child = (SearchCriteria) parent;
		
		// 자식 메소드 사용 가능
		child.setSearchType("t");
		child.setKeyword("test keyword");
		
		// 또는
		((SearchCriteria) parent).setSearchType("t");
		
		System.out.println("강제 캐스팅 " + ((SearchCriteria) parent).getSearchType());
		System.out.println(child.getSearchType());
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// 부모 타입의 변수가 부모 객체를 참조할 경우는 강제 형변환이 불가능
		Criteria cri = new Criteria();
		/*
		SearchCriteria childCri = (SearchCriteria) cri;
		childCri.setSearchType("w");
		
		
		System.out.println(childCri.getSearchType());
		System.out.println(cri.hashCode());
		System.out.println(childCri.hashCode());
		*/
	}
	
}
