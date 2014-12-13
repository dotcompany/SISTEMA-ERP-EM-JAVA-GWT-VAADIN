package dc.controller.geral.pessoal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.PessoaEntity;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
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
	private PessoaFormController pessoaFormController;

	@Autowired
	private PessoaDAO dao;

	@Override
	protected CRUDFormController<PessoaEntity> getFormController() {
		return pessoaFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "tipoPessoa", "email", "site" };
	}

	@Override
	protected String getTitulo() {
		return super.getTitulo(this);
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super PessoaEntity> getEntityClass() {
		return PessoaEntity.class;
	}

	@Override
	protected List<PessoaEntity> pesquisa(String valor) {
		try {
			return (List<PessoaEntity>) dao.fullTextSearch(valor);
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PessoaEntity>();
		}
	}

	@Override
	protected List<PessoaEntity> pesquisaDefault() {
		try {
			return (List<PessoaEntity>) dao.getAll(getEntityClass());
		} catch (Exception e) {
			e.printStackTrace();

			return new ArrayList<PessoaEntity>();
		}
	}

	@Override
	protected boolean deletaEmCascata() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();
	}

}