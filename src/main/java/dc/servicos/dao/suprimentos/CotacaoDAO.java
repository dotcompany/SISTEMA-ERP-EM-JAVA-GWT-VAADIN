package dc.servicos.dao.suprimentos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.Cotacao;
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
@Transactional
public class CotacaoDAO extends AbstractCrudDAO<Cotacao>{

		@Override
		public Class<Cotacao> getEntityClass() {
			return Cotacao.class;
		}
		
		@Override
		public Cotacao find(Serializable id) {
			Cotacao cotacao = super.find(id);
			// workaround para lazy initialization exception
			cotacao.getCompraFornecedorCotacaos().size();
			cotacao.getCompraReqCotacaoDetalhes().size();
			return cotacao;
		}


		protected String[] getDefaultSearchFields() {
			return new String[] {"codigo", "descricao", "nome"};
		}

}
