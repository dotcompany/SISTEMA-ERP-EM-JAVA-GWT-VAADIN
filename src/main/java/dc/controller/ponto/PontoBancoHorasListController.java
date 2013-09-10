package dc.controller.ponto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.ponto.PontoBancoHoras;
import dc.servicos.dao.ponto.PontoBancoHorasDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;
import dc.visao.ponto.PontoBancoHorasFormView.SituacaoBancoHoras;

@Controller
@Scope("prototype")
@SuppressWarnings("unchecked")
public class PontoBancoHorasListController extends CRUDListController<PontoBancoHoras>{
	@Autowired
	PontoBancoHorasDAO dao;
	
	@Autowired
	PontoBancoHorasFormController pontoBancoHorasFormController;
	

	@Override
	protected String[] getColunas() {
		return new String[] {"colaborador", "dataTrabalho", "quantidade", "situacao"};
	}

	@Override
	protected Class<? super PontoBancoHoras > getEntityClass() {
		return PontoBancoHoras.class;
	}


	@Override
	protected String getTitulo() {
		return "Banco de Horas";
	}

	@Override
	protected List<PontoBancoHoras> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}
	

	@Override
	protected CRUDFormController<PontoBancoHoras> getFormController() {
		return pontoBancoHorasFormController;
	}

	//Identificador da VIEW, para posterior uso nas urls de navegacao
	@Override
	public String getViewIdentifier() {
		return "listaPontoBancoHoras";
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected List<PontoBancoHoras> pesquisaDefault() {
		return ajustaEnums((List<PontoBancoHoras>) dao.getAll(getEntityClass()));
	}

	private List<PontoBancoHoras> ajustaEnums(List<PontoBancoHoras> all) {
		for(PontoBancoHoras pontoBancoHoras : all)
		{
			pontoBancoHoras.setSituacao(SituacaoBancoHoras.getSituacao(pontoBancoHoras.getSituacao()).getLabel());
		}
		return all;
	}
	

}
