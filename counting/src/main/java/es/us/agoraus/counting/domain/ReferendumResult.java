package es.us.agoraus.counting.domain;

public class ReferendumResult implements Result {
	String question;
	Integer yes;
	Integer no;
	public ReferendumResult(String question, Integer yes, Integer no) {
		super();
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
	public Integer getYes() {
		return yes;
	}
	public void setYes(Integer yes) {
		this.yes = yes;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	
	
}
