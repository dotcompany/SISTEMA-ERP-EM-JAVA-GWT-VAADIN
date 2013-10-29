package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.Dependente;
import dc.entidade.empresa.Socio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DependenteDAO extends AbstractCrudDAO<Dependente> {


	@Override
	public Class<Dependente> getEntityClass() {
		return Dependente.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}

	@Transactional
	public List<Dependente> findBySocio(Socio socio){

		List<Dependente> lista = null;

		try{
			if(socio!=null){
				lista = getSession().createQuery("from Dependente d where d.socio = :socio")
						.setParameter("socio", socio).list();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;

	}

}


