package edu.westga.cd4985.clinicApp.test.ClinicAppViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.viewmodel.ClinicAppViewModel;

public class TestConstructor {
	@Test
	public void testConstructor() {
		ClinicAppViewModel viewModel = new ClinicAppViewModel();
		assertAll(() -> assertEquals(null, viewModel.userNameProperty().getValue()),
				() -> assertEquals(null, viewModel.passwordTextProperty().getValue()));
	}
}
