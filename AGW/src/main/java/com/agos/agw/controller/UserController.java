package com.agos.agw.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
	
	// 전체 사용자 조회
	@RequestMapping("/UserAllList")
	public String UserAllList(Model model
									, @RequestParam(value="nowPage", required=false)String nowPage
									, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		// 페이징
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
		model.addAttribute("userList", service.listUserPaging(vo));			// mapper에서 listUserPaging
//		ArrayList<UserVO> userList = service.listAllUser();
//		model.addAttribute("userList", userList);
		
		return "userListForm";
	}
	
	// 검색 기능
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
		
		 // 페이징		
		 if (nowPage == null && cntPerPage == null) { 
			  nowPage = "1"; cntPerPage = "20";
		 } else if (nowPage == null) { 
			  nowPage = "1"; 
		 } else if (cntPerPage == null) {
			  cntPerPage = "20"; }
		 
		 HashMap<String,Object> param = new HashMap<String,Object>();
		 param.put("searchType", searchType);
		 param.put("searchKeyword", searchKeyword);
		
		 ArrayList<UserVO> userList = service.userSearch(param);
		 
		 System.out.println(userList);			
		 
		 int total = userList.size();
		 System.out.println(total);			// 177
		 
		 PagingVO paging = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		 
		 paging.setSearchType((String)param.get("searchType"));
		 paging.setSearchKeyword((String)param.get("searchKeyword"));
		 
		 System.out.println(paging.getSearchKeyword());		// test
		 
		 userList = service.userSearchPaging(paging);
		 
		 total = userList.size();
		 System.out.println(total);		// 20
		 
		 System.out.println(userList);		
		 
		 System.out.println(paging);
		 
		 System.out.println((String)param.get("searchKeyword"));	
		 
		 model.addAttribute("paging", paging);
		 model.addAttribute("userList", service.userSearchPaging(paging));
			
		 model.addAttribute("searchType", (String)param.get("searchType"));
		 model.addAttribute("searchKeyword", (String)param.get("searchKeyword"));
		 
		 
		return "userSearchResultView";
	}
	
	// 사용자 신청 페이지 불러오기
	@RequestMapping("/userRequestList")
	public String userRequestList(Model model
											, @RequestParam(value="nowPage", required=false)String nowPage
											, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		// 페이징
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
	
	
	// 사용자 삭제
	//	@RequestMapping(value="/userDelete", method = RequestMethod.GET)
	//	public String userDelete(String usr_idx) {
	//		service.deleteUser(usr_idx);
	//		return "redirect:/UserAllList";
	//	}
	
	// 사용자 선택 삭제
	@RequestMapping(value="/userSelectDelete")
	public String userDelete(HttpServletRequest request) throws Exception {		//HttpServletRequest를 통해 ajax에서 보낸 data를 받아낸다.
		String[] ajaxMsg = request.getParameterValues("chkArr");						//문자 배열에 받아온 data를 뿌린다
		int size = ajaxMsg.length;																	//배열 크기만큼 삭제 반복
		for(int i = 0 ; i < size ; i++) {
			service.deleteUser(ajaxMsg[i]);															//ajaxMsg 배열에 usr_idx값이 들어있다
		}
		return "redirect:/UserAllList";
	}
	
//	// 사용자 선택 승인 ( 난이도가 있어서 보류)
//	@RequestMapping(value="/userSelectApprove")
//	public String userSelectApprove(HttpServletRequest request, UserVO userVO) throws Exception {
//		List<UserVO> ajaxMsg = request.getParameterValues("chkArr")					// string[] 타입을 변환해야한다....
//		service.approveUser(userVO);
//		return "redirect:/UserAllList";
//	}
}
