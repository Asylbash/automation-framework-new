package ui_tests;

import com.demo_qa.ui.test_data.models.TextBoxData;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextBoxTest extends BaseTest {

    @Test
    @Description("Verify that the text box form can be filled and submitted successfully, and the confirmation modal displays the correct information")
    void fillTextBoxFormTest() {
        TextBoxData data = textBoxRandomData.createRandomTextBoxData();
        textBoxPage
                .openTextBoxPage();
        javaScriptHelper.removeFixedElements();
        textBoxPage
                .typeUserName(data.userName())
                .typeUserEmail(data.userEmail())
                .typeCurrentAddress(data.currentAddress())
                .typePermanentAddress(data.permanentAddress())
                .submitForm();
        assertEquals(
                "Name:" + data.userName(),
                $("#output #name").getText()
        );

        assertEquals(
                "Email:" + data.userEmail(),
                $("#output #email").getText()
        );

        assertEquals(
                "Current Address :" + data.currentAddress(),
                $("#output #currentAddress").getText()
        );

        assertEquals(
                "Permananet Address :" + data.permanentAddress(),
                $("#output #permanentAddress").getText()
        );
    }
}
