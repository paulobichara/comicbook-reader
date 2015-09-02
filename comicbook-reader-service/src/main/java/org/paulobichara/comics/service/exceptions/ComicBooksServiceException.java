package org.paulobichara.comics.service.exceptions;

public class ComicBooksServiceException extends Exception {

	private static final long serialVersionUID = 5608389380392088404L;
	
	public ComicBooksServiceException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return new StringBuilder().append("Some error occurred - ").append(this.getCause().getMessage()).toString();
	}
	
}
