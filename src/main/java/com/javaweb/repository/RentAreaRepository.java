package com.javaweb.repository;

import java.util.List;

public interface RentAreaRepository {
	List<String> findAreaOfBuilding(long idBuilding);
}
