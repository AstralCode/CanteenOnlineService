package com.astralcode.canteenonlineservice.resource;

import com.astralcode.canteenonlineservice.domain.Canteen;
import com.astralcode.canteenonlineservice.domain.DayWeek;
import com.astralcode.canteenonlineservice.domain.Dish;
import com.astralcode.canteenonlineservice.domain.DishType;
import com.astralcode.canteenonlineservice.domain.Information;
import com.astralcode.canteenonlineservice.domain.Meal;
import com.astralcode.canteenonlineservice.domain.MealDish;
import com.astralcode.canteenonlineservice.domain.MealType;
import com.astralcode.canteenonlineservice.domain.Menu;
import com.astralcode.canteenonlineservice.domain.Price;
import com.astralcode.canteenonlineservice.domain.Rank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Path(DatabaseExampleDataResource.PATH)
public class DatabaseExampleDataResource extends AbstractResource
{
	public static final String PATH = "/create";

	private static final Random RANDOM = new Random();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response createDatabase()
	{
		List<Canteen> canteens = createCanteens();
		List<DishType> dishTypes = createDishTypes();
		List<MealType> mealTypes = createMealTypes();
		List<DayWeek> dayWeeks = createDayWeeks();

		List<Dish> canteenADishes = createDishesForCanteen(canteens.get(0), dishTypes);
		List<Dish> canteenBDishes = createDishesForCanteen(canteens.get(1), dishTypes);

		createRanksForDishes(canteenADishes);
		createRanksForDishes(canteenBDishes);

		createMenuForCanteen(canteens.get(0), canteenADishes, dayWeeks, mealTypes);
		createMenuForCanteen(canteens.get(1), canteenBDishes, dayWeeks, mealTypes);

		createPrices(canteens);
		createInformationsForCanteen(canteens);

		return Response.ok("Database created.").build();
	}

	private void createRanksForDishes(List<Dish> canteenDishes)
	{
		for (Dish dish : canteenDishes)
		{
			if (dish.getType().isRankable())
			{
				List<Rank> ranks = new ArrayList<>();

				for (int i = 0; i < 2 + RANDOM.nextInt(4); i++)
				{
					ranks.add(new Rank(RANDOM.nextInt(5) + 1, dish));
				}

				persistEntities(ranks);
			}
		}
	}

	private void createMealForCanteen(Meal meal, List<Dish> canteenDishes)
	{
		if (meal.getType().getName().equals("Lunch"))
		{
			persistEntity(new MealDish(meal, canteenDishes.get(RANDOM.nextInt(7))));
			persistEntity(new MealDish(meal, canteenDishes.get(13 + RANDOM.nextInt(7))));

			if (RANDOM.nextBoolean())
			{
				persistEntity(new MealDish(meal, canteenDishes.get(20 + RANDOM.nextInt(8))));
			}
		}
		else if (meal.getType().getName().equals("Dinner"))
		{
			persistEntity(new MealDish(meal, canteenDishes.get(RANDOM.nextInt(7))));
			persistEntity(new MealDish(meal, canteenDishes.get(7 + RANDOM.nextInt(6))));
			persistEntity(new MealDish(meal, canteenDishes.get(13 + RANDOM.nextInt(7))));

			if (RANDOM.nextBoolean())
			{
				persistEntity(new MealDish(meal, canteenDishes.get(20 + RANDOM.nextInt(8))));
			}
			else
			{
				persistEntity(new MealDish(meal, canteenDishes.get(28 + RANDOM.nextInt(5))));
			}
		}
	}

	private void createMenuForCanteen(Canteen canteen, List<Dish> canteenDishes, List<DayWeek> dayWeeks, List<MealType> mealTypes)
	{
		for (int i = 0; i < 5; i++)
		{
			Menu menu = new Menu(dayWeeks.get(i), canteen);

			persistEntity(menu);

			Meal lunch = new Meal(mealTypes.get(0), menu);
			Meal dinner = new Meal(mealTypes.get(1), menu);

			persistEntity(lunch);
			persistEntity(dinner);

			createMealForCanteen(lunch, canteenDishes);
			createMealForCanteen(dinner, canteenDishes);
		}
	}

	private List<Dish> createDishesForCanteen(Canteen canteen, List<DishType> dishTypes)
	{
		List<Dish> canteenDishes = new ArrayList<>();

		// Drink 7
		canteenDishes.add(new Dish("Black tea", dishTypes.get(0), canteen));
		canteenDishes.add(new Dish("Orange juice", dishTypes.get(0), canteen));
		canteenDishes.add(new Dish("Milk", dishTypes.get(0),  canteen));
		canteenDishes.add(new Dish("Soy milk", dishTypes.get(0),  canteen));
		canteenDishes.add(new Dish("Peppermint Hot Chocolate", dishTypes.get(0),  canteen));
		canteenDishes.add(new Dish("Pink Blueberry Lemonade", dishTypes.get(0),  canteen));
		canteenDishes.add(new Dish("Sparkling Lemon Herb Tea", dishTypes.get(0),  canteen));

		// Soup 13
		canteenDishes.add(new Dish("Canja de Galinha", dishTypes.get(1),  canteen));
		canteenDishes.add(new Dish("Stone soup", dishTypes.get(1),  canteen));
		canteenDishes.add(new Dish("Cabbage soup", dishTypes.get(1),  canteen));
		canteenDishes.add(new Dish("Shark fin soup", dishTypes.get(1),  canteen));
		canteenDishes.add(new Dish("Nettle soup", dishTypes.get(1),  canteen));
		canteenDishes.add(new Dish("Chicken noodle soup", dishTypes.get(1),  canteen));

		// Salad 20
		canteenDishes.add(new Dish("Apple Trio Salad with Bacon Vinaigrette", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("Mom’s Green Bean Salad", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("Watermelon Tomato Salad With Feta Cheese", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("BBQ Corn Salad", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("Blueberry Pecan Chicken Salad", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("Chicken Salad with Red Potatoes", dishTypes.get(2),  canteen));
		canteenDishes.add(new Dish("Spring Green Tortellini Pasta Salad", dishTypes.get(2),  canteen));

		// Fish 28
		canteenDishes.add(new Dish("White Fish (Swai)", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Catfish", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Mahi Mahi", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Salmon", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Cajun Seared Ahi Tuna", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Giant Shrimp", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Tilapia", dishTypes.get(3),  canteen));
		canteenDishes.add(new Dish("Trout", dishTypes.get(3),  canteen));

		// Meat 33
		canteenDishes.add(new Dish("Prato variado", dishTypes.get(4),  canteen));
		canteenDishes.add(new Dish("Grilled Citrus Steak", dishTypes.get(4),  canteen));
		canteenDishes.add(new Dish("Osso Bucco", dishTypes.get(4),  canteen));
		canteenDishes.add(new Dish("Beef with Prosciutto and Mushrooms", dishTypes.get(4),  canteen));
		canteenDishes.add(new Dish("Veal Parmigiana", dishTypes.get(4),  canteen));

		persistEntities(canteenDishes);

		return canteenDishes;
	}

	private void createPrices(List<Canteen> canteens)
	{
		for (Canteen canteen : canteens)
		{
			persistEntity(new Price("Single dish", "Price for single dish", 1, canteen));
			persistEntity(new Price("Minimum menu", "Contains: drink and dish", 2 + RANDOM.nextInt(3), canteen));
			persistEntity(new Price("Full menu", "Contains: drink, soup, salad and dish", 4 + RANDOM.nextInt(3), canteen));
		}
	}

	private void createInformationsForCanteen(List<Canteen> canteens)
	{
		long time = Calendar.getInstance().getTimeInMillis();

		for (Canteen canteen : canteens)
		{
			persistEntity(new Information("Opening time", "from 7:00 a.m. to 19:00 p.m.", new Date(time), canteen));
			persistEntity(new Information("Serving time", "Lunch till 11:30 a.m.\\nDinner from 1:00 a.m. to 3:00 a.m.", new Date(time), canteen));
		}
	}

	private List<Canteen> createCanteens()
	{
		List<Canteen> canteens = new ArrayList<>();

		canteens.add(new Canteen("Paraya"));
		canteens.add(new Canteen("Paradisia"));

		persistEntities(canteens);

		return canteens;
	}

	private List<DishType> createDishTypes()
	{
		List<DishType> dishTypes = new ArrayList<>();

		dishTypes.add(new DishType("Drink", false, 1));
		dishTypes.add(new DishType("Soup", true, 2));
		dishTypes.add(new DishType("Salad", true, 3));
		dishTypes.add(new DishType("Fish", true, 4));
		dishTypes.add(new DishType("Meat", true, 5));

		persistEntities(dishTypes);

		return dishTypes;
	}

	private List<MealType> createMealTypes()
	{
		List<MealType> mealTypes = new ArrayList<>();

		mealTypes.add(new MealType("Lunch", 1));
		mealTypes.add(new MealType("Dinner", 2));

		persistEntities(mealTypes);

		return mealTypes;
	}

	private List<DayWeek> createDayWeeks()
	{
		List<DayWeek> dayWeeks = new ArrayList<>();

		dayWeeks.add(new DayWeek("Monday", 1));
		dayWeeks.add(new DayWeek("Tuesday", 2));
		dayWeeks.add(new DayWeek("Wednesday", 3));
		dayWeeks.add(new DayWeek("Thursday", 4));
		dayWeeks.add(new DayWeek("Friday", 5));

		persistEntities(dayWeeks);

		return dayWeeks;
	}

	private <T> void persistEntity(T entity)
	{
		try (Session session = openSession())
		{
			Transaction transaction = session.beginTransaction();
			session.persist(entity);
			transaction.commit();
		}
	}

	private <T> void persistEntities(List<T> entities)
	{
		try (Session session = openSession())
		{
			Transaction transaction = session.beginTransaction();
			for (T entity : entities)
			{
				session.persist(entity);
			}
			transaction.commit();
		}
	}
}
