package edu.neu.cs6510.model;

import edu.neu.cs6510.model.primeKey.LookUpPK;

import javax.persistence.*;
import java.io.Serializable;

/**
 * LookUpData represents 'gov_fin_lookup' table as LookUpData object
 * It has a primary key which is represented by LookUpPk object
 * For each primary key, it has a value represented by value attribute
 */
@Entity
@Table(name = "gov_fin_lookup")
public class LookUpData implements Serializable {

	@EmbeddedId
	private LookUpPK lookUpPK;

	private Double value;

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

	@Override
	public String toString() {
		return "LookUpData{" +
				"lookUpPK=" + lookUpPK +
				", value=" + value +
				'}';
	}
}
