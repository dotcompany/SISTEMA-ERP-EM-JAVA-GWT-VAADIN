package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.Colaborador;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ColaboradorListController extends CRUDListController<Colaborador> {

	@Autowired
	ColaboradorDAO dao;

	@Autowired
	ColaboradorFormController colaboradorFormController;

	@Override
	protected CRUDFormController<Colaborador> getFormController() {
		return colaboradorFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "codigoTurmaPonto", "dataCadastro", "dataAdmissao", "vencimentoFerias", "dataTransferencia" };
	}

	@Override
	public String getViewIdentifier() {
		return "listaColaborador";
	}

	@Override
	public Class<? super Colaborador> getEntityClass() {
		return Colaborador.class;
	}

	@Override
	protected List<Colaborador> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected String getTitulo() {
		return "Colaborador";
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
	protected List<Colaborador> pesquisaDefault() {
		return (List<Colaborador>) dao.getAll(getEntityClass());
	}

}
