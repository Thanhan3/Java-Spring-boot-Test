package com.javaweb.service;

import com.javaweb.model.RentAreasDTO;

public interface RentAreaService {
	RentAreasDTO findAreaOfBuilding(long idBuilding);
}
