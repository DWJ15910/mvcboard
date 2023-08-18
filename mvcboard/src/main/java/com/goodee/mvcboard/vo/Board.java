package com.goodee.mvcboard.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
   private int boardNo;
   private String localName;
   private String boardTitle;
   private String boardContent;
   private String memberId;
   private String createdate;
   private String updatedate;
   
   
   private List<MultipartFile> multipartFile;
   // jsp에서 multipartFile을 input name으로 잡아서 실행
}