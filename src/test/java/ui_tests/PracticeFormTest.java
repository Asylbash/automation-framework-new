package ui_tests;

import com.demo_qa.ui.test_data.models.PracticeFormData;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTest extends BaseTest {

    @Test
    @Description("Verify successful form submission when all required fields are filled with valid data and the confirmation modal displays correct information")
    void fillPracticeFormTest() throws InterruptedException {
        PracticeFormData data = practiceFormRandomData.createRandomData();
        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage
                .insertFirstName(data.firstName())
                .insertLastName(data.lastName())
                .insertUserEmail(data.email())
                .selectGender(data.gender())
                .insertUserNumber(data.mobile())
                .selectDateOfBirth(data.day(), data.month(), data.year())
                .insertSubjects(data.subjects())
                .selectHobby(data.hobbies())
                .uploadPicture(data.selectPic())
                .insertCurrentAddress(data.address())
                .selectState(data.state())
                .selectCity(data.city())
                .clickSubmitForm();
        assertEquals(
                "Thanks for submitting the form",
                submissionModal.getTitle()
        );

        assertEquals(
                data.firstName() + " " + data.lastName(),
                submissionModal.getValue("Student Name")
        );

        assertEquals(
                data.email(),
                submissionModal.getValue("Student Email")
        );

        assertEquals(
                data.gender(),
                submissionModal.getValue("Gender"));
        assertEquals(
                data.mobile(),
                submissionModal.getValue("Mobile")
        );
        String expectedDay = String.format("%02d", Integer.parseInt(data.day()));

        assertEquals(
                expectedDay + " " + data.month() + "," + data.year(),
                submissionModal.getValue("Date of Birth")
        );
        assertEquals(
                String.join(", ", data.subjects()),
                submissionModal.getValue("Subjects")
        );
        assertEquals(
                String.join(", ", data.hobbies()),
                submissionModal.getValue("Hobbies")
        );
        assertEquals(
                practiceFormPage.getFileName(data.selectPic()),
                submissionModal.getValue("Picture")
        );
        assertEquals(
                data.address(),
                submissionModal.getValue("Address")
        );
        assertEquals(
                data.state() + " " + data.city(),
                submissionModal.getValue("State and City")
        );
    }
}
