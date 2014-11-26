package dc.controller.suprimentos.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.suprimentos.compra.RequisicaoEntity;
import dc.servicos.dao.suprimentos.compra.RequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoCompraListController extends
		CRUDListController<RequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RequisicaoDAO dao;

	@Autowired
	private RequisicaoCompraFormController formController;

	@Override
	public String[] getColunas() {
		return new String[] { "id", "dataRequisicao",
				"colaborador.pessoa.nome", "tipoRequisicao.descricao" };
	}

	@Override
	protected String getTitulo() {
		return "Requisição de Compra";
	}

	@Override
	protected List<RequisicaoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected CRUDFormController<RequisicaoEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super RequisicaoEntity> getEntityClass() {
		return RequisicaoEntity.class;
	}

	@Override
	protected List<RequisicaoEntity> pesquisaDefault() {
		return dao.getAll(RequisicaoEntity.class);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

}