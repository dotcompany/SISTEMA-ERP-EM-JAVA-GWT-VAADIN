package dc.controller.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.entidade.pessoal.ColaboradorEntity;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ColaboradorListController extends CRUDListController<ColaboradorEntity> {

	@Autowired
	ColaboradorDAO dao;

	@Autowired
	ColaboradorFormController colaboradorFormController;

	@Override
	protected CRUDFormController<ColaboradorEntity> getFormController() {
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
	public Class<? super ColaboradorEntity> getEntityClass() {
		return ColaboradorEntity.class;
	}

	@Override
	protected List<ColaboradorEntity> pesquisa(String valor) {
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
	protected List<ColaboradorEntity> pesquisaDefault() {
		return (List<ColaboradorEntity>) dao.getAll(getEntityClass());
	}

}
