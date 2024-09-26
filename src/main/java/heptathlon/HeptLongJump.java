package heptathlon;

import common.CalcTrackAndField;
import common.InputResult;
import decathlon.InvalidResultException;

public class HeptLongJump {


	private double A = 0.188807;
	private double B = 210;
	private double C = 1.41;

	CalcTrackAndField calc = new CalcTrackAndField();

	// Calculate the score based on time. All running events.
	public int calculateResult(double distance) throws InvalidResultException {


		if (distance < 0) {
			System.out.println("Value too low");
			throw new InvalidResultException("Value too low");
		} else if (distance > 1000) {
			System.out.println("Value too high");
			throw new InvalidResultException("Value too high");
		}

		int score = calc.calculateField(A, B, C, distance);

		System.out.println("The result is " + score);

		return score;


	}
}
