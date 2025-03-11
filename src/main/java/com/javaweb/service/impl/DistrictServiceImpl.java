package com.javaweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.DistrictDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.DistrictService;
@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public DistrictDTO findDistrictById(long id) {
		DistrictEntity districtEntity = districtRepository.findDistrictById(id);
		DistrictDTO districtDTO = new DistrictDTO();
		districtDTO.setCode(districtEntity.getCode());
		districtDTO.setName(districtEntity.getName());
		return districtDTO;
	}

}
