package decathlon;

import common.*;

public class DecaLongJump {

	private double A = 0.14354;
	private double B = 220;
	private double C = 1.4;

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
