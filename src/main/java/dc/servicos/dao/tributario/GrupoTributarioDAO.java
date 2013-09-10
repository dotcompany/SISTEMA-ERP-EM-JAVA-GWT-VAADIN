package dc.servicos.dao.tributario;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.GrupoTributario;
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
public class GrupoTributarioDAO extends AbstractCrudDAO<GrupoTributario>{

	@Override
	protected Class<GrupoTributario> getEntityClass() {
		return GrupoTributario.class;
	}
	
	@Transactional
	public List<GrupoTributario> listaTodos() {
		return getSession().createQuery("from GrupoTributario").list();
	}

	@Transactional
	public List<GrupoTributario> procuraNomeContendo(String query) {
		return getSession().createQuery("from GrupoTributario where descricao like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"origemMercadoria", "descricao"};
	}
	
	@Transactional
	public List<GrupoTributario> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from GrupoTributario where lower(descricao) like :q").setParameter("q", q).list();
	}

}
