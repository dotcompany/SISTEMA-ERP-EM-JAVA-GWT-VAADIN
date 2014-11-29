package dc.servicos.dao.suprimentos.contrato;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dc.entidade.suprimentos.contrato.ContratoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosContratoContratoDAO")
public class ContratoDAO extends AbstractCrudDAO<ContratoEntity> {

	@Override
	public Class<ContratoEntity> getEntityClass() {
		return ContratoEntity.class;
	}

	@Override
	protected String[] getDefaultSearchFields() {

		return new String[] { "numero", "nome", "descricao", "observacao" };
	}

	@Override
	@Transactional
	public ContratoEntity find(Serializable id) {
		ContratoEntity contrato = super.find(id);
		// workaround para lazy initialization exception
		contrato.getContratosHistoricosFaturamentos();
		contrato.getContratosHistoricosReajustes();
		contrato.getContratosPrevisoesFaturamentos();

		return contrato;
	}

}