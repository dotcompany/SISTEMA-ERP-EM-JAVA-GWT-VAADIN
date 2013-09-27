package dc.servicos.dao.empresa;

import org.springframework.stereotype.Repository;

import dc.entidade.empresa.Dependente;
import dc.entidade.empresa.ParticipacaoSocietaria;
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
	
}



