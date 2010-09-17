package prob;

/**
 * Implementation of Bayes 
 * Some lame attempts at automating some of the computation involved
 * 
 * p(A | B) = p(B | A) p(A) / p(B)
 * p(Hypothesis | Data) = p(Data | Hypothesis) p(Hypothesis) / p(Data)
 * 
 * p(B | A) = conditional probability of A, given B
 * p(A) = prior probability or marginal probability of A
 * p(B) = prior or marginal probability of B
 * 
 * 
 * http://jsteinhardt.wordpress.com/2010/09/13/nobody-understands-probability/
 * http://en.wikipedia.org/wiki/Bayes'_theorem
 * 
 */
public class Bayes
{
	
	
	public final static double pAGivenB(double a, double b)
	{
		// can this be normalized? 
		return 0;
	}
	
	
	/**
	 * Observation to a probability
	 * 1 in 1,000,000 converted to a probability
	 * 
	 * @param occurances
	 * @param totalOccurances
	 * @return
	 */
	public final static double observationToProbability(
			double occurances, double totalOccurances)
	{
		return occurances/totalOccurances;
	}
}
