package Entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;
/**
 * Entity implementation class for Entity: Countries
 *
 */
@Entity
@Table(name = "Countries")

public class Countries implements Serializable {
	
	@Id
	private Integer id;
	private String sortname;
	private String name;
	private Integer phonecode;
	private static final long serialVersionUID = 1L;

	public Countries() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getSortname() {
		return this.sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public Integer getPhonecode() {
		return this.phonecode;
	}

	public void setPhonecode(Integer phonecode) {
		this.phonecode = phonecode;
	}

	@Override
	public String toString() {
		return "Countries [id=" + id + ", sortname=" + sortname + ", name=" + name + ", phonecode=" + phonecode + "]";
	}
	
	
	public Countries(String name) {
		super();
		this.name = name;
	}
	
}
