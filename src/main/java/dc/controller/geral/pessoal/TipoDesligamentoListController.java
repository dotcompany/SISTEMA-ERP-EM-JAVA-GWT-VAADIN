package dc.controller.geral.pessoal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.TipoDesligamentoEntity;
import dc.servicos.dao.geral.pessoal.ITipoDesligamentoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Controller
@Scope("prototype")
public class TipoDesligamentoListController extends
		CRUDListController<TipoDesligamentoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITipoDesligamentoDAO dao;

	@Autowired
	TipoDesligamentoFormController tipoDesligamentoFormController;

	@Override
	protected CRUDFormController<TipoDesligamentoEntity> getFormController() {
		return tipoDesligamentoFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "descricao" };
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public Class<? super TipoDesligamentoEntity> getEntityClass() {
		return TipoDesligamentoEntity.class;
	}

	@Override
	protected List<TipoDesligamentoEntity> pesquisa(String valor) {
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
	protected List<TipoDesligamentoEntity> pesquisaDefault() {
		return (List<TipoDesligamentoEntity>) dao.getAll(getEntityClass());
	}

}