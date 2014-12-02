package mx.cinvestav.gdl.iot.exception;

public class DatabaseException extends Exception
{
	private static final long serialVersionUID = 8243393451400905123L;
	
	public DatabaseException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
