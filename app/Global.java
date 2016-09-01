import conf.AppConf;
import conf.DataConf;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.*;
import play.mvc.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Global extends GlobalSettings {
	
	private ApplicationContext context;
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Override
    public void onStart(final Application app) {
    	log.info("APP Start");
        context = new AnnotationConfigApplicationContext(AppConf.class, DataConf.class);
    }
    
    @Override
    public void onStop(Application app) {
    	 log.info("APP Stop");
    }   
    
//    @Override
//    public <A> A getControllerInstance(final Class<A> clazz) {
//        BeanFactory context;
//		return context.getBean(clazz);
//    }
}