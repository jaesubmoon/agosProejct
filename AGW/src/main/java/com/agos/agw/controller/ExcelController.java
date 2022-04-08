package com.agos.agw.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
		
	  XSSFWorkbook workbook = new XSSFWorkbook();				// Workbook ���� ( ���� ����)
	  
	  XSSFSheet sheet = workbook.createSheet("��� ���");		// �ϳ��� ��Ʈ�� ����
	  
	  // workbook ��ü�� createCellStyle �� ���� cell ��Ÿ���� �����մϴ�. 
	  XSSFCellStyle style = workbook.createCellStyle();
	  XSSFFont font = workbook.createFont();
	  
	  // ���ϴ� ��Ʈ�� �����ϰ� Font��ü�� CellStyle ��ü�� �����մϴ�. 
	  font.setFontName("���� ���");
	  style.setFont(font);
	  
	  //�� ����
	  sheet.addMergedRegion(new CellRangeAddress(0,0,1,6)); //�����, ������, ������, ������ (�ڹٹ迭�� ���� 0���� ����)
	  sheet.addMergedRegion(new CellRangeAddress(1,1,1,6));
	  sheet.addMergedRegion(new CellRangeAddress(0,1000,0,0));
	  sheet.addMergedRegion(new CellRangeAddress(0,1000,7,100));
	  
	  sheet.setDefaultColumnWidth(13); 					// sheet ��ü �⺻ �ʺ���
	  sheet.setColumnWidth(0, 9000);
	  sheet.setColumnWidth(4, 6000); 					// Ư�� cell ���� => 5��°(e) cell 2100=7.63
	  sheet.setColumnWidth(5, 6000); 
	  sheet.setColumnWidth(6, 9000);
	  
	  
	
	  int rowNo = 0;		// row number�� ī���� �ϱ� ���� ����
	  
	  Row mainTitleRow = sheet.createRow(rowNo++);
	 
	  createCell(workbook, mainTitleRow, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);		// ������ ���� �Լ��� �̿��� �� ����
	  createCell(workbook, mainTitleRow, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, mainTitleRow, 3, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);	
	  createCell(workbook, mainTitleRow, 4, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);	
	  createCell(workbook, mainTitleRow, 5, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);	
	  createCell(workbook, mainTitleRow, 6, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"��� ����"
			  			,FillPatternType.FINE_DOTS, IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);	
	  
	  mainTitleRow.setHeight((short)1000);		// �� ���� ����
	  
	  Row nullRow = sheet.createRow(rowNo++);			// �� �� �� ����
	     
	  nullRow.createCell(1).setCellValue("");;
	  
	  Row headerRow = sheet.createRow(rowNo++);		// ��Ʈ�� ����(header) �κ� �����ϰ� �� �߰�
	  
	  createCell(workbook, headerRow, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"�̸�"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, headerRow, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"ID"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, headerRow, 3, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"����"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, headerRow, 4, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"����ó"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, headerRow, 5, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"�̸���"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	  createCell(workbook, headerRow, 6, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,"�ּ�"
			  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THICK);
	 
	  headerRow.setHeight((short)600);
	  
	  List<ExcelVO> list = service.excelList(param);
		
		
	  //for (ExcelVO excelVO : list) { System.out.println(excelVO); }
		 
	  int colorPattern = 1;											// �����Ǵ� ���� �����ϱ� ���� ����
	  for (ExcelVO excelVO : list) {								// �ݺ����� ���ؼ� DB ���� �࿡ �Է�
		  if(colorPattern % 2 == 1) {
		  Row row = sheet.createRow(rowNo++);
		  
		  createCell(workbook, row, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_nm()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
		  createCell(workbook, row, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_id()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
		  createCell(workbook, row, 3, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_position()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
	  	  createCell(workbook, row, 4, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_phone()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
		  createCell(workbook, row, 5, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_email()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
		  createCell(workbook, row, 6, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_address()
				  			,FillPatternType.FINE_DOTS,IndexedColors.WHITE, BorderStyle.THIN);
		  colorPattern ++;
		  }else {
			  
		  Row row = sheet.createRow(rowNo++);
		  
		  createCell(workbook, row, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_nm()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
		  createCell(workbook, row, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_id()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
		  createCell(workbook, row, 3, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_position()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
	  	  createCell(workbook, row, 4, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_phone()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
		  createCell(workbook, row, 5, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_email()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
		  createCell(workbook, row, 6, HorizontalAlignment.CENTER, VerticalAlignment.CENTER,excelVO.getUsr_address()
				  			,FillPatternType.FINE_DOTS,IndexedColors.GREY_25_PERCENT, BorderStyle.THIN);
		  colorPattern ++;
		  }
	  }
	  
	  response.setContentType("ms-vnd/excel");
      response.setHeader("Content-Disposition", "attachment;filename=userlist.xlsx");		// ������ ���� ���ϸ�
      
      workbook.write(response.getOutputStream());
      workbook.close();
		 
				
	}
	private void createCell(XSSFWorkbook workbook, Row rowName, int column, HorizontalAlignment halign,
			VerticalAlignment valign, String value, FillPatternType pattern, IndexedColors color, BorderStyle border) {
		 	Cell cell = rowName.createCell(column);
		    cell.setCellValue(value);
		    CellStyle cellStyle = workbook.createCellStyle();
		    cellStyle.setFillForegroundColor(color.getIndex());
		    cellStyle.setFillPattern(pattern);
		    cellStyle.setBorderTop(border);
		    cellStyle.setBorderBottom(border);
		    cellStyle.setAlignment(halign);
		    cellStyle.setVerticalAlignment(valign);
		    cell.setCellStyle(cellStyle);
	}
}
	

