package dc.servicos.dao.suprimentos;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.Requisicao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
 *
 * @author Alberto Anderick Jr
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o m�todo do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações l� no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
 */
@Repository
@Transactional
public class RequisicaoDAO extends AbstractCrudDAO<Requisicao>{

	@Override
	protected Class<Requisicao> getEntityClass() {
		return Requisicao.class;
	}

	@Override
	public Requisicao find(Serializable id) {
		Requisicao requisicao = super.find(id);
		// workaround para lazy initialization exception
		requisicao.getRequisicaoDetalhes().size();
		return requisicao;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo", "descricao", "nome"};
	}

}

