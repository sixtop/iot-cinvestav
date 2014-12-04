package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import mx.cinvestav.gdl.iot.webpage.dao.IoTEntity;
import mx.cinvestav.gdl.iot.webpage.dao.IoTProperty;

@RemoteServiceRelativePath("storeSeSrvice")
public interface EntityStoreService extends RemoteService
{
	public void storeEntity(IoTEntity entity, Collection<? extends IoTProperty> props);
}
