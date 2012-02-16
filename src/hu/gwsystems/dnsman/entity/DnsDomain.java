package hu.gwsystems.dnsman.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class DnsDomain implements Serializable {
	private static final long serialVersionUID = -23587623231L;

	@Id
	@SequenceGenerator(name="DNSDOMAIN_SEQ", sequenceName="DNSDOMAIN_SEQ", allocationSize=1, initialValue=0)
	@GeneratedValue(generator="DNSDOMAIN_SEQ", strategy=GenerationType.SEQUENCE)
	@Column
	private Long id;
	
	@Column
	private String domainName;
	
	@Column
	private String master;
	
	@Column
	private Long lastCheck;
	
	@Column
	private String type;
	
	@Column
	private String notifiedSerial;
	
	@Column
	private Long userId;
	
	public Long getId() {
		return id;
	}
	
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public Long getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(Long lastCheck) {
		this.lastCheck = lastCheck;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNotifiedSerial() {
		return notifiedSerial;
	}

	public void setNotifiedSerial(String notifiedSerial) {
		this.notifiedSerial = notifiedSerial;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
