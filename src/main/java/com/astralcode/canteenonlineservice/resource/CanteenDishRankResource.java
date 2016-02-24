package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Dish;
import com.astralcode.canteenonlineservice.domain.Rank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenDishRankResource extends AbstractResource
{
	public static final String PATH = Dish.ID_PATH_PARAMETER + "/ranks";

	@GET
	@Path(Rank.ID_PATH_PARAMETER)
	public Response getCanteenDishRank
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Dish.ID_PARAMETER)
		Integer dishId,
		@PathParam(Rank.ID_PARAMETER)
		Integer rankId
	)
	{
		Query query = createNamedQuery(Rank.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Dish.ID_PARAMETER, dishId);
		query.setParameter(Rank.ID_PARAMETER, rankId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenDishRanks
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Dish.ID_PARAMETER)
		Integer dishId
	)
	{
		Query query = createNamedQuery(Rank.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Dish.ID_PARAMETER, dishId);

		return getEntities(query, Rank.class);
	}
}