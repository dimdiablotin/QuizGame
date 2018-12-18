package com.claustredimitri.quizgame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleQuiz {

	private String replay;
	private long timeElapsed;
	private int score = 0;
	private int nbrOfQuestions = 0;
	private boolean done = false;

	
	Scanner sc = new Scanner(System.in);
	ArrayList<Integer> alreadyIndex;
	Random rand = new Random();
	
	
	public ConsoleQuiz(int nbrOfQuestions) {
		this.nbrOfQuestions = nbrOfQuestions;
	}
	

	public void start() {

		try {
			long startTime = System.currentTimeMillis();

			for (Questions questions : generate(nbrOfQuestions)) {
				System.out.println(questions.getText());
				String answer = sc.nextLine();

				if (answer.equalsIgnoreCase(questions.getResponse())) {
					System.out.println("Bonne r�ponse !");
					score++;
				} else {
					System.out.println("Mauvaise r�ponse ! Il fallait r�pondre " + questions.getResponse());
				}
			}
			done = true;
			long endTime = System.currentTimeMillis();
			timeElapsed = endTime - startTime;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			done = false;
		}
	}

	private int getTimeElapsedInSeconds(long timeInMilliseconds) {
		return (int) (timeInMilliseconds / 1000);
	}

	public void displayResults() {

		if (done == true) {
			displayScore();
			displayTimeElapsed();
		}

	}

	private void displayScore() {
		System.out.println("Votre score est : " + score + "/" + nbrOfQuestions);
	}

	private void displayTimeElapsed() {
		System.out.println("Votre temps r�ponse est de : " + getTimeElapsedInSeconds(timeElapsed) + " secondes pour "
				+ nbrOfQuestions + " questions.");
	}

	public ArrayList<Questions> generate(int nbrQuestions) {

		String[][] data = getData();

		// on l�ve une exception si nbrQuestions > la taille du tableau de questions.
		if (nbrQuestions > data.length) {
			throw new IllegalArgumentException(
					"Attention ! On ne peut g�n�rer que " + data.length + " questions maximum.");
		}

		ArrayList<Questions> question = new ArrayList<>();
		int index;
		score = 0;
		ArrayList<Integer> alreadyIndex = new ArrayList<>();

		alreadyIndex.clear(); // permet de supprimer les �l�ments de l'arrayList quand on relance la partie

		for (int i = 0; i < nbrQuestions; i++) {

			do { // un pays al�atoire sans se r�p�ter
				index = rand.nextInt(data.length);
			} while (alreadyIndex.contains(index));

			alreadyIndex.add(index);
			String country = data[index][0];
			String capital = data[index][1];

			String questionText = String.format("Quelle est la capitale de ce pays : %s ?", country);

			question.add(new Questions(questionText, capital));
		}
		return question;
	}

	public String[][] getData() {
		String[][] tab = { { "Allemagne", "Berlin" }, { "Lib�ria", "Monrovia" }, { "P�rou", "Lima" },
				{ "Monaco", "Monaco" }, { "France", "Paris" }, { "Nig�ria", "Abuja" }, { "S�n�gal", "Dakar" },
				{ "Gabon", "Libreville" }, { "Italie", "Rome" }, };
		return tab;
	}

}
