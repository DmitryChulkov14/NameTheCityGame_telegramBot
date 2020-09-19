package com.telegrambot.thecitygame.repository;

import com.telegrambot.thecitygame.model.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    City findById(long id);

    List<City> findAllByNameStartingWith(String letter);

}
