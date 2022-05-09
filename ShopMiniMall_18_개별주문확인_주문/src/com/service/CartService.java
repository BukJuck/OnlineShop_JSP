package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dto.CartDTO;
import com.dto.OrderDTO;

public class CartService {

	public int cartAdd(CartDTO xx) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAdd(session, xx);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return n;
	}

	public List<CartDTO> cartList(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<CartDTO> list = null;
		try {
			CartDAO dao = new CartDAO();
			list = dao.cartList(session, userid);
		} finally {
			session.close();
		}
		return list;
	}

	public int cartDel(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartDel(session, num);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return n;
	}

	public int cartUpdate(HashMap<String, Integer> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartUpdate(session, map);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return n;
	}

	public int cartAllDel(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAllDel(session, list);
			session.commit();
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return n;
	}

	public CartDTO cartbyNum(String num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDTO dto = null;
		try {
			CartDAO dao = new CartDAO();
			dto = dao.cartbyNum(session, num);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		return dto;
	
	}

	public int orderDone(OrderDTO dto2, String orderNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n= dao.orderDone(session, dto2);
			System.out.println("insert: "+n);
			n = dao.cartDel(session, Integer.parseInt(orderNum));
			System.out.println("delete: "+n);
			session.commit();
		}catch (Exception e) {
			session.rollback();
			System.out.println("rollback됨"+e);
		}finally {
			// TODO: handle finally clause
			session.close();
		}
		return n;
	}

}
