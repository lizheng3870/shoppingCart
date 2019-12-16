package com.demo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Item;
import com.demo.service.CartService;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")

public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopServlet() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_id = request.getParameter("product_id");
		String product_image = request.getParameter("product_image");
		String quantity = request.getParameter("quantity");
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		System.out.println("Product_id: " + product_id + ", quantity: " + quantity + ", user_id: " + user_id
				+ ", product_image:" + product_image);
		if (user_id.equals("null")) {
			update_visitor_session(request, product_id, quantity, product_image);
		} else {
			add_to_cart(user_id, product_id, quantity, product_image);
		}
		response.sendRedirect("index.jsp");
	}

	private void add_to_cart(String user_id, String product_id, String quantity, String product_image) {
		CartService cartService = new CartService();
		cartService.update_carts(user_id, product_id, quantity, product_image);
	}

	private void update_visitor_session(HttpServletRequest request, String product_id, String quantity,
			String product_image) {
		List<Item> itemList = (List<Item>) request.getSession().getAttribute("visitor_cart");
		boolean is_exist = true;
		if (itemList == null) {
			itemList = new ArrayList<Item>();
			Item item = new Item();
			item.setProduct_id(Integer.parseInt(product_id));
			item.setQuantity(Integer.parseInt(quantity));

			item.setImage(product_image);
			itemList.add(item);
		} else {
			
			for (int i = 0; i < itemList.size(); i++) {
				if (product_id.equals(String.valueOf(itemList.get(i).getProduct_id()))) {
					int num = itemList.get(i).getQuantity() + Integer.parseInt(quantity);
					itemList.get(i).setQuantity(num);
					is_exist = false;
				}
			}
			if(is_exist) {
				Item item = new Item();
				item.setProduct_id(Integer.parseInt(product_id));
				item.setQuantity(Integer.parseInt(quantity));

				item.setImage(product_image);
				itemList.add(item);
			}
		}
		request.getSession().setAttribute("visitor_cart", itemList);

	}

}
