package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.DependenteEntity;
import dc.entidade.empresa.ParticipacaoSocietariaEntity;
import dc.entidade.empresa.SocioEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ParticipacaoSocietariaDAO extends AbstractCrudDAO<ParticipacaoSocietariaEntity> {


	@Override
	public Class<ParticipacaoSocietariaEntity> getEntityClass() {
		return ParticipacaoSocietariaEntity.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"cnpj"};
	}
	
	@Transactional
	public List<ParticipacaoSocietariaEntity> findBySocio(SocioEntity socio){

		List<ParticipacaoSocietariaEntity> lista = null;

		try{
			if(socio!=null){
				lista = getSession().createQuery("from ParticipacaoSocietaria p where p.socio = :socio")
						.setParameter("socio", socio).list();
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return lista;

	}

	
}



