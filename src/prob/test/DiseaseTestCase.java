package prob.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import prob.Bayes;


/** 
 * http://jsteinhardt.wordpress.com/2010/09/13/nobody-understands-probability/
 * 
 * One example is the following famous problem: A doctor has a test for a 
 * disease that is 99% accurate. In other words, it has a 1% chance of telling 
 * you that you have a disease even if you don’t, and it has a 1% chance of 
 * telling you that you don’t have a disease even if you do. Now suppose that 
 * the disease that this tests for is extremely rare, and only affects 1 in 
 * 1,000,000 people. If the doctor performs the test on you, and it comes up 
 * positive, how likely are you to have the disease?
 * 
 * p(A | B) = p(B | A) p(A) / p(B)
 * p(Disease | Test is positive) = p(Test is positive | Disease)*p(Disease)/p(Test is positive)
 * 
 * 0.99*10^-6 / (0.01*(1-10^-6) + 0.99*10^-6)
 * The answer is in the order of 10^-4 = 1.0E-4 = 0.00001
 */
public class DiseaseTestCase
{
	/**
	 * P(A) = P(Disease)
	 * 
	 * A randomly observed person has the disease
	 */
	@Test public void testPersonHasDisease()
	{
		double personHasDisease = Bayes.observationToProbability(1, 1000000);
		assertEquals(1.0E-6, personHasDisease, 0);
	}
	
	/**
	 * p(B | A) = p(Test is positive | Disease)
	 * 
	 * A randomly observed 'test applied to a person with the disease' was positive
	 */
	@Test public void testTestIsPositiveGivenDisease()
	{
		double testPositiveGivenDisease = Bayes.observationToProbability(99, 100);
		assertEquals(0.99, testPositiveGivenDisease, 0);
	}
	
	/**
	 * p(B) = p(Test is positive)
	 * 
	 * A randomly observed test is observed being positive
	 */
	@Test public void testTestIsPositive()
	{
		//
		// disease and test positive
		//
		
		// P(Disease)
		double personHasDisease = Bayes.observationToProbability(1, 1000000);
		assertEquals(1.0E-6, personHasDisease, 0);
		// p(Test is positive | Disease) = true positive
		double testPositiveGivenDisease = 1.0 - Bayes.observationToProbability(1, 100); // 99/100
		assertEquals(0.99, testPositiveGivenDisease, 0);
		// P(Disease) and p(Test is positive | Disease)
		double diseaseAndTestPositive = personHasDisease * testPositiveGivenDisease;
		assertEquals(9.9E-7, diseaseAndTestPositive, 0); // very small!
		
		//
		// no disease and test positive
		//
		
		// P(No Disease)
		double personDoesNotHaveDisease = 1.0 - Bayes.observationToProbability(1, 1000000);
		assertEquals(1-1.0E-6, personDoesNotHaveDisease, 0);
		// p(Test is positive | Disease) = false positive
		double testPositiveGivenNoDisease = Bayes.observationToProbability(1, 100);
		assertEquals(0.01, testPositiveGivenNoDisease, 0.001);
		// P(No Disease) and p(Test is positive | No Disease Disease)
		double noDiseaseAndTestPositive = personDoesNotHaveDisease * testPositiveGivenNoDisease;
		assertEquals(0.00999999, noDiseaseAndTestPositive, 0); // very small!
		
		// p(Test is positive)
		double testPositive = diseaseAndTestPositive + noDiseaseAndTestPositive;
		assertEquals(0.01, testPositive, 0.001);
	}
	
	/**
	 * p(A | B) = p(B | A) p(A) / p(B)
	 * p(Disease | Test is positive) = p(Test is positive | Disease)*p(Disease)/p(Test is positive)
	 * 
	 * Given a randomly observed person who tests positive has the disease
	 * = ~0.009% (!!) 
	 */
	@Test public void testDiseaseGivenTestPositive()
	{
		// p(B | A) = p(Test is positive | Disease)
		double testPositiveGivenDisease = 1.0 - Bayes.observationToProbability(1, 100); // 99/100
		assertEquals(0.99, testPositiveGivenDisease, 0);
		
		// P(A) = P(Disease)
		double personHasDisease = Bayes.observationToProbability(1, 1000000);
		assertEquals(1.0E-6, personHasDisease, 0);
		

		
		// P(Disease) and p(Test is positive | Disease)
		double diseaseAndTestPositive = personHasDisease * testPositiveGivenDisease;
		assertEquals(9.9E-7, diseaseAndTestPositive, 0); // very small!
		// P(No Disease) and p(Test is positive | No Disease Disease)
		double noDiseaseAndTestPositive = (1.0-personHasDisease) * (1.0-testPositiveGivenDisease);
		assertEquals(0.00999999, noDiseaseAndTestPositive, 0.0000001); // very small!
		
		// p(Test is positive)
		double testPositive = diseaseAndTestPositive + noDiseaseAndTestPositive;
		assertEquals(0.01, testPositive, 0.001);
		
		// 0.99*10^-6 / (0.01*(1-10^-6) + 0.99*10^-6)
		double diseaseGivenTestPositive = (testPositiveGivenDisease*personHasDisease)/testPositive;
		assertEquals(1.0E-4, diseaseGivenTestPositive, 1.0E-5); // ~0.0001
		// ~0.009% probability
	}
}
