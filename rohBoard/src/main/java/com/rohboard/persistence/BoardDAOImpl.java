package com.rohboard.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.rohboard.mapper.BoardMapper.";
	
	@Override
	public void create(BoardVO vo) {
		session.insert(namespace + "create", vo);
	}

	@Override
	public BoardVO read(Integer bno) {
		return session.selectOne(namespace + "read", bno);
	}

	@Override
	public void update(BoardVO vo) {
		session.update(namespace + "update", vo);
	}

	@Override
	public int delete(Integer bno) {
		int result = -1;
		
		result = session.delete(namespace + "delete", bno);
		return result;
	}

	@Override
	public List<BoardVO> listAll() {
		return session.selectList(namespace + "listAll");
	}

	@Override
	public void updateLikeCnt(Integer bno) throws Exception {
		session.update(namespace + "updateLikeCnt", bno);
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0)
			page = 1;
		
		page = (page - 1) * 10;
		
		return session.selectList(namespace + "listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + "listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + "countPaging", cri);
	}

}
