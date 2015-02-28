package Application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Authority {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	@OneToOne
	@JoinColumn(name = "user_id")
	private NcUser user;
}
