package org.dselent.scheduling.server.returnobjects;

public class ReturnUser
{	
	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private Integer userStateId;
	private Long createdAt;
	private Long updatedAt;

	//
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}


	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Integer getUserStateId()
	{
		return userStateId;
	}

	public void setUserStateId(Integer userStateId)
	{
		this.userStateId = userStateId;
	}

	public Long getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Long createdAt)
	{
		this.createdAt = createdAt;
	}

	public Long getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt)
	{
		this.updatedAt = updatedAt;
	}
}
