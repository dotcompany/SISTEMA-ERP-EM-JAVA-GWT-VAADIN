package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDetalheDAO")
public class RequisicaoDetalheDAO extends
		AbstractCrudDAO<RequisicaoDetalheEntity> {

	@Override
	public Class<RequisicaoDetalheEntity> getEntityClass() {
		return RequisicaoDetalheEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "produto.descricao" };
	}

}