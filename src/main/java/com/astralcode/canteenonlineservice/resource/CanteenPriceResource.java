package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Price;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenPriceResource extends AbstractResource
{
	public static final String PATH = Canteen.ID_PATH_PARAMETER + "/prices";

	@GET
	@Path(Price.ID_PATH_PARAMETER)
	public Response getCanteenPrice
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Price.ID_PARAMETER)
		Integer priceId
	)
	{
		Query query = createNamedQuery(Price.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Price.ID_PARAMETER, priceId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenPrices
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId
	)
	{
		Query query = createNamedQuery(Price.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);

		return getEntities(query, Price.class);
	}
}