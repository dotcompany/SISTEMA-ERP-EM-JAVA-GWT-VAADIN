package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.RequisicaoCompraDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDetalheDAO")
public class RequisicaoDetalheDAO extends
		AbstractCrudDAO<RequisicaoCompraDetalheEntity> implements IRequisicaoDetalheDAO {

	@Override
	public Class<RequisicaoCompraDetalheEntity> getEntityClass() {
		return RequisicaoCompraDetalheEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "produto.descricao" };
	}

}