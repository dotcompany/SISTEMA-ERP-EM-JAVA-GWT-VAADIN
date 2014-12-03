package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.SindicatoEntity;
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
public class SindicatoDAO extends AbstractCrudDAO<SindicatoEntity>{

	@Override
	public Class<SindicatoEntity> getEntityClass() {
		return SindicatoEntity.class;
	}

	@Transactional
	public List<SindicatoEntity> listaTodos() {
		return getSession().createQuery("from Sindicato").list();
	}

	@Transactional
	public List<SindicatoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from Sindicato where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome","logradouro"};
	}
	
	@Transactional
	public List<SindicatoEntity> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from Sindicato where lower(nome) like :q").setParameter("q", q).list();
	}

}
