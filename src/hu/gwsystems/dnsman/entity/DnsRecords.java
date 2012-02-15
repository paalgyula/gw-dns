package hu.gwsystems.dnsman.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class DnsRecords implements Serializable {
	private static final long serialVersionUID = -1298687397451L;

	@Id
	@SequenceGenerator(name="DNSRECORD_SEQ", sequenceName="DNSRECORD_SEQ")
	@GeneratedValue(generator="DNSRECORD_SEQ", strategy=GenerationType.SEQUENCE)
	@Column
	private Integer id;
	
	private DnsDomain domain;
	
	@Column
	private String name;
	
	@Column
	private String value;
	
	@Column
	private int TTL;
	
	@Column
	private int priority;

	
	public int getId() {
		return id;
	}

	public DnsDomain getDomain() {
		return domain;
	}

	public void setDomain(DnsDomain domain) {
		this.domain = domain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getTTL() {
		return TTL;
	}

	public void setTTL(int tTL) {
		TTL = tTL;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
