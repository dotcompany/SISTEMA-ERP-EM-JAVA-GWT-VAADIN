package dc.servicos.dao.suprimentos.contrato;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.contrato.ContratoTipoServico;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ContratoTipoServicoDAO extends
		AbstractCrudDAO<ContratoTipoServico> {

	@Override
	public Class<ContratoTipoServico> getEntityClass() {
		return ContratoTipoServico.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}