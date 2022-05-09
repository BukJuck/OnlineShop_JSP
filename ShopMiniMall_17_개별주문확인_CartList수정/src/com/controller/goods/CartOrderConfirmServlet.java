package com.controller.goods;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.MemberService;

/**
 * Servlet implementation class CartOrderConfirmServlet
 */
@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderConfirmServlet() {
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
			String num = request.getParameter("num"); //cartDTO
			System.out.println(num);
			CartService cService = new CartService();
			CartDTO cDTO = cService.cartbyNum(num); //cart 정보조회
			
			String userid = dto.getUserid(); //MemberDTO
			MemberService mService = new MemberService();
			MemberDTO mDTO = mService.mypage(userid); //사용자 정보 조회 (미리 만들어 놓은것임)
			
			request.setAttribute("cDTO", cDTO); //상품주문정보
			request.setAttribute("mDTO", mDTO); //고객정보
			
			nextPage = "orderConfirm.jsp";
			
		}else {
			nextPage = "LoginUIServlet";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("orderConfirm.jsp");
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
