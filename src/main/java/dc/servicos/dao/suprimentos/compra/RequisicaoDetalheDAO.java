package dc.servicos.dao.suprimentos.compra;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDetalheDAO")
public class RequisicaoDetalheDAO extends
		AbstractCrudDAO<RequisicaoDetalheEntity> implements IRequisicaoDetalheDAO {

	@Override
	public Class<RequisicaoDetalheEntity> getEntityClass() {
		return RequisicaoDetalheEntity.class;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "produto.descricao" };
	}

}