package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.IoTEntityDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("iot.store")
public interface EntityStoreService extends RemoteService 
{
	public void storeEntity(IoTEntityDTO entity, Collection<? extends IoTPropertyDTO> props) throws DatabaseException;

	public <T extends IoTEntityDTO> List<T> getEntity(T entityClass, Integer id) throws DatabaseException;
}
