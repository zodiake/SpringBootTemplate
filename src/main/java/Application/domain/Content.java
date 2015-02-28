package Application.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Content {
	@Id
	@GeneratedValue
	private int id;
	
	private String content;

	@OneToOne(mappedBy = "content")
	private Post post;

}
