package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.TipoRequisicao;
import dc.servicos.dao.suprimentos.TipoRequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class TipoRequisicaoListController extends
		CRUDListController<TipoRequisicao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	TipoRequisicaoDAO dao;

	@Autowired
	TipoRequisicaoFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "id", "codigo", "nome", "descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Tipo Requisição";
	}

	@Override
	protected List<TipoRequisicao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<TipoRequisicao> getFormController() {
		return formController;
	}

	@Override
	public Class<? super TipoRequisicao> getEntityClass() {
		return TipoRequisicao.class;
	}

	@Override
	protected List<TipoRequisicao> pesquisaDefault() {
		return dao.getAll(TipoRequisicao.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}