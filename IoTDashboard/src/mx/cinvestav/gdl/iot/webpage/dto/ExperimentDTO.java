package mx.cinvestav.gdl.iot.webpage.dto;

import java.util.Date;
import java.util.Map;

public class ExperimentDTO implements IoTEntityDTO
{
	private static final long serialVersionUID = -1165471382042735357L;

	private Integer idSmartThing;
	private String name;
	private String description;
	private Date dateStart;
	private Date dateEnd;
	private String notes;
	
	public ExperimentDTO()
	{
		super();
	}
	
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdSmartThing() {
		return idSmartThing;
	}

	public void setIdSmartThing(Integer idSmartThing) {
		this.idSmartThing = idSmartThing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}





}