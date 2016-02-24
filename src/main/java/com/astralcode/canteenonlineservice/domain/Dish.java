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
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Dish.QUERY_GET,
		query = "SELECT x FROM Dish x WHERE x.mId = :" + Dish.ID_PARAMETER + " " +
			"AND x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Dish.QUERY_GET_ALL,
		query = "SELECT x FROM Dish x WHERE x.mCanteen.mId = :" + Canteen.ID_PARAMETER + " " +
			"ORDER BY x.mType.mDisplayOrder"
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "name", "type"
})
public class Dish implements Serializable
{
	public static final String ID_PARAMETER = "dishId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Dish.get";
	public static final String QUERY_GET_ALL = "Dish.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "name", length = 64, nullable = false)
	private String mName;

	@ManyToOne
	@JoinColumn(name = "dish_type_id", nullable = false, foreignKey = @ForeignKey(name = "fk_dish_dish_type_id"))
	private DishType mType;

	@ManyToOne
	@JoinColumn(name = "canteen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_dish_canteen_id"))
	private Canteen mCanteen;

	public Dish()
	{

	}

	public Dish(String name, DishType type, Canteen canteen)
	{
		mName = name;
		mType = type;
		mCanteen = canteen;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlElement(name = "name")
	public void setName(String name)
	{
		mName = name;
	}

	@XmlElement(name = "type")
	public void setType(DishType type)
	{
		mType = type;
	}

	@XmlTransient
	public void setCanteen(Canteen canteen)
	{
		mCanteen = canteen;
	}

	public Integer getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}

	public DishType getType()
	{
		return mType;
	}

	public Canteen getCanteen()
	{
		return mCanteen;
	}
}
