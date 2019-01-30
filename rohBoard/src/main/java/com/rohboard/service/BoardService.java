package com.rohboard.service;

import java.util.List;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public int remove(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public void upLikeCnt(Integer bno) throws Exception;

	public List<BoardVO> listPage(int page) throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;
}
