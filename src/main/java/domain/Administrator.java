package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor{
	
	//Constructor
	public Administrator(){
		super();
	}
	
	
	// relationships
	
	private Collection<Thread> threads;
	
	@NotNull
	@OneToMany(mappedBy="administrator")
	public Collection<Thread> getThreads() {
		return threads;
	}

	public void setThreads(Collection<Thread> threads) {
		this.threads = threads;
	}
}