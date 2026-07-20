package ui_tests;

import com.demo_qa.ui.test_data.models.PracticeFormData;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PracticeFormTest extends BaseTest {

    @Test
    @Description("Verify successful form submission when all required fields are filled with valid data")
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

        submissionModal
                .shouldBeOpened()
                .shouldHaveTitle("Thanks for submitting the form")
                .shouldHaveValue("Student Name", data.firstName() + " " + data.lastName())
                .shouldHaveValue("Student Email", data.email())
                .shouldHaveValue("Gender", data.gender())
                .shouldHaveValue("Mobile", data.mobile())
                .shouldHaveValue("Date of Birth", String.format("%02d", Integer.parseInt(data.day()))
                        + " " + data.month()
                        + "," + data.year())
                .shouldHaveValue("Subjects", String.join(", ", data.subjects()))
                .shouldHaveValue("Hobbies", String.join(", ", data.hobbies()))
                .shouldHaveValue("Picture", practiceFormPage.getFileName(data.selectPic()))
                .shouldHaveValue("Address", data.address())
                .shouldHaveValue("State and City", data.state() + " " + data.city());

//        assertEquals(
//                "Thanks for submitting the form",
//                submissionModal.getActualTitle()
//        );
//
//        assertEquals(
//                data.firstName() + " " + data.lastName(),
//                submissionModal.getActualValue("Student Name")
//        );
//
//        assertEquals(
//                data.email(),
//                submissionModal.getActualValue("Student Email")
//        );
//
//        assertEquals(
//                data.gender(),
//                submissionModal.getActualValue("Gender"));
//        assertEquals(
//                data.mobile(),
//                submissionModal.getActualValue("Mobile")
//        );
//        String expectedDay = String.format("%02d", Integer.parseInt(data.day()));
//
//        assertEquals(
//                expectedDay + " " + data.month() + "," + data.year(),
//                submissionModal.getActualValue("Date of Birth")
//        );
//        assertEquals(
//                String.join(", ", data.subjects()),
//                submissionModal.getActualValue("Subjects")
//        );
//        assertEquals(
//                String.join(", ", data.hobbies()),
//                submissionModal.getActualValue("Hobbies")
//        );
//        assertEquals(
//                practiceFormPage.getFileName(data.selectPic()),
//                submissionModal.getActualValue("Picture")
//        );
//        assertEquals(
//                data.address(),
//                submissionModal.getActualValue("Address")
//        );
//        assertEquals(
//                data.state() + " " + data.city(),
//                submissionModal.getActualValue("State and City")
//        );
    }
}
