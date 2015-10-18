package com.multidevice.test.pages;

import javax.validation.constraints.NotNull;

import com.multidevice.test.annotations.FindBy;
import com.multidevice.test.annotations.Page;
import com.multidevice.test.annotations.Steps;
import com.multidevice.test.domain.Locator;
import com.multidevice.test.framework.AbstractScreen;
import lombok.AccessLevel;
import lombok.Getter;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

/**
 * This class describes Calculator screen.
 */
@Page
@Steps
public class CalculatorPage extends AbstractScreen {

  @FindBy(id = "formula")
  private Locator formulaField;

  @FindBy(id = "digit_0")
  private Locator numberZeroButton;

  @FindBy(id = "digit_1")
  private Locator numberOneButton;

  @FindBy(id = "digit_2")
  private Locator numberTwoButton;

  @FindBy(id = "digit_3")
  private Locator numberThreeButton;

  @FindBy(id = "digit_4")
  private Locator numberFourButton;

  @FindBy(id = "digit_5")
  private Locator numberFiveButton;

  @FindBy(id = "digit_6")
  private Locator numberSixButton;

  @FindBy(id = "digit_7")
  private Locator numberSevenButton;

  @FindBy(id = "digit_8")
  private Locator numberEightButton;

  @FindBy(id = "digit_9")
  private Locator numberNineButton;

  @FindBy(id = "dec_point")
  private Locator decPointButton;

  @FindBy(id = "eq")
  private Locator equalButton;

  @FindBy(id = "del")
  private Locator deleteButton;

  @FindBy(id = "op_div")
  private Locator divideButton;

  @FindBy(id = "op_mul")
  private Locator multiplyButton;

  @FindBy(id = "op_sub")
  private Locator substractButton;

  @FindBy(id = "op_add")
  private Locator addButton;

  @FindBy(id = "result")
  private Locator resultField;

  @Getter(AccessLevel.PROTECTED)
  @FindBy(id = "formula")
  private Locator identifier;

  /**
   * Presses numeric button with the given number.
   *
   * @param number
   *          Numeric button number (0-9).
   */
  @When("Press button with number $number")
  public void pressNumericButton(@NotNull final int number) {
    Locator locator = null;
    switch (number) {
      case 0:
        locator = numberZeroButton;
        break;
      case 1:
        locator = numberOneButton;
        break;
      case 2:
        locator = numberTwoButton;
        break;
      case 3:
        locator = numberThreeButton;
        break;
      case 4:
        locator = numberFourButton;
        break;
      case 5:
        locator = numberFiveButton;
        break;
      case 6:
        locator = numberSixButton;
        break;
      case 7:
        locator = numberSevenButton;
        break;
      case 8:
        locator = numberEightButton;
        break;
      case 9:
        locator = numberNineButton;
        break;
      default:
        break;
    }
    findBy(locator).click();
  }

  /**
   * Presses Delete button.
   */
  @When("Press DELETE button")
  public void pressDeleteButton() {
    findBy(deleteButton).click();
  }

  /**
   * Presses Divide button.
   */
  @When("Press DIVIDE button")
  public void pressDivideButton() {
    findBy(divideButton).click();
  }

  /**
   * Switches to another test phone to continue testing.
   */
  @When("Switch to another phone")
  public void switchDevice() {
    switchToNextDevice();
  }

  /**
   * Presses Multiply button.
   */
  @When("Press MULTIPLY button")
  public void pressMultiplyButton() {
    findBy(multiplyButton).click();
  }

  /**
   * Presses Add button.
   */
  @When("Press ADD button")
  public void pressAddButton() {
    findBy(addButton).click();
  }

  /**
   * Presses Substract button.
   */
  @When("Press SUBSTRACT button")
  public void pressSubstractButton() {
    findBy(substractButton).click();
  }

  @When("Press EQUAL button")
  public void pressEqualButton() {
    findBy(equalButton).click();
  }

  /**
   * Verifies if result corresponds to expected.
   */
  @Then("Result equals to $result")
  public void verifyResultEqualsTo(final String expectedResult) {
    final String actualResult = findBy(formulaField).getText();
    Assert.assertEquals(expectedResult, actualResult);
  }

}
