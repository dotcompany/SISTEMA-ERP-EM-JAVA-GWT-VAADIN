package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.DependenteEntity;
import dc.entidade.empresa.SocioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class DependenteDAO extends AbstractCrudDAO<DependenteEntity> {


	@Override
	public Class<DependenteEntity> getEntityClass() {
		return DependenteEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"numero"};
	}

	@Transactional
	public List<DependenteEntity> findBySocio(SocioEntity socio){

		List<DependenteEntity> lista = null;

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


