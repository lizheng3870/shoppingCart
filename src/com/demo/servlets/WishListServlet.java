package com.demo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.CartService;
import com.demo.service.WishListService;

/**
 * Servlet implementation class WishListServlet
 */
@WebServlet("/WishListServlet")
public class WishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String product_id = request.getParameter("product_id");
		String product_image = request.getParameter("product_image");
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		if(!user_id.equals("null")) {
			add_wishList(user_id, product_id, product_image);
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		String product_id = request.getParameter("product_id");
		String action = request.getParameter("action");
		if (action.equals("delete"))
			delete_item(user_id, product_id);
		if(action.equals("move")) {
			String product_image = request.getParameter("product_image");
			move_item_to_cart(user_id, product_id, product_image);
		}
		response.sendRedirect("wishList.jsp");
	}
	
	private void add_wishList(String user_id, String product_id, String product_image) {
		WishListService wishListService = new WishListService();
		wishListService.insert(user_id, product_id, product_image);
	}
	private void delete_item(String user_id, String product_id) {
		WishListService wishListService = new WishListService();
		wishListService.delete(user_id, product_id);
	}
	private void move_item_to_cart(String user_id, String product_id, String product_image) {
		CartService cartService = new CartService();
		cartService.insert_item_from_wishList_to_cart(user_id, product_id, product_image);
		
	}

}
