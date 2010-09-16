package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Bayes;

/** 
 * One example is the following famous problem: A doctor has a test for a 
 * disease that is 99% accurate. In other words, it has a 1% chance of telling 
 * you that you have a disease even if you don’t, and it has a 1% chance of 
 * telling you that you don’t have a disease even if you do. Now suppose that 
 * the disease that this tests for is extremely rare, and only affects 1 in 
 * 1,000,000 people. If the doctor performs the test on you, and it comes up 
 * positive, how likely are you to have the disease?
 * 
 * p(Disease | Test is positive) = p(Test is positive | Disease)p(Disease)/p(Test is positive)
 */
public class DiseaseTestCase
{
	/**
	 * Independent probability of disease (without the test)
	 */
	@Test public void testProbabilityOfDisease()
	{
		// have disease
		double probOfDisease = Bayes.observationToProbability(1,1000000);
		assertEquals(1.0E-6, probOfDisease, 0);
		// do not have disease
		double probNoDisease = 1.0 - Bayes.observationToProbability(1,1000000);
		assertEquals(0.99E-6, probNoDisease, 0);
	}
	
	/**
	 * Independent probability of test outcome (without disease)
	 */
	@Test public void testProbabilityOfTestAccurate()
	{
		// test is correct
		double testCorrect = Bayes.observationToProbability(99,100);		
		assertEquals(0.99E-2, testCorrect, 0);
		// test is incorrect (false negative, false positive)
		double testIncorrect = 1.0 - Bayes.observationToProbability(99,100);
		assertEquals(1.0E-2, testIncorrect, 0);
	}
	
	/**
	 * Test is positive given you have the disease
	 * p(Test is positive | Disease)
	 */
	@Test public void testIsPositiveGivenDisease()
	{
		// p(Test is positive | Disease)
		double probOfTestPositiveGivenDisease = 1.0 - Bayes.observationToProbability(1,100);
		assertEquals(0.99E-2, probOfTestPositiveGivenDisease, 0);
	}
	
	/**
	 * probability of test positive for disease and have it
	 */
	@Test public void testTestIsPositive()
	{
		
		
	}
	
	/**
	 * If the doctor performs the test on you, and it comes up positive, 
	 * how likely are you to have the disease?
	 * 
	 * p(Disease | Test is positive) = p(Test is positive | Disease)p(Disease)/p(Test is positive)
	 */
	@Test public void testTestIsPositiveAndHaveDisease() 
	{
		
	}
}
