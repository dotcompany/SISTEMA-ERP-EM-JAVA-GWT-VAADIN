package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.compra.Cotacao;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class CotacaoListController extends CRUDListController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private CotacaoDAO dao;

	@Autowired
	private CotacaoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "id", "dataCotacao", "descricao", "situacao" };
	}

	@Override
	protected String getTitulo() {
		return "cotação de Compra";
	}

	@Override
	protected List<Cotacao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<Cotacao> getFormController() {
		return formController;
	}

	@Override
	public Class<? super Cotacao> getEntityClass() {
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