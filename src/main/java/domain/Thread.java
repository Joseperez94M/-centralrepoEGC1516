package domain;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Thread extends DomainEntity{
	
	//Atributtes
	private String title;
	private Date creationMoment;
	private String text;
	private int censusId;
	
	//Constructor
	public Thread(){
		super();
	}
	

	//RelationShips
	private Administrator administrator;
	private Collection<Comment> comments;
	
	//Getters and Setters
	@NotBlank
	@SafeHtml
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	//siempre recibe un numero para saber a que censo corresponde, 
	//en caso de ser censo abierto recibe un 0
	@NotNull
	public int getCensusId() {
		return censusId;
	}
	public void setCensusId(int censusId) {
		this.censusId = censusId;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getCreationMoment() {
		return creationMoment;
	}
	
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}
	
	@NotBlank
	@SafeHtml
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@NotNull
	@ManyToOne(optional=false)
	public Administrator getAdministrator() {
		return administrator;
	}
	
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	@NotNull
	@OneToMany(mappedBy="thread")
	public Collection<Comment> getComments() {
		return comments;
	}
	
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
}
