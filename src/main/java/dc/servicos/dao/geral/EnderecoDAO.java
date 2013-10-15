
package dc.servicos.dao.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.Endereco;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository
@SuppressWarnings("unchecked")
public class EnderecoDAO extends AbstractCrudDAO<Endereco>{

	@Override
	public Class<Endereco> getEntityClass() {
		return Endereco.class;
	}
	
	@Transactional
	public List<Endereco> listaTodos() {
		return getSession().createQuery("from Endereco").list();
	}
	
	@Transactional
	public List<Endereco> listaPorEmpresa(Empresa empresa) {
		return getSession().createQuery("from Endereco c where c.empresa = :emp ")
				.setParameter("emp", empresa)
				.list();
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[]{"nome", "email"};
	}

}

