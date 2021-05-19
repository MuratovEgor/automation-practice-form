package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successfulSubmitForm() {
        open("/automation-practice-form");

        $("#firstName").setValue("Egor");
        $("#lastName").setValue("Muratov");
        $("#userEmail").setValue("egormuratov@yahoo.com");

        $(("[for=\"gender-radio-1\"]")).click();
        $("#userNumber").setValue("89674242424");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select [value=\"5\"]").click();
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day--007").click();

        $("#subjectsInput").setValue("Hindi").pressEnter();

        $("[for=\"hobbies-checkbox-1\"]").click();
        $("[for=\"hobbies-checkbox-2\"]").click();

        $("#currentAddress").setValue("Krasnodar");

        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit").click();

        $("#modal-content").shouldBe();
        $("#example-modal-sizes-title-lg").shouldHave((text("Thanks for submitting the form")));
        $(".table-responsive").shouldHave(
                text("Student Name"), text("Egor Muratov"),
                text("Student Email"), text("egormuratov@yahoo.com"),
                text("Gender"), text("Male"),
                text("Mobile"), text("8967424242"),
                text("Date of Birth"), text("07 June,1991"),
                text("Subjects"), text("Hindi"),
                text("Hobbies"), text("Sports, Reading"),
                text("Picture"),
                text("Address"), text("Krasnodar"),
                text("State and City"), text("NCR Delhi")
        );

        $("#closeLargeModal").click();
        $("#modal-content").shouldNotBe();
    }
}
