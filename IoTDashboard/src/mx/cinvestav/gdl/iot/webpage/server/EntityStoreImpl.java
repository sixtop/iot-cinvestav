package mx.cinvestav.gdl.iot.webpage.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.cinvestav.gdl.iot.webpage.client.DatabaseException;
import mx.cinvestav.gdl.iot.webpage.client.EntityStoreService;
import mx.cinvestav.gdl.iot.webpage.dao.Controller;
import mx.cinvestav.gdl.iot.webpage.dao.DAO;
import mx.cinvestav.gdl.iot.webpage.dao.IoTEntity;
import mx.cinvestav.gdl.iot.webpage.dto.IoTEntityDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EntityStoreImpl extends RemoteServiceServlet implements EntityStoreService
{
	private static final long serialVersionUID = -8306702743270115220L;
	Logger logger = Logger.getLogger(EntityStoreImpl.class.getName());
	Mapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"dozer-bean-mappings.xml"}));
	
	public EntityStoreImpl()
	{
		super();
		System.setProperty("dozer.debug", "true");
	}

	public void storeEntity(IoTEntityDTO entityDTO, Collection<? extends IoTPropertyDTO> propsDTO)
			throws DatabaseException
	{
		try
		{
			IoTEntity entity = mapper.map(entityDTO, Controller.class);
			//Collection prop = mapper.map(propsDTO, Collection.class);
			DAO.insertEntity(entity, null);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in storeEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends IoTEntityDTO> List<T> getEntity(T entityClassDTO, Integer id)
			throws DatabaseException
	{
		try
		{
			IoTEntity entity = mapper.map(entityClassDTO, IoTEntity.class);
			List<? extends IoTEntity> entity2 = DAO.getEntity(entity.getClass() , id);
			
			Collection map = mapper.map(entity2, Collection.class);
			return (List<T>) map;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}
}