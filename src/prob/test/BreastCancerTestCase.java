package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * http://yudkowsky.net/rational/bayes
 *
 * 1% of women at age forty who participate in routine screening have breast 
 * cancer.  80% of women with breast cancer will get positive mammographies.  
 * 9.6% of women without breast cancer will also get positive mammographies.  
 * A woman in this age group had a positive mammography in a routine screening. 
 * What is the probability that she actually has breast cancer?
 * 
 * P(A|B) = P(B|A)P(A)/P(B)
 */
public class BreastCancerTestCase
{
	/**
	 * P(A|B) = P(B|A)P(A)/P(B)
	 * P(Cancer|PositiveResult) = P(PositiveResult|Cancer)P(Cancer)/P(PositiveResult)
	 */
	@Test public void testCancerGivenPositiveTest()
	{
		// P(PositiveResult|Cancer)
		double positiveResultGivenCancer = 0.80;
		
		// P(Cancer)
		double haveCancer = 0.01;
		
		// true positive
		// P(Cancer) and P(PositiveResult|Cancer)
		double truePositive = haveCancer * positiveResultGivenCancer;		
		
		// false positive
		
		// P(no cancer)
		double noCancer = 1.0-haveCancer;
		// P(positive | no cancer)
//		double positiveGivenNoCancer = 1-positiveResultGivenCancer;
		double positiveGivenNoCancer = 0.096;
		// P(no cancer) and P(positive | no cancer)
		double falsePositive = noCancer*positiveGivenNoCancer;

		// P(PositiveResult)
		double probCancer = truePositive+falsePositive;
		
		// result
		// P(Cancer|PositiveResult) = P(PositiveResult|Cancer)P(Cancer)/P(PositiveResult)
		double cancerGivenTest = (positiveResultGivenCancer*haveCancer) / probCancer;
		assertEquals(0.078, cancerGivenTest, 0.001);
		
	}
}
