package es.us.agoraus.counting.domain;

public class Answer {

	private String question;
	private String answer_question;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer_question() {
		return answer_question;
	}

	public void setAnswer_question(String answer_question) {
		this.answer_question = answer_question;
	}

	@Override
	public String toString() {
		return "Answer [question=" + question + ", answer_question="
				+ answer_question + "]";
	}

}

