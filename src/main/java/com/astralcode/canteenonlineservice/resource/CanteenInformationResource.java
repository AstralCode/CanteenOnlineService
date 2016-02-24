package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Information;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenInformationResource extends AbstractResource
{
	public static final String PATH = Canteen.ID_PATH_PARAMETER + "/informations";

	@GET
	@Path(Information.ID_PATH_PARAMETER)
	public Response getCanteenInformation
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Information.ID_PARAMETER)
		Integer informationId
	)
	{
		Query query = createNamedQuery(Information.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Information.ID_PARAMETER, informationId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenInformations
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId
	)
	{
		Query query = createNamedQuery(Information.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);

		return getEntities(query, Information.class);
	}
}
