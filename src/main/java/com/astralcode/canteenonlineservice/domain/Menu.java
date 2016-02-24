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
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Menu.QUERY_GET,
		query = "SELECT x FROM Menu x WHERE x.mId = :" + Menu.ID_PARAMETER + " " +
			"AND x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Menu.QUERY_GET_ALL,
		query = "SELECT x FROM Menu x WHERE x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "dayWeek"
})
public class Menu implements Serializable
{
	public static final String ID_PARAMETER = "menuId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Menu.get";
	public static final String QUERY_GET_ALL = "Menu.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@ManyToOne
	@JoinColumn(name = "day_week_id", nullable = false, foreignKey = @ForeignKey(name = "fk_menu_day_week_id"))
	private DayWeek mDay;

	@ManyToOne
	@JoinColumn(name = "canteen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_menu_canteen_id"))
	private Canteen mCanteen;

	public Menu()
	{

	}

	public Menu(DayWeek day, Canteen canteen)
	{
		mDay = day;
		mCanteen = canteen;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlElement(name = "dayWeek")
	public void setDayWeek(DayWeek day)
	{
		mDay = day;
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

	public DayWeek getDayWeek()
	{
		return mDay;
	}

	public Canteen getCanteen()
	{
		return mCanteen;
	}
}
