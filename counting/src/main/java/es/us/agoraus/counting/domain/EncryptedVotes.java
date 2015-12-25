package es.us.agoraus.counting.domain;

import java.util.List;

public class EncryptedVotes {

	private Integer msg;
	private List<String> votes;

	public Integer getMsg() {
		return this.msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public List<String> getVotes() {
		return votes;
	}

	public void setVotes(List<String> votes) {
		this.votes = votes;
	}

}
