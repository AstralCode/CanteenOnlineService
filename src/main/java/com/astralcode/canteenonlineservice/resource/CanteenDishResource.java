package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Dish;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenDishResource extends AbstractResource
{
	public static final String PATH = Canteen.ID_PATH_PARAMETER + "/dishes";

	@GET
	@Path(Dish.ID_PATH_PARAMETER)
	public Response getCanteenDish
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Dish.ID_PARAMETER)
		Integer dishId
	)
	{
		Query query = createNamedQuery(Dish.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Dish.ID_PARAMETER, dishId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenDishes
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId
	)
	{
		Query query = createNamedQuery(Dish.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);

		return getEntities(query, Dish.class);
	}

	@Path(CanteenDishRankResource.PATH)
	public Class<CanteenDishRankResource> getCanteenDishRankResource()
	{
		return CanteenDishRankResource.class;
	}
}
