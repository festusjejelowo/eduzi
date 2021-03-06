package com.ocularminds.eduzi.vao;

import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="edz_photos",uniqueConstraints={@UniqueConstraint(columnNames={"photo_id"})})
public class Photo implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="photo_id", nullable=false, unique=true, length=11)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mid", insertable = true, updatable = true,nullable=false)
    Message message;

	@Column(name="link", length=35, nullable=true)
	private String link;

	public Photo(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
