package mx.cinvestav.gdl.iot.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "experiment")
public class Experiment implements IoTEntity
{
	private static final long serialVersionUID = -6484142395365405436L;

	@Id
	@GeneratedValue
	@Column(name = "idexperiment")
	private Integer id;

	private String name;
	private String description;
	private Timestamp start_date;
	private Timestamp end_date;
	private Integer idthing;
	private String notes;

	public Experiment()
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
