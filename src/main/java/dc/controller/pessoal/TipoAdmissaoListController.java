package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.TipoAdmissao;
import dc.servicos.dao.pessoal.TipoAdmissaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;


@Controller
@Scope("prototype")
public class TipoAdmissaoListController extends CRUDListController<TipoAdmissao> {

	@Autowired
	TipoAdmissaoDAO dao;

	@Autowired
	TipoAdmissaoFormController tipoAdmissaoFormController;

	@Override
	protected CRUDFormController<TipoAdmissao> getFormController() {
		return tipoAdmissaoFormController;
	}

	@Override
	protected String[] getColunas() {
		return new String[]{"codigo", "nome", "descricao"};
	}

	@Override
	public String getViewIdentifier() {
		return "listaTipoAdmissao";
	}

	@Override
	protected Class<? super TipoAdmissao> getEntityClass() {
		return TipoAdmissao.class;
	}
	

	@Override
	protected List<TipoAdmissao> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Tipo Admissão";
	}
	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
		
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	
	@Override
	protected List<TipoAdmissao> pesquisaDefault() {
		return (List<TipoAdmissao>) dao.getAll(getEntityClass());
	}

}
