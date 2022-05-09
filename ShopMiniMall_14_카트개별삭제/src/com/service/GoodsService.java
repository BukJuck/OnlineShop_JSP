package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.GoodsDAO;
import com.dto.GoodsDTO;

public class GoodsService {

	public List<GoodsDTO> goodsList(String string) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<GoodsDTO> list = null;
		try {
			GoodsDAO dao = new GoodsDAO();
			list = dao.goodsList(session, string);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		SqlSession session = MySqlSessionFactory.getSession();
		GoodsDTO dto = null;
		try {
			GoodsDAO dao = new GoodsDAO();
			dto = dao.goodsRetrieve(session, gCode);
		} finally {
			session.close();
		}
		return dto;
	}

}
