package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	
	public static void joinTable(Map<String,Object> params, List<String> typeCode, StringBuilder sql) {
		Long staffId = (Long)params.get("staffId");
		if(staffId!=null) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id =assignmentbuilding.buildingid ");
		}
		if(typeCode!=null && typeCode.size()!=0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttypeid ");
		}
		Long rentAreaTo = (Long)params.get("areaTo");
		Long rentAreaFrom = (Long)params.get("areaFrom");
		if(rentAreaTo!= null || rentAreaFrom!=null  ) {
			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
		
	}
	
	
	public static void query(Map<String,Object> params, List<String> typeCode) {
		
	}
	
	
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params, List<String> typeCode) {
		StringBuilder sqlString = new StringBuilder(" SELECT b.id , b.districtid, b.street , b.ward , b.numberofbasement, b.floorarea , b.rentprice ,"
				+ "b.managername, b.managerphonenumber,b.servicefee,b.brokeragefee FROM building b ");
		joinTable( params, typeCode,sqlString);
		System.out.print(sqlString.toString());
		StringBuilder where = new StringBuilder("WHERE 1=1");
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString.toString());) {
			while (rs.next()) {
				BuildingEntity buildingEndtity = new BuildingEntity();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

}
