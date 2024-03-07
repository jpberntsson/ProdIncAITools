package com.jpberntsson.tariffmodule.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffRestriction {
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;
    private Double minKWh;
    private Double maxKWh;
    private Double minCurrent;
    private Double maxCurrent;
    private Double minPower;
    private Double maxPower;
    private Integer minDuration;
    private Integer maxDuration;
    // Assuming DayOfWeek is an enum representing days of the week
    private DayOfWeek dayOfWeek;
    private ReservationRestrictionType reservation;

    // Constructors, Getters, and Setters
}
