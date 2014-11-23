package dc.servicos.dao.suprimentos.contrato;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.contrato.Contrato;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContratoDAO extends AbstractCrudDAO<Contrato> {

	@Override
	public Class<Contrato> getEntityClass() {
		return Contrato.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "numero", "nome", "descricao", "observacao" };
	}

	@Override
	@Transactional
	public Contrato find(Serializable id) {
		Contrato contrato = super.find(id);
		// workaround para lazy initialization exception
		contrato.getContratosHistoricosFaturamentos();
		contrato.getContratosHistoricosReajustes();
		contrato.getContratosPrevisoesFaturamentos();

		return contrato;
	}

}