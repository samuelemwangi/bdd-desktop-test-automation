package calculator.ui.steps;

import calculator.ui.pages.CalculatorPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class Calculator {

    CalculatorPage calPage = new CalculatorPage();

    @Given("I have opened a calculator")
    public void step_impl_load_calc() {
        calPage.loadCalc();
    }

    @When("I add {string} and {string}")
    public void step_impl_add_nums(String num1, String num2) {
        calPage.sumNumbers(num1,num2);
    }

    @When("I click equals")
    public void step_impl_click_equals(){
        calPage.findSum();
    }

    @Then("The result should be {string}")
    public void step_impl_total_sum(String expectedResult) {

        try {

            String actualResult =  calPage.getResults();
            Assert.assertEquals(actualResult, expectedResult);

        }catch (Exception e){
            Assert.fail();
        }
    }


}
