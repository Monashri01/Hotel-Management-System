package com.rate.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rate.service.model.RateDetails;

@Repository
public interface RateRepository extends MongoRepository<RateDetails,Integer> {

}
