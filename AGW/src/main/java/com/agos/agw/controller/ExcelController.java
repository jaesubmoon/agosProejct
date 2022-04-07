package com.agos.agw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agos.agw.model.ExcelVO;
import com.agos.agw.service.UserService;

@Controller
public class ExcelController {
	
	@Autowired
	UserService service;
	@RequestMapping(value= {"/excel/{searchType}/{searchKeyword}", "/excel/{searchType}"}) 
	public void downloadExcel (HttpServletResponse response
										,@PathVariable(required = false) String searchType
										,@PathVariable(required = false) String searchKeyword) throws IOException {
		
	  if(searchType == null)
		searchType = "";
	  if(searchKeyword == null)
		searchKeyword = "";
	  
	  HashMap<String,Object> param = new HashMap<String,Object>();
	  param.put("searchType", searchType);
	  param.put("searchKeyword", searchKeyword);
		
	  Workbook workbook = new HSSFWorkbook();				// Workbook 생성 ( 엑셀 파일)
	  
	  Sheet sheet = workbook.createSheet("사원 목록");		// 하나의 시트를 만듬
	  
	  int rowNo = 0;		// row number를 카운팅 하기 위한 변수
	  
	  Row headerRow = sheet.createRow(rowNo++);		// 시트의 제목(header) 부분 생성하고 행 추가
	  
	  headerRow.createCell(0).setCellValue("이름");
	  headerRow.createCell(1).setCellValue("ID");
	  headerRow.createCell(2).setCellValue("직급");
	  headerRow.createCell(3).setCellValue("연락처");
	  headerRow.createCell(4).setCellValue("이메일");
	  headerRow.createCell(5).setCellValue("주소");
	  
	  List<ExcelVO> list = service.excelList(param);
		
		
	  for (ExcelVO excelVO : list) { System.out.println(excelVO); }
		 
	  
	  for (ExcelVO excelVO : list) {								// 반복문을 통해서 DB 값을 행에 입력
		  Row row = sheet.createRow(rowNo++);
		  
		  row.createCell(0).setCellValue(excelVO.getUsr_nm());
          row.createCell(1).setCellValue(excelVO.getUsr_id());
          row.createCell(2).setCellValue(excelVO.getUsr_position());
          row.createCell(3).setCellValue(excelVO.getUsr_phone());
          row.createCell(4).setCellValue(excelVO.getUsr_email());
          row.createCell(5).setCellValue(excelVO.getUsr_address());
	  }
	  
	  response.setContentType("ms-vnd/excel");
      response.setHeader("Content-Disposition", "attachment;filename=userlist.xls");
      
      workbook.write(response.getOutputStream());
      workbook.close();
		 
				
	}
}
	

