package com.astralcode.canteenonlineservice.resource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

@Produces(MediaType.APPLICATION_JSON)
public class AbstractResource
{
	private static SessionFactory SESSION_FACTORY;

	static
	{
		try
		{
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
		}
		catch(HibernateException exception)
		{
			Logger.getLogger(AbstractResource.class).error("Build session factory", exception);
		}
	}

	protected Session openSession()
	{
		return getSessionFactory().openSession();
	}

	protected Query createNamedQuery(String queryId)
	{
		return openSession().getNamedQuery(queryId);
	}

	protected Response getEntity(Query query)
	{
		Object result = query.uniqueResult();

		if (result == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(result).build();
	}

	protected Response getEntities(Query query, final Class<?> resourceClass)
	{
		List result = query.list();

		if (result.isEmpty())
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		ParameterizedType parameterizedType = new ParameterizedType()
		{
			@Override
			public Type[] getActualTypeArguments()
			{
				return new Class<?>[]{ resourceClass };
			}

			@Override
			public Type getRawType()
			{
				return List.class;
			}

			@Override
			public Type getOwnerType()
			{
				return List.class;
			}
		};

		GenericEntity entity = new GenericEntity(result, parameterizedType){};

		return Response.ok(entity).build();
	}

	protected SessionFactory getSessionFactory()
	{
		return SESSION_FACTORY;
	}
}
