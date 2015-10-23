package dc.servicos.dao.suprimentos.compra;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import dc.entidade.suprimentos.compra.CotacaoEntity;
import dc.servicos.dao.framework.geral.AbstractCrudDAO;

@Repository("suprimentosCompraCotacaoDAO")
public class CotacaoDAO extends AbstractCrudDAO<CotacaoEntity> {

	@Override
	public Class<CotacaoEntity> getEntityClass() {
		return CotacaoEntity.class;
	}

	@Override
	public CotacaoEntity find(Serializable id) {
		CotacaoEntity cotacao = super.find(id);
		// workaround para lazy initialization exception
		cotacao.getCompraFornecedorCotacaos().size();
		cotacao.getCompraReqCotacaoDetalhes().size();

		return cotacao;
	}

	public String[] getDefaultSearchFields() {
		return new String[] { "codigo", "descricao", "nome" };
	}

}