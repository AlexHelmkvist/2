package heptathlon;

import common.*;
import decathlon.InvalidResultException;

public class Hep800M {


	private double A = 0.11193;
	private double B = 254;
	private double C = 1.88;

	CalcTrackAndField calc = new CalcTrackAndField();

	// Calculate the score based on time. All running events.
	public int calculateResult(double runningTime) throws InvalidResultException {


		if (runningTime < 70) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (runningTime > 250) {
			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}

		int score = calc.calculateTrack(A, B, C, runningTime);

		System.out.println("The result is " + score);

		return score;


	}
}
