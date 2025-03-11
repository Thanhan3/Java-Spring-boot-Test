package com.javaweb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.RentAreasDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.RentAreaService;

@Component
public class BuildingMapper {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired 
	private RentAreaService rentAreaService;
	
	
	public BuildingDTO toBuildingDTO(BuildingEntity item ) {
		BuildingDTO buildingDTO = new BuildingDTO();
		buildingDTO.setName(item.getName());
		DistrictEntity districtEntity = districtRepository.findDistrictById(item.getDistrictId());
		RentAreasDTO rentAreasDTO = rentAreaService.findAreaOfBuilding(item.getId());
		buildingDTO.setRentArea(rentAreasDTO.getAreas());
		buildingDTO.setAddress(item.getStreet()+","+item.getWard()+","+districtEntity.getName());
		buildingDTO.setManagerName(item.getManagerName());
		buildingDTO.setBrokerageFee(item.getBrokerageFee());
		buildingDTO.setManagerPhoneNumber(item.getManagerPhoneNumber());
		buildingDTO.setServiceFee(item.getServiceFee());
		buildingDTO.setRentPrice(item.getRentPrice());
		buildingDTO.setFloorArea(item.getFloorArea());
		return buildingDTO;
	}
}
