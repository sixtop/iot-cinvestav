package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.IoTEntityDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTTypeSensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EntityStoreServiceAsync
{

	public <T extends IoTEntityDTO> void storeEntity(T entity,
			Collection<? extends IoTPropertyDTO> props, AsyncCallback<Void> callback);

	public <T extends IoTEntityDTO> void getEntity(T entityDTO, Integer id,
			AsyncCallback<List<T>> callback);

	public <T extends IoTPropertyDTO> void getProperties(T propertyClass, Integer id,
			AsyncCallback<List<T>> callback);

	public <T extends IoTPropertyDTO> void deleteProperty(T propertyClass, Integer id,
			AsyncCallback<Void> callback);

	public <T extends IoTEntityDTO> void deleteEntity(T entityDTO, Integer id,
			AsyncCallback<Void> callback);

	public void getSensorData(Integer idsensor, Date startDate, Date endDate, AsyncCallback<List<MeasureDTO>> callback);
	
	public <T extends IoTTypeSensorDTO> void storeSensorType(T typeSensorDTO, AsyncCallback<Void> callback);
	
	public <T extends IoTTypeSensorDTO> void getSensorType(AsyncCallback<List<T>> callback);
}
