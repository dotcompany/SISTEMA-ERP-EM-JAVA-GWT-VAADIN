package dc.controller.suprimentos.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.contratos.Contrato;
import dc.servicos.dao.contratos.ContratoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ContratoListController extends CRUDListController<Contrato> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ContratoDAO dao;

	@Autowired
	ContratoFormController contratoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "numero", "nome", "dataCadastro", "observacao" };
	}

	@Override
	public Class<? super Contrato> getEntityClass() {
		return Contrato.class;
	}

	@Override
	protected String getTitulo() {
		return "Contrato";
	}

	@Override
	protected List<Contrato> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<Contrato> getFormController() {
		return contratoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<Contrato> pesquisaDefault() {
		return (List<Contrato>) dao.getAll(getEntityClass());
	}

}