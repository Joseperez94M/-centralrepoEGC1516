package es.us.agoraus.counting.domain;

import java.util.List;

public class Vote {

	private String age;
	private List<Answer> answers;
	private String autonomous_community;
	private String genre;
	private String id;
	private String id_poll;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getAutonomous_community() {
		return autonomous_community;
	}

	public void setAutonomous_community(String autonomous_community) {
		this.autonomous_community = autonomous_community;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_poll() {
		return id_poll;
	}

	public void setId_poll(String id_poll) {
		this.id_poll = id_poll;
	}

	@Override
	public String toString() {
		return "Voto [age=" + age + ", answers=" + answers
				+ ", autonomous_community=" + autonomous_community + ", genre="
				+ genre + ", id=" + id + ", id_poll=" + id_poll + "]";
	}

}