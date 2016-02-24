package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

@Path(CanteenResource.PATH)
public class CanteenResource extends AbstractResource
{
	public static final String PATH = "/canteens";

	@GET
	@Path(Canteen.ID_PATH_PARAMETER)
	public Response getCanteen
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId
	)
	{
		Query query = createNamedQuery(Canteen.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);

		return getEntity(query);
	}

	@GET
	public Response getCanteens()
	{
		Query query = createNamedQuery(Canteen.QUERY_GET_ALL);

		return getEntities(query, Canteen.class);
	}

	@Path(CanteenDishResource.PATH)
	public Class<CanteenDishResource> getCanteenDishResource()
	{
		return CanteenDishResource.class;
	}

	@Path(CanteenMenuResource.PATH)
	public Class<CanteenMenuResource> getCanteenMenuResource()
	{
		return CanteenMenuResource.class;
	}

	@Path(CanteenPriceResource.PATH)
	public Class<CanteenPriceResource> getCanteenPriceResource()
	{
		return CanteenPriceResource.class;
	}

	@Path(CanteenInformationResource.PATH)
	public Class<CanteenInformationResource> getCanteenInformationResource()
	{
		return CanteenInformationResource.class;
	}
}
