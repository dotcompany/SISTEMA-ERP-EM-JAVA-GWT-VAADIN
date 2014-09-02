package dc.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

@org.springframework.stereotype.Component
@Scope("singleton")
public class ConfigProperties {

	@Value("${combo.delayValue}")
	public int COMBO_DELAYVALUE;
	
	
	
}
