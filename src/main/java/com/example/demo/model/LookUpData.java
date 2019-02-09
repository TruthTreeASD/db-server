package com.example.demo.model;

import com.example.demo.model.primeKey.LookUpPK;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "gov_fin_lookup")

public class LookUpData implements Serializable {

    private Integer id;

	private String name;

	@EmbeddedId
	private LookUpPK lookUpPK;

	private Double value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LookUpPK getLookUpPK() {
		return lookUpPK;
	}

	public void setLookUpPK(LookUpPK lookUpPK) {
		this.lookUpPK = lookUpPK;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
