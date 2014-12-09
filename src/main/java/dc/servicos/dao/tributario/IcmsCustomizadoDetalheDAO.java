package dc.servicos.dao.tributario;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.IcmsCustomizadoEntity;
import dc.entidade.tributario.IcmsCustomizadoDetalheEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class IcmsCustomizadoDetalheDAO extends AbstractCrudDAO<IcmsCustomizadoDetalheEntity> {

	@Override
	public Class<IcmsCustomizadoDetalheEntity> getEntityClass() {
		return IcmsCustomizadoDetalheEntity.class;
	}

//	@Override
//	public ContagemEstoque find(Serializable id) {
//		 ContagemEstoque contagemEstoque = super.find(id);
//		// workaround para lazy initialization exception
//		//contagemEstoque.getContagemDetalhes().size();
//		return contagemEstoque;
//	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"descricao"};
	}
	
	@Transactional
	public List<IcmsCustomizadoDetalheEntity> findByIcms(IcmsCustomizadoEntity icms){
		
		List<IcmsCustomizadoDetalheEntity> lista = null;
		Criteria c = getSession().createCriteria(IcmsCustomizadoDetalheEntity.class);
		c.add(Restrictions.eq("icmsCustomizado", icms));
		lista = c.list();
		return lista;
		
	}

}
 