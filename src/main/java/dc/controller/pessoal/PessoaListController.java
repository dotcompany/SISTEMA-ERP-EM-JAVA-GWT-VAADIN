package dc.controller.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClasseUtil;
import dc.entidade.geral.PessoaEntity;
import dc.servicos.business.pessoal.PessoaBusiness;
import dc.servicos.business.pessoal.PessoaBusinessImpl;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class PessoaListController extends CRUDListController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PessoaFormController formController;

	// @Autowired
	// private PessoaDAO dao;

	private PessoaBusiness pessoaBusiness = new PessoaBusinessImpl();

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "tipo", "email", "site" };
	}

	@Override
	protected String getTitulo() {
		return "Pessoa";
	}

	@Override
	protected List<PessoaEntity> pesquisa(String valor) {
		// return dao.fullTextSearch(valor);
		return new ArrayList<PessoaEntity>();
	}

	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
	}

	@Override
	protected CRUDFormController<PessoaEntity> getFormController() {
		return formController;
	}

	@Override
	public Class<? super PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Override
	protected List<PessoaEntity> pesquisaDefault() {
		return new ArrayList<PessoaEntity>();
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

}