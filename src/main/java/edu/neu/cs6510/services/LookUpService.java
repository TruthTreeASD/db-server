package edu.neu.cs6510.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import edu.neu.cs6510.dto.LocationInfoDTO;
import edu.neu.cs6510.model.Location;
import edu.neu.cs6510.model.LookUpData;
import edu.neu.cs6510.repositories.LocationRepository;
import edu.neu.cs6510.repositories.LookUpRepository;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.Result;

@Service
public class LookUpService {
	@Autowired
	LookUpRepository lookUpRepository;

	@Autowired
	LocationRepository locationRepository;

	public ResponseMessage findAllRecords() {
		return Result.success(lookUpRepository.findAll());
	}

	public List<LookUpData> findAllRecordsWithAttributeIds() {
		return null;
	}

	public ResponseMessage findRecordsAtSameLevel(
			@RequestParam(value = "location") int locationId,
			@RequestParam(value = "year") int year, 
			@RequestParam(value = "attributes") List<Integer> attributeIds) {

		int typeCode = locationRepository.findTypeCode(locationId);
		
		
		List<Location> locations = locationRepository.findAllLocationsAtGivenLevel(typeCode);
		
		List<Integer> locationsIds = new ArrayList<Integer>();
		
		for(Location l : locations) {
			locationsIds.add(l.getId());
		}
		
		List<LookUpData> attributeData=  lookUpRepository.findByAttributeIdAndLocAndTime(attributeIds, locationsIds, year);
		
		List<LocationInfoDTO> results = new ArrayList<>();
		
		for(Location l : locations) {
			LocationInfoDTO temp = new LocationInfoDTO(l);
			
			
			for(LookUpData d : attributeData) {
				if(d.getLookUpPK().getLocation_id() == l.getId()) {
					temp.getAttributeValues().add(d);
				}
				
			}
			
			results.add(temp);
			
		}
		
		return Result.success(results);
	}

    public ResponseMessage findRecordsForSameParent(
            @RequestParam(value = "location") int locationId,
            @RequestParam(value = "year") int year,
            @RequestParam(value = "attributes") List<Integer> attributeIds) {

        int parentCode = locationRepository.findParentId(locationId);

        int typeCode = locationRepository.findTypeCode(locationId);


        List<Location> locations = locationRepository.findAllLocationsWithGivenParent(parentCode, typeCode);

        List<Integer> locationsIds = new ArrayList<Integer>();

        for(Location l : locations) {
            locationsIds.add(l.getId());
        }

        List<LookUpData> attributeData=  lookUpRepository.findByAttributeIdAndLocAndTime(attributeIds, locationsIds, year);

        List<LocationInfoDTO> results = new ArrayList<>();

        for(Location l : locations) {
            LocationInfoDTO temp = new LocationInfoDTO(l);


            for(LookUpData d : attributeData) {
                if(d.getLookUpPK().getLocation_id() == l.getId()) {
                    temp.getAttributeValues().add(d);
                }

            }

            results.add(temp);

        }

        return Result.success(results);
    }

    public ResponseMessage findAvailbeAttr(List<Integer> attributeId, List<Integer> year, List<Integer> locationId, Integer typeCode, String orderBy, String order, Integer from, Integer to) {
        String sort = orderBy + " " + order;
        if (typeCode != null) {
            return Result.success(lookUpRepository.queryLookUpData(attributeId, year, typeCode, from, to, sort));
        } else if (locationId != null && !locationId.isEmpty()){
            return Result.success(lookUpRepository.queryLookUpData(attributeId, year,locationId,from, to, sort));
        } else {
            return Result.error("Please provide locationId or type code");
        }
    }
}
