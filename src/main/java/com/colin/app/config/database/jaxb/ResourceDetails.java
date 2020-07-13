package com.colin.app.config.database.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
<resourcedetail>
	<resourcedetail>
		<name>com.mysql.jdbc.Driver</name>
		<url>jdbc:mysql://localhost:3306/super_db?createDatabaseIfNotExist=true&amp;allowPublicKeyRetrieval=true&amp;useSSL=false</url>
		<user>root</user>
		<pass>root123456</pass>
	</resourcedetail>
</resourcedetail>
 */

@XmlRootElement(name = "resourcedetails")
@XmlAccessorType (XmlAccessType.FIELD)
public class ResourceDetails {
	@XmlElement(name = "resourcedetail")
	private List<ResourceDetail> resourcedetail = null;

	public ResourceDetails() {
		super();
	}

	public ResourceDetails(List<ResourceDetail> resourcedetail) {
		super();
		this.resourcedetail = resourcedetail;
	}

	public List<ResourceDetail> getResourcedetail() {
		return resourcedetail;
	}

	public void setResourcedetails(List<ResourceDetail> resourcedetail) {
		this.resourcedetail = resourcedetail;
	}
}
