package edu.westga.cs4985.clinicApp.view;

import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;
import javafx.fxml.FXML;


public class ClinicAppCodeBehind {
	
	private ClinicAppViewModel viewmodel;
	
	/**
	 * Instantiates a new code behind
	 */
	public ClinicAppCodeBehind() {
		this.viewmodel = new ClinicAppViewModel();
	}
	
	@FXML
	private void initialize() {
		this.bindToViewModel();
	}
	
	private void bindToViewModel() {
	}
}
