package dc.servicos.dao.suprimentos.compra;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.RequisicaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraRequisicaoDAO")
public class RequisicaoDAO extends AbstractCrudDAO<RequisicaoEntity> implements IRequisicaoDAO {

	@Override
	public Class<RequisicaoEntity> getEntityClass() {
		return RequisicaoEntity.class;
	}

	@Override
	public RequisicaoEntity find(Serializable id) {
		RequisicaoEntity requisicao = super.find(id);
		// workaround para lazy initialization exception
		requisicao.getRequisicaoDetalhes().size();

		return requisicao;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "dataRequisicao", "observacao", "colaborador","tipoRequisicao" };
	}

}