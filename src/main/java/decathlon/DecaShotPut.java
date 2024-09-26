package decathlon;

import common.CalcTrackAndField;
import common.InputResult;

public class DecaShotPut {

	private double A = 51.39;
	private double B = 1.5;
	private double C = 1.05;

	CalcTrackAndField calc = new CalcTrackAndField();

	// Calculate the score based on time. All running events.
	public int calculateResult(double distance) throws InvalidResultException {


		if (distance < 0) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 30) {
			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}

		int score = calc.calculateField(A, B, C, distance);

		System.out.println("The result is " + score);

		return score;


	}
}
