package be.odisee.jzzz.domain;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Lid {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	private String voornaam;
	private String familienaam;
	private String email;
	private Date vervaldag;
	private Date created;
	private Date updated;
	
	public Lid() {}

	public Lid(String voornaam, String familienaam, String email) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
	}

	public Lid(String voornaam, String familienaam, String email, Date vervaldag) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.email = email;
		this.vervaldag = vervaldag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getVervaldag() {
		return vervaldag;
	}

	public void setVervaldag(Date vervaldag) {
		this.vervaldag = vervaldag;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}
}
