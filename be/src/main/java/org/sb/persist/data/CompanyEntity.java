package org.sb.persist.data;

import java.util.Set;

import org.sb.data.Company;





public class CompanyEntity extends Entity<Company>
{
	private Set<WorkCardSummary> workCards;

	public Set<WorkCardSummary> getWorkCards() {
		return workCards;
	}

	public void setWorkCards(Set<WorkCardSummary> workCards) {
		this.workCards = workCards;
	}
	
	
}
