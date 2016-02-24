package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Menu;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenMenuResource extends AbstractResource
{
	public static final String PATH = Canteen.ID_PATH_PARAMETER + "/menus";

	@GET
	@Path(Menu.ID_PATH_PARAMETER)
	public Response getCanteenMenu
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Menu.ID_PARAMETER)
		Integer menuId
	)
	{
		Query query = createNamedQuery(Menu.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Menu.ID_PARAMETER, menuId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenMenus
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId
	)
	{
		Query query = createNamedQuery(Menu.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);

		return getEntities(query, Menu.class);
	}

	@Path(CanteenMenuMealResource.PATH)
	public Class<CanteenMenuMealResource> getMeals()
	{
		return CanteenMenuMealResource.class;
	}
}
