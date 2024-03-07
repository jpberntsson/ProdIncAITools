package com.jpberntsson.tariffmodule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Date;

@Document
@Getter
@Setter
@NoArgsConstructor
public class Tariff {
    private String countryCode;
    private String partyId;
    @MongoId
    private String id;
    private String currency;
    private TariffType type;
    private List<DisplayText> tariffAltText;
    private String tariffAltUrl;
    private Price minPrice;
    private Price maxPrice;
    private List<TariffElement> elements;
    private Date startDateTime;
    private Date endDateTime;
    private EnergyMix energyMix;
    private Date lastUpdated;

    public Tariff(String id, String eur) {
        this.id = id;
        this.currency = eur;
    }

    // Constructors, Getters, Setters
}
