package Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Entity implementation class for Entity: States
 *
 */
@Entity

public class States implements Serializable {

	@Id
	private Integer id;
	private String name;
	private Integer country_id;
	private static final long serialVersionUID = 1L;

	public States() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Integer getCountry_id() {
		return this.country_id;
	}

	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}
	@Override
	public String toString() {
		return "States [id=" + id + ", name=" + name + ", country_id=" + country_id + "]";
	}
}
