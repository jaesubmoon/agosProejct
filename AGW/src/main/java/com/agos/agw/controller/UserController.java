package com.agos.agw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agos.agw.model.PagingVO;
import com.agos.agw.model.UserVO;
import com.agos.agw.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired
	JavaMailSender javaMailSender;

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
		
		model.addAttribute("total", total);											// 총 사원 수 출력을 위함
		
		model.addAttribute("paging", vo);
		model.addAttribute("userList", service.listUserPaging(vo));			// mapper에서 listUserPaging
//		ArrayList<UserVO> userList = service.listAllUser();
//		model.addAttribute("userList", userList);
		
		return "/UserAdmin/userListForm";
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
		 
		 
		return "/UserAdmin/userSearchResultView";
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
		return "/UserAdmin/userRequestListForm";
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
			service.deleteUser(ajaxMsg[i]);														//ajaxMsg 배열에 usr_idx값이 들어있다
		}
		return "redirect:/UserAllList";
	}
	
	
	  // 사용자 선택 수정 
	  @RequestMapping(value="/userSelectUpdate")
	  public String userSelectUpdate(HttpServletRequest request) throws Exception {
		  
		 UserVO vo = new UserVO();
		  
		 String[] ajaxIdx = request.getParameterValues("idxArr");
		 String[] ajaxPosition = request.getParameterValues("positionArr");
		 String[] ajaxRight = request.getParameterValues("rightArr");
		
		 int size = ajaxIdx.length;
		 
		 //System.out.println("size : " + size);	// 선택 개수 확인
		 
		 for(int i = 0 ; i < size ; i ++) {
			 
			vo.setUsr_idx(ajaxIdx[i]);
			vo.setUsr_position(ajaxPosition[i]);
			vo.setUsr_right(ajaxRight[i]);
			
			//System.out.println("vo : " + vo);  // vo set 확인
			
			service.updateUser(vo);
		 }
	
		  return "redirect:/UserAllList";
	  }
	  
	  // 사용자 요청 선택 승인
	  @RequestMapping(value="/userSelectApprove")
	  public String userSelectApprove(HttpServletRequest request) throws Exception {
		  
		 UserVO vo = new UserVO();
		  
		 String[] ajaxIdx = request.getParameterValues("idxArr");
		 String[] ajaxPosition = request.getParameterValues("positionArr");
		 String[] ajaxRight = request.getParameterValues("rightArr");
		
		 int size = ajaxIdx.length;
		 
		 //System.out.println("size : " + size);	// 선택 개수 확인
		 
		 for(int i = 0 ; i < size ; i ++) {
			 
			vo.setUsr_idx(ajaxIdx[i]);
			vo.setUsr_position(ajaxPosition[i]);
			vo.setUsr_right(ajaxRight[i]);
			
			//System.out.println("vo : " + vo);  // vo set 확인
			
			service.approveUser(vo);
		 }
	
		  return "redirect:/UserAllList";
	  }
	  
	  // 상세 정보
	  @RequestMapping(value="/userDetail") 
	  public String userDetail (Model model
			  							,@RequestParam(value="usr_idx") int usr_idx)  throws Exception { 
		  
		  model.addAttribute("userList",service.userDetail(usr_idx));
		  
		  return "/UserAdmin/detailView";
	  }
	  
	  // 사원 신청 폼
	  @RequestMapping(value="/joinForm")
	  public String joinForm () {
		  
		  return "/joinView";
	  }
	  
	  // 사원 신청
	  @RequestMapping(value="/userJoin")
	  public String userJoin(UserVO userVO) {
		  	  
		  service.insertUser(userVO);
		  
		  return "redirect:/userRequestList";
	  }
	  
	  // 이메일 인증
	  @RequestMapping(value="/checkMail")
	  @ResponseBody
	  public String sendMail(String mail) {
		  
		  Random random = new Random();		//난수 생성을 위한 랜덤 클래스
		  String key = "";					// 인증번호
		  
		  SimpleMailMessage message = new SimpleMailMessage();
		  message.setTo(mail);				// 인증 메일을 받을 메일 값
		  
		  //입력 키를 위한 코드
		  for(int i=0 ; i<3 ; i++) {
			  int index = random.nextInt(25) + 65;	// A~Z까지 랜덤 알파벳 생성
			  key += (char)index;
		  }
		  int numIndex = random.nextInt(9999) + 1000;	// 4자리 랜덤 정수를 생성
		  key += numIndex;
		  message.setSubject("AGOS 사원등록을 위한 인증번호 전송");
		  message.setText("인증 번호 : " + key + "\n사원 등록 신청페이지에 인증번호를 입력해주세요.");
		  
		  javaMailSender.send(message);
		  
		  System.out.println(key); //인증번호 확인
		 
		  return key;
	  }
	  

}








