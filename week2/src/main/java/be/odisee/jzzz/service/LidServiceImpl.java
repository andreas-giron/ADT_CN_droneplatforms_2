package be.odisee.jzzz.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import be.odisee.jzzz.dao.LidRepository;
import be.odisee.jzzz.domain.Lid;

@Service
public class LidServiceImpl implements LidService {
	
	@Autowired
	private LidRepository lidRepository = null;

	private Lid veranderVervaldag(int lidId, Date nieuweDatum) {
		Lid lid = null;
		try {
			lid = lidRepository.findById(lidId).get();
			lid.setVervaldag(nieuweDatum);
			lidRepository.saveAndFlush(lid);
		} catch (ObjectRetrievalFailureException e) {
			e.printStackTrace();
		}
		return lid;
	}
	
	@Override
	public Lid daglidVandaag(int lidId) {
		return veranderVervaldag(lidId, new Date());
	}

	@Override
	public Lid jaarlidVandaag(int lidId) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, +1);
		Date oneYearFromNow = cal.getTime();
		return veranderVervaldag(lidId, oneYearFromNow);
	}

	@Override
	public Lid nieuwLid(Lid lid) {
		// TODO controleer of voor gegeven voornaam + familienaam + email-adres al een lid bestaat 
		return lidRepository.save(lid);
	}

}
