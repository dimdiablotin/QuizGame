package com.claustredimitri.quizgame;

public class DriverTest {

	public static void main(String[] args) {
		
		ConsoleQuiz quiz = new ConsoleQuiz(2);
		
		quiz.start();

		quiz.displayResults();
	}

}
