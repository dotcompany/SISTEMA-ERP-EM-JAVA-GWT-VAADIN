package dc.servicos.dao.geral.tabela;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.geral.tabela.SefipCategoriaTrabalho;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;



/**
*
* @author Wesley Jr
*
*/


@Repository
@SuppressWarnings("unchecked")
public class SefipCategoriaTrabalhoDAO extends AbstractCrudDAO<SefipCategoriaTrabalho>{

	@Override
	public Class<SefipCategoriaTrabalho> getEntityClass() {
		return SefipCategoriaTrabalho.class;
	}
	
	@Transactional
	public List<SefipCategoriaTrabalho> listaTodos() {
		return getSession().createQuery("from SefipCategoriaTrabalho").list();
	}

	@Transactional
	public List<SefipCategoriaTrabalho> procuraNomeContendo(String query) {
		return getSession().createQuery("from SefipCategoriaTrabalho where nome like :q").setParameter("q", "%" + query + "%").list();
	}
	
	protected String[] getDefaultSearchFields() {
		return new String[] {"codigo","nome"};
	}

}
