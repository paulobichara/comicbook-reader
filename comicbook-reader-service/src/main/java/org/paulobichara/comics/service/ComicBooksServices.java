package org.paulobichara.comics.service;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.paulobichara.comics.business.ComicBookRepositoryBean;
import org.paulobichara.comics.domain.entity.ComicBook;
import org.paulobichara.comics.domain.to.ComicBookSearchResult;
import org.paulobichara.comics.service.exceptions.ComicBooksServiceException;
/**
 * 
 * Class representing the REST comic book services end point
 * 
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Path("/comicBooks")
public class ComicBooksServices {

	@EJB
	private ComicBookRepositoryBean comicBookRepository;

	/**
	 * @api {get} /comicBooks/ Get all Comic Books
	 * @apiName GetAllComicBooks
	 * @apiGroup ComicBook
	 *
	 * @apiSuccess {Number} recordsTotal Number of all Comic Books
	 * @apiSuccess {Number} recordsFiltered Number of filtered Comic Books
	 * @apiSuccess {Object[]} data List of comic book objects
	 * 
	 * @apiError ComicBooksServiceException Some error occurred - <code>[error-message]</code>
	 * 
	 */	
	/**
	 * Method implementing a REST service responsible for listing all comic books
	 *  
	 * @param  uriInfo Request metadata used to extract optional query parameters
	 * @return REST Response object containing fetched data
	 * @see <a href="https://datatables.net/manual/server-side">DataTables server-side protocol reference.</a>
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getAllComicBooks(@Context UriInfo uriInfo) {
		ComicBookSearchResult response = new ComicBookSearchResult();		
		try {
			int numberOfRecords = comicBookRepository.countAllComicBooks().intValue();
			response.setData(comicBookRepository.getAllComicBooks());
			response.setRecordsFiltered(numberOfRecords);
			response.setRecordsTotal(numberOfRecords);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ComicBooksServiceException(e).getMessage()).build();
		}
	}	

	/**
	 * @api {get} /comicBooks/:comicBookId Get a specific comic book
	 * @apiName GetComicBook
	 * @apiGroup ComicBook
	 *
	 * @apiParam {Number} comicbookId Comic Books unique ID
	 *
	 * @apiSuccess {Number} id comic book unique id
	 * @apiSuccess {String} title comic book title
	 * @apiSuccess {String} description comic book description 
	 * @apiSuccess {Number} sequenceNumber comic book sequenceNumber
	 * @apiSuccess {Number} type comic book type
	 * @apiSuccess {Number} rating comic book rating
	 * 
	 * @apiError ComicBooksServiceException Some error occurred - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for find a specific comic book by its id.
	 *  
	 * @param  comic bookId  comic book unique identifier
	 * @return REST Response object containing requested comic book
	 */
	@GET
	@Path("{comicBookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getcomicBook(@PathParam("comicBookId") Long comicBookId) {
		try {
			ComicBook comicBook = comicBookRepository.findComicBookById(comicBookId);
			return Response.status(200).entity(comicBook).build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ComicBooksServiceException(e).getMessage()).build();
		}
	}

}
