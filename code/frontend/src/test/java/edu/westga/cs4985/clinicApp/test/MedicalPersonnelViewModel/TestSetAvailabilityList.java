package edu.westga.cs4985.clinicApp.test.MedicalPersonnelViewModel;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import edu.westga.cs4985.clinicApp.model.MedicalPersonnel;
import edu.westga.cs4985.clinicApp.model.User;
import edu.westga.cs4985.clinicApp.utils.Country;
import edu.westga.cs4985.clinicApp.utils.Ethnicity;
import edu.westga.cs4985.clinicApp.utils.Gender;
import edu.westga.cs4985.clinicApp.utils.Race;
import edu.westga.cs4985.clinicApp.viewmodel.MedicalPersonnelViewModel;

public class TestSetAvailabilityList {

	public MedicalPersonnel medicalPersonnelDummy() {
		Gender gender = new Gender();
		Country country = new Country();
		Race race = new Race();
		Ethnicity ethnicity = new Ethnicity();
		MedicalPersonnel medicalPersonnel = new MedicalPersonnel("Xavier", "Jameson", gender.sex[0], "08-08-2008", "912 Maple Street",
				"East Maple Building 2B", "Carrollton", "GA", country.country[0], race.race[1], ethnicity.ethnicity[1],
				"770-111-222", "email@email.com", "New", "New", "30118");
		return medicalPersonnel;
	}
	
	@Test
	public void testSetAvailability() throws ParseException {
		
		List<LocalDateTime> dayTimes = new ArrayList<LocalDateTime>();
		dayTimes.add(LocalDateTime.of(2024,9,01,14,00));
		dayTimes.add(LocalDateTime.of(2024,10,01,14,00));
		MedicalPersonnel medicalPersonnel = medicalPersonnelDummy();
		User.setUser(medicalPersonnel);
		MedicalPersonnelViewModel viewModel = new MedicalPersonnelViewModel();
		viewModel.setAvailabilityList(dayTimes);
		assertAll(() -> assertEquals(2, viewModel.availabilityListProperty().size()),
				() -> assertEquals(2, viewModel.availabilityList().size()));
	}
}
