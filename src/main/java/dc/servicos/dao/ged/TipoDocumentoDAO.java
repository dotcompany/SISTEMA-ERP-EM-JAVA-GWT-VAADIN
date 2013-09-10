package dc.servicos.dao.ged;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ged.TipoDocumento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class TipoDocumentoDAO extends AbstractCrudDAO<TipoDocumento>{

	@Override
	protected Class<TipoDocumento> getEntityClass() {
		return TipoDocumento.class;
	}
	
	@Transactional
	public List<TipoDocumento> listaTodos() {
		return getSession().createQuery("from TipoDocumento").list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome", "tamanhoMaximo"};
	}
	
	@Transactional
	public List<TipoDocumento> query(String q) {
		q = "%" + q.toLowerCase() +"%";
		return getSession().createQuery("from TipoDocumento where lower(nome) like :q").setParameter("q", q).list();
	}

}
