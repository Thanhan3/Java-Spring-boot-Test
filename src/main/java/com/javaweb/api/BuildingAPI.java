package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customexception.FieldRequiredExeption;
import com.javaweb.model.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;

	@GetMapping("/api/buildings/")
	public List<BuildingDTO> getBuildings(@RequestParam Map<String, Object> params,
			@RequestParam(name = "typeCode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findAll(params, List<String> typeCode);
		return result;
	}

	public void validate(BuildingDTO buildingDTO) {
		if (buildingDTO.getName() == null || buildingDTO.getName().equals("")
				|| buildingDTO.getNumberOfBasement() == null) {
			throw new FieldRequiredExeption("name or numberOfBasement is null");
		}
	}

	@PostMapping("/api/buildings/")
	public Object createBuilding(@RequestBody BuildingDTO buildingDTO) {
		validate(buildingDTO);
		BuildingDTO result = new BuildingDTO();
		return result;
	}

	@DeleteMapping("/api/buildings/{id}")
	public void deleteItem(@PathVariable Integer id) {
		System.out.print("xoa toa nha co id =" + id);
	}

	// Test error detail

}
