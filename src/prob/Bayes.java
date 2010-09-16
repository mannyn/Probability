package prob;

/**
 * Implementation of Bayes 
 *
 * http://jsteinhardt.wordpress.com/2010/09/13/nobody-understands-probability/
 * http://en.wikipedia.org/wiki/Bayes'_theorem
 * 
 */
public class Bayes
{
	/**
	 * p(A | B) = p(B | A) p(A) / p(B)
	 * p(Hypothesis | Data) = p(Data | Hypothesis) p(Hypothesis) / p(Data)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public final static double pAGivenB(double a, double b)
	{
		return 0;
	}
	
	
	/**
	 * Observation to a probability
	 * 1 in 1,000,000 converted to a probability
	 * 
	 * @param occurances
	 * @param total
	 * @return
	 */
	public final static double observationToProbability(
			double occurances, double total)
	{
		return occurances/total;
	}
}
