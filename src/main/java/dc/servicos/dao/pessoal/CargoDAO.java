package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.Cargo;
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
public class CargoDAO extends AbstractCrudDAO<Cargo>{

	@Override
	protected Class<Cargo> getEntityClass() {
		return Cargo.class;
	}
	
	@Transactional
	public List<Cargo> listaTodos() {
		return getSession().createQuery("from Cargo").list();
	}

	@Transactional
	public List<Cargo> procuraNomeContendo(String query) {
		return getSession().createQuery("from Cargo where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "descricao"};
	}
	
	@Transactional
	public List<Cargo> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Cargo where lower(nome) like :q").setParameter("q", q).list();
	}

}
