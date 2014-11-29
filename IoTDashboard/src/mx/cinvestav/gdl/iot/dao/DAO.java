package mx.cinvestav.gdl.iot.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	static
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

		}
	}

	public static EntityManager createEntityManager()
	{
		return emf.createEntityManager();
	}

	public static void insert(Object c)
	{
		EntityManager em = null;
		try
		{
			em = emf.createEntityManager();
			em.persist(c);
		}
		finally
		{
			if (em != null) em.close();
		}
	}
}
