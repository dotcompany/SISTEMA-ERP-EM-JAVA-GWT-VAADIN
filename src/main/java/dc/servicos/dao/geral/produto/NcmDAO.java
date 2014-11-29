package dc.servicos.dao.geral.produto;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.produto.NcmEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class NcmDAO extends AbstractCrudDAO<NcmEntity> {

	@Override
	public Class<NcmEntity> getEntityClass() {
		return NcmEntity.class;
	}

	@Transactional
	public List<NcmEntity> listaTodos() {
		return getSession().createQuery("from NCM").list();
	}

	@Transactional
	public List<NcmEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from NCM where descricao like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao" };
	}

}