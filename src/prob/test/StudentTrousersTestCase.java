package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

import prob.Bayes;


/**
 * Bayes' theorem
 * http://en.wikipedia.org/wiki/Bayes'_theorem#A_simple_example_of_Bayes.27_theorem
 * 
 * Suppose there is a school with 60% boys and 40% girls as its students. 
 * The female students wear trousers or skirts in equal numbers; the boys all 
 * wear trousers. An observer sees a (random) student from a distance, and what 
 * the observer can see is that this student is wearing trousers. What is the 
 * probability this student is a girl? The correct answer can be computed using 
 * Bayes' theorem.
 * 
 * The event A is that the student observed is a girl, and the event B is that 
 * the student observed is wearing trousers. To compute P(A|B), 
 * we first need to know:
 * 
 * - P(A), or the probability that the student is a girl regardless of any other 
 * information. Since the observers sees a random student, meaning that all 
 * students have the same probability of being observed, and the fraction of 
 * girls among the students is 40%, this probability equals 0.4.
 * - P(B|A), or the probability of the student wearing trousers given that the 
 * student is a girl. Since they are as likely to wear skirts as trousers, 
 * this is 0.5.
 * - P(B), or the probability of a (randomly selected) student wearing trousers 
 * regardless of any other information. Since half of the girls and all of the 
 * boys are wearing trousers, this is 0.5×0.4 + 1.0×0.6 = 0.8.
 * 
 * The answer is 0.25 (25%)
 */
public class StudentTrousersTestCase
{
	/**
	 * P(A) = P(Girl)
	 * 
	 * A randomly observed student is a girl
	 */
	@Test public void testStudentIsAGirl()
	{
		double studentIsAGirl = Bayes.observationToProbability(40, 100);
		assertEquals(0.4, studentIsAGirl, 0);	
	}
	/**
	 * P(B|A) = P(Trousers|Girl)
	 * 
	 * A randomly observed girl is wearing trousers
	 */
	@Test public void testTrousersGivenGirl()
	{
		double trousersGivenGirl = Bayes.observationToProbability(50, 100);
		assertEquals(0.5, trousersGivenGirl, 0);	
	}
	/**
	 * P(B) = P(Trousers)
	 * 
	 * A randomly observed student is observed wearing trousers
	 */
	@Test public void testStudentWearingTrousers()
	{
		//
		// girl wear trousers
		//
		
		// P(girl)
		double studentIsAGirl = Bayes.observationToProbability(40, 100);
		assertEquals(0.4, studentIsAGirl, 0);	
		// P(trousers | girl)
		double trousersGivenGirl = Bayes.observationToProbability(50, 100);
		assertEquals(0.5, trousersGivenGirl, 0);
		// P(girl) . P(trousers | girl)
		double girlAndWearTrousers = studentIsAGirl * trousersGivenGirl;
		assertEquals(0.2, girlAndWearTrousers, 0);
		
		//
		// boy wear trousers
		//
		
		// P(boy)
		double studentIsABoy = Bayes.observationToProbability(60, 100);
		assertEquals(0.6, studentIsABoy, 0);
		// P(trousers | boy)
		double trousersGivenBoy = Bayes.observationToProbability(100, 100);
		assertEquals(1.0, trousersGivenBoy, 0);
		// P(boy) . P(trousers | boy)
		double boyAndWearTrousers = studentIsABoy * trousersGivenBoy;
		assertEquals(0.6, boyAndWearTrousers, 0);
		
		// student wears trousers
		// P(Trousers)
		double studentWearsTrousers = girlAndWearTrousers + boyAndWearTrousers;
		assertEquals(0.8, studentWearsTrousers, 0);
	}
	
	/**
	 * P(A|B) = P(Girl | Trousers)  
	 *        = p(Trousers| Girl)p(Girl)/p(Trousers)
	 */
	@Test public void testStudentGirlWearingTrousers()
	{
		// P(Trousers|Girl)
		double trousersGivenGirl = Bayes.observationToProbability(50, 100);
		assertEquals(0.5, trousersGivenGirl, 0);	
		
		// P(Girl)
		double studentIsAGirl = Bayes.observationToProbability(40, 100);
		assertEquals(0.4, studentIsAGirl, 0);	
		
		// P(girl) . P(trousers | girl)
		double girlAndWearTrousers = 0.4 * 0.5;
		assertEquals(0.2, girlAndWearTrousers, 0);
		// P(boy) . P(trousers | boy)
		double boyAndWearTrousers = 0.6 * 1.0;
		assertEquals(0.6, boyAndWearTrousers, 0);				
		// P(Trousers)
		double studentWearsTrousers = girlAndWearTrousers + boyAndWearTrousers;
		assertEquals(0.8, studentWearsTrousers, 0);
		
		// P(Girl | Trousers)  
		double girlGivenWearingTrousers = (trousersGivenGirl*studentIsAGirl)/studentWearsTrousers;
		assertEquals(0.25, girlGivenWearingTrousers, 0);
	}
}
