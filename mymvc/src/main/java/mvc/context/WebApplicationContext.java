package mvc.context;

import org.springframework.context.ApplicationContext;

public interface WebApplicationContext  extends ApplicationContext {

    public final String ROOT_NAME = WebApplicationContext.class.getName() + "root";

}
