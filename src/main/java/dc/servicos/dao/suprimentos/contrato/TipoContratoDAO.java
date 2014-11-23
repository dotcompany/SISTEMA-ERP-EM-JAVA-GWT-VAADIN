package dc.servicos.dao.suprimentos.contrato;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.contrato.TipoContrato;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class TipoContratoDAO extends AbstractCrudDAO<TipoContrato> {

	@Override
	public Class<TipoContrato> getEntityClass() {
		return TipoContrato.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

}