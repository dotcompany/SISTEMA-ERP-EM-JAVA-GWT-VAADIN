package dc.controller.suprimentos.contrato;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.contrato.ContratoTipoServico;
import dc.servicos.dao.suprimentos.contrato.ContratoTipoServicoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class ContratoTipoServicoListController extends
		CRUDListController<ContratoTipoServico> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	ContratoTipoServicoDAO dao;

	@Autowired
	ContratoTipoServicoFormController tipoContratoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "descricao" };
	}

	@Override
	public Class<? super ContratoTipoServico> getEntityClass() {
		return ContratoTipoServico.class;
	}

	@Override
	protected String getTitulo() {
		return "Tipo serviço";
	}

	@Override
	protected List<ContratoTipoServico> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected CRUDFormController<ContratoTipoServico> getFormController() {
		return tipoContratoFormController;
	}

	// Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<ContratoTipoServico> pesquisaDefault() {
		return (List<ContratoTipoServico>) dao.getAll(getEntityClass());
	}

}