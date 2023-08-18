package com.goodee.mvcboard.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Board;

@Mapper
public interface BoardMapper {

	int updateBoard(Board board);
	
	int deleteBoard(Board board);
	
	int insertBoard(Board board);
	
	Board selectBoardOne(Board board);
	
	List<Map<String,Object>> selectLocalNameList();
	
	List<Board> selectBoardListByPage(Map<String, Object>map);
	
	// 전체행의 수
	int selectBoardCount();
}
