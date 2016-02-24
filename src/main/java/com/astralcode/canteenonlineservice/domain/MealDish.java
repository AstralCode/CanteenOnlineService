package com.astralcode.canteenonlineservice.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "meal_dish", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = MealDish.QUERY_GET,
		query = "SELECT x FROM MealDish x WHERE x.mId = :" + Dish.ID_PARAMETER + " " +
			"AND x.mMeal.mId = :" + Meal.ID_PARAMETER + " " +
			"AND x.mMeal.mMenu.mId = :" + Menu.ID_PARAMETER + " " +
			"AND x.mDish.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = MealDish.QUERY_GET_ALL,
		query = "SELECT x FROM MealDish x WHERE x.mMeal.mId = :" + Meal.ID_PARAMETER + " " +
			"AND x.mMeal.mMenu.mId = :" + Menu.ID_PARAMETER + " " +
			"AND x.mDish.mCanteen.mId = :" + Canteen.ID_PARAMETER + " " +
			"ORDER BY x.mDish.mType.mDisplayOrder"
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "dish"
})
public class MealDish implements Serializable
{
	public static final String QUERY_GET = "MealDish.get";
	public static final String QUERY_GET_ALL = "MealDish.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@ManyToOne
	@JoinColumn(name = "meal_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meal_dish_meal_id"))
	private Meal mMeal;

	@ManyToOne
	@JoinColumn(name = "dish_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meal_dish_dish_id"))
	private Dish mDish;

	public MealDish()
	{

	}

	public MealDish(Meal meal, Dish dish)
	{
		mMeal = meal;
		mDish = dish;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlTransient
	public void setMeal(Meal meal)
	{
		mMeal = meal;
	}

	@XmlElement(name = "dish")
	public void setDish(Dish dish)
	{
		mDish = dish;
	}

	public Integer getId()
	{
		return mId;
	}

	public Meal getMeal()
	{
		return mMeal;
	}

	public Dish getDish()
	{
		return mDish;
	}
}
