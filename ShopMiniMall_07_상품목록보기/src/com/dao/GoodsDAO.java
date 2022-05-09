package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dto.GoodsDTO;

public class GoodsDAO {

	public List<GoodsDTO> goodsList(SqlSession session, String string) {
		List<GoodsDTO> list = session.selectList("GoodsMapper.goodsList", string); 
		return list;
	}

}
