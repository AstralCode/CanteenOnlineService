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
@Table(name = "price", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Price.QUERY_GET,
		query = "SELECT x FROM Price x WHERE x.mId = :" + Price.ID_PARAMETER + " " +
			"AND x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Price.QUERY_GET_ALL,
		query = "SELECT x FROM Price x WHERE x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "title", "description", "price"
})
public class Price implements Serializable
{
	public static final String ID_PARAMETER = "priceId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Price.get";
	public static final String QUERY_GET_ALL = "Price.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "title", length = 64, nullable = false)
	private String mTitle;

	@Column(name = "description", length = 64, nullable = false)
	private String mDescription;

	@Column(name = "price", nullable = false)
	private Integer mPrice;

	@ManyToOne
	@JoinColumn(name = "canteen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_price_canteen_id"))
	private Canteen mCanteen;

	public Price()
	{

	}

	public Price(String title, String description, Integer price, Canteen canteen)
	{
		mTitle = title;
		mDescription = description;
		mPrice = price;
		mCanteen = canteen;
	}

	@XmlElement(name = "id")
	public void setId(Integer id)
	{
		mId = id;
	}

	@XmlElement(name = "title")
	public void setTitle(String title)
	{
		mTitle = title;
	}

	@XmlElement(name = "description")
	public void setDescription(String description)
	{
		mDescription = description;
	}

	@XmlElement(name = "price")
	public void setPrice(Integer price)
	{
		mPrice = price;
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

	public String getTitle()
	{
		return mTitle;
	}

	public String getDescription()
	{
		return mDescription;
	}

	public Integer getPrice()
	{
		return mPrice;
	}

	public Canteen getCanteen()
	{
		return mCanteen;
	}
}
