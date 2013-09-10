package dc.servicos.dao.diversos;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.diversos.OperadoraCartao;
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
public class OperadoraCartaoDAO extends AbstractCrudDAO<OperadoraCartao>{

	@Override
	protected Class<OperadoraCartao> getEntityClass() {
		return OperadoraCartao.class;
	}

	@Transactional
	public List<OperadoraCartao> listaTodos() {
		return getSession().createQuery("from OperadoraCartao").list();
	}

	@Transactional
	public List<OperadoraCartao> procuraNomeContendo(String query) {
		return getSession().createQuery("from OperadoraCartao where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"nome", "bandeira"};
	}
}
