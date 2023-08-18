package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Service
@Transactional // 트렌젝션 실행시에 전부 성공하거나 실패하거나 하는 기능
public class BoardService {
	@Autowired //의존성 주입을 수행하는데 사용
	private BoardMapper boardMapper;
	@Autowired //의존성 주입을 수행하는데 사용
	private BoardfileMapper boardfileMapper;
	
	// REST API Chart 호출용
	public List<Map<String,Object>> getLocalNameList() {
		
		return boardMapper.selectLocalNameList();
	}
	
	
	// 보드 삭제
	public int removeBoard(Board board) {
		int row = boardfileMapper.deleteBoardFile(board);
		row = boardMapper.deleteBoard(board);
		
		return row;
	}
	
	// 보드 수정
	public int modifyBoard(Board board) {
		System.out.println("modify수정-->"+boardMapper.updateBoard(board));
		return boardMapper.updateBoard(board);
	}
	
	// 보드 추가
	public int addBoard(Board board,String path) {
		int row = boardMapper.insertBoard(board);
		
		// 첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		System.out.println("fileList.size-->"+fileList.size());
		// board 입력이 성공하고 
		if(row==1 && fileList != null && fileList.size()>0) {
			int boardNo = board.getBoardNo();
			
			// 첨부된 파일의 갯수만 큼 반복
			for(MultipartFile mf : fileList) {
				System.out.println("파일업로드 반복 실행");
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); //부모키값
				bf.setOriginFilename(mf.getOriginalFilename()); //파일원본
				bf.setFilesize(mf.getSize()); // 파일 사이즈
				bf.setFiletype(mf.getContentType()); // 파일타입
				// 저장될 파일이름(.뒤에 있는 문자열만 자르기)
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
				// 새로운 이름+확장자(UUID + 확장자)
				bf.setSaveFilename(UUID.randomUUID().toString().replace("-","") + ext);
				
				// 테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				// 파일 저장(저장위치 -> path변수)
				//path 위치에 저장파일이름으로 빈파일을 생성
				File f = new File(path+bf.getSaveFilename()); 
				// 빈파일에 첨부된 파일의 스트림을 주입한다
				try {
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					// 트랜젝션 작동을 위해 예외(try catch를 강요하지 않는 예외 ex)RuntimeException) 발생이 필요
					throw new RuntimeException();
				}
				
			} // for문 닫기
		} // if문 닫기
		
		return row;
	}
	
	// 보드상세보기
	public Board getBoardOne(Board board) {
		Board boardOne = boardMapper.selectBoardOne(board);
		return boardOne;
	}
	
	// 보드 리스트
	public Map<String,Object> getBoardList(int currentPage, int rowPerPage, String localName) {
		
		// service layer 역할1: controller가 넘겨진 매개값을 dao의 매개값에 맞게 가공
		int beginRow = (currentPage-1)*rowPerPage;
		
		// 반환값 1
		List<Map<String,Object>> localNameList = boardMapper.selectLocalNameList();
		
		// 반환값 2
		Map<String,Object> paramMap = new HashMap<String,Object>();
		// 임시로 데이터를 담아둘 paramMap 변수선언
		paramMap.put("beginRow",beginRow);
		paramMap.put("rowPerPage",rowPerPage);
		paramMap.put("localName",localName);
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		// 반환값 3
		// service의 layer 역할2 : dao가 넘겨진 반환받은 값을 가공하여 controller에 반환
		int boardCount = boardMapper.selectBoardCount();
		int lastPage = boardCount / rowPerPage;
		if((boardCount%rowPerPage) != 0) {
			lastPage++;
		}
		
		// 반환값 1,2를 합쳐서 resultMap 반환
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("localNameList",localNameList);
		resultMap.put("boardList",boardList);
		resultMap.put("lastPage",lastPage);
		
		return resultMap;
	}
}
