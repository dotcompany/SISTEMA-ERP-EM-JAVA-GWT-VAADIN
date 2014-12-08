package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.EmpresaEntity;
import dc.entidade.geral.PessoaContatoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class PessoaContatoDAO extends AbstractCrudDAO<PessoaContatoEntity>{

	@Override
	public Class<PessoaContatoEntity> getEntityClass() {
		return PessoaContatoEntity.class;
	}
	
	@Transactional
	public List<PessoaContatoEntity> listaTodos() {
		return getSession().createQuery("from PessoaContato").list();
	}
	
	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome", "email"};
	}

}
