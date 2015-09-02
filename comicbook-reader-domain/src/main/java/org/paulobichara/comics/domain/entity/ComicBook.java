package org.paulobichara.comics.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Class that represents a comic book
 * @author Paulo Augusto Dacach Bichara
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "ComicBook.all", query = "select c from ComicBook c"),
	@NamedQuery(name = "ComicBook.countAll", query = "select count(c) from ComicBook c"),
	@NamedQuery(name = "ComicBook.countByFilter", query = "select count(c) from ComicBook c where c.title LIKE :prFilter")	
})
public class ComicBook implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = true)
	private String title;
	
	@Column(nullable = true)
	private String description;	

	@Column(nullable = false)
	private Integer sequenceNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ComicBookType type;
	
	@Column(nullable = true)
	private Integer rating;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
	private ComicBookCollection collection;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public ComicBookType getType() {
		return type;
	}

	public void setType(ComicBookType type) {
		this.type = type;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ComicBookCollection getCollection() {
		return collection;
	}

	public void setCollection(ComicBookCollection collection) {
		this.collection = collection;
	}	

}
