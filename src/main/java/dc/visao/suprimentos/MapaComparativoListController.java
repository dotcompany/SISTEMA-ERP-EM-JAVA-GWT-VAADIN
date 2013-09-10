package dc.visao.suprimentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.entidade.suprimentos.Cotacao;
import dc.servicos.dao.suprimentos.CotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class MapaComparativoListController extends CRUDListController<Cotacao> {

	@Autowired
	CotacaoDAO dao;
	
	@Autowired
	MapaComparativoFormController formController;
	
	@Override
	protected String[] getColunas() {
		return new String[] {"dataCotacao", "descricao", "situacao"};
	}

	@Override
	protected String getTitulo() {
		return "Mapa Comparativo";
	}

	@Override
	protected List<Cotacao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	
	@Override
	public String getViewIdentifier() {
		return "listaCotacao";
	}

	@Override
	protected CRUDFormController<Cotacao> getFormController() {
		return formController;
	}

	@Override
	protected Class<? super Cotacao> getEntityClass() {
		return Cotacao.class;
	}

	@Override
	protected List<Cotacao> pesquisaDefault() {
		return dao.getAll(Cotacao.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}


}
