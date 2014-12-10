package mx.cinvestav.gdl.iot.webpage.dao;

import java.io.Serializable;

public interface IoTProperty extends Serializable
{
	public Integer getParentId();

	public void setParentId(Integer idparent);

	public void setId(int id);
	
	public void setName(String name);

	public void setValue(String value);

	public void setActive(boolean active);

}
