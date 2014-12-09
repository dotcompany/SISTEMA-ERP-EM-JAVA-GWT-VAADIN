package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.QuadroSocietarioEntity;
import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class QuadroSocietarioDAO extends AbstractCrudDAO<QuadroSocietarioEntity> {

	

	@Override
	public Class<QuadroSocietarioEntity> getEntityClass() {
		return QuadroSocietarioEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
	@Transactional
	public List<QuadroSocietarioEntity> listaTodos() {
		return getSession().createQuery("from QuadroSocietario").list();
	}
	
}
