package com.javaweb.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingMapper {
	
	
	public BuildingDTO toBuildingDTO(BuildingEntity item ) {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(item.getName());
		DistrictEntity districtEntity = item.getDistrict();
		List<RentAreaEntity> rentAreas = item.getItems();
		String rentAreaResult = rentAreas.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
		buildingDTO.setRentArea(rentAreaResult);
		buildingDTO.setAddress(item.getStreet()+","+item.getWard()+","+districtEntity.getName());
		buildingDTO.setManagerName(item.getManagerName());
		buildingDTO.setBrokerageFee(item.getBrokerageFee());
		buildingDTO.setManagerPhoneNumber(item.getManagerPhoneNumber());
		buildingDTO.setServiceFee(item.getServiceFee() );
		buildingDTO.setRentPrice(item.getRentPrice());
		buildingDTO.setFloorArea(item.getFloorArea());
		return buildingDTO;
	}
}
