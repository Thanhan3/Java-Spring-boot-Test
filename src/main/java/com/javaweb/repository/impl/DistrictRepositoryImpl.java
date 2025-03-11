package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	@Override
	public DistrictEntity findDistrictById(Long id) {
		String sql = "SELECT * FROM district WHERE district.id ="+id.toString();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());) {
			while(rs.next()) {
				DistrictEntity districtEntity = new DistrictEntity();
				districtEntity.setId(rs.getLong("district.id"));
				districtEntity.setCode(rs.getString("district.code"));
				districtEntity.setName(rs.getString("district.name"));
				return districtEntity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

}
