# Mercator Technical Excercise
Saucelabs feature, steps and Page Objects that will work with both Selenium and Playwright.

Edit a single line in the CommonPageOject import path to switch between the two : 

**import com.mercator.implementations.playwright.CommonPage;**

or

**import com.mercator.implementations.selenium.CommonPage;**

To run, in Intellij, 

1 ) Open the file : **Mercator\src\test\resources\features\Mercator.feature**

2 ) Run the scenario **Open the page and order an item**

<img width="395" height="175" alt="image" src="https://github.com/user-attachments/assets/6dbb8025-8644-480d-9be4-a94c66c594d9" />

This is a Maven project.  To download the dependencies run : **mvn install**

Exercise demonstrates knowledge of : 

Java : Interfaces and implementations, Design Patterns

Test Technologies : Playwright, Selenium, Cucumber, Lombock

Note that Data Table **Data Transport Objects** ( DTO ) replace default 
Cucumber DataTables.

These are simple Java Bean objects passed down to
the steps and POs.

Console logging is also implemented





