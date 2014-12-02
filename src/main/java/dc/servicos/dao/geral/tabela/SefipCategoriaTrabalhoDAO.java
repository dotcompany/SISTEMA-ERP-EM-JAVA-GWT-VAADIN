package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SefipCategoriaTrabalhoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SefipCategoriaTrabalhoDAO extends AbstractCrudDAO<SefipCategoriaTrabalhoEntity>{

	@Override
	public Class<SefipCategoriaTrabalhoEntity> getEntityClass() {
		return SefipCategoriaTrabalhoEntity.class;
	}
	
	@Transactional
	public List<SefipCategoriaTrabalhoEntity> listaTodos() {
		return getSession().createQuery("from SefipCategoriaTrabalho").list();
	}

	@Transactional
	public List<SefipCategoriaTrabalhoEntity> procuraNomeContendo(String query) {
		return getSession().createQuery("from SefipCategoriaTrabalho where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","nome"};
	}

}
