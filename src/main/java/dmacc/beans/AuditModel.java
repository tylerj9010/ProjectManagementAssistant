package dmacc.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@SuppressWarnings("serial")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditModel implements Serializable {
	@Temporal(TemporalType.DATE)
	@Column(name = "dateCreated", nullable = false)
	@CreatedDate
	private Date dateCreated;
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	}
