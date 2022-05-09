package com.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.dto.MemberDTO;

public class MemberDAO {
	
	public int memberAdd(SqlSession session, MemberDTO dto) {
		int n = session.insert("MemberMapper.memberAdd", dto);
		return n;
	}

	public int idCheck(SqlSession session, String userid) {
		int count = session.selectOne("MemberMapper.idCheck", userid);
		return count;
	}

	public MemberDTO login(SqlSession session, HashMap<String, String> map) {
		MemberDTO n = session.selectOne("MemberMapper.login", map);
		return n;
	}

	public MemberDTO mypage(SqlSession session,String userid) {
		 MemberDTO n = session.selectOne("MemberMapper.mypage", userid);
		 return n;
	   }

	public int memberUpdate(SqlSession session, MemberDTO dto2) {
		int num = session.update("MemberMapper.memberUpdate", dto2);
		return num;
	}


}
