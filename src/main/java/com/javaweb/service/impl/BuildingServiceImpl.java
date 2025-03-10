package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode) {
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(params, List<String> typeCode);
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();

		for (BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setAddressString(item.getWard() + "," + item.getStreet());
			buildingDTO.setName(item.getName());
			buildingDTO.setNumberOfBasement(item.getNumberOfBasement());
			result.add(buildingDTO);
		}
		return result;
	}

}
