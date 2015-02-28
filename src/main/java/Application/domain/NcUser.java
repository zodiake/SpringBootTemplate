package Application.domain;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ncuser")
public class NcUser {
	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String password;

	@OneToMany(mappedBy = "createdBy")
	private Set<Post> posts;

	@Column(name = "createdTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdTime;

	@OneToOne(mappedBy = "user")
	private Authority authorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}
}
