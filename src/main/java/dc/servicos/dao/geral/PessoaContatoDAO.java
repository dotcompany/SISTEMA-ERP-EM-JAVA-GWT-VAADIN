package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.PessoaContato;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaContatoDAO extends AbstractCrudDAO<PessoaContato>{

	@Override
	public Class<PessoaContato> getEntityClass() {
		return PessoaContato.class;
	}
	
	@Transactional
	public List<PessoaContato> listaTodos() {
		return getSession().createQuery("from PessoaContato").list();
	}
	
	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome", "email"};
	}

}
