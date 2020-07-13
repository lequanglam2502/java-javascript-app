package com.colin.app.config.database.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Declare the database resource as following xml format
 * 
<resoucerouting>
	<resourcedetail>
		<name>com.mysql.jdbc.Driver</name>
		<url>jdbc:mysql://localhost:3306/super_db?createDatabaseIfNotExist=true&amp;allowPublicKeyRetrieval=true&amp;useSSL=false</url>
		<user>root</user>
		<pass>root123456</pass>
	</resourcedetail>
</resoucerouting>
 */
@XmlRootElement(name = "resourcedetail")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResourceDetail {

	@XmlAttribute(name= "key")
	private String key;

	private String name;

	private String url;

	private String user;

	private String pass;

	public ResourceDetail() {
		super();
	}

	public ResourceDetail(String key, String name, String url, String user, String pass) {
		super();
		this.key = key;
		this.name = name;
		this.url = url;
		this.user = user;
		this.pass = pass;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
