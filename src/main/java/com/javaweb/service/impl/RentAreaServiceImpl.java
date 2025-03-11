package com.javaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.RentAreasDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;

@Service
public class RentAreaServiceImpl implements RentAreaService {

	@Autowired
	private RentAreaRepository rentAreaRepository;

	@Override
	public RentAreasDTO findAreaOfBuilding(long idBuilding) {
		List<String> areas = rentAreaRepository.findAreaOfBuilding(idBuilding);
		RentAreasDTO rentAreasDTO = new RentAreasDTO();
		rentAreasDTO.setAreas(String.join(",", areas));
		return rentAreasDTO;
	}

}
