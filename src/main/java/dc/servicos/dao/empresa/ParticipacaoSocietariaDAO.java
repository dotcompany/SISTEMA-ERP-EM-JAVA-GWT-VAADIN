package dc.servicos.dao.empresa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.empresa.Dependente;
import dc.entidade.empresa.ParticipacaoSocietaria;
import dc.entidade.empresa.Socio;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
public class ParticipacaoSocietariaDAO extends AbstractCrudDAO<ParticipacaoSocietaria> {


	@Override
	public Class<ParticipacaoSocietaria> getEntityClass() {
		return ParticipacaoSocietaria.class;
	}

	protected String[] getDefaultSearchFields() {
		return new String[] {"cnpj"};
	}
	
	@Transactional
	public List<ParticipacaoSocietaria> findBySocio(Socio socio){

		List<ParticipacaoSocietaria> lista = null;

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



