package com.agos.agw.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agos.agw.model.UserVO;
import com.agos.agw.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	// 전체 사용자 조회
	@RequestMapping("/UserAllList")
	public String UserAllList(Model model) {
		ArrayList<UserVO> userList = service.listAllUser();
		model.addAttribute("userList", userList);
		
		return "userListForm";
	}
	
	// 사용자 신청 페이지 불러오기
	@RequestMapping("/userRequestList")
	public String userRequestList(Model model) {
		ArrayList<UserVO> userList = service.listRequestUser();
		model.addAttribute("userList", userList);
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
		String[] ajaxMsg = request.getParameterValues("chkArr");							//문자 배열에 받아온 data를 뿌린다
		int size = ajaxMsg.length;																	//배열 크기만큼 삭제 반복
		for(int i = 0 ; i < size ; i++) {
			service.deleteUser(ajaxMsg[i]);															//ajaxMsg 배열에 usr_idx값이 들어있다
		}
		return "redirect:/UserAllList";
	}
	
//	// 사용자 승인
//	@RequestMapping("/approveUser")
//	public String approveUser(UserVO userVO) {
//		service.approveUser(userVO);
//		return "redirect:/UserAllList";
//	}
}
