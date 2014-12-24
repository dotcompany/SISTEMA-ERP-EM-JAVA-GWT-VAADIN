package dc.controller.financeiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.AgenciaBancoEntity;
import dc.servicos.dao.financeiro.AgenciaBancoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class AgenciaBancoListController extends
		CRUDListController<AgenciaBancoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private AgenciaBancoDAO dao;

	@Autowired
	private AgenciaBancoFormController agenciaBancoFormController;

	@Override
	public String[] getColunas() {
		return new String[] { "nome", "logradouro" };
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
	public Class<? super AgenciaBancoEntity> getEntityClass() {
		return AgenciaBancoEntity.class;
	}

	@Override
	protected CRUDFormController<AgenciaBancoEntity> getFormController() {
		return agenciaBancoFormController;
	}

	@Override
	protected List<AgenciaBancoEntity> pesquisa(String valor) {
		return dao.fullTextSearch(valor);
	}

	@Override
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<AgenciaBancoEntity> pesquisaDefault() {
		return (List<AgenciaBancoEntity>) dao.getAll(getEntityClass());
	}

	@Override
	protected void actionRemoverSelecionados() {
		super.actionRemoverSelecionados();

	}

}