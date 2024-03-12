package action.logout;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.action.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	
	public Logout()
	{
		
	}
	
	@Override
	public void withSession(Map<String, Object> session) {
		this.session = session;
		
	}
	
	public String execute()
	{
		session.put("username", null);
		return SUCCESS;
	}

}
