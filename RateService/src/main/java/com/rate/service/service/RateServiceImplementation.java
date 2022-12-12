package com.rate.service.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.service.exception.RateNotFoundException;
import com.rate.service.model.RateDetails;
import com.rate.service.repository.RateRepository;

@Service
public class RateServiceImplementation implements RateService{
	
	@Autowired
	private RateRepository repository;
	
	Logger log = LoggerFactory.getLogger(RateServiceImplementation.class);

	@Override
	public List<RateDetails> showAllRateDetails() {
		log.info("Show All Rate Details Method Started");
		List<RateDetails> rateDetails = repository.findAll();
		log.debug("rates are {} ", rateDetails);
		log.info("Show All Rate Details Method Ended");
		return rateDetails;
	}

	@Override
	public RateDetails showRateById(int id) throws RateNotFoundException {
		log.info("Show Rate By Id Method Started");
		return repository.findById(id).orElseThrow(() -> new RateNotFoundException("Rate with the id " + id + " Doesn't Exists"));
		
	}

	@Override
	public RateDetails addRateDetails(RateDetails rateDetails) throws RateNotFoundException{
		log.info("Add RateDetails Method Started");
		Optional<RateDetails> rate = repository.findById(rateDetails.getRateId());
		if (!rate.isPresent()) {
			log.info("Add RateDetails Method Ended");
			return repository.insert(rateDetails);
		} else {
			return rate.orElseThrow(() -> new RateNotFoundException("Rate Already Exists"));
		}
	}

	@Override
	public RateDetails updateRateDetails(RateDetails rateDetails)throws RateNotFoundException {
		log.info("Update Rate Method Started");
		Optional<RateDetails> rate = repository.findById(rateDetails.getRateId());
		if (!rate.isPresent())
			return rate.orElseThrow(
					() -> new RateNotFoundException("Rtae with the id " + rateDetails.getRateId() + " Doesn't Exists"));
		log.info("Update Rate Method Ended");
		return repository.save(rateDetails);
	}

	@Override
	public String deleteRateDetails(int id)throws RateNotFoundException {
		log.info("Delete Rate Details Method Started");
		Optional<RateDetails> rateDetails = repository.findById(id);
		if (!rateDetails.isPresent()) {
			rateDetails.orElseThrow(() -> new RateNotFoundException("Rate with the id " + id + " Doesn't Exists"));
		} else {
			repository.deleteById(id);
			log.debug("Deleted SuccessFully {}", rateDetails);
			log.info("Delete Dealer Method Ended");
		}
		return "Farmer with the " + id + " Deleted Successfully!";
	}

}
