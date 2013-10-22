package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.QuadroSocietario;
import dc.entidade.geral.Pessoa;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class QuadroSocietarioDAO extends AbstractCrudDAO<QuadroSocietario> {

	

	@Override
	public Class<QuadroSocietario> getEntityClass() {
		return QuadroSocietario.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"dataRegistro"};
	}
	
	@Transactional
	public List<QuadroSocietario> listaTodos() {
		return getSession().createQuery("from QuadroSocietario").list();
	}
	
}
