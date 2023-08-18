package com.goodee.mvcboard.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
//게시물 삭제
	@GetMapping("/board/removeBoard")
	public String removeBoard(Board board) {
		int row = boardService.removeBoard(board);
		// 디버깅 그린
		log.debug("row-->"+row);
		System.out.println("removeBoard.row-->"+row);
		return "redirect:/board/boardList";
	}
	
// 게시물 수정
	@GetMapping("/board/modifyBoard")
	public String modifyBoard(Model model,Board board) {
		model.addAttribute("boardNo",board.getBoardNo());
		model.addAttribute("memberId",board.getMemberId());
		return "board/modifyBoard";
	}
	
	@PostMapping("/board/modifyBoard")
	public String modifyBoard(Board board) {
		int row = boardService.modifyBoard(board);
		// 디버깅 그린
		log.debug("row-->"+row);
		System.out.println("modifyBoard.row-->"+row);
		return "redirect:/board/boardList";
	}
	
// 게시물 추가
	@GetMapping("/board/addBoard")
	public String addBoard() {
		
		return "board/addBoard";
	}
	
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request,Board board) { //매개값으로 request객체를 받는다 <- request api를 직접 호출하기 위해서
		// 파일 이 저장될 경로 설정
		String path = request.getServletContext().getRealPath("/upload");
		int row = boardService.addBoard(board,path);
		System.out.println("addrow-->"+row);
		return "redirect:/board/boardList";
	}
	
// 상세보기
	@GetMapping("/board/boardOne")
	public String boardOne(Model model,Board board) {
		Board boardOne = boardService.getBoardOne(board);
		model.addAttribute("boardOne",boardOne);
		System.out.println("boardOne-->"+boardOne);
		return "/board/boardOne";
	}
	
// boardList보기
	@GetMapping("/board/boardList")
	public String boardList(Model model,
							@RequestParam(name="currentPage", defaultValue="1")int currentPage,
							@RequestParam(name="rowPerPage", defaultValue="10")int rowPerPage,
							// required는 기본 true인대 false를 할시에 null값이 넘어오면 null값을 localName에 넣어준다
							// defaultValue경우에는 null일 경우에 value설정이 가능하다
							@RequestParam(name="localName",required = false) String localName) {
		log.debug("localName-->"+localName);
		
		Map<String,Object> resultMap = boardService.getBoardList(currentPage,rowPerPage,localName);
		// 서비스에서 가져온 getBoardList를 resultMap으로 선언
		
		// view로 넘길때는 다시분리해서
		model.addAttribute("localNameList",resultMap.get("localNameList"));
		model.addAttribute("boardList",resultMap.get("boardList"));
		model.addAttribute("lastPage",resultMap.get("lastPage"));
		
		model.addAttribute("currentPage",currentPage);
		
		return "/board/boardList";
	}
}
