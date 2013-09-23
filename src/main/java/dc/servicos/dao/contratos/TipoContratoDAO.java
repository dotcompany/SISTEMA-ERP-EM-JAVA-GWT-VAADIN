package dc.servicos.dao.contratos;

import org.springframework.stereotype.Repository;

import dc.entidade.contratos.TipoContrato;
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
