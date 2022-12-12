package com.rate.service.service;

import java.util.List;

import com.rate.service.exception.RateNotFoundException;
import com.rate.service.model.RateDetails;

public interface RateService {
	public List<RateDetails> showAllRateDetails()throws RateNotFoundException;
	public RateDetails showRateById(int id) throws RateNotFoundException;
	public RateDetails addRateDetails(RateDetails rateDetails) throws RateNotFoundException;
	public RateDetails updateRateDetails(RateDetails rateDetails)throws RateNotFoundException;
	public String deleteRateDetails(int id) throws RateNotFoundException;


}
