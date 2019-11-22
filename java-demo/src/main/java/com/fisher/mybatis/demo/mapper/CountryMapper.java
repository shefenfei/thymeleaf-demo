package com.fisher.mybatis.demo.mapper;

import com.fisher.mybatis.demo.model.Country;

import java.util.List;

public interface CountryMapper {

    int insertCountry(Country country);

    List<Country> findCountries();
}
