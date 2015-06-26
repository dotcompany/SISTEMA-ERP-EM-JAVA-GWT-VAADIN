package dc.model.business.eventos;

import org.springframework.stereotype.Component;

import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

@Component
public interface ContratoBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {

}
