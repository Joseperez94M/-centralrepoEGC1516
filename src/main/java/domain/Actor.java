package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.SafeHtml;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity{
	
	//Atributtes
	private String name;
	private String surname;
	private String email;
	private String location;

	//Constructor
	public Actor(){
		super();
	}
	
	//RelationShips
	UserAccount userAccount;
	
	//Getters and Setters
	@SafeHtml
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@SafeHtml
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@SafeHtml
	@Email
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@SafeHtml
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@OneToOne(cascade=CascadeType.ALL, optional=false)
	@NotNull
	public UserAccount getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}