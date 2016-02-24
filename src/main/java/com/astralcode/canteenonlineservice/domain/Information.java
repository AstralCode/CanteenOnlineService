package com.astralcode.canteenonlineservice.domain;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = "information", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Information.QUERY_GET,
		query = "SELECT x FROM Information x WHERE x.mId = :" + Information.ID_PARAMETER + " " +
			"AND x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Information.QUERY_GET_ALL,
		query = "SELECT x FROM Information x WHERE x.mCanteen.mId = :" + Canteen.ID_PARAMETER
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "title", "information", "dateCreated"
})
public class Information implements Serializable
{
	public static final String ID_PARAMETER = "informationId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Information.get";
	public static final String QUERY_GET_ALL = "Information.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "title", length = 64, nullable = false)
	private String mTitle;

	@Column(name = "information", length = 64, nullable = false)
	private String mInformation;

	@Column(name = "date_created", nullable = false)
	private Date mDateCreated;

	@ManyToOne
	@JoinColumn(name = "canteen_id", nullable = false, foreignKey = @ForeignKey(name = "fk_information_canteen_id"))
	private Canteen mCanteen;

	public Information()
	{

	}

	public Information(String title, String information, Date dateCreated, Canteen canteen)
	{
		mTitle = title;
		mInformation = information;
		mDateCreated = dateCreated;
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

	@XmlElement(name = "information")
	public void setInformation(String information)
	{
		mInformation = information;
	}

	@XmlElement(name = "date_created")
	public void setDateCreated(Date dateCreated)
	{
		mDateCreated = dateCreated;
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

	public String getInformation()
	{
		return mInformation;
	}

	public Date getDate()
	{
		return mDateCreated;
	}

	public Canteen getCanteen()
	{
		return mCanteen;
	}
}
