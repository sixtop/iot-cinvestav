package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dao.IoTEntity;
import mx.cinvestav.gdl.iot.webpage.dao.IoTProperty;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EntityStoreServiceAsync 
{

	public <T extends IoTEntity> void storeEntity(T entity,
			Collection<? extends IoTProperty> props, AsyncCallback<Void> callback);

	public <T extends IoTEntity> void getEntity(T entity, Integer id,
			AsyncCallback<List<T>> callback);

}
