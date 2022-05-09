package com.controller.goods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.CartService;

/**
 * Servlet implementation class CartDelAllServlet
 */
@WebServlet("/CartDelAllServlet2")
public class CartDelAllServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDelAllServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); //회원전용이니까 회원세션 가져오기
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		
		if(dto!=null) {
			String [] data = request.getParameterValues("check");
			//System.out.println(data.length);
			List<String> list = Arrays.asList(data); //배열로 바꾸는 이유 : list :in 사용하기 위해
			CartService service = new CartService();
			int n = service.cartAllDel(list);//전체 삭제
			System.out.println(n);
			
			nextPage = "CartListServlet";
		}else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다");
		}
		response.sendRedirect(nextPage);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
