package com.astralcode.canteenonlineservice.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "canteen", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
@NamedQueries(
{
	@NamedQuery
	(
		name = Canteen.QUERY_GET,
		query = "SELECT x FROM Canteen x WHERE x.mId = :" + Canteen.ID_PARAMETER
	),
	@NamedQuery
	(
		name = Canteen.QUERY_GET_ALL,
		query = "SELECT x FROM Canteen x"
	)
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "name"
})
public class Canteen implements Serializable
{
	public static final String ID_PARAMETER = "canteenId";
	public static final String ID_PATH_PARAMETER = "{" + ID_PARAMETER + "}";

	public static final String QUERY_GET = "Canteen.get";
	public static final String QUERY_GET_ALL = "Canteen.getAll";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "name", length = 64, nullable = false)
	private String mName;
	
	public Canteen()
	{

	}

	public Canteen(String name)
	{
		mName = name;
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

	public Integer getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}
}
