package com.telegrambot.thecitygame.service;

import com.telegrambot.thecitygame.model.City;
import com.telegrambot.thecitygame.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional(readOnly = true)
    public City findById(long id) {
        return cityRepository.findById(id);
    }

    @Transactional (readOnly = true)
    public List<City> findAllCitiesByNameStartingWith(String letter) {
        return cityRepository.findAllByNameStartingWith(letter);
    }
}
