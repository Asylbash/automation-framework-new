package ui_tests;

import com.demo_qa.ui.test_data.models.PracticeFormUser;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class PracticeFormTest extends BaseTest {

    @Test
    @Description("Verify successful form submission when all required fields are filled with valid data")
    void fillPracticeFormTest() {
        PracticeFormUser data = practiceFormRandomData.createNewUser();
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
    }

    @Test
    @Description("Verify that the form submission fails when required fields are left empty")
    void fillPracticeFormWithEmptyFieldsTest() {
        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage.clickSubmitForm()
                .shouldShowValidation()
                .shouldNotOpenModal();
    }

    @Test
    @Description("Verify that first name field is validated when left empty")
    void fillPracticeFormWithEmptyFirstNameTest() {
        PracticeFormUser data = practiceFormRandomData.createNewUser();

        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage
                .insertLastName(data.lastName())
                .insertUserEmail(data.email())
                .selectGender(data.gender())
                .clickSubmitForm()
                .shouldShowValidation()
                .shouldBeInvalid("firstName")
                .shouldNotOpenModal();
    }

    @Test
    @Description("Verify that only requared fields are filled and form submission is successful")
    void fillPracticeFormWithOnlyRequiredFieldsTest() {
        PracticeFormUser data = practiceFormRandomData.createNewUser();
        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage
                .insertFirstName(data.firstName())
                .insertLastName(data.lastName())
                .selectGender(data.gender())
                .insertUserNumber(data.mobile())
                .clickSubmitForm();
        submissionModal
                .shouldBeOpened()
                .shouldHaveTitle("Thanks for submitting the form")
                .shouldHaveValue("Student Name", data.firstName() + " " + data.lastName())
                .shouldHaveValue("Gender", data.gender())
                .shouldHaveValue("Mobile", data.mobile());

    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/test-data/invalid-emails.csv",
            numLinesToSkip = 1
    )
    @Description("Verify that the form submission fails when an invalid email is provided")
    void shouldNotAcceptInvalidEmail(String email) {
        PracticeFormUser data = practiceFormRandomData.createNewUser()
                .toBuilder()
                .email(email)
                .build();
        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage
                .insertFirstName(data.firstName())
                .insertLastName(data.lastName())
                .insertUserEmail(data.email())
                .selectGender(data.gender())
                .insertUserNumber(data.mobile())
                .clickSubmitForm()
                .shouldShowValidation()
                .shouldBeInvalid("userEmail")
                .shouldNotOpenModal();
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/test-data/invalid-phones.csv",
            numLinesToSkip = 1
    )
    @Description("Verify that the form submission fails when an invalid phone number is provided")
    void shouldNotAcceptInvalidPhoneNumber(String phoneNumber) {
        PracticeFormUser data = practiceFormRandomData.createNewUser()
                .toBuilder()
                .mobile(phoneNumber)
                .build();
        practiceFormPage.openPracticeFormPage();
        javaScriptHelper.removeFixedElements();
        practiceFormPage
                .insertFirstName(data.firstName())
                .insertLastName(data.lastName())
                .insertUserEmail(data.email())
                .selectGender(data.gender())
                .insertUserNumber(data.mobile())
                .clickSubmitForm()
                .shouldShowValidation()
                .shouldBeInvalid("userNumber")
                .shouldNotOpenModal();
    }

}
