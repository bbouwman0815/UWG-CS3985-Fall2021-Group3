package edu.westga.cd4985.clinicApp.test.ClinicAppViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;

public class TestLogin {
	@Test
	public void testConstructor() {
		ClinicAppViewModel viewModel = new ClinicAppViewModel();
		viewModel.userNameProperty().set("jimmy");
		viewModel.passwordTextProperty().set("1234");
		assertEquals("jimmy,1234", viewModel.login());
	}
}
