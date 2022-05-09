package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); //로그인된 세션정보 가져옴
		MemberDTO dto = (MemberDTO)session.getAttribute("login");
		String nextPage = null;
		
		if (dto!=null) {
			nextPage = "mypage.jsp";
			String userid = dto.getUserid(); //위에서 MemberDTO dto 에 로그인된 세션 저장해놨으니 getUserid로 id만 가져올 수 있다.
			
			MemberService service = new MemberService();
			MemberDTO x = service.mypage(userid);
			//System.out.println(x);
			if(x!=null) {
				session.setAttribute("login", x); //세션데이터 덮어쓰기
				System.out.println("mypage이동");
			}
			
			
		} else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage); //위에 다 세션으로 만들어서 이 부분은 redirect로 만들어도 상관X
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
