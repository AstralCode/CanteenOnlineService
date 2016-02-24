package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.Dish;
import com.astralcode.canteenonlineservice.domain.Meal;
import com.astralcode.canteenonlineservice.domain.MealDish;
import com.astralcode.canteenonlineservice.domain.Menu;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.hibernate.Query;

public class CanteenMenuMealDishResource extends AbstractResource
{
	public static final String PATH = Meal.ID_PATH_PARAMETER + "/dishes";

	@GET
	@Path(Menu.ID_PATH_PARAMETER)
	public Response getCanteenMenuMealDish
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Menu.ID_PARAMETER)
		Integer menuId,
		@PathParam(Meal.ID_PARAMETER)
		Integer mealId,
		@PathParam(Dish.ID_PARAMETER)
		Integer dishId
	)
	{
		Query query = createNamedQuery(MealDish.QUERY_GET);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Menu.ID_PARAMETER, menuId);
		query.setParameter(Meal.ID_PARAMETER, mealId);
		query.setParameter(Dish.ID_PARAMETER, dishId);

		return getEntity(query);
	}

	@GET
	public Response getCanteenMenuMealDishes
	(
		@PathParam(Canteen.ID_PARAMETER)
		Integer canteenId,
		@PathParam(Menu.ID_PARAMETER)
		Integer menuId,
		@PathParam(Meal.ID_PARAMETER)
		Integer mealId
	)
	{
		Query query = createNamedQuery(MealDish.QUERY_GET_ALL);
		query.setParameter(Canteen.ID_PARAMETER, canteenId);
		query.setParameter(Menu.ID_PARAMETER, menuId);
		query.setParameter(Meal.ID_PARAMETER, mealId);

		return getEntities(query, MealDish.class);
	}
}