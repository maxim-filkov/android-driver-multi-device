Narrative:
In order to check it is possible to run test scenarios om multiple devices
As a tester
I want to work with native Android calculator on first device and then switch to second device.

Scenario: This test case just works with calculator on two devices one by one.
When Press button with number 1
And Press ADD button
And Press button with number 1
And Press EQUAL button
And Switch to another phone
And Press button with number 1
And Press ADD button
And Press button with number 1
And Press EQUAL button
Then Result equals to 2
