package com.demo.service;

import java.sql.*;
import java.util.*;

import com.demo.model.Item;

public class CartService {

	private Connection connection;

	public CartService() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingdb", "root", "leslie821821");
			if (connection != null) {
				System.out.println("Connected to Database!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Item> view(String user_id) {
		List<Item> itemList = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from carts where user_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setProduct_id(rs.getInt("product_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setImage(rs.getString("image"));
				itemList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public void insert(String user_id, String product_id, String quantity, String product_image) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("insert into carts (user_id, product_id, quantity, image) values (?,?,?,?);");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ps.setInt(3, Integer.parseInt(quantity));
			ps.setString(4, product_image);
			ps.executeUpdate();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void update_carts(String user_id, String product_id, String quantity, String product_image) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(*) from carts where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				if (res.getInt(1) == 1)
					update(user_id, product_id, quantity, product_image);
				else
					insert(user_id, product_id, quantity, product_image);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void update(String user_id, String product_id, String quantity, String product_image) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("update carts set quantity = quantity + ? where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(quantity));
			ps.setInt(2, Integer.parseInt(user_id));
			ps.setInt(3, Integer.parseInt(product_id));
			ps.executeUpdate();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void update_item(String user_id, String product_id, String quantity) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("update carts set quantity = ? where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(quantity));
			ps.setInt(2, Integer.parseInt(user_id));
			ps.setInt(3, Integer.parseInt(product_id));
			ps.executeUpdate();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public void delete_item(String user_id, String product_id) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("delete from carts where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void combine(List<Item> visitorItemList, String user_id) {
		try {
			if (visitorItemList != null) {
				for (Item item : visitorItemList) {
					combine_one_item(item, user_id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void combine_one_item(Item item, String user_id) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(*) from carts where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, item.getProduct_id());
			ResultSet rs = ps.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				insert(user_id, String.valueOf(item.getProduct_id()), String.valueOf(item.getQuantity()),
						String.valueOf(item.getImage()));
			}

			if (count == 1) {
				update(user_id, String.valueOf(item.getProduct_id()), String.valueOf(item.getQuantity()), null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void insert_item_from_wishList_to_cart(String user_id, String product_id, String product_image) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(*) from carts where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ResultSet rs = ps.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				PreparedStatement statement = connection.prepareStatement("insert into carts (user_id, product_id, image, quantity) values (?,?,?,1);");
				statement.setInt(1, Integer.parseInt(user_id));
				statement.setInt(2, Integer.parseInt(product_id));
				statement.setString(3, product_image);
				statement.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
