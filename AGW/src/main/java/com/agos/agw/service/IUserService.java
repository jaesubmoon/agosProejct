package com.agos.agw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.agos.agw.model.ExcelVO;
import com.agos.agw.model.PagingVO;
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
	
	// �� ����� ��
	public int countUser();
	
	// ����¡ ó�� 
	public List<UserVO> listUserPaging(PagingVO pagingvo);
	public List<UserVO> listRequestPaging(PagingVO pagingvo);
	
	// �˻�
	ArrayList<UserVO> userSearch(HashMap<String, Object> map);	// ����� �˻�
	
	public ArrayList<UserVO> userSearchPaging(PagingVO pagingvo); 	// �˻� �� ����¡ ó��
	
	//���� ����Ʈ
	ArrayList<ExcelVO> excelList();
}
