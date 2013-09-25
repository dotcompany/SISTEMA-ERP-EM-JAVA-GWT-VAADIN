package dc.servicos.dao.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.produto.UnidadeProduto;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o Método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/


@Repository
@SuppressWarnings("unchecked")
public class UnidadeProdutoDAO extends AbstractCrudDAO<UnidadeProduto>{

	@Override
	public Class<UnidadeProduto> getEntityClass() {
		return UnidadeProduto.class;
	}
	
	@Transactional
	public List<UnidadeProduto> listaTodos() {
		return getSession().createQuery("from UnidadeProduto").list();
	}

	@Transactional
	public List<UnidadeProduto> procuraNomeContendo(String query) {
		return getSession().createQuery("from UnidadeProduto where sigla like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"sigla", "descricao"};
	}
	
	@Transactional
	public List<UnidadeProduto> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from UnidadeProduto where lower(sigla) like :q").setParameter("q", q).list();
	}

}
