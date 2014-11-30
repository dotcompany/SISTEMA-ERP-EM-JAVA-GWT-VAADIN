package dc.servicos.dao.suprimentos.compra;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.compra.PedidoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraPedidoCompraDAO")
public class PedidoCompraDAO extends AbstractCrudDAO<PedidoEntity> {

	@Override
	public Class<PedidoEntity> getEntityClass() {
		return PedidoEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "nome" };
	}

	@Transactional(readOnly = true)
	public boolean existsPedidoDetalheByCotacao(Integer id) {
		Query query = getSession()
				.createQuery(
						"from CotacaoPedidoDetalhe cpd where cpd.compraCotacaoDetalhe.id = ?");
		query.setInteger(0, id);

		return query.list().size() > 0;
	}

}