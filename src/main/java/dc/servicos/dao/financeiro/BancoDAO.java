package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.Banco;
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
public class BancoDAO extends AbstractCrudDAO<Banco>{

	@Override
	public Class<Banco> getEntityClass() {
		return Banco.class;
	}
	
	
	/*@Transactional
	public List<AgenciaBanco> listAgencias(Banco banco) {
		return getSession().createQuery("from AgenciaBanco where banco.id = :bid").setParameter("bid", banco.getId()).list();
	}*/


	@Transactional
	public List<Banco> listaTodos() {
		return getSession().createQuery("from Banco").list();
	}

	@Transactional
	public List<Banco> procuraNomeContendo(String query) {
		return getSession().createQuery("from Banco where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "url"};
	}
	
	@Transactional
	public List<Banco> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Banco where lower(nome) like :q").setParameter("q", q).list();
	}
	
}
