package dc.servicos.dao.ged;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.ged.Documento;
import dc.entidade.ged.DocumentoArquivo;
import dc.entidade.ged.VersaoDocumento;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DocumentoDAO extends AbstractCrudDAO<Documento>{

	@Override
	protected Class<Documento> getEntityClass() {
		return Documento.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[]{"nome", "descricao", "palavraChave"};
	}

	@Transactional
	public  void saveOrUpdate(VersaoDocumento versao) {
		sessionFactory.getCurrentSession().saveOrUpdate(versao);
	}
	
	@Transactional
	public Integer nextVersionNumber(Documento documento)
	{
		Criteria criteria = sessionFactory.getCurrentSession()
			    .createCriteria(VersaoDocumento.class)
			    .add(Restrictions.eq("documento", documento))
			    .setProjection(Projections.max("versao"));
			Integer maxVersao = (Integer)criteria.uniqueResult();
			
		return ++maxVersao;	
	}
	
	@Transactional
	public void deleteDocumentoArquivo(DocumentoArquivo documentoArquivo)
	{
		sessionFactory.getCurrentSession().delete(documentoArquivo);
		
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	@Override
	@Transactional
	public <Documento> List<Documento> getAll(Class<Documento> type) {
		final Session session = sessionFactory.getCurrentSession();
		final Criteria crit = session.createCriteria(type);
		crit.add(Restrictions.isNull("dataExclusao"));
		return crit.list();
	}
}
