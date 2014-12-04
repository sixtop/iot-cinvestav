package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dao.IoTEntity;
import mx.cinvestav.gdl.iot.webpage.dao.IoTProperty;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("storeService")
public interface EntityStoreService extends RemoteService
{
	public void storeEntity(IoTEntity entity, Collection<? extends IoTProperty> props) throws DatabaseException;

	public <T extends IoTEntity> List<T> getEntity(Class<T> entityClass, Integer id) throws DatabaseException;
}
