package mx.cinvestav.gdl.iot.webpage.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.IoTEntity;
import mx.cinvestav.gdl.iot.dao.IoTProperty;
import mx.cinvestav.gdl.iot.dao.Measure;
import mx.cinvestav.gdl.iot.dao.SensorType;
import mx.cinvestav.gdl.iot.webpage.client.DatabaseException;
import mx.cinvestav.gdl.iot.webpage.client.EntityStoreService;
import mx.cinvestav.gdl.iot.webpage.dto.IoTEntityDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTPropertyDTO;
import mx.cinvestav.gdl.iot.webpage.dto.IoTTypeSensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EntityStoreImpl extends RemoteServiceServlet implements EntityStoreService
{
	private static final long serialVersionUID = -8306702743270115220L;
	Logger logger = Logger.getLogger(EntityStoreImpl.class.getName());
	private static Mapper mapper = new DozerBeanMapper(
			Arrays.asList(new String[] { "dozer-bean-mappings.xml" }));

	@Override
	public void storeEntity(IoTEntityDTO entityDTO, Collection<? extends IoTPropertyDTO> propDTOList)
			throws DatabaseException
	{
		try
		{
			IoTEntity entity = mapper.map(entityDTO, IoTEntity.class);
			Collection<IoTProperty> propList = new ArrayList<>();
			for (IoTPropertyDTO prop : propDTOList)
			{
				propList.add(mapper.map(prop, IoTProperty.class));
			}
			DAO.insertEntity(entity, propList);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in storeEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public List<IoTEntityDTO> getEntity(IoTEntityDTO entityDTO, Integer id)
			throws DatabaseException
	{
		try
		{
			//map to non DTO object
			IoTEntity entity = mapper.map(entityDTO, IoTEntity.class);

			// perform query
			List<? extends IoTEntity> entityList = DAO.getEntity(entity.getClass(), id);

			//map back to DTO
			List<IoTEntityDTO> propDTOList = new ArrayList<>();
			for (IoTEntity result : entityList)
			{
				IoTEntityDTO dto = mapper.map(result, IoTEntityDTO.class);
				propDTOList.add(dto);
			}
			return propDTOList;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public List<IoTPropertyDTO> getProperties(IoTPropertyDTO entityDTO, Integer id)
			throws DatabaseException
	{
		//map to non DTO object
		IoTProperty entity = mapper.map(entityDTO, IoTProperty.class);
		try
		{
			List<? extends IoTProperty> properties = DAO.getProperties(entity.getClass(), id);

			//map back to DTO
			List<IoTPropertyDTO> propDTOList = new ArrayList<>();
			for (IoTProperty result : properties)
			{
				IoTPropertyDTO dto = mapper.map(result, IoTPropertyDTO.class);
				propDTOList.add(dto);
			}
			return propDTOList;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public void deleteProperty(IoTPropertyDTO propertyDTO, Integer id) throws DatabaseException
	{
		//map to non DTO object
		IoTProperty entity = mapper.map(propertyDTO, IoTProperty.class);
		try
		{
			DAO.deleteProperty(entity.getClass(), id);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in deleteEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public void deleteEntity(IoTEntityDTO entityDTO, Integer id) throws DatabaseException
	{
		//map to non DTO object
		IoTEntity entity = mapper.map(entityDTO, IoTEntity.class);
		try
		{
			DAO.deleteEntity(entity.getClass(), id);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in deleteEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}
	
	public List<MeasureDTO> getSensorData(Integer idsensor, Integer idexperiment) throws DatabaseException
	{
		try
		{
			List<Measure> sensorData = DAO.getSensorData(idsensor, idexperiment);
			
			//map back to DTO
			List<MeasureDTO> measureDTOList = new ArrayList<>();
			for (Measure data : sensorData)
			{
				MeasureDTO dto = mapper.map(data, MeasureDTO.class);
				measureDTOList.add(dto);
			}
			return measureDTOList;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getData: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public void storeSensorType(IoTTypeSensorDTO typeSensorDTO) throws DatabaseException 
	{
		try
		{
			SensorType entity = mapper.map(typeSensorDTO, SensorType.class);
			DAO.insertSensorType(entity);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in storeTypeSensor: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public List<IoTTypeSensorDTO> getSensorType() throws DatabaseException 
	{
		try
		{
			// perform query
			List<SensorType> entityList = DAO.getSensorTypeList();
			
			//map back to DTO
			List<IoTTypeSensorDTO> propDTOList = new ArrayList<>();
			for (SensorType result : entityList)
			{
				IoTTypeSensorDTO dto = mapper.map(result, IoTTypeSensorDTO.class);
				propDTOList.add(dto);
			}
			return propDTOList;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in getEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}
}
