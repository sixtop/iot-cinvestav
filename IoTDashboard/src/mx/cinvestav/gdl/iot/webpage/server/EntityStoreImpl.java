package mx.cinvestav.gdl.iot.webpage.server;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.cinvestav.gdl.iot.exception.DatabaseException;
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
	{
		try
		{
			DAO.insertEntity(entity, props);
		}
		catch (DatabaseException e)
		{
			String message = "Cannot store in DB: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
		}
	}
}