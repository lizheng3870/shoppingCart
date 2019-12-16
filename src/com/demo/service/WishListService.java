package com.demo.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Item;
import com.demo.model.Product;

public class WishListService {
	private Connection connection;

	public WishListService() {
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

	public List<Item> viewAll(String user_id) {
		List<Item> itemList = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from wishlist where user_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setProduct_id(rs.getInt("product_id"));
				item.setImage(rs.getString("image"));
				itemList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}


	public void insert(String user_id, String product_id, String product_image) {
		try {
			PreparedStatement statement = connection.prepareStatement("select count(*) from wishlist where user_id = ? and product_id = ?;");
			statement.setInt(1, Integer.parseInt(user_id));
			statement.setInt(2, Integer.parseInt(product_id));
			ResultSet rs = statement.executeQuery();
			int count = -1;
			while(rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				PreparedStatement ps = connection.prepareStatement("insert into wishList (user_id, product_id, image) values (?,?,?);");
				ps.setInt(1, Integer.parseInt(user_id));
				ps.setInt(2, Integer.parseInt(product_id));
				ps.setString(3, product_image);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delete(String user_id, String product_id) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from wishList where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert_item_from_cart_to_wishList(String user_id, String product_id, String product_image) {
		try {
			PreparedStatement ps = connection
					.prepareStatement("select count(*) from wishList where user_id = ? and product_id = ?;");
			ps.setInt(1, Integer.parseInt(user_id));
			ps.setInt(2, Integer.parseInt(product_id));
			ResultSet rs = ps.executeQuery();
			int count = -1;
			while (rs.next()) {
				count = rs.getInt(1);
			}
			if (count == 0) {
				PreparedStatement statement = connection.prepareStatement("insert into wishList (user_id, product_id, image) values (?,?,?);");
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
