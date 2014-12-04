package mx.cinvestav.gdl.iot.webpage.server;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.cinvestav.gdl.iot.webpage.client.DatabaseException;
import mx.cinvestav.gdl.iot.webpage.client.EntityStoreService;
import mx.cinvestav.gdl.iot.webpage.dao.DAO;
import mx.cinvestav.gdl.iot.webpage.dao.IoTEntity;
import mx.cinvestav.gdl.iot.webpage.dao.IoTProperty;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EntityStoreImpl extends RemoteServiceServlet implements EntityStoreService
{
	private static final long serialVersionUID = -8306702743270115220L;
	Logger logger = Logger.getLogger(EntityStoreImpl.class.getName());

	public void storeEntity(IoTEntity entity, Collection<? extends IoTProperty> props)
			throws DatabaseException
	{
		try
		{
			DAO.insertEntity(entity, props);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in storeEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public <T extends IoTEntity> List<T> getEntity(Class<T> entityClass, Integer id)
			throws DatabaseException
	{
		try
		{
			return DAO.getEntity(entityClass, id);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}
}