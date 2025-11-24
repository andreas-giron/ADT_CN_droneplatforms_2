package be.odisee.jzzz.service;

import be.odisee.jzzz.domain.Lid;

public interface LidService {

	public Lid daglidVandaag(int lidId);

	public Lid jaarlidVandaag(int lidId);

	public Lid nieuwLid(Lid lid);

}
