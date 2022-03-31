package com.agos.agw.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.agos.agw.model.UserVO;

public interface IUserService {
	
	// CRUD
	void insertUser(UserVO userVO);
	void updateUser(UserVO userVO);
	void deleteUser(String usr_idx);
	
	// ����� ����
	void approveUser(UserVO userVO);
	// ����� ��ü ��ȸ
	ArrayList<UserVO> listAllUser();
	// ����� ��ȸ
	ArrayList<UserVO> listRequestUser();
	// ����� �˻�
	ArrayList<UserVO> searchUser(HashMap<String, Object> map);

}
