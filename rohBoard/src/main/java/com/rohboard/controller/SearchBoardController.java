package com.rohboard.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;
import com.rohboard.domain.PageMaker;
import com.rohboard.domain.SearchCriteria;
import com.rohboard.service.BoardService;

@Controller
@RequestMapping("/sboard")
public class SearchBoardController {

	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("called sboard register get");
		
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("called sboard register post");
		logger.info("### BoardVO: " + board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listPage(
			// view 에서 searchType, keyword 사용하기 위해
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("called sboard listPage");
		logger.info("### 페이지 정보: " + cri.toString());
		
		// model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		// pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("called sboard readPage");
		logger.info("### 페이지 정보: " + cri.toString());
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno,
			SearchCriteria cri,
			Model model) throws Exception {
		logger.info("called sboard modifyPage get");
		logger.info("### 페이지 정보: " + cri.toString());
		
		model.addAttribute("cri", cri);

		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO board,
			SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("called sboard modifyPage post");
		logger.info("### 페이지 정보: " + cri.toString());
		
		service.modify(board);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("bno", board.getBno());
		
		return "redirect:/sboard/readPage";
	}
	
	@RequestMapping(value = "removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno,
			SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("called sboard removePage");
		logger.info("### 페이지 정보: " + cri.toString());
		
		int removeRst = service.remove(bno);
		
		if (removeRst > 0) {
			rttr.addFlashAttribute("result", "success");
		}
		// 페이징 정보 및 검색 조건 전달
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "like", method = RequestMethod.POST)
	public String like(@RequestParam("bno") int bno,
			SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("called sboard like");
		logger.info("### 페이지 정보: " + cri.toString());
		
		service.upLikeCnt(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("bno", bno);
		
		return "redirect:/sboard/readPage";
	}
	
}
