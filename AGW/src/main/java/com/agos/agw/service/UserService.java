package com.agos.agw.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.agos.agw.dao.IUserDAO;
import com.agos.agw.model.ExcelVO;
import com.agos.agw.model.PagingVO;
import com.agos.agw.model.UserVO;

@Service
public class UserService implements IUserService {
	
	@Autowired
	@Qualifier("IUserDAO")
	IUserDAO dao;

	@Override
	public void insertUser(UserVO userVO) {
		dao.insertUser(userVO);

	}

	@Override
	public void updateUser(UserVO userVO) {
		dao.updateUser(userVO);

	}
	
	@Override
	public void deleteUser(String usr_idx) {
		dao.deleteUser(usr_idx);
	}
	
	@Override
	public void approveUser(UserVO userVO) {
		dao.approveUser(userVO);

	}

	@Override
	public ArrayList<UserVO> listAllUser() {
		
		return dao.listAllUser();
	}
	
	
	@Override
	public ArrayList<UserVO> listRequestUser() {
		
		return dao.listRequestUser();
	}
	
	

	@Override
	public ArrayList<UserVO> searchUser(HashMap<String, Object> map) {
		
		return dao.searchUser(map);
	}
	
	@Override
	public int countUser() {
		
		return dao.countUser();
	}
	
	@Override
	public List<UserVO> listUserPaging(PagingVO pagingvo) {
		
		
		return dao.listUserPaging(pagingvo);
	}
	
	@Override
	public List<UserVO> listRequestPaging(PagingVO pagingvo) {
		
		
		return dao.listRequestPaging(pagingvo);
	}
	
	@Override
	public ArrayList<UserVO> userSearch(HashMap<String, Object> map) {
		
		return dao.userSearch(map);
	}
	
	@Override
	public ArrayList<UserVO> userSearchPaging(PagingVO pagingvo) {
		
		return dao.userSearchPaging(pagingvo);
	}
	
	@Override
	public ArrayList<ExcelVO> excelList(HashMap<String, Object> map) {
		
		return dao.excelList(map);
	}
	
	@Override
	public ArrayList<UserVO> userDetail(int usr_idx) {
		
		return dao.userDetail(usr_idx);
	}

}
