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
		
	  Workbook workbook = new HSSFWorkbook();				// Workbook ���� ( ���� ����)
	  
	  Sheet sheet = workbook.createSheet("��� ���");		// �ϳ��� ��Ʈ�� ����
	  
	  int rowNo = 0;		// row number�� ī���� �ϱ� ���� ����
	  
	  Row headerRow = sheet.createRow(rowNo++);		// ��Ʈ�� ����(header) �κ� �����ϰ� �� �߰�
	  
	  headerRow.createCell(0).setCellValue("�̸�");
	  headerRow.createCell(1).setCellValue("ID");
	  headerRow.createCell(2).setCellValue("����");
	  headerRow.createCell(3).setCellValue("����ó");
	  headerRow.createCell(4).setCellValue("�̸���");
	  headerRow.createCell(5).setCellValue("�ּ�");
	  
	  List<ExcelVO> list = service.excelList(param);
		
		
	  for (ExcelVO excelVO : list) { System.out.println(excelVO); }
		 
	  
	  for (ExcelVO excelVO : list) {								// �ݺ����� ���ؼ� DB ���� �࿡ �Է�
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
	

