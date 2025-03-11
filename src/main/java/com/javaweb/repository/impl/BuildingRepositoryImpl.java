package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	private static final String URL = "jdbc:mysql://localhost:3306/estatebasic";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static void joinTable(Map<String, Object> params, List<String> typeCode, StringBuilder sql) {
		String staffId = (String) params.get("staffId");
		if (StringUtils.checkString(staffId)) {
			sql.append(" INNER JOIN assignmentbuilding ON b.id =assignmentbuilding.buildingid ");
		}
		if (typeCode != null && typeCode.size() != 0) {
			sql.append(" INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append(" INNER JOIN renttype ON buildingrenttype.renttypeid = renttypeid ");
		}
		String rentAreaTo = (String) params.get("rentAreaTo");
		String rentAreaFrom = (String) params.get("rentAreaFrom");
		if (StringUtils.checkString(rentAreaTo) || StringUtils.checkString(rentAreaFrom)) {
			sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}

	}

	public static void queryNomal(Map<String, Object> params, StringBuilder where) {
		for (Map.Entry<String, Object> item : params.entrySet()) {
			if (!item.getKey().equals("staffId") && !item.getKey().equals("typeCode")
					&& !item.getKey().startsWith("rentArea") && !item.getKey().startsWith("rentPrice")) {
				String value = item.getValue().toString();
				if (StringUtils.checkString(value)) {
					if (NumberUtils.isNumber(value)) {
						where.append(" AND b." + item.getKey() + " = " + value);
					} else {
						where.append(" AND b." + item.getKey() + " LILE '%" + value + "%'  ");
					}
				}
			}
		}
	}

	public static void querySpecial(Map<String, Object> params, List<String> typeCode, StringBuilder where) {
		String staffId = (String) params.get("staffId");
		if (StringUtils.checkString(staffId)) {
			where.append(" AND assignmentbuilding.staffId = " + staffId);
		}
		String rentAreaFrom = (String) params.get("rentAreaFrom");
		String rentAreaTo = (String) params.get("rentAreaTo");
		if (StringUtils.checkString(rentAreaTo) || StringUtils.checkString(rentAreaFrom)) {
			if (StringUtils.checkString(rentAreaTo) && NumberUtils.isNumber(rentAreaTo)) {
				where.append(" AND rentarea.value <= " + rentAreaTo);
			}
			if (StringUtils.checkString(rentAreaFrom) && NumberUtils.isNumber(rentAreaFrom)) {
				where.append(" AND rentarea.value  >= " + rentAreaFrom);
			}
		}
		String rentPriceFrom = (String) params.get("rentPriceFrom");
		String rentPriceTo = (String) params.get("rentPriceTo");
		if (StringUtils.checkString(rentPriceTo) || StringUtils.checkString(rentPriceFrom)) {
			if (StringUtils.checkString(rentPriceTo) && NumberUtils.isNumber(rentPriceTo)) {
				where.append(" AND b.rentprice <= " + rentPriceTo);
			}
			if (StringUtils.checkString(rentPriceFrom) && NumberUtils.isNumber(rentPriceFrom)) {
				where.append(" AND b.rentprice  >= " + rentPriceFrom);
			}
		}

		if (typeCode != null && typeCode.size() != 0) {
			String joinedTypeCodes = typeCode.stream().map(code -> "'" + code + "'").collect(Collectors.joining(", "));
			where.append(" AND renttype.code IN (" + joinedTypeCodes + ") ");
		}

	}

	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params, List<String> typeCode) {
		StringBuilder sqlString = new StringBuilder(
				" SELECT b.id ,b.name, b.districtid, b.street , b.ward , b.numberofbasement, b.floorarea , b.rentprice ,"
						+ "b.managername, b.managerphonenumber,b.servicefee,b.brokeragefee FROM building b ");
		joinTable(params, typeCode, sqlString);
		StringBuilder where = new StringBuilder("WHERE 1=1");
		queryNomal(params, where);
		querySpecial(params, typeCode, where);
		sqlString.append(where);
		sqlString.append(" Group by b.id ");
		List<BuildingEntity> result = new ArrayList<BuildingEntity>();
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sqlString.toString());) {
			while (rs.next()){
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getLong("b.id"));
				buildingEntity.setName(rs.getString("b.name")); 
				buildingEntity.setWard (rs.getString("b.ward"));
				buildingEntity.setDistrictId(rs.getLong("b.districtid"));
				buildingEntity.setNumberOfBasement(rs.getInt("b.numberofbasement"));
				buildingEntity.setStreet(rs.getString("b.street"));
				buildingEntity.setFloorArea(rs.getLong("b.floorarea"));
				buildingEntity.setRentPrice(rs.getLong("b.rentprice"));
				buildingEntity.setServiceFee(rs.getString("b.servicefee")); 
				buildingEntity.setBrokerageFee(rs.getLong("b.brokeragefee"));
				buildingEntity.setManagerName(rs.getString("b.managername"));
				buildingEntity.setManagerPhoneNumber(rs.getString("b.managerphonenumber"));
				result.add(buildingEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return result;
	}

}
