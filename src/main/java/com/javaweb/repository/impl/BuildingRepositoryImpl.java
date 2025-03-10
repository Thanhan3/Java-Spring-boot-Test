package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	@Override
	public List<BuildingEntity> findAll() {
		String sqlString = "Select * From building";
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try(Connection connection = DriverManager.getConnection(URL, USER,PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString);
				){
			while (rs.next()) {
				BuildingEntity buildingEndtity = new BuildingEntity();
				buildingEndtity.setName(rs.getString("name"));
				buildingEndtity.setStreet(rs.getString("street"));
				buildingEndtity.setNumberOfBasement(rs.getInt("numberofbasement"));
				buildingEndtity.setWard(rs.getString("ward"));
				result.add(buildingEndtity);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

}
