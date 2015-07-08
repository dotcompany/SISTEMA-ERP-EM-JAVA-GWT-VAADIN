package dc.model.business.financeiro;

import dc.entidade.financeiro.LancamentoPagar;
import dc.entidade.financeiro.StatusParcela;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface StatusParcelaBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {
	
	StatusParcela findByLancamento(LancamentoPagar t);

}
