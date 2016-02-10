package domain;

import javax.persistence.Entity;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public class Ban extends DomainEntity{

	private boolean banned;

	
	@NotNull
	public boolean getBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	
	//RelationShips -----------------------------------------------
	
	
	
	
	
}
