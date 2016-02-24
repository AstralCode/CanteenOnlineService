package com.astralcode.canteenonlineservice.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "dish_type", uniqueConstraints =
{
	@UniqueConstraint(columnNames = "id"),
	@UniqueConstraint(columnNames = "name", name = "uk_day_week_name"),
	@UniqueConstraint(columnNames = "display_order", name = "uk_day_week_display_order")
})
@XmlRootElement
@XmlType(propOrder =
{
	"id", "name", "rankable", "displayOrder"
})
public class DishType implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer mId;

	@Column(name = "name", length = 64, nullable = false)
	private String mName;

	@Column(name = "rankable", nullable = false)
	private Boolean mRankable;

	@Column(name = "display_order", nullable = false)
	private Integer mDisplayOrder;

	public DishType()
	{

	}

	public DishType(String name, Boolean rankable, Integer displayOrder)
	{
		mName = name;
		mRankable = rankable;
		mDisplayOrder = displayOrder;
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

	@XmlElement(name = "rankable")
	public void setRankable(Boolean rankable)
	{
		mRankable = rankable;
	}

	@XmlElement(name = "displayOrder")
	public void setDisplayOrder(Integer displayOrder)
	{
		mDisplayOrder = displayOrder;
	}

	public Boolean isRankable()
	{
		return mRankable;
	}

	public Integer getId()
	{
		return mId;
	}

	public String getName()
	{
		return mName;
	}

	public Integer getDisplayOrder()
	{
		return mDisplayOrder;
	}
}
