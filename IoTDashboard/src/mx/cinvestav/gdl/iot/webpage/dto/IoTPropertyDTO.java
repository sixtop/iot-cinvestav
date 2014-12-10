package mx.cinvestav.gdl.iot.webpage.dto;

import java.io.Serializable;

public interface IoTPropertyDTO extends Serializable
{
	public Integer getParentId();
	public void setParentId(Integer idparent);
	public void setId(int id);
	public void setName(String name);
	public void setValue(String value);
	public void setActive(boolean active);
	
}
