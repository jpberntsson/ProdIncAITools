package com.jpberntsson.tariffmodule.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceComponent {
    private TariffDimensionType type;
    private Double price;
    private Double vat;
    private Integer step_size;

}

