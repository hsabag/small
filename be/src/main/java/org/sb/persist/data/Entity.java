package org.sb.persist.data;

import javax.persistence.Id;

public class Entity<T> {
	@Id
    private String id;
	
	private T entity;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
}
