package dc.servicos.dao.diversos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Estado;
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
public class EstadoDAO extends AbstractCrudDAO<Estado>{

	@Override
	public Class<Estado> getEntityClass() {
		return Estado.class;
	}
	
	@Transactional
	public List<Estado> listaTodos() {
		return getSession().createQuery("from Estado").list();
	}

	@Transactional
	public List<Estado> procuraNomeContendo(String query) {
		return getSession().createQuery("from Estado where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "sigla"};
	}
	
	@Transactional
	public List<Estado> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Estado where lower(nome) like :q").setParameter("q", q).list();
	}

}
