package com.rohboard.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rohboard.domain.BoardVO;
import com.rohboard.domain.Criteria;
import com.rohboard.domain.PageMaker;
import com.rohboard.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// @Autowired 와 병용 가능
	@Inject
	private BoardService service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		logger.info(formattedDate);
		
		return "home";
	}
	
	// Model 객체는 메소드에서 뷰에 데이터를 전달하는 용도로 사용
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) {
		logger.info("called register get");
		
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("called register post");
		logger.info(board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("result", "success");
		
		// return "/board/success";
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("called listAll");
		
		// logger.info(service.listAll().toString());
		
		model.addAttribute("list", service.listAll());
	}
	
//	@RequestMapping(value = "read", method = RequestMethod.GET)
//	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
//		logger.info("called read");
//		
//		model.addAttribute(service.read(bno));
//	}
	
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public void readPage(@RequestParam("bno") int bno,
			// @ModelAttribute 는 자동으로 해당 객체를 뷰까지 전달
			@ModelAttribute("cri") Criteria cri,
			Model model) throws Exception {
		logger.info("called readPage");
		
		model.addAttribute(service.read(bno));
	}
	
//	@RequestMapping(value = "modify", method = RequestMethod.GET)
//	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
//		logger.info("called modify get");
//		
//		model.addAttribute(service.read(bno));
//	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno,
			@ModelAttribute("cri") Criteria cri,
			Model model) throws Exception {
		logger.info("called modifyPage get");
		
		model.addAttribute(service.read(bno));
	}
	
//	@RequestMapping(value = "modify", method = RequestMethod.POST)
//	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
//		logger.info("called modify post");
//		
//		service.modify(board);
//		
//		rttr.addFlashAttribute("result", "success");
//		rttr.addAttribute("bno", board.getBno());
//		
//		return "redirect:/board/readPage";
//	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO board,
			Criteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("called modifyPage post");
		
		service.modify(board);
		
		rttr.addFlashAttribute("result", "success");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("bno", board.getBno());
		
		return "redirect:/board/readPage";
	}
	
//	@RequestMapping(value = "remove", method = RequestMethod.POST)
//	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
//		logger.info("called remove");
//		
//		service.remove(bno);
//		
//		rttr.addFlashAttribute("result", "success");
//		
//		return "redirect:/board/listPage";
//	}
	
	@RequestMapping(value = "removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			Criteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("called removePage");
		
		int removeRst = service.remove(bno);
		
		if (removeRst > 0) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "like", method = RequestMethod.POST)
	public String like(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("called like");
		
		service.upLikeCnt(bno);
		
		rttr.addAttribute("bno", bno);
		
		return "redirect:/board/read";
	}
	
	@RequestMapping(value = "listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) throws Exception {
		logger.info("called listPage with Criteria");
		
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception {
		logger.info("called listPage with Criteria");
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		logger.info("total row count: " + pageMaker.getTotalCount());
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	// javaScript 처리 테스트
	@RequestMapping(value = "listPage2", method = RequestMethod.GET)
	public String listPageScript(Criteria cri, Model model) throws Exception {
		logger.info("called listPage for javaScript testing");
		
		model.addAttribute("list", service.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute(pageMaker);
		
		return "board/listPage_script";
	}
}
