package mx.cinvestav.gdl.iot.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.appengine.api.utils.SystemProperty;

public class DAO
{
//	public static PersistenceManager getPersistenceManager()
//	{
//		Map<String, String> props = new HashMap<String, String>();
//		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory(props);
//		PersistenceManager pm = pmf.getPersistenceManager();
//		return pm;
//	}
	private static Logger logger = Logger.getLogger(DAO.class.getName());
	public static EntityManager getEntityManager()
	{
		Map<String, String> properties = new HashMap<String, String>();
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
		{
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
			logger.log(Level.INFO, "Production selected!");
		}
		else
		{
			//properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
			properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url.dev"));
			logger.log(Level.INFO, "Dev selected!");
		}
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SmartCitiesCloudSQL", properties);
		EntityManager em = emf.createEntityManager();
		
		return em;
	}
}
