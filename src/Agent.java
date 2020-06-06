
public class Agent {
	String agentName; 
	String agentCompany;
	String agentPhone;
	String profileUrl;
	
	public Agent() {
		super();
	}

	public Agent(String agentName,String agentCompany, String agentPhone, String profileUrl) {
		super();
		this.agentName = agentName;
		this.agentCompany = agentCompany;
		this.agentPhone = agentPhone;
		this.profileUrl = profileUrl;
	}
	
	public String getAgentCompany() {
		return agentCompany;
	}

	public void setAgentCompany(String agentCompany) {
		this.agentCompany = agentCompany;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	

	
	
	
}
