package com.goodee.mvcboard.restapi;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodee.mvcboard.service.BoardService;

@CrossOrigin
// Cross 브라우저를 제한을 풀어주는 역할
@RestController
// JSON 반환
public class BoardRest {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/rest/localNameList")
	public List<Map<String,Object>> getLocalNameList() {
		
		return boardService.getLocalNameList();
	}
}
