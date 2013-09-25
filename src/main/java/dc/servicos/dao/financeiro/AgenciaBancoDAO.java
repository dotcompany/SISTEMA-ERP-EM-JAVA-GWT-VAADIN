package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.AgenciaBanco;
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
public class AgenciaBancoDAO extends AbstractCrudDAO<AgenciaBanco>{

	@Override
	public Class<AgenciaBanco> getEntityClass() {
		return AgenciaBanco.class;
	}

	@Transactional
	public List<AgenciaBanco> listaTodos() {
		return getSession().createQuery("from AgenciaBanco").list();
	}

	@Transactional
	public List<AgenciaBanco> procuraNomeContendo(String query) {
		return getSession().createQuery("from AgenciaBanco where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "logradouro"};
	}
	
	@Transactional
	public List<AgenciaBanco> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from AgenciaBanco where lower(nome) like :q").setParameter("q", q).list();
	}

}
