package dc.servicos.dao.diversos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.Pais;
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
public class PaisDAO extends AbstractCrudDAO<Pais>{

	@Override
	protected Class<Pais> getEntityClass() {
		return Pais.class;
	}

	@Transactional
	public List<Pais> listaTodos() {
		return getSession().createQuery("from Pais").list();
	}

	@Transactional
	public List<Pais> procuraNomeContendo(String query) {
		return getSession().createQuery("from Pais where nomeEn like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nomeEn", "nomePtbr","sigla2","sigla3"};
	}
	
	@Transactional
	public List<Pais> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Pais where lower(nomeEn) like :q").setParameter("q", q).list();
	}

}
