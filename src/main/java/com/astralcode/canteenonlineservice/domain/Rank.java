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
@Table(name = "rank", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Rank.QUERY_GET,
		query = "SELECT x FROM Rank x WHERE x.mId = :" + Rank.ID_PARAMETER + " " +
			"AND x.mDish.mId = :" + Dish.ID_PARAMETER + " " +
			"AND x.mDish.mCanteen.mId = :" + Canteen.ID_PARAMETER),
	@NamedQuery
	(
		name = Rank.QUERY_GET_ALL,
		query = "SELECT x FROM Rank x WHERE x.mDish.mId = :" + Dish.ID_PARAMETER + " " +
			"AND x.mDish.mCanteen.mId = :" + Canteen.ID_PARAMETER
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "rank"
})
public class Rank implements Serializable
{
	public static final String ID_PARAMETER = "rankId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Rank.get";
	public static final String QUERY_GET_ALL = "Rank.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "rank", nullable = false)
	private Integer mRank;

	@ManyToOne
	@JoinColumn(name = "dish_id", nullable = false, foreignKey = @ForeignKey(name = "fk_rank_dish_id"))
	private Dish mDish;

	public Rank()
	{

	}

	public Rank(Integer rank, Dish dish)
	{
		mRank = rank;
		mDish = dish;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlElement(name = "rank")
	public void setRank(Integer rank)
	{
		mRank = rank;
	}

	@XmlTransient
	public void setDish(Dish dish)
	{
		mDish = dish;
	}

	public Integer getId()
	{
		return mId;
	}

	public Integer getRank()
	{
		return mRank;
	}

	public Dish getDish()
	{
		return mDish;
	}
}
