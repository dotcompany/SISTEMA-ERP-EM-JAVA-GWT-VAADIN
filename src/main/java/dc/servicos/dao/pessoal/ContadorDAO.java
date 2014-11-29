package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.ContadorEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalContadorDAO")
public class ContadorDAO extends AbstractCrudDAO<ContadorEntity> {

	@Override
	public Class<ContadorEntity> getEntityClass() {
		return ContadorEntity.class;
	}

	@Transactional
	public List<ContadorEntity> listaTodos() {
		return getSession().createQuery("from Contador").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "" };
	}

}