package calculator.ui.pages;

import calculator.ui.utils.AppUtilities;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Hashtable;

public class CalculatorPage extends AppUtilities {

    // Define locators
    private static final By clearButton = By.name("Clear");
    private static final By plusButton = By.name("Plus");
    private static final By equalsButton = By.name("Equals");

    //Results
    private static final By results = new MobileBy.ByAccessibilityId("CalculatorResults");

    //Numbers
    private static final Hashtable<String,String> numButtons = new Hashtable<String, String>();


    public CalculatorPage() {
        // initialize num buttons
        numButtons.put("0","Zero");
        numButtons.put("1","One");
        numButtons.put("2","Two");
        numButtons.put("3","Three");
        numButtons.put("4","Four");
        numButtons.put("5","Five");
        numButtons.put("6","Six");
        numButtons.put("7","Seven");
        numButtons.put("8","Eight");
        numButtons.put("9","Nine");
    }

    public void loadCalc() {
        findElement(clearButton).click();
    }

    public void sumNumbers(String num1, String num2){
        clickNum(num1);
        findElement(plusButton).click();
        clickNum(num2);
    }

    public  void findSum(){
        findElement(equalsButton).click();
    }

    public void clickNum(String num){

        char[] numChar =  num.toCharArray();

        for (char i: numChar) {
            By numVal =  By.name(numButtons.get(i+""));
            findElement(numVal).click();
        }
    }

    public String getResults()
    {
        WebElement calcResults = findElement(results);
        return  calcResults.getText().replace("Display is","").trim();
    }

}
