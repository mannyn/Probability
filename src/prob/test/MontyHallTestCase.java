package prob.test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * http://en.wikipedia.org/wiki/Bayes'_theorem#Example_3:_The_Monty_Hall_problem
 *
 * We are presented with three doors — red, green, and blue — from which to 
 * choose, one of which has a prize hidden behind it. Suppose we choose the 
 * red door. The host of the contest, who knows the location of the prize and 
 * will not open that door, opens the blue door and reveals that there is no 
 * prize behind it. He then asks if we wish to change from our initial choice 
 * of red. Will changing to green now improve our chances of winning the prize?
 * 
 * One may think, with two doors left unopened, that one has a 50:50 chance 
 * with either one, so there is no point for or against changing doors. However,
 * this is not true.
 * 
 * Let us call the situations where the prize is behind one of the doors door 
 * Ar, Ag, and Ab, for the red, green, and blue doors, respectively. It is 
 * commonly assumed the car is placed randomly P(Ar)=P(Ag)=P(Ab) = 
 * (1/3 or 33.33%)
 * 
 * When the prize is behind the red door, the host is free to open the green or 
 * the blue door. If the host opens them uniformly at random: 1/2 (50%) 
 * 
 * - Let us call "the host opens the blue door" proposition B. It is assumed the 
 * host choose at random when he has a choice, hence B must have probability 
 * P(B|Ar) = 1/2.
 * - When the prize is behind the green door, the host must open the blue door. 
 * Thus: P(B | Ag) = 1.
 * - When the prize is behind the blue door, the host must open the green door. 
 * Thus, P(B | Ab) = 0.
 * 
 * We should change from the red to the green door for the higher probability 
 * of winning.
 */
public class MontyHallTestCase
{
	/**
	 * p(A | B) = p(B | A) p(A) / p(B)
	 * P(Ar|B) = P(B|Ar)* P(Ar) / p(B)
	 *         = 1/3 = 33.33%
	 * 
	 * Should we stay on red?
	 * 
	 * Probability that prize is behind door RED given the host opens
	 * door BLUE.
	 *  
	 */
	@Test public void testOpenRedDoorAndWinPrize()
	{
		// P(B|Ar)
		double probOpenBlueGivenPrizeBehindRed = 1.0/2.0;		
		// P(Ar)
		double probPrizeBehindRed = 1.0/3.0;
		// p(B)
		double probOpenBlueDoor = 1.0/2.0;
		// p(A | B)
		double probPrizeBehindAGivenBOpened = 
				(probOpenBlueGivenPrizeBehindRed*probPrizeBehindRed)/probOpenBlueDoor;
		assertEquals(1.0/3.0, probPrizeBehindAGivenBOpened, 0);
	}
	
	/**
	 * p(A | B) = p(B | A) p(A) / p(B)
	 * p(Ag|B) = P(B|Ag)*P(Ag) / P(B)
	 *         = 2/3 = 66.66%
	 * 
	 * Should we change to green?
	 * 
	 * Probability that prize is behind door GREEN given the host opens
	 * door BLUE.
	 */
	@Test public void testSwitchToGeenDoorAndWinPrize()
	{
		// P(B|Ag)
		// why? because you have chosen red, only choice left!
		double probOpenBlueGivenPrizeBehindGreen = 1.0;
		// P(Ag)
		// why? placed randomly behind a door
		double probPrizeBehindGreen = 1.0/3.0;
		// P(B)
		// why? you have chosen red, he can only open blue or green doors
		double probOpenBlueDoor = 1.0/2.0;
		// p(Ag|B)
		double probPrizeBehindGreenGivenBlueOpened = 
			(probOpenBlueGivenPrizeBehindGreen*probPrizeBehindGreen)/probOpenBlueDoor;
		assertEquals(2.0/3.0, probPrizeBehindGreenGivenBlueOpened, 0);
	}
	
	
	/**
	 * p(A | B) = p(B | A) p(A) / p(B)
	 * P(Ab | B) = P(B | Ab) P(Ab) / P(B)
	 * 	     = 0 = 0%
	 * 
	 * Should we switch to the blue door?
	 * 
	 * Probability that prize is behind door BLUE given the host
	 * opens door BLUE. Impossible right!
	 * 
	 */
	@Test public void testSwitchToBlueDoorAndWinPrize()
	{
		// P(B | Ab)
		// the host opens blue given the prize is behind blue
		// never!
		double probHostOpenBlueDoorGivenPrizeBlueDoor = 0;
		// P(Ab)
		// could be any door
		double probPrizeBehindBlue = 1.0/3.0;
		// P(B)
		// you chose red, only two options left
		double probHostOpensBlue = 1.0/2.0;
		// P(Ab | B)
		double probPrizeBehindBlueHostOpenBlue = 
			(probHostOpenBlueDoorGivenPrizeBlueDoor*probPrizeBehindBlue)/probHostOpensBlue;
		assertEquals(0, probPrizeBehindBlueHostOpenBlue, 0);
	}
}

