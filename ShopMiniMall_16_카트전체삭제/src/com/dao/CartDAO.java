package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.CartDTO;

public class CartDAO {

	public int cartAdd(SqlSession session, CartDTO xx) {
		int n = session.insert("CartMapper.cartAdd", xx);
		return n;
	}

	public List<CartDTO> cartList(SqlSession session, String userid) {
		List<CartDTO> list = session.selectList("CartMapper.cartList", userid);
		return list;
	}

	public int cartDel(SqlSession session, int num) {
		int n = session.delete("CartMapper.cartDel", num);
		return n;
	}

	public int cartUpdate(SqlSession session, HashMap<String, Integer> map) {
		int n = session.update("CartMapper.cartUpdate", map);
		return n;
	}

	public int cartAllDel(SqlSession session, List<String> list) {
		int n = session.delete("CartMapper.cartAllDel", list);
		return n;
	}

}
