package be.odisee.jzzz.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import be.odisee.jzzz.domain.Lid;

@RepositoryRestResource
public interface LidRepository extends JpaRepository<Lid, Integer> {
	Optional<List<Lid>> findByVoornaamStartingWith(@Param("voornaam") String voornaam);
	Optional<List<Lid>> findByFamilienaamStartingWith(@Param("familienaam") String familienaam);
	Optional<List<Lid>> findByVoornaamStartingWithIgnoreCaseAndFamilienaamStartingWithIgnoreCaseOrderByFamilienaam
							(@Param("voornaam") String voornaam, @Param("familienaam") String familienaam);
}
