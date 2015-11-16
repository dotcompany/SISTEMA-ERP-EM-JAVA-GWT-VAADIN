package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.ColaboradorEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoEntity;
import dc.entidade.suprimentos.compra.TipoRequisicaoEntity;
import dc.model.dao.geral.pessoal.IColaboradorDAO;
import dc.model.dao.geral.produto.IProdutoDAO;
import dc.servicos.dao.suprimentos.compra.IRequisicaoDAO;
import dc.servicos.dao.suprimentos.compra.ITipoRequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.RequisicaoCompraFormView;

@Controller
@Scope("prototype")
public class RequisicaoCompraFormController extends
		CRUDFormController<RequisicaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RequisicaoCompraFormView subView;

	@Autowired
	private IRequisicaoDAO requisicaoDAO;

	@Autowired
	private ITipoRequisicaoDAO tipoRequisicaoDAO;

	@Autowired
	private IColaboradorDAO colaboradorDAO;

	@Autowired
	private IProdutoDAO produtoDAO;

	private RequisicaoEntity currentBean;

	@Override
	protected String getNome() {
		return "Requisição de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setTipoRequisicao((TipoRequisicaoEntity) subView
					.getCmbTipoRequisicao().getValue());
			currentBean.setColaborador((ColaboradorEntity) subView
					.getLkpRequisitante().getValue());
			currentBean.setDataRequisicao(subView.getCalDataRequisicao()
					.getValue());
			currentBean.setObservacao(subView.getTxtObservacao().getValue());
			requisicaoDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = requisicaoDAO.find((Integer) id);
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getCmbTipoRequisicao().select(currentBean.getTipoRequisicao());
		subView.getLkpRequisitante().select(currentBean.getColaborador());
		subView.getCalDataRequisicao()
				.setValue(currentBean.getDataRequisicao());
		subView.getTxtObservacao().setValue(currentBean.getObservacao());

		subView.fillRequisicaoDetalhesSubForm(currentBean
				.getRequisicaoDetalhes());
	}

	@Override
	protected void initSubView() {
		subView = new RequisicaoCompraFormView(this);
		subView.fillCmbTipoRequisicao(tipoRequisicaoDAO
				.getAll(TipoRequisicaoEntity.class));
		subView.fillCmbRequisitante(colaboradorDAO.getAll(ColaboradorEntity.class));
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new RequisicaoEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		requisicaoDAO.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.fillRequisicaoDetalhesSubForm(currentBean
				.getRequisicaoDetalhes());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public RequisicaoDetalheEntity novoRequisicaoDetalhe() {
		RequisicaoDetalheEntity requisicaoDetalhe = new RequisicaoDetalheEntity();
		currentBean.addRequisicaoDetalhe(requisicaoDetalhe);

		return requisicaoDetalhe;
	}

	public List<ProdutoEntity> buscarProdutos() {
		return produtoDAO.getAll(ProdutoEntity.class);
	}

	public void removerRequisicaoDetalhes(
			List<RequisicaoDetalheEntity> requisicaoDetalhes) {
		for (RequisicaoDetalheEntity requisicaoDetalhe : requisicaoDetalhes) {
			currentBean.removeRequisicaoDetalhe(requisicaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public RequisicaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}