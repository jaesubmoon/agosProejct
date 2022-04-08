package com.agos.agw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.agos.agw.model.ExcelVO;
import com.agos.agw.model.PagingVO;
import com.agos.agw.model.UserVO;

public interface IUserDAO {
	
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
	
	// 총 사용자 수
	public int countUser();
	
	// 페이징 처리 
	public List<UserVO> listUserPaging(PagingVO pagingvo);	
	public List<UserVO> listRequestPaging(PagingVO pagingvo);
	
	// 검색
	ArrayList<UserVO> userSearch(HashMap<String, Object> map);	// 사용자 검색
	
	public ArrayList<UserVO> userSearchPaging(PagingVO pagingvo); 	// 검색 후 페이징 처리
	
	//엑셀 리스트
	ArrayList<ExcelVO> excelList(HashMap<String, Object> map);
	
	//사용자 상세 정보
	ArrayList<UserVO> userDetail(int usr_idx);
	
}
