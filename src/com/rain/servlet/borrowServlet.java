package com.rain.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rain.bean.AdminBean;
import com.rain.dao.AdminDao;
import com.rain.dao.BookDao;

/**
 * Servlet implementation class borrowServlet
 */
@WebServlet("/borrowServlet")
public class borrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public borrowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//设置编码类型
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		BookDao bookdao = new BookDao();
		//为了区分收藏和取消收藏的功能，设置tip，tip为1，表示收藏
		int tip = Integer.parseInt(request.getParameter("tip"));
		if(tip==1){
			//获取房源id
			int bid = Integer.parseInt(request.getParameter("bid"));
			HttpSession session = request.getSession();
			AdminBean admin = new AdminBean();
			//获取到存入session的aid用户id
			String aid = (String)session.getAttribute("aid");
			AdminDao admindao = new AdminDao();
			//通过aid获取到用户的信息
			admin = admindao.get_AidInfo2(aid);
			//将收藏记录存入数据表
			bookdao.borrowBook(bid,admin);
			response.sendRedirect("/books/selectServlet");
		}else{
			//取消收藏功能，获取收藏记录的hid
			int hid = Integer.parseInt(request.getParameter("hid"));
			
			int show = Integer.parseInt(request.getParameter("show"));
			//调用取消收藏函数，改变status字段
			bookdao.borrowBook2(hid);
			if(show==1){
				response.sendRedirect("/books/borrow.jsp");
			}else{
				response.sendRedirect("/books/admin_borrow.jsp");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
