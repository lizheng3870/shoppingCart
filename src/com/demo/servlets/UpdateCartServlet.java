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
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCartServlet() {
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

		String quantity = request.getParameter("quantity");
		String product_id = request.getParameter("product_id");
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		System.out.println("update quantity in cart: " + quantity);
		if (user_id.equals("null"))
			update_visitor_cart(request, product_id, quantity);
		else
			update_user_cart(user_id, product_id, quantity);
		response.sendRedirect("cart.jsp");
	}

	private void update_user_cart(String user_id, String product_id, String quantity) {
		CartService cartService = new CartService();
		cartService.update_item(user_id, product_id, quantity);
	}

	private void update_visitor_cart(HttpServletRequest request, String product_id, String quantity) {
		List<Item> itemList = (List<Item>) request.getSession().getAttribute("visitor_cart");
		for(int i = 0; i < itemList.size(); i ++) {
			if(product_id.equals(String.valueOf(itemList.get(i).getProduct_id()))) {
				itemList.get(i).setQuantity(Integer.parseInt(quantity));
			}
		}
	}

}
