package dc.model.business.financeiro;

import dc.entidade.financeiro.LancamentoPagarEntity;
import dc.entidade.financeiro.StatusParcela;
import dc.model.business.AbstractBusiness;
import dc.model.business.AbstractComboBusiness;

public interface StatusParcelaBusiness<T> extends AbstractBusiness<T>,AbstractComboBusiness<T> {
	
	StatusParcela findByLancamento(LancamentoPagarEntity t);

}
