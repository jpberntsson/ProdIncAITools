package com.jpberntsson.tariffmodule.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import java.util.List;

@Getter
@Setter
public class TariffElement {
    private List<PriceComponent> priceComponents;
    private List<TariffRestriction> restrictions;
}


