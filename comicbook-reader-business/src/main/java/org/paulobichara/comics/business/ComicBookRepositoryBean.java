package org.paulobichara.comics.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.paulobichara.comics.domain.entity.ComicBook;

@Stateless
public class ComicBookRepositoryBean {
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method that searches for a comic book by its unique id
	 * @param id requested comic book unique id
	 * @return requested comic book object
	 */
	public ComicBook findComicBookById(Long id) {
		return this.entityManager.find(ComicBook.class, id);
	}
	
	/**
	 * Method that returns all the comic books in the database
	 * @return list of all comic books
	 */
	@SuppressWarnings("unchecked")
	public List<ComicBook> getAllComicBooks() {
		return this.entityManager.createNamedQuery("ComicBook.all").getResultList();
	}
	
	/**
	 * Method that searches for comic book based on provided parameters.
	 * 
	 * @param start search offset
	 * @param length maximum number of records to be retrieved
	 * @param filter filter passed from the interface
	 * @param orderByColumn column name indicating the reference attribute for ordering
	 * @param orderByDirection string that defines sorting direction (asc/desc)
	 * @return list of retrieved comic books
	 */
	@SuppressWarnings("unchecked")
	public List<ComicBook> searchComicBooks(Integer start, Integer length, String filter, String orderByColumn, String orderByDirection) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ComicBook> criteriaQuery = criteriaBuilder.createQuery(ComicBook.class);
		Root<ComicBook> comicBookEntity = criteriaQuery.from(ComicBook.class);
		
		if (filter != null && !filter.trim().equals("")) {
			Expression<String> comicBookTitle = comicBookEntity.get("name");
			Expression<String> filterParameter = criteriaBuilder.parameter(String.class, "prFilter");
			criteriaQuery = criteriaQuery.where(criteriaBuilder.like(comicBookTitle, filterParameter));
		}

		criteriaQuery = this.setupCriteriaQueryOrder(orderByColumn, orderByDirection, criteriaQuery, comicBookEntity);
		Query query = this.entityManager.createQuery(criteriaQuery);
		this.setupQueryParameter(filter, query);
		this.setupQueryLimits(query, start, length);
		return query.getResultList();
	}
	
	/**
	 * Method that counts all comic books in the database.
	 * @return number of comic books in the database
	 */
	public Long countAllComicBooks() {
		return this.countComicBooks(null);
	}	
	
	/**
	 * Method that counts comic books based in a string filter
	 * @param filter filter passed from the interface
	 * @return number of filtered comic books
	 */
	public Long countComicBooks(String filter) {
		Query query = null; 
		if (filter != null && !filter.trim().equals("")) {
			query = this.entityManager.createNamedQuery("ComicBook.countByFilter", Long.class);
			query.setParameter("prFilter", new StringBuilder().append("%").append(filter).append("%").toString());
		} else {
			query = this.entityManager.createNamedQuery("ComicBook.countAll");			
		}
		return (Long)query.getSingleResult();
	}
	
	/**
	 * Method that sets the CriteriaQuery ordering 
	 * @param orderByColumn column name indicating the reference attribute for ordering
	 * @param orderByDirection string that defines sorting direction (asc/desc)
	 * @param criteriaQuery criteria query created to fetch the requested data
	 * @param comicBookEntity root query entity (ComicBook in this case)
	 * @return CriteriaQuery with ordering
	 */
	private CriteriaQuery<ComicBook> setupCriteriaQueryOrder(String orderByColumn, String orderByDirection, CriteriaQuery<ComicBook> criteriaQuery, Root<ComicBook> comicBookEntity) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		if (orderByColumn != null) {
			if (orderByDirection.toLowerCase().trim().equals("desc")) {
				return criteriaQuery.orderBy(criteriaBuilder.desc(comicBookEntity.get(orderByColumn)));
			} else {
				return criteriaQuery.orderBy(criteriaBuilder.asc(comicBookEntity.get(orderByColumn)));
			}
		}
		return criteriaQuery;
	}
	
	/**
	 * Method that sets Query limits (offset and length)
	 * @param query query object
	 * @param start search offset
	 * @param length maximum number of records to be retrieved
	 */
	private void setupQueryLimits(Query query, Integer start, Integer length) {
		if (length != null && length > 0) {
			query.setMaxResults(length);
		}
		if (start != null && start >= 0) {
			query.setFirstResult(start);
		}			
	}
	
	/**
	 * Method that sets the query requested parameters if any
	 * @param filter filter passed from the interface
	 * @param query
	 */
	private void setupQueryParameter(String filter, Query query) {
		if (filter != null && !filter.trim().equals("")) {
			query.setParameter("prFilter", new StringBuilder().append("%").append(filter).append("%").toString());
		}
	}
}
