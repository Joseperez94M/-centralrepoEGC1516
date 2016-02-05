package es.us.agoraus.counting.dto;

public class PartialSegmentResult implements YesNoSettable {

	private int yes;
	private int no;

	public int getYes() {
		return yes;
	}

	public int getNo() {
		return no;
	}

	public void setYes(int yes) {
		this.yes = yes;
	}

	public void setNo(int no) {
		this.no = no;
	}

}
