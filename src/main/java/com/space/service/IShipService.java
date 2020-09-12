package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IShipService {
    Ship createShip(Ship ship);
    Ship getShip(Long id);
    Ship updateShip(Ship ship, Long id);
    void deleteShip(Long id);
    Long auditId(String id);
    Page<Ship> getShipsList(Specification<Ship> specification, Pageable sortedBy);
    Integer getShipsCount(Specification<Ship> specification);

    Specification<Ship> searchByName(String name);
    Specification<Ship> searchByPlanet(String planet);
    Specification<Ship> searchByShipType(ShipType shipType);
    Specification<Ship> searchByProdDate(Long after, Long before);
    Specification<Ship> searchByUse(Boolean isUsed);
    Specification<Ship> searchBySpeed(Double minSpeed, Double maxSpeed);
    Specification<Ship> searchByCrewSize(Integer minCrewSize, Integer maxCrewSize);
    Specification<Ship> searchByRating(Double minRating, Double maxRating);
}
