# Instructions for ATDD exercises 

## The legacy code
The legacy code is a simple number converter. At the beginning you'll find some converters 
for decimals, binaries and hexadecimals. 

### Tests
The ATDD part is based on cucumber (https://cucumber.io/). The tests are divided in
- feature files
- a JUnit runner i.e. NumberConverterTest.java
- step files

You can execute the test by running the JUnit runner in your IDE.

The implementation of the tests is missing. 

## 1. Exercise
Implement the cucumber steps to test the requirements defined in ConvertingIntoDifferentFormats.feature.

## 2. Exercise
Enhance your tests and the legacy code base to implement the new requirements defined in ConvertingIllegalNumbers.feature.

## 3. Exercise
- Enhance your requirements with specifications for a Roman number converter  
- Implement a RomanConverter by using TDD
- Add the glue code to instantiate your new RomanConverter correctly (you can add it as soon it is available i.e. before you complete TDD loop)

## 4. Exercise
