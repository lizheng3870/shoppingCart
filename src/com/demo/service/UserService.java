package com.demo.service;

import java.sql.*;

import com.demo.model.User;

public class UserService {
	private Connection connection;
	private Statement statement;

	public UserService() {
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

	public int insert(User user) {
		int key = 0;
		try {
			PreparedStatement statement = connection
					.prepareStatement("insert into users (username, password) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				key = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return key;
	}

	public boolean checkUsername(User user) {
		boolean is_error = false;
		try {
			if (user.getUsername() == null)
				is_error = true;
			else {
				PreparedStatement statement = connection.prepareStatement("select username from users;");
				ResultSet res = statement.executeQuery();
				while (res.next()) {
					if (res.getString("username").equals(user.getUsername())) {
						is_error = true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return is_error;
	}

	public boolean authorize(User user) {
		String password = "";
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where username = ?;");
			statement.setString(1, user.getUsername());

			ResultSet res = statement.executeQuery();
			while (res.next()) {
				password = res.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return password.equals(user.getPassword());
	}
	public int get_user_id(User user) {
		int user_id = 0;
		String password = "";
		boolean is_correct = false;
		try {
			PreparedStatement statement = connection.prepareStatement("select * from users where username = ?;");
			statement.setString(1, user.getUsername());

			
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				password = res.getString("password");
				user_id = res.getInt("id");
			}
			
			System.out.println("authorize user_id: " + user_id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user_id;
	}

}
