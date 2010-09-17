package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Bayes;

/**
 * http://www.celiagreen.com/charlesmccreery/statistics/bayestutorial.pdf
 * 
 * First-year Statistics for Psychology Students Through Worked Examples
 * Probability and Bayes’ Theorem
 * 
 * M&M's and Smarties3 are two different brands of small milk chocolates in a 
 * crisp coloured shell. Each item of confectionery is about the same size and 
 * each brand comes in a mixture of colours.
 * 
 * A large bowl contains a mixture of the two brands in the ratio of five M&M's 
 * to four Smarties in just four colours - red, yellow, orange and green.
 * 
 * The proportions of the M&M's which are red, yellow, orange and green are 
 * 0.3, 0.4, 0.1 and 0.2 respectively, while the equivalent proportions for 
 * Smarties are 0.25, 0.2, 0.3 and 0.25
 * 
 * A sweet is chosen at random from the bowl. What is the probability that it 
 * is (i) a green Smartie; (ii) green; (iii) a Smartie if it is green?
 * 
 * The shades of green used by the two brands are very different and can be 
 * readily identified, but it is not possible to differentiate the two brands 
 * from the other colours. A single sweet is drawn at random from the container 
 * and a statistician is shown its colour. If it is green, the statistician 
 * will identify its brand correctly, but if it is not green, the statistician 
 * will toss a fair coin to decide which brand she thinks it is. What is the 
 * probability that she correctly identifies the brand?
 * 
 * If four sweets are chosen from this very large pool of sweets, what is the 
 * probability that three will be of one brand and the other sweet of the 
 * other brand?
 */
public class SmartiesAndMandMsTestCase
{
	/**
	 * probability of of each type 
	 */
	@Test public void testBasicDistributions()
	{
		// prob of an m&m
		double probMandM = Bayes.observationToProbability(5, 9);
		assertEquals(0.555, probMandM, 1.0E-3);
		// red, yellow, orange and green = 0.3, 0.4, 0.1 and 0.2
		
		// prob of a smartie
		double probSmartie = Bayes.observationToProbability(4, 9);
		assertEquals(0.444, probSmartie, 1.0E-3);
		// red, yellow, orange and green = 0.25, 0.2, 0.3 and 0.25		
	}
		
	/**
	 * probability of a green smartie
	 * 
	 * P(Green and Smartie)
	 *  = P(Smartie) * P(Green | Smartie) 
	 * 
	 */
	@Test public void testGreenSmartie()
	{
		// P(Smartie)
		double probSmartie = Bayes.observationToProbability(4, 9);
		assertEquals(0.444, probSmartie, 1.0E-3);
		// P(Green | Smartie) 
		double probGreenGivenSmartie = 0.25;
		// P(Smartie) * P(Green | Smartie) 
		double probGreenSmartie = probSmartie * probGreenGivenSmartie;
		assertEquals(0.111, probGreenSmartie, 1.0E-3);
	}
	
	/**
	 * probability of a green candy
	 * 
	 * P(Smartie)*P(Green|Smartie) + P(M&M)*P(Green|M&M)
	 */
	@Test public void testGreen()
	{
		// P(Smartie)
		double probSmartie = Bayes.observationToProbability(4, 9);
		assertEquals(0.444, probSmartie, 1.0E-3);
		// P(Green | Smartie) 
		double probGreenGivenSmartie = 0.25;
		// P(Smartie) * P(Green | Smartie) 
		double probGreenSmartie = probSmartie * probGreenGivenSmartie;
		assertEquals(0.111, probGreenSmartie, 1.0E-3);
		
		// P(M&M)
		double probMandM = Bayes.observationToProbability(5, 9);
		assertEquals(0.555, probMandM, 1.0E-3);
		// P(Green | M&M)
		double probGreenGivenMandM = 0.2;
		//  P(M&M)*P(Green|M&M)
		double probGreenMandM = probMandM * probGreenGivenMandM;
		assertEquals(0.111, probGreenMandM, 1.0E-3);
		
		//  P(Smartie)*P(Green|Smartie) + P(M&M)*P(Green|M&M)
		double probGreen = probGreenSmartie + probGreenMandM;
		assertEquals(0.222, probGreen, 1.0E-3);
	}
	
	/**
	 * probability of a smartie if green
	 * 
	 * P(Smartie | Green)
	 * = P(Green | Smartie) * P(Smartie) / P(Green)
	 */
	@Test public void testSmartieIfGreen()
	{
		// P(Green | Smartie) 
		double probGreenGivenSmartie = 0.25;
		// P(Smartie)
		double probSmartie = Bayes.observationToProbability(4, 9);
		assertEquals(0.444, probSmartie, 1.0E-3);
		
		// P(Smartie) * P(Green | Smartie) 
		double probGreenSmartie = probSmartie * probGreenGivenSmartie;
		assertEquals(0.111, probGreenSmartie, 1.0E-3);
		// P(M&M)
		double probMandM = Bayes.observationToProbability(5, 9);
		assertEquals(0.555, probMandM, 1.0E-3);
		// P(Green | M&M)
		double probGreenGivenMandM = 0.2;
		//  P(M&M)*P(Green|M&M)
		double probGreenMandM = probMandM * probGreenGivenMandM;
		assertEquals(0.111, probGreenMandM, 1.0E-3);		
		//  P(Smartie)*P(Green|Smartie) + P(M&M)*P(Green|M&M)
		double probGreen = probGreenSmartie + probGreenMandM;
		assertEquals(0.222, probGreen, 1.0E-3);
		
		// P(Smartie | Green)
		double probSmartieGivenGreen = (probGreenGivenSmartie*probSmartie) / probGreen;
		assertEquals(0.5, probSmartieGivenGreen, 0);
	}

	/**
	 * probability of guessing the brand with a random coin flip (not green)
	 * 
	 * P(not green) and P(guess correctly)
	 * +
	 * P(green)
	 */
	@Test public void testBrand()
	{
		// P(green)
		double probGreen = 0.22222222;
		// P(not green)
		double probNotGreen = 1.0-probGreen;
		// P(guess smartie/m&m)
		double probGuessNonGreenBrand = 0.5;
		// P(not green) and P(guess correctly)
		double probGuessNonGreenBrandCorrectly = probNotGreen * probGuessNonGreenBrand;
		assertEquals(0.3888, probGuessNonGreenBrandCorrectly, 1.0E-4);
		
		// P(guess non-green brand correctly) + P(green)
		double probGuessCorrectly = probGreen + probGuessNonGreenBrandCorrectly;
		assertEquals(0.6111, probGuessCorrectly, 1.0E-4);		
	}

	/**
	 * Draw 4, what is the probability of 3 being one brand and 1 another?
	 * 
	 */
	@Test public void testBrandPartition()
	{
		// prob of an m&m
		double probMandM = Bayes.observationToProbability(5, 9);
		assertEquals(0.555, probMandM, 1.0E-3);
		// prob of a smartie
		double probSmartie = Bayes.observationToProbability(4, 9);
		assertEquals(0.444, probSmartie, 1.0E-3);
		
		// probability of 3*M&M 1*Smartie
		// P(M&M)^3 because they are AND's 
		// = P(M&M)*P(M&M)*P(M&M) * P(Smartie)
		double probThreeMandMOneSmartie = probMandM * probMandM * probMandM * probSmartie;
		assertEquals(0.0759, probThreeMandMOneSmartie, 0.001);
		
		// probability of 3*smarties and 1*M&M
		// P(Smartie)*P(Smartie)*P(Smartie) * P(M&M)
		double probThreeSmartieOneMandM = probSmartie*probSmartie*probSmartie * probMandM;
		assertEquals(0.0486, probThreeSmartieOneMandM, 0.001);
		
		// probability of getting 3 & 1 in a given set of 4
		// here the 4 comes from the fact that you can only have 4 possible cases of each
		// MMMS, MMSM, MSMM, SMMM, and the inverse
		double prob4And1 = (4*probThreeMandMOneSmartie) + (4*probThreeSmartieOneMandM);
		assertEquals(0.4999, prob4And1, 0.0001);
	}
}
