package dc.servicos.dao.tributario;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.tributario.ICMSCustomizado;
import dc.entidade.tributario.ICMSCustomizadoDetalhe;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
//@SuppressWarnings("unchecked")
public class ICMSCustomizadoDetalheDAO extends AbstractCrudDAO<ICMSCustomizadoDetalhe> {

	@Override
	public Class<ICMSCustomizadoDetalhe> getEntityClass() {
		return ICMSCustomizadoDetalhe.class;
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
	public List<ICMSCustomizadoDetalhe> findByIcms(ICMSCustomizado icms){
		
		List<ICMSCustomizadoDetalhe> lista = null;
		Criteria c = getSession().createCriteria(ICMSCustomizadoDetalhe.class);
		c.add(Restrictions.eq("icmsCustomizado", icms));
		lista = c.list();
		return lista;
		
	}

}
 
