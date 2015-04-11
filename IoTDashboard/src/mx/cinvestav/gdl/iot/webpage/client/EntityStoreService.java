package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.IoTEntityDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTTypeSensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("iot.store")
public interface EntityStoreService extends RemoteService
{
	public void storeEntity(IoTEntityDTO entity, Collection<? extends IoTPropertyDTO> props)
			throws DatabaseException;

	public List<IoTEntityDTO> getEntity(IoTEntityDTO entityDTO, Integer id)
			throws DatabaseException;

	public List<IoTPropertyDTO> getProperties(IoTPropertyDTO propertyClass, Integer id)
			throws DatabaseException;
	
	public void deleteProperty(IoTPropertyDTO propertyClass, Integer id)
			throws DatabaseException;
	
	public void deleteEntity(IoTEntityDTO entityDTO, Integer id) throws DatabaseException;
	
	public List<MeasureDTO> getSensorData(Integer idsensor, Date startDate, Date endDate) throws DatabaseException;
	
	public void storeTypeSensor(IoTTypeSensorDTO typeSensorDTO)
			throws DatabaseException;
	
	public List<IoTTypeSensorDTO> getTypeSensor()
			throws DatabaseException;
}
