package dc.servicos.dao.framework.geral;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;

@Repository
public class EmpresaSeguimentoDAO extends AbstractCrudDAO<EmpresaSeguimento> {

	@Override
	public Class<EmpresaSeguimento> getEntityClass() {
		return EmpresaSeguimento.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {
		return new String[] { "nome", "descricao" };
	}

	@Transactional
	public List<EmpresaSeguimento> listaPorEmpresa(EmpresaEntity empresa) {
		return getSession()
				.createQuery("from EmpresaSeguimento e where e.empresa = :emp")
				.setParameter("emp", empresa).list();
	}

}