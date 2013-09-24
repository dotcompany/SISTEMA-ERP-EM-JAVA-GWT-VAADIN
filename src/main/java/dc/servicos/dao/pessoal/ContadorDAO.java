package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.Contador;
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
public class ContadorDAO extends AbstractCrudDAO<Contador>{

	@Override
	public Class<Contador> getEntityClass() {
		return Contador.class;
	}
	
	@Transactional
	public List<Contador> listaTodos() {
		return getSession().createQuery("from Contador").list();
	}

	@Transactional
	public List<Contador> procuraNomeContendo(String query) {
		return getSession().createQuery("from Contador where logradouro like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"logradouro","complemento", "bairro","cep","municipioIBGE","fax","telefone"};
	}

}
