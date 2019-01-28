package com.rohboard.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.rohboard.domain.BoardVO;

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
	public void delete(Integer bno) {
		session.delete(namespace + "delete", bno);
	}

	@Override
	public List<BoardVO> listAll() {
		return session.selectList(namespace + "listAll");
	}

	@Override
	public void updateLikeCnt(Integer bno) throws Exception {
		session.update(namespace + "updateLikeCnt", bno);
	}

}
