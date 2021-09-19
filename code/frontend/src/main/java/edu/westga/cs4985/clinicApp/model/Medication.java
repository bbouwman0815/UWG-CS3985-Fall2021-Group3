package edu.westga.cs4985.clinicApp.model;

import java.util.Date;

public class Medication {
	
	private String brand;
	private String form;
	private String dosage;
	private String frequency;
	private Date refilDate;
	private String specialInstructionString;
	private int refills;
	
	/**
	 * Instantiates a new mediation object
	 * 
	 * @param brand
	 * 		The brand of the mediation
	 * @param from
	 * 		The form of the mediations
	 * @param dosage
	 * 		The dosage of the mediation
	 * @param frequency
	 * 		The frequency of the mediation
	 * @param refilDate
	 * 		The refill date of the mediation
	 * @param instructions
	 * 		The instructions for the mediation
	 * @param refils
	 * 		The number of refills for the mediation
	 */
	public Medication(String brand, String form, String dosage, String frequency, Date refilDate, String instructions, int refils) {
		if (brand == null) {
			throw new IllegalArgumentException();
		}
		if (brand.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (form == null) {
			throw new IllegalArgumentException();
		}
		if (form.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (dosage == null) {
			throw new IllegalArgumentException();
		}
		if (dosage.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (frequency == null) {
			throw new IllegalArgumentException();
		}
		if (frequency.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (refilDate == null) {
			throw new IllegalArgumentException();
		}
		if (instructions == null) {
			throw new IllegalArgumentException();
		}
		if (instructions.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (refils < 0) {
			throw new IllegalArgumentException();
		}
		this.brand = brand;
		this.form = form;
		this.dosage = dosage;
		this.frequency = frequency;
		this.refilDate = refilDate;
		this.specialInstructionString = instructions;
		this.refills = refils;
	}

	public String getBrand() {
		return this.brand;
	}

	public String getForm() {
		return this.form;
	}

	public String getDosage() {
		return this.dosage;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public Date getRefilDate() {
		return this.refilDate;
	}

	public String getSpecialInstructionString() {
		return this.specialInstructionString;
	}

	public int getRefills() {
		return this.refills;
	}
}
