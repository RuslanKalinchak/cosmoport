package com.space.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "planet")
    private String planet;

    @Enumerated(EnumType.STRING)
    @Column(name = "shipType")
    private ShipType shipType;

    @Column(name = "prodDate")
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPlanet() {
        return planet;
    }
    public ShipType getShipType() {
        return shipType;
    }
    public Date getProdDate() {
        return prodDate;
    }
    public Boolean getUsed() {
        return isUsed;
    }
    public Double getSpeed() { return speed; }
    public Integer getCrewSize() {
        return crewSize;
    }
    public Double getRating() { return rating; }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPlanet(String planet) {
        this.planet = planet;
    }
    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }
    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }
    public void setUsed(Boolean used) {
        isUsed = used;
    }
    public void setSpeed(Double speed) {
        this.speed = speed;
    }
    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }
}

