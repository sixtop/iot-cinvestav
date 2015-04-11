package mx.cinvestav.gdl.iot.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

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
	public static synchronized EntityManager getEntityManager() throws DatabaseException
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

	public static <T extends IoTEntity> List<T> getEntity(Class<T> entityClass, Integer id) throws DatabaseException
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
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
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
	public static <T extends IoTEntity> void insertEntity(T entity, Collection<? extends IoTProperty> properties)
			throws DatabaseException
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
			tx.begin();
			if (entity.getId() == null)
				em.persist(entity);
			else
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
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static <T extends IoTProperty> List<T> getProperties(Class<T> propertyClass, Integer parentId)
			throws DatabaseException
	{
		EntityManager em = null;
		List<T> resultList = null;
		if (parentId == null)
		{
			throw new IllegalArgumentException("getProperties: must provide IoTEntity id.");
		}
		try
		{
			em = getEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(propertyClass);
			Root<T> from = cq.from(propertyClass);
			ParameterExpression<Integer> parent = cb.parameter(Integer.class);
			cq.select(from).where(cb.equal(from.get(getParentRowName(propertyClass)), parent));
			TypedQuery<T> createQuery = em.createQuery(cq);
			createQuery.setParameter(parent, parentId);
			resultList = createQuery.getResultList();
			return resultList;
		}
		catch (Exception e)
		{
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static <T extends IoTProperty> void deleteProperty(Class<T> propertyClass, Integer id)
			throws DatabaseException
	{
		if (id == null)
		{
			throw new IllegalArgumentException("delete: must provide IoTProperty id.");
		}
		EntityManager em = null;
		EntityTransaction tx = null;
		try
		{
			em = getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			T prop = em.find(propertyClass, id);
			em.remove(prop);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while deleting property:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static <T extends IoTEntity> void deleteEntity(Class<T> EntityClass, Integer id) throws DatabaseException
	{
		EntityManager em = null;
		EntityTransaction tx = null;
		if (id == null)
		{
			throw new IllegalArgumentException("delete: must provide IoTEntity id.");
		}
		try
		{
			em = getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			T prop = em.find(EntityClass, id);
			em.remove(prop);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	private static String getParentRowName(Class<?> propertyClass)
	{
		if (ControllerProperty.class.equals(propertyClass)) return "idcontroller";
		if (SensorProperty.class.equals(propertyClass)) return "idsensor";
		if (SmartThingProperty.class.equals(propertyClass)) return "idthing";
		if (Controller.class.equals(propertyClass)) return "idcontroller";
		if (Sensor.class.equals(propertyClass)) return "idsensor";
		if (SmartThing.class.equals(propertyClass)) return "idthing";
		return null;
	}

	public static List<Measure> getSensorData(Integer idsensor, Date startDate, Date endDate,
			Map<String, Boolean> filter) throws DatabaseException
	{
		EntityManager em = null;
		if (idsensor == null)
		{
			throw new IllegalArgumentException("delete: must provide IoTEntity id.");
		}
		try
		{
			em = getEntityManager();
			Calendar c = Calendar.getInstance();
			c.setTime(endDate);
			c.set(Calendar.HOUR_OF_DAY, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);

			String filterTxt = "'";
			Iterator<Entry<String, Boolean>> i = filter.entrySet().iterator();
			while (i.hasNext())
			{
				Entry<String, Boolean> entry = i.next();
				filterTxt += "+" + entry.getKey() + "_" + entry.getValue() + " ";
			}
			filterTxt += "'";
			
			String query = "select * from data.data where idsensor=? and measure_date>=? and measure_date<=? and MATCH(metadata) AGAINST(? IN BOOLEAN MODE)";

			@SuppressWarnings("unchecked")
			List<Measure> resultList = (List<Measure>) em.createNativeQuery(query, Measure.class)
					.setParameter(1, idsensor).setParameter(2, startDate).setParameter(3, endDate)
					.setParameter(4, filterTxt).getResultList();

			return resultList;
		}
		catch (Exception e)
		{
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static User getUser(String username) throws DatabaseException
	{
		EntityManager em = null;
		if (username == null)
		{
			throw new IllegalArgumentException("getUser: must provide username.");
		}
		try
		{
			em = getEntityManager();
			TypedQuery<User> createQuery = em
					.createQuery("SELECT u FROM User u" + " WHERE u.username = ?1", User.class);
			createQuery.setParameter(1, username);
			List<User> resultList = createQuery.getResultList();
			if (resultList.size() >= 1) return resultList.get(0);
			return null;
		}
		catch (Exception e)
		{
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static void storeUser(User user) throws DatabaseException
	{
		if (user == null)
		{
			throw new IllegalArgumentException("User cannot be null.");
		}
		EntityManager em = null;
		EntityTransaction tx = null;
		try
		{
			em = getEntityManager();
			tx = em.getTransaction();
			tx.begin();

			if (user.getId() == null)
			{
				em.persist(user);
			}
			else
			{
				User stored = em.find(User.class, user.getId());
				if ("".equals(user.getHash())) user.setHash(stored.getHash());
				em.merge(user);
			}
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static List<User> getUser(Integer id) throws DatabaseException
	{
		EntityManager em = null;
		try
		{
			em = getEntityManager();
			List<User> resultList = null;

			if (id == null)
			{
				CriteriaQuery<User> cq = em.getCriteriaBuilder().createQuery(User.class);
				cq.select(cq.from(User.class));
				resultList = em.createQuery(cq).getResultList();
			}
			else
			{
				resultList = new ArrayList<>(1);
				User e = em.find(User.class, id);
				resultList.add(e);
			}

			return resultList;
		}
		catch (Exception e)
		{
			throw new DatabaseException("Database exception while retrieving user:" + e.getMessage(), e);
		}
		finally
		{
			if (em != null)
			{
				em.close();
			}
		}
	}

	public static void deleteUser(Integer id) throws DatabaseException
	{
		EntityManager em = null;
		EntityTransaction tx = null;
		if (id == null)
		{
			throw new IllegalArgumentException("delete: must provide user id.");
		}
		try
		{
			em = getEntityManager();
			tx = em.getTransaction();
			tx.begin();
			User user = em.find(User.class, id);
			em.remove(user);
			tx.commit();
		}
		catch (Exception e)
		{
			if (tx != null && tx.isActive())
			{
				tx.rollback();
			}
			throw new DatabaseException("Database exception while inserting entity:" + e.getMessage(), e);
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
