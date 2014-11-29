package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.servicos.dao.geral.pessoal.ColaboradorDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class ColaboradorListController extends
		CRUDListController<ColaboradorEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return new String[] { "codigoTurmaPonto", "dataCadastro",
				"dataAdmissao", "vencimentoFerias", "dataTransferencia" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
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
		return super.getTitulo(this);
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