package dc.servicos.dao.suprimentos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.NotaFiscal;
import dc.entidade.suprimentos.Requisicao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@Transactional
public class NotaFiscalDAO
extends AbstractCrudDAO<NotaFiscal>{
	
	@Override
	public Class<NotaFiscal> getEntityClass() {
		return NotaFiscal.class;
	}

	@Override
	public NotaFiscal find(Serializable id) {
		NotaFiscal nfe = super.find(id);
		// workaround para lazy initialization exception
		return nfe;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"id"};
	}

}
