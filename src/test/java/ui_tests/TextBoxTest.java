package ui_tests;

import com.demo_qa.ui.test_data.models.TextBoxUser;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TextBoxTest extends BaseTest {

    @Test
    @Description("Verify that the text box form can be filled and submitted successfully, " +
            "and the confirmation modal displays the correct information")
    void fillTextBoxFormTest() {
        TextBoxUser data = textBoxRandomData.createRandomTextBoxData();
        textBoxPage
                .openTextBoxPage();
        javaScriptHelper.removeFixedElements();
        textBoxPage
                .typeUserName(data.userName())
                .typeUserEmail(data.userEmail())
                .typeCurrentAddress(data.currentAddress())
                .typePermanentAddress(data.permanentAddress())
                .submitForm()
                .checkField("name", data.userName())
                .checkField("email", data.userEmail())
                .checkField("currentAddress", data.currentAddress())
                .checkField("permanentAddress", data.permanentAddress());
    }

    @Test
    @Description("Negative test: Verify that the form does not accept invalid email format and " +
            "highlights the email field as invalid")
    void fillTextBoxFormWithInvalidEmailTest() {
        TextBoxUser data = textBoxRandomData.createRandomTextBoxData()
                .toBuilder()
                .userEmail("invalid-email-format")
                .build();
        textBoxPage
                .openTextBoxPage();
        javaScriptHelper.removeFixedElements();
        textBoxPage
                .typeUserName(data.userName())
                .typeUserEmail(data.userEmail())
                .typeCurrentAddress(data.currentAddress())
                .typePermanentAddress(data.permanentAddress())
                .submitForm()
                .shouldHighlightEmailAsInvalid()
                .shouldNotShowOutput();
    }

    @Test
    @Description("Negative test: Verify that the form does not accept empty fields and does not display output")
    void fillTextBoxFormWithEmptyFieldsTest() {
        textBoxPage
                .openTextBoxPage();
        javaScriptHelper.removeFixedElements();
        textBoxPage
                .submitForm()
                .shouldNotShowOutput();
    }

    @Test
    @Description("Verify that if fillup one field system accepts it and display output")
    void fillTextBoxFormWithOneFieldTest() {
        TextBoxUser data = textBoxRandomData.createRandomTextBoxData();
        textBoxPage
                .openTextBoxPage();
        javaScriptHelper.removeFixedElements();
        textBoxPage
                .typeUserName(data.userName())
                .submitForm()
                .checkField("name", data.userName());
    }
}
