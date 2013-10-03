package dc.framework;

import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

public class SearchTextFieldExtension extends AbstractExtension {
	
		
	    public void extend(TextField field) {
	    	System.out.println("extend..being called");
	        super.extend(field);
	    }
	    
	    
	
}
