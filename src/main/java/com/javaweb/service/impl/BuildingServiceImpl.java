package com.javaweb.service.impl;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService  {
	@Autowired
	private BuildingRepository buildingRepository ;
	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		for(BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setAddressString(item.getWard()+","+item.getStreet());
			buildingDTO.setName(item.getName());
			buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
			result.add(buildingDTO);
		}
		return result ;
	}
	
	

}
