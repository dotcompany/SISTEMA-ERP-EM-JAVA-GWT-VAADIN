package dc.controller.suprimento.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dc.control.util.ClassUtils;
import dc.entidade.suprimentos.compra.RequisicaoEntity;
import dc.servicos.dao.suprimentos.compra.RequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.CRUDListController;

@Component
@Scope("prototype")
public class RequisicaoCompraListController extends
		CRUDListController<RequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RequisicaoDAO dao;

	@Autowired
	private RequisicaoCompraFormController requisicaoCompraFormController;

	/**
	 * CONSTRUTOR
	 */

	public RequisicaoCompraListController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected CRUDFormController<RequisicaoEntity> getFormController() {
		return requisicaoCompraFormController;
	}

	@Override
	public String[] getColunas() {
		return new String[] { "id", "dataRequisicao",
				"colaborador.pessoa.nome", "tipoRequisicao.descricao" };
	}

	@Override
	public Class<? super RequisicaoEntity> getEntityClass() {
		return RequisicaoEntity.class;
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
	protected boolean deletaEmCascata() {
		return false;
	}

	@Override
	protected List<RequisicaoEntity> pesquisa(String valor) {
		try {
			List<RequisicaoEntity> auxLista = (List<RequisicaoEntity>) this.dao
					.fullTextSearch(valor);

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected List<RequisicaoEntity> pesquisaDefault() {
		try {
			List<RequisicaoEntity> auxLista = (List<RequisicaoEntity>) this.dao
					.getAll(getEntityClass());

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

}