package dc.servicos.dao.pessoal;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.pessoal.TipoAdmissaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("pessoalTipoAdmissaoDAO")
public class TipoAdmissaoDAO extends AbstractCrudDAO<TipoAdmissaoEntity> {

	@Override
	public Class<TipoAdmissaoEntity> getEntityClass() {
		return TipoAdmissaoEntity.class;
	}

	@Transactional
	public List<TipoAdmissaoEntity> listaTodos() {
		return getSession().createQuery("from TipoAdmissao").list();
	}

	@Transactional
	public List<TipoAdmissaoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from TipoAdmissao where nome like :q")
				.setParameter("q", "%" + query + "%").list();
	}

	protected String[] getDefaultSearchFields() {
		return new String[] { "codigo", "nome", "descricao" };
	}

}