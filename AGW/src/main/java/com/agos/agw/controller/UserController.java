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
	
	// ��ü ����� ��ȸ
	@RequestMapping("/UserAllList")
	public String UserAllList(Model model) {
		ArrayList<UserVO> userList = service.listAllUser();
		model.addAttribute("userList", userList);
		
		return "userListForm";
	}
	
	// ����� ��û ������ �ҷ�����
	@RequestMapping("/userRequestList")
	public String userRequestList(Model model) {
		ArrayList<UserVO> userList = service.listRequestUser();
		model.addAttribute("userList", userList);
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
		String[] ajaxMsg = request.getParameterValues("chkArr");							//���� �迭�� �޾ƿ� data�� �Ѹ���
		int size = ajaxMsg.length;																	//�迭 ũ�⸸ŭ ���� �ݺ�
		for(int i = 0 ; i < size ; i++) {
			service.deleteUser(ajaxMsg[i]);															//ajaxMsg �迭�� usr_idx���� ����ִ�
		}
		return "redirect:/UserAllList";
	}
	
//	// ����� ����
//	@RequestMapping("/approveUser")
//	public String approveUser(UserVO userVO) {
//		service.approveUser(userVO);
//		return "redirect:/UserAllList";
//	}
}
