package mx.cinvestav.gdl.iot.webpage.dto;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	private static final long serialVersionUID = 5699132015960050416L;

	private Integer id;
	private String username;
	private String hash;
	private String email;
	private String name;
	private String sessionID;
	private Boolean loggedIn;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getHash()
	{
		return hash;
	}

	public void setHash(String hash)
	{
		this.hash = hash;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSessionID()
	{
		return sessionID;
	}

	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}

	public Boolean getLoggedIn()
	{
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}
}
