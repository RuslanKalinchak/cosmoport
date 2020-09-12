package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.IShipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/rest")
public class ShipController {
    private final IShipService shipService;

    @Autowired
    public ShipController(IShipService shipService) {
        this.shipService=shipService;
    }

    @PostMapping(path = "/ships")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        Ship result= shipService.createShip(ship);;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/ships/{id}")
    public ResponseEntity<Ship> updateShip(@RequestBody Ship source, @PathVariable String id) {
        Long longId = shipService.auditId(id);
        Ship result = shipService.updateShip(source, longId);;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/ships/{id}")
    public ResponseEntity<?> deleteShip (@PathVariable String id) {
        Long longId = shipService.auditId(id);
        shipService.deleteShip(longId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/ships/{id}")
    public ResponseEntity<Ship> getShip(@PathVariable String id) {
        Long longId = shipService.auditId(id);
        Ship result = shipService.getShip(longId);;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/ships")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> getAllShips(@RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "planet", required = false) String planet,
                                  @RequestParam(value = "shipType", required = false) ShipType shipType,
                                  @RequestParam(value = "after", required = false) Long after,
                                  @RequestParam(value = "before", required = false) Long before,
                                  @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                  @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                  @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                  @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                  @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                  @RequestParam(value = "minRating", required = false) Double minRating,
                                  @RequestParam(value = "maxRating", required = false) Double maxRating,
                                  @RequestParam(value = "order", required = false, defaultValue = "ID") ShipOrder order,
                                  @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));

        return shipService.getShipsList(
                Specification.where(shipService.searchByName(name)
                        .and(shipService.searchByPlanet(planet)))
                        .and(shipService.searchByShipType(shipType))
                        .and(shipService.searchByProdDate(after, before))
                        .and(shipService.searchByUse(isUsed))
                        .and(shipService.searchBySpeed(minSpeed, maxSpeed))
                        .and(shipService.searchByCrewSize(minCrewSize, maxCrewSize))
                        .and(shipService.searchByRating(minRating, maxRating)), pageable)
                .getContent();
    }


    @GetMapping("/ships/count")
    public ResponseEntity<Integer> getCount(@RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "planet", required = false) String planet,
                                            @RequestParam(value = "shipType", required = false) ShipType shipType,
                                            @RequestParam(value = "after", required = false) Long after,
                                            @RequestParam(value = "before", required = false) Long before,
                                            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
                                            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
                                            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
                                            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
                                            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
                                            @RequestParam(value = "minRating", required = false) Double minRating,
                                            @RequestParam(value = "maxRating", required = false) Double maxRating) {

        Specification<Ship> specification = Specification.where(shipService.searchByName(name)
                .and(shipService.searchByPlanet(planet))
                .and(shipService.searchByShipType(shipType))
                .and(shipService.searchByProdDate(after, before))
                .and(shipService.searchByUse(isUsed))
                .and(shipService.searchBySpeed(minSpeed, maxSpeed))
                .and(shipService.searchByCrewSize(minCrewSize, maxCrewSize))
                .and(shipService.searchByRating(minRating, maxRating)));

        return new ResponseEntity<>(shipService.getShipsCount(specification), HttpStatus.OK);
    }
}
