package be.odisee.jzzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.odisee.jzzz.domain.Lid;
import be.odisee.jzzz.service.LidService;

@RestController
public class LidController {
	
	@Autowired
	LidService lidService;
	
	@RequestMapping (path = "bestaandDaglid", method=RequestMethod.POST) 
	Lid bestaandDaglid (@RequestBody Lid lid)	{
		return lidService.daglidVandaag(lid.getId()); 
	}

	@RequestMapping (path = "nieuwDaglid", method=RequestMethod.POST) 
	Lid nieuwDaglid (@RequestBody Lid lid)	{
		lid = lidService.nieuwLid(lid);
		return lidService.daglidVandaag(lid.getId());
	}
	
	@RequestMapping (path = "verlengJaarlid", method=RequestMethod.POST) 
	Lid verlengJaarlid (@RequestBody Lid lid)	{
		return lidService.jaarlidVandaag(lid.getId());
	}
	
	@RequestMapping (path = "nieuwJaarlid", method=RequestMethod.POST) 
	Lid nieuwJaarlid (@RequestBody Lid lid)	{
		lid = lidService.nieuwLid(lid);
		return lidService.jaarlidVandaag(lid.getId());
	}
	
}
