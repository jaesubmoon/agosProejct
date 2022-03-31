package com.agos.agw.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.agos.agw.model.UserVO;

public interface IUserService {
	
	// CRUD
	void insertUser(UserVO userVO);
	void updateUser(UserVO userVO);
	void deleteUser(String usr_idx);
	
	// 사용자 승인
	void approveUser(UserVO userVO);
	// 사용자 전체 조회
	ArrayList<UserVO> listAllUser();
	// 대기자 조회
	ArrayList<UserVO> listRequestUser();
	// 사용자 검색
	ArrayList<UserVO> searchUser(HashMap<String, Object> map);

}
