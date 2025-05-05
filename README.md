🧪 AlMosafer Automation Testing

This project contains a suite of automated UI tests for the AlMosafer website using Java, Selenium WebDriver, and TestNG. The tests validate core functionality such as language, currency, contact number, date pickers, and hotel search results across different locales.
📂 Project Structure

AlMosaferAutomationTesting


├── AppTest.java         # Main test class containing all TestNG test cases

├── TestData.java        # Shared data, configurations, and setup logic

⚙️ Technologies Used

    Java

    Selenium WebDriver

    TestNG

    Maven (assumed for dependency management)

    ChromeDriver

✅ Test Scenarios
Test #	Description
1	(Disabled) Language check (html[lang])

2	Currency display validation (Expecting SAR)

3	Contact number validation (+966554400000)

4	Check for Qitaf logo presence in footer

5	Ensure the Hotel tab is not selected by default

6	Validate flight departure date is set to tomorrow

7	Validate return date is set to day after tomorrow

8	Randomly load Arabic/English version and validate language

9	Random hotel city selection (based on locale) and visitor config

10	Validate search results are retrieved

🚀 How to Run the Tests

    Install Dependencies
    Make sure you have the following installed:

        Java JDK 8+

        Maven (optional but recommended)

        Chrome browser

        ChromeDriver (compatible with your browser version)

    Run via TestNG

    mvn test

    Or run AppTest.java via your IDE (e.g., IntelliJ, Eclipse) with TestNG support.

⚠️ Notes

    Test 1 (CheckLanguageTest) is disabled by default because it takes a parameter (ExpectedLanguage) and is better run from another test context (e.g., test suite or test 8).

    Tests rely on dynamic UI elements — the site structure might change, requiring selector updates.

    Make sure pop-up handling logic in TestSetup() is valid for your environment.

📌 Author

This project is authored as a practice/test suite for automated functional testing of a commercial travel platform.