package es.us.agoraus.counting.dto;

public class ReferendumResult implements AlgorithmResult, YesNoSettable {
	String question;
	int yes;
	int no;

	public ReferendumResult(String question) {
		this.question = question;
	}
	
	public ReferendumResult(String question, int yes, int no) {
		this.question = question;
		this.yes = yes;
		this.no = no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getYes() {
		return yes;
	}

	public void setYes(int yes) {
		this.yes = yes;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
