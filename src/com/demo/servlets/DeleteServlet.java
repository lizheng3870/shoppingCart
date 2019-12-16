package com.demo.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Item;
import com.demo.service.CartService;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_id = request.getParameter("product_id");
		System.out.println("delete: " + product_id);
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		if (user_id.equals("null")) {
			delete_visitor_items(request, product_id);
		} else
			delete_user_items(user_id, product_id);
		response.sendRedirect("cart.jsp");
	}

	private void delete_user_items(String user_id, String product_id) {
		CartService cartService = new CartService();
		cartService.delete_item(user_id, product_id);
	}

	private void delete_visitor_items(HttpServletRequest request, String product_id) {
		List<Item> itemList = (List<Item>) request.getSession().getAttribute("visitor_cart");
		for (int i = 0; i < itemList.size(); i++) {
			if (product_id.equals(String.valueOf(itemList.get(i).getProduct_id()))) {
				itemList.remove(i);
			}
		}
	}

}
