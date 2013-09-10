package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.NivelFormacao;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o m�todo do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações l� no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/


@Repository
@SuppressWarnings("unchecked")
public class NivelFormacaoDAO extends AbstractCrudDAO<NivelFormacao>{

	@Override
	protected Class<NivelFormacao> getEntityClass() {
		return NivelFormacao.class;
	}
	
	@Transactional
	public List<NivelFormacao> listaTodos() {
		return getSession().createQuery("from NivelFormacao").list();
	}

	@Transactional
	public List<NivelFormacao> procuraNomeContendo(String query) {
		return getSession().createQuery("from NivelFormacao where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome"};
	}
	
	@Transactional
	public List<NivelFormacao> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from NivelFormacao where lower(nome) like :q").setParameter("q", q).list();
	}

}
