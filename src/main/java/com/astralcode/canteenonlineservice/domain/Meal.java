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
@Table(name = "meal", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Meal.QUERY_GET,
		query = "SELECT x FROM Meal x WHERE x.mId = :" + Meal.ID_PARAMETER + " " +
			"AND x.mMenu.mId = :" + Menu.ID_PARAMETER + " " +
			"AND x.mMenu.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Meal.QUERY_GET_ALL,
		query = "SELECT x FROM Meal x WHERE x.mMenu.mId = :" + Menu.ID_PARAMETER + " " +
			"AND x.mMenu.mCanteen.mId = :" + Canteen.ID_PARAMETER + " " +
			"ORDER BY x.mMenu.mDay.mDisplayOrder"
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "type"
})
public class Meal implements Serializable
{
	public static final String ID_PARAMETER = "mealId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Meal.get";
	public static final String QUERY_GET_ALL = "Meal.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@ManyToOne
	@JoinColumn(name = "meal_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meal_meal_type_id"))
	private MealType mType;

	@ManyToOne
	@JoinColumn(name = "menu_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meal_menu_id"))
	private Menu mMenu;

	public Meal()
	{

	}

	public Meal(MealType type, Menu menu)
	{
		mType = type;
		mMenu = menu;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlElement(name = "type")
	public void setType(MealType type)
	{
		mType = type;
	}

	@XmlTransient
	public void setCanteen(Menu menu)
	{
		mMenu = menu;
	}

	public Integer getId()
	{
		return mId;
	}

	public MealType getType()
	{
		return mType;
	}

	public Menu getMenu()
	{
		return mMenu;
	}
}
