package dc.servicos.dao.geral.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.pessoal.ContadorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalContadorDAO")
public class ContadorDAO extends AbstractCrudDAO<ContadorEntity> {

	@Override
	public Class<ContadorEntity> getEntityClass() {
		return ContadorEntity.class;
	}

	@Transactional
	public List<ContadorEntity> listaTodos() {
		return getSession().createQuery("FROM ContadorEntity ent").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "" };
	}

}