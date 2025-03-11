package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	@Override
	public List<String> findAreaOfBuilding(long idBuilding) {
		String sql = "SELECT * FROM rentarea WHERE rentarea.buildingid = " + idBuilding;
		List<String> rentAreas = new ArrayList<String>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while(rs.next()) {
				rentAreas.add((rs.getString("rentarea.value")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return rentAreas;
	}

}
