package dc.visao.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextStartedEvent;

import com.sun.istack.logging.Logger;

@org.springframework.stereotype.Component
@Scope("singleton")
public class DCWebApplicationListener implements ApplicationListener<ContextStartedEvent>{
	
	@Autowired
	SearchIndexerDAO dao;
	
	@Autowired
	SecuritySessionProvider secureSession;
	
	@Value("${search.indexOnStartup}") 
	private String indexOnStartup;
	
	public static Logger logger = Logger.getLogger(DCWebApplicationListener.class);
	
	@PostConstruct
	protected void init() {
		logger.info("DCWebApplicationListener started. will build index for firstTime");
		if(indexOnStartup != null && Boolean.valueOf(indexOnStartup)){
			logger.info("Index on startup configured. should index...");
			dao.loadIndex();	
		}
		
			
	}
	
	@Override
	public void onApplicationEvent(ContextStartedEvent  arg0) {
		// TODO Auto-generated method stub
		logger.info("applicaticon event...  " + arg0);
		//dao.loadIndex();
		logger.info("applicaticon event...  handled");
	}

}
