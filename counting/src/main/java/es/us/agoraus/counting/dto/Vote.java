package es.us.agoraus.counting.dto;

import java.util.List;

import org.springframework.util.StringUtils;

import com.google.gson.annotations.SerializedName;

public class Vote {

	private String age;
	private List<Answer> answers;
	@SerializedName("autonomous_community")
	private String autonomousCommunity;
	@SerializedName("genre")
	private String gender;
	private String id;
	@SerializedName("id_poll")
	private String pollId;

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

	public String getAutonomousCommunity() {
		return autonomousCommunity;
	}

	public void setAutonomousCommunity(String autonomousCommunity) {
		this.autonomousCommunity = autonomousCommunity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public boolean isValid() {
		return StringUtils.hasText(age) && !answers.isEmpty() && StringUtils.hasText(autonomousCommunity)
				&& StringUtils.hasText(gender) && StringUtils.hasText(id) && StringUtils.hasText(pollId);
	}

	@Override
	public String toString() {
		return "Voto [age=" + age + ", answers=" + answers + ", autonomous_community=" + autonomousCommunity
				+ ", gender=" + gender + ", id=" + id + ", id_poll=" + pollId + "]";
	}

}