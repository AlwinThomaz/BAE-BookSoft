package com.project.booksoft.persistence.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Bookmark {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToMany(mappedBy = "chosenBookmarks")
	private Set<Project> chosenProjects;
	
	
	@Column(name = "Type", unique=true, nullable = false)
	private String type;	
	@Column(name = "Name",length=50, nullable=false, unique=false)
	private String name;
	@Column(name = "Description", length=200, nullable=false, unique=false)
	private String description;
	@Column(name = "URL", nullable=false, unique=true)
	private String url;
	

	public Bookmark(Set<Project> chosenProjects, String type, String name, String description, String url) {
		super();
		this.chosenProjects = chosenProjects;
		this.type = type;
		this.name = name;
		this.description = description;
		this.url = url;
	}

	public Bookmark() {
	
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Project> getChosenProjects() {
		return chosenProjects;
	}

	public void setChosenProjects(Set<Project> chosenProjects) {
		this.chosenProjects = chosenProjects;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chosenProjects == null) ? 0 : chosenProjects.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bookmark other = (Bookmark) obj;
		if (chosenProjects == null) {
			if (other.chosenProjects != null)
				return false;
		} else if (!chosenProjects.equals(other.chosenProjects))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
	
	

	