package mx.cinvestav.gdl.iot.webpage.dto;

import java.sql.Timestamp;

public class ExperimentDTO implements IoTEntityDTO
{
	private static final long serialVersionUID = -1165471382042735357L;

	private Integer id;
	private String name;
	private String description;
	private Timestamp start_date;
	private Timestamp end_date;
	private Integer idthing;
	private String notes;
	
	public ExperimentDTO()
	{
		super();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Timestamp getStart_date()
	{
		return start_date;
	}

	public void setStart_date(Timestamp start_date)
	{
		this.start_date = start_date;
	}

	public Timestamp getEnd_date()
	{
		return end_date;
	}

	public void setEnd_date(Timestamp end_date)
	{
		this.end_date = end_date;
	}

	public Integer getIdthing()
	{
		return idthing;
	}

	public void setIdthing(Integer idthing)
	{
		this.idthing = idthing;
	}

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}
}