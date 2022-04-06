package com.agos.agw.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agos.agw.model.PagingVO;
import com.agos.agw.model.UserVO;
import com.agos.agw.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	// ��ü ����� ��ȸ
	@RequestMapping("/UserAllList")
	public String UserAllList(Model model
									, @RequestParam(value="nowPage", required=false)String nowPage
									, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		// ����¡
		int total = service.countUser();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "20";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "20";
		}
		
		PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		System.out.println("total : "+total);
		
		model.addAttribute("total", total);											// �� ��� �� ����� ����
		
		model.addAttribute("paging", vo);
		model.addAttribute("userList", service.listUserPaging(vo));			// mapper���� listUserPaging
//		ArrayList<UserVO> userList = service.listAllUser();
//		model.addAttribute("userList", userList);
		
		return "userListForm";
	}
	
	// �˻� ���
	@RequestMapping("/userSearch")
	public String userSearch(@RequestParam(value="searchType", required=false) String searchType
										 ,@RequestParam(value="searchKeyword", required=false) String searchKeyword
										 ,@RequestParam(value="nowPage", required=false)String nowPage
										 ,@RequestParam(value="cntPerPage", required=false)String cntPerPage
										 ,@RequestParam(value="morePage", required=false)String morePage
										 ,Model model) {
		
		System.out.println("---------------------------------------");
		
		
		if(searchType == null)
			searchType = "";
		if(searchKeyword == null)
			searchKeyword = "";
		
		 // ����¡		
		 if (nowPage == null && cntPerPage == null) { 
			  nowPage = "1"; cntPerPage = "20";
		 } else if (nowPage == null) { 
			  nowPage = "1"; 
		 } else if (cntPerPage == null) {
			  cntPerPage = "20"; }
		 
		 HashMap<String,Object> param = new HashMap<String,Object>();
		 param.put("searchType", searchType);
		 param.put("searchKeyword", searchKeyword);
		 
		 //System.out.println(param.get("searchType"));
		
		 ArrayList<UserVO> userList = service.userSearch(param);
		 
		 //System.out.println(userList);			
		 
		 int total = userList.size();
		 System.out.println(total);			// 177
		 
		 PagingVO paging = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		 
		 paging.setSearchType((String)param.get("searchType"));
		 paging.setSearchKeyword((String)param.get("searchKeyword"));
		 
		 //System.out.println(paging.getSearchKeyword());		// test
		 
		 //System.out.println(searchType);
		 userList = service.userSearchPaging(paging);
		 
		 total = userList.size();
		 //System.out.println(total);	
		 
		 //System.out.println(userList);		
		 
		 //System.out.println(paging);
		 
		 //System.out.println((String)param.get("searchKeyword"));	
		 
		 model.addAttribute("paging", paging);
		 model.addAttribute("userList", service.userSearchPaging(paging));
			
		 model.addAttribute("searchType", (String)param.get("searchType"));
		 model.addAttribute("searchKeyword", (String)param.get("searchKeyword"));
		 
		 
		return "userSearchResultView";
	}
	
	// ����� ��û ������ �ҷ�����
	@RequestMapping("/userRequestList")
	public String userRequestList(Model model
											, @RequestParam(value="nowPage", required=false)String nowPage
											, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		// ����¡
		int total = service.countUser();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "20";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "20";
		}
		
		PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		System.out.println("total : "+total);
		
		model.addAttribute("paging", vo);
		
		model.addAttribute("userList", service.listRequestPaging(vo));
//		ArrayList<UserVO> userList = service.listRequestUser();
//		model.addAttribute("userList", userList);
		return "userRequestListForm";
	}
	
	
	// ����� ����
	//	@RequestMapping(value="/userDelete", method = RequestMethod.GET)
	//	public String userDelete(String usr_idx) {
	//		service.deleteUser(usr_idx);
	//		return "redirect:/UserAllList";
	//	}
	
	// ����� ���� ����
	@RequestMapping(value="/userSelectDelete")
	public String userDelete(HttpServletRequest request) throws Exception {		//HttpServletRequest�� ���� ajax���� ���� data�� �޾Ƴ���.
		String[] ajaxMsg = request.getParameterValues("chkArr");						//���� �迭�� �޾ƿ� data�� �Ѹ���
		int size = ajaxMsg.length;																	//�迭 ũ�⸸ŭ ���� �ݺ�
		for(int i = 0 ; i < size ; i++) {
			service.deleteUser(ajaxMsg[i]);														//ajaxMsg �迭�� usr_idx���� ����ִ�
		}
		return "redirect:/UserAllList";
	}
	
	
	  // ����� ���� ���� 
	  @RequestMapping(value="/userSelectUpdate")
	  public String userSelectUpdate(HttpServletRequest request) throws Exception {
		  
		 UserVO vo = new UserVO();
		  
		 String[] ajaxIdx = request.getParameterValues("idxArr");
		 String[] ajaxPosition = request.getParameterValues("positionArr");
		 String[] ajaxRight = request.getParameterValues("rightArr");
		
		 int size = ajaxIdx.length;
		 
		 //System.out.println("size : " + size);	// ���� ���� Ȯ��
		 
		 for(int i = 0 ; i < size ; i ++) {
			 
			vo.setUsr_idx(ajaxIdx[i]);
			vo.setUsr_position(ajaxPosition[i]);
			vo.setUsr_right(ajaxRight[i]);
			
			//System.out.println("vo : " + vo);  // vo set Ȯ��
			
			service.updateUser(vo);
		 }
	
		  return "redirect:/UserAllList";
	  }
	  
	  // ����� ��û ���� ����
	  @RequestMapping(value="/userSelectApprove")
	  public String userSelectApprove(HttpServletRequest request) throws Exception {
		  
		 UserVO vo = new UserVO();
		  
		 String[] ajaxIdx = request.getParameterValues("idxArr");
		 String[] ajaxPosition = request.getParameterValues("positionArr");
		 String[] ajaxRight = request.getParameterValues("rightArr");
		
		 int size = ajaxIdx.length;
		 
		 //System.out.println("size : " + size);	// ���� ���� Ȯ��
		 
		 for(int i = 0 ; i < size ; i ++) {
			 
			vo.setUsr_idx(ajaxIdx[i]);
			vo.setUsr_position(ajaxPosition[i]);
			vo.setUsr_right(ajaxRight[i]);
			
			//System.out.println("vo : " + vo);  // vo set Ȯ��
			
			service.approveUser(vo);
		 }
	
		  return "redirect:/UserAllList";
	  }
	 
}
