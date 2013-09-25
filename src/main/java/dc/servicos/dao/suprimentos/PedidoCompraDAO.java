package dc.servicos.dao.suprimentos;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.PedidoCompra;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 *
 * @author Alberto Anderick Jr
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o Método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
 */
@Repository
public class PedidoCompraDAO extends AbstractCrudDAO<PedidoCompra>{

		@Override
		public Class<PedidoCompra> getEntityClass() {
			return PedidoCompra.class;
		}
		

		protected String[] getDefaultSearchFields() {
			return new String[] {"codigo", "descricao", "nome"};
		}

		@Transactional(readOnly=true)
		public boolean existsPedidoDetalheByCotacao(Integer id) {
			Query query = getSession().createQuery("from CotacaoPedidoDetalhe cpd where cpd.compraCotacaoDetalhe.id = ?");
			query.setInteger(0, id);
			return query.list().size() > 0;
		}

}
