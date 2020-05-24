package com.agf.mongo.service.mapper;


import com.agf.mongo.domain.*;
import com.agf.mongo.service.dto.CountryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Country} and its DTO {@link CountryDTO}.
 */
@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface CountryMapper extends EntityMapper<CountryDTO, Country> {

    @Mapping(source = "region.id", target = "regionId")
    CountryDTO toDto(Country country);

    @Mapping(source = "regionId", target = "region")
    Country toEntity(CountryDTO countryDTO);

    default Country fromId(String id) {
        if (id == null) {
            return null;
        }
        Country country = new Country();
        country.setId(id);
        return country;
    }
}
