package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Bayes;

/**
 * http://en.wikipedia.org/wiki/Bayes'_theorem#Example_1:_Drug_testing
 * 
 * An example of the use of Bayes' theorem is the evaluation of drug test 
 * results. Suppose a certain drug test is 99% sensitive and 99% specific, that 
 * is, the test will correctly identify a drug user as testing positive 99% of 
 * the time, and will correctly identify a non-user as testing negative 99% of 
 * the time. This would seem to be a relatively accurate test, but Bayes' 
 * theorem can be used to demonstrate the relatively high probability of 
 * misclassifying non-users as users. Let's assume a corporation decides to 
 * test its employees for drug use, and that only 0.5% of the employees 
 * actually use the drug. What is the probability that, given a positive drug 
 * test, an employee is actually a drug user? Let "D" stand for being a drug 
 * user and "N" indicate being a non-user. Let "+" be the event of a positive 
 * drug test. We need to know the following:
 * 
 * - P(D), or the probability that the employee is a drug user, regardless of any
 *  other information. This is 0.005, since 0.5% of the employees are drug 
 *  users. This is the prior probability of D.
 * - P(N), or the probability that the employee is not a drug user. This is 
 * 1 − P(D), or 0.995.
 * - P(+|D), or the probability that the test is positive, given that the 
 * employee is a drug user. This is 0.99, since the test is 99% accurate.
 * - P(+|N), or the probability that the test is positive, given that the 
 * employee is not a drug user. This is 0.01, since the test will produce a 
 * false positive for 1% of non-users.
 * - P(+), or the probability of a positive test event, regardless of other 
 * information. This is 0.0149 or 1.49%, which is found by adding the 
 * probability that a true positive result will appear (= 99% x 0.5% = 0.495%) 
 * plus the probability that a false positive will appear 
 * (= 1% x 99.5% = 0.995%). This is the prior probability of +.
 * 
 * Given this information, we can compute the posterior probability P(D|+) of 
 * an employee who tested positive actually being a drug user: 0.3322
 * 
 * Despite the specificity and sensitivity of the test, the low base-rate of 
 * use renders the accuracy of the test low: the probability that an employee
 * who tests positive actually using drugs is only about 33%, so it is in fact 
 * more likely that the employee is not a drug user. The rarer the condition 
 * for which we are testing, the greater the percentage of positive tests that 
 * will be false positives.
 * 
 * p(A | B) = p(B | A) p(A) / p(B)
 * P(D|+) = p(+ | D) p(D) / p(+)
 */
public class DrugTestingTestCase
{

	/**
	 * p(A) = p(D)
	 */
	@Test public void testDrugUser()
	{
		double probUser = Bayes.observationToProbability(0.5, 100);
		assertEquals(0.005, probUser, 0);
	}
	
	/**
	 * p(B | A) = p(+ | D)
	 */
	@Test public void testPositiveGivenDrugUser()
	{
		double probPositiveGivenUser = Bayes.observationToProbability(99, 100);
		assertEquals(0.99, probPositiveGivenUser, 0);
	}
	
	/**
	 * p(B) = p(+)
	 */
	@Test public void testPositive()
	{
		// a drug user tests positive
		
		// p(user)
		double probUser = Bayes.observationToProbability(0.5, 100);
		assertEquals(0.005, probUser, 0);
		// p(user | positive)
		double probPositiveGivenUser = Bayes.observationToProbability(99, 100);
		assertEquals(0.99, probPositiveGivenUser, 0);
		// user . user | positive
		double userTestsPositive = probUser * probPositiveGivenUser;
		assertEquals(0.00495, userTestsPositive, 0);
		
		// not user and positive
		
		// p(non user)
		double probNonUser = 1.0-Bayes.observationToProbability(0.5, 100);
		assertEquals(0.995, probNonUser, 0);
		// p(non user | positive)
		double probNonUserGivenPositive = 1.0 - Bayes.observationToProbability(99, 100);
		assertEquals(0.01, probNonUserGivenPositive, 1.0E-8);
		// non user . non user | positive
		double nonUserTestsPositive = probNonUser * probNonUserGivenPositive;
		assertEquals(0.00995, nonUserTestsPositive, 1.0E-8);
		
		
		// test positive
		double testPositive = userTestsPositive + nonUserTestsPositive;
		assertEquals(0.0149, testPositive, 1.0E-8); // ~1.5%
	}
	
	/**
	 * p(A | B) = P(D|+)
	 */
	@Test public void testDrugUserGivenPositive()
	{
		// p(+ | D)
		double probPositiveGivenUser = Bayes.observationToProbability(99, 100);
		assertEquals(0.99, probPositiveGivenUser, 0);
		
		// p(D)
		double probUser = Bayes.observationToProbability(0.5, 100);
		assertEquals(0.005, probUser, 0);
		
		// p(+)
		double testPositive = 0.00495 + 0.00995;
		assertEquals(0.0149, testPositive, 1.0E-8); // ~1.5%	
		
		// P(D|+) = p(+ | D) p(D) / p(+)
		double userGivenPositive = (probPositiveGivenUser*probUser) / testPositive;
		assertEquals(0.3322, userGivenPositive, 1.0E-4);
	}
}
