package mx.cinvestav.gdl.iot.webpage.client;

import java.io.Serializable;

public class DatabaseException extends Exception implements Serializable
{
	private static final long serialVersionUID = 8243393451400905123L;
	
	public DatabaseException()
	{
		super();
	}
	
	public DatabaseException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
