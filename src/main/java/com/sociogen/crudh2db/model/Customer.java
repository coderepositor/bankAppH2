package com.sociogen.crudh2db.model;
import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;


@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="cname")
	private String cname;
	@Column(name="lname")
	private String lname;
	@Column(name="acno")
	private double acno;
	@CreationTimestamp
	private Date createdAt;
	@CreationTimestamp
	private Date updatedAt;
	
	public Customer() {}

	public Customer(String cname, String lname, long acno){
		this.cname = cname;
		this.lname = lname;
		this.acno = acno;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public double getAcno() {
		return acno;
	}

	public void setAcno(double acno) {
		this.acno = acno;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
