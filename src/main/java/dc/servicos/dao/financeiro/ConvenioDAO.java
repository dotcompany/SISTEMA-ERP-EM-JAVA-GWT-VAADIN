package dc.servicos.dao.financeiro;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.financeiro.Convenio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

/**
*
* @author Wesley Jr
/*
 *Nessa classe temos a Extensão a classe principal abstractCrudDao e dela herdamos
 *alguns métodos, fazemos uma Conexão com o Banco, uma listagem
 *E aqui herdamos também o método do pesquisar, onde nela colocamos os campos
 *que colocamos as anotações lá no TO (ENTIDADE), que vai ser pesquisado na Tela
 *quando rodar o projeto.
 *
*/


@Repository
@SuppressWarnings("unchecked")
public class ConvenioDAO extends AbstractCrudDAO<Convenio>{

	@Override
	protected Class<Convenio> getEntityClass() {
		return Convenio.class;
	}
	
	
	@Transactional
	public List<Convenio> listaTodos() {
		return getSession().createQuery("from Convenio").list();
	}

	@Transactional
	public List<Convenio> procuraNomeContendo(String query) {
		return getSession().createQuery("from Convenio where logradouro like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"logradouro", "bairro"};
	}

}
