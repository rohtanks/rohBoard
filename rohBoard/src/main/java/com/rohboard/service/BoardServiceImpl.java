package com.rohboard.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;
import com.rohboard.domain.SearchCriteria;
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
	public int remove(Integer bno) throws Exception {
		logger.info("called remove()");
		
		return dao.delete(bno);
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

//	@Override
//	public List<BoardVO> listPage(int page) throws Exception {
//		logger.info("called listPage()");
//		return dao.listPage(page);
//	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		logger.info("called listCriteria()");
		return dao.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return dao.countPaging(cri);
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

}
