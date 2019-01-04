package de.bartmail.moviebutler.main;

public class MovieButlerException extends Exception
{
	private static final long serialVersionUID = 1L;

	private Exception mainException = null;

	public MovieButlerException(String msg)
	{
		super(msg);
	}

	public MovieButlerException(String msg, Exception e)
	{
		super(msg);
		mainException = e;
	}

	public Exception getMainException()
	{
		return mainException;
	}
}
