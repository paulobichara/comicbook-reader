package org.paulobichara.comics.domain.to;

import java.util.List;

import org.paulobichara.comics.domain.entity.ComicBook;

/**
 * Class that represents a transfer object for a comic book search result. It contains the fetched data and also some metadata.
 * @author Paulo Augusto Dacach Bichara
 *
 */
public class ComicBookSearchResult {
	
	private Integer recordsTotal;

	private Integer recordsFiltered;

	private List<ComicBook> data;
	
	/**
	 * Get the number of all records
	 * @return number of all records
	 */
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * Set the number of all records
	 * @param recordsTotal number of all records
	 */
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * Get the number of all filtered records, ignoring pagination
	 * @return number of all filtered records
	 */
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * Set the number of all filtered records, ignoring pagination
	 * @param recordsFiltered number of all filtered records
	 */
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * Get the list of comic books
	 * @return list of comic books
	 */
	public List<ComicBook> getData() {
		return data;
	}

	/**
	 * Set the list of comic books
	 * @param data list of comic books
	 */
	public void setData(List<ComicBook> data) {
		this.data = data;
	}
	
}
