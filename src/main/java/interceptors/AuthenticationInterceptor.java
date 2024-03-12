package interceptors;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {
	
    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void init() {
        // Do nothing
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
    	
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session == null || session.get("username") == null) {
            return "login";
        } else {
            return invocation.invoke();
        }
    }
}
