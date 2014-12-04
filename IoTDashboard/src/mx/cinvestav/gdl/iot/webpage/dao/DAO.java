package mx.cinvestav.gdl.iot.webpage.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import mx.cinvestav.gdl.iot.webpage.client.DatabaseException;

import com.google.appengine.api.utils.SystemProperty;

public class DAO
{

	private static final String DEV_URL_ENDPOINT = "cloudsql.url.dev";
	private static final String PROD_URL_ENDPOINT = "cloudsql.url";
	private static final String GOOGLE_DRIVER = "com.mysql.jdbc.GoogleDriver";
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "javax.persistence.jdbc.url";
	private static final String JDBC_DRIVER = "javax.persistence.jdbc.driver";
	private static final String PERSISTENCE_UNIT_NAME = "SmartCitiesCloudSQL";

	private static EntityManagerFactory emf = null;

	/**
	 * Returns an entity manager instance
	 * @return
	 * @throws DatabaseException 
	 */
	public static EntityManager getEntityManager() throws DatabaseException
	{
		if (emf == null)
		{
			try
			{
				Map<String, String> db_props = new HashMap<String, String>();
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
				{
					db_props.put(JDBC_DRIVER, GOOGLE_DRIVER);
					db_props.put(JDBC_URL, System.getProperty(PROD_URL_ENDPOINT));
				}
				else
				{
					db_props.put(JDBC_DRIVER, MYSQL_DRIVER);
					db_props.put(JDBC_URL, System.getProperty(DEV_URL_ENDPOINT));
				}
				emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, db_props);
			}
			catch (Exception e)
			{
				Logger logger = Logger.getLogger(DAO.class.getName());
				logger.log(Level.SEVERE, "Unexpected exception initializing DAO", e);
				throw new DatabaseException("Exception creating entity manager", e);
			}
		}
		return emf.createEntityManager();
	}

	public static <T extends IoTEntity> List<T> getEntity(Class<T> entityClass, Integer id)
			throws DatabaseException
	{
		EntityManager em = null;
		List<T> resultList = null;
		try
		{
			em = getEntityManager();
			if (id == null)
			{
				CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
				cq.select(cq.from(entityClass));
				resultList = em.createQuery(cq).getResultList();
			}
			else
			{
				resultList = new ArrayList<>(1);
				T e = em.find(entityClass, id);
				resultList.add(e);
			}
			return resultList;
		}
		catch (Exception e)
		{
			throw new DatabaseException("Database exception while inserting entity:"
					+ e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	/**
	 * Insert a new controller with a collection of properties
	 * @param controller
	 * @param properties
	 * @throws DatabaseException
	 */
	public static <T extends IoTEntity> void insertEntity(T entity,
			Collection<? extends IoTProperty> properties) throws DatabaseException
	{
		if (entity == null)
		{
			throw new IllegalArgumentException("Entity cannot be null.");
		}
		EntityManager em = null;
		EntityTransaction tx = null;
		try
		{
			em = getEntityManager();
			tx = em.getTransaction();
			em.merge(entity);
			if (properties != null)
			{
				for (IoTProperty p : properties)
				{
					p.setParentId(entity.getId());
					em.merge(p);
				}
			}
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null)
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while inserting entity:"
					+ e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}
}
