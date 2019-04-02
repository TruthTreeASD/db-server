package edu.neu.cs6510.services;

import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.util.Page;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Service
public class LocationService {
	@Autowired
	LocationRepository locationRepository;

	/**
	 * Method that gives all the locations.
	 * 
	 * @return location list in the response
	 */
	public ResponseMessage<List<Location>> findAllLocationBasedData() {
		return Result.success((List<Location>) locationRepository.findAll());
	}

	/**
	 * Method to find parent for a given location.
	 * 
	 * @param id
	 *            for the location we wish to find the parent for
	 * @return Parent which is state for a county and a county for a city
	 */
	public ResponseMessage<List<Location>> findParents(Integer id) {
		return Result.success(locationRepository.findParents(id));
	}

	/**
	 * Method that gives all the cities for a given county.
	 * 
	 * @id county id
	 * @return city list in the response
	 */
	public ResponseMessage<List<Location>> findCities(Integer id) {
		return id == null ? Result.success(locationRepository.findAllCities())
				: Result.success(locationRepository.findCities(id));
	}

	/**
	 * Method that gives all the states.
	 * 
	 * @return state list in the response
	 */
	public ResponseMessage<List<Location>> findStates() {
		return Result.success(locationRepository.findStates());
	}

	/**
	 * Method that gives all the counties for a given state.
	 * 
	 * @id state id
	 * @return county list in the response
	 */
	public ResponseMessage<List<Location>> findCounties(@RequestParam(value = "id", required = false) Integer id) {
		return id == null ? Result.success(locationRepository.findAllCounties())
				: Result.success(locationRepository.findCounties(id));
	}

	/**
	 * Information about a location using the location id.
	 * 
	 * @param id
	 *            for the location
	 * @return location info in the location dto
	 */
	public ResponseMessage<Location> queryById(Integer id) {
		Optional<Location> optionalLocation = locationRepository.findById(id);
		return Result.success(optionalLocation.isPresent() ? optionalLocation.get() : null);
	}

	/**
	 * Find ids of all locations at same level by pagination by providing either
	 * location, typecode
	 * 
	 * @param id
	 *            of the location
	 * @param typeCode
	 * @param parentId
	 * @param orderBy
	 * @param order
	 * @param pageSize
	 * @param currentPage
	 * @return list of locations in the response
	 */
	public ResponseMessage<Page<Location>> findLocPagination(Integer id, Integer typeCode, Integer parentId,
			String orderBy, String order, Integer pageSize, Integer currentPage) {
		String sort = orderBy + " " + order;
		long total;
		List<Location> data;
		if (id != null) {
			total = locationRepository.countLocationsById(id, typeCode);
			data = locationRepository.queryLocationsByIdPage(id, typeCode, sort, pageSize,
					pageSize * (currentPage - 1));
		} else if (parentId != null) {
			total = locationRepository.countLocationByuParentId(parentId, typeCode);
			data = locationRepository.findParentIdPage(parentId, typeCode, sort, pageSize,
					pageSize * (currentPage - 1));
		} else if (typeCode != -1) {
			total = locationRepository.countByType(typeCode);
			data = locationRepository.queryByTypePage(typeCode, sort, pageSize, pageSize * (currentPage - 1));
		} else {
			return Result.error("Please provide locationId / parentId /type code");
		}
		return Result.success(new Page(data, total, currentPage, pageSize));
	}
}
