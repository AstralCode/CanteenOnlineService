package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Meal;
import com.astralcode.canteenonlineservice.domain.Menu;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenMenuMealResource extends AbstractResource
{
	public static final String PATH = Menu.ID_PATH_PARAMETER + "/meals";

	@GET
	@Path(Meal.ID_PATH_PARAMETER)
	public Response getCanteenMenuMeal
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Menu.ID_PARAMETER)
		Integer menuId,
		@PathParam(Meal.ID_PARAMETER)
		Integer mealId
	)
	{
		Query query = createNamedQuery(Meal.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Menu.ID_PARAMETER, menuId);
		query.setParameter(Meal.ID_PARAMETER, mealId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenMenuMeals
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Menu.ID_PARAMETER)
		Integer menuId
	)
	{
		Query query = createNamedQuery(Meal.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Menu.ID_PARAMETER, menuId);

		return getEntities(query, Meal.class);
	}

	@Path(CanteenMenuMealDishResource.PATH)
	public Class<CanteenMenuMealDishResource> getDishes()
	{
		return CanteenMenuMealDishResource.class;
	}
}