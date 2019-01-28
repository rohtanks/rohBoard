package com.rohboard.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rohboard.domain.BoardVO;
import com.rohboard.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		logger.info("called regist()");
		// 기본게시판 Category - 101
		board.setCategory(101);
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		logger.info("called read()");
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		logger.info("called modify()");
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		logger.info("called remove()");
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		logger.info("called listAll()");
		return dao.listAll();
	}

	@Override
	public void upLikeCnt(Integer bno) throws Exception {
		logger.info("called upLike()");
		dao.updateLikeCnt(bno);
	}

}
