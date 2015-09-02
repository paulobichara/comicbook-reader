package org.paulobichara.comics.business.startup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.paulobichara.comics.domain.entity.ComicBook;
import org.paulobichara.comics.domain.entity.ComicBookCollection;
import org.paulobichara.comics.domain.entity.ComicBookType;

/**
 * Bean that initialize the in-memory database with the sample data present in src/main/resources/sample-comicbooks.csv
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Singleton
@Startup
public class SampleDatabasePopulatorBean {

	/* Path to the samples comic books resource file */
	private static final String COMIC_BOOKS_SAMPLE = "/comic-books-sample.csv";
	/* Path to the samples comic books collections resource file */
	private static final String COMIC_BOOKS_COLLECTIONS_SAMPLE = "/comic-books-collections-sample.csv";	
	/* Token separator used in the CSV resource file's format */
	private static final String CSV_TOKEN_SEPARATOR = ";";		

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method that initializes the database.
	 */
	@PostConstruct
	public void init() throws URISyntaxException, IOException {
		try {
			this.populateComicBooksCollections();
			this.populateComicBooks();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Method that clears the database.
	 */
	@PreDestroy
	public void finalize() throws Throwable {
		this.entityManager.createQuery("delete from ComicBook c").executeUpdate();
	}

	private void populateComicBooksCollections() throws URISyntaxException, IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(COMIC_BOOKS_COLLECTIONS_SAMPLE)));
			ComicBookCollection comicBookCollection = null;
			String line = null;
			String[] fields = null;
			while ((line = bufferedReader.readLine()) != null) {
				fields = line.split(CSV_TOKEN_SEPARATOR);
				comicBookCollection = new ComicBookCollection();
//				comicBookCollection.setId(Long.valueOf(fields[0]));
				comicBookCollection.setTitle(fields[1]);
				comicBookCollection.setDescription(fields[2]);
				comicBookCollection.setRating(Integer.valueOf(fields[3]));
				entityManager.persist(comicBookCollection);
			}			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {}
		}
	}		
	
	private void populateComicBooks() throws URISyntaxException, IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(COMIC_BOOKS_SAMPLE)));
			ComicBook comicBook = null;
			String line = null;
			String[] fields = null;
			while ((line = bufferedReader.readLine()) != null) {
				fields = line.split(CSV_TOKEN_SEPARATOR);
				comicBook = new ComicBook();
				comicBook.setTitle(fields[0] != null && !fields[0].trim().equals("") ? fields[0] : null);
				comicBook.setDescription(fields[1] != null && !fields[1].trim().equals("") ? fields[1] : null);
				comicBook.setSequenceNumber(Integer.valueOf(fields[2]));
				comicBook.setType(ComicBookType.valueOf(fields[3]));
				comicBook.setRating(Integer.valueOf(fields[4]));
				if (fields.length == 6) {
					comicBook.setCollection(fields[5] != null && !fields[5].trim().equals("") ? this.entityManager.find(ComicBookCollection.class, Long.valueOf(fields[5])) : null);
				}
				this.entityManager.persist(comicBook);
			}			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {}
		}
	}

}
