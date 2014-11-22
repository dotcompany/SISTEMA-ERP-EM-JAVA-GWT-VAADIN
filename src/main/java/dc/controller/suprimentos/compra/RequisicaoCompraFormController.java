package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.pessoal.Colaborador;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.Requisicao;
import dc.entidade.suprimentos.RequisicaoDetalhe;
import dc.entidade.suprimentos.TipoRequisicao;
import dc.servicos.dao.pessoal.ColaboradorDAO;
import dc.servicos.dao.produto.ProdutoDAO;
import dc.servicos.dao.suprimentos.RequisicaoDAO;
import dc.servicos.dao.suprimentos.TipoRequisicaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.RequisicaoCompraFormView;

@Controller
@Scope("prototype")
public class RequisicaoCompraFormController extends
		CRUDFormController<Requisicao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RequisicaoCompraFormView subView;

	@Autowired
	RequisicaoDAO requisicaoDAO;

	@Autowired
	TipoRequisicaoDAO tipoRequisicaoDAO;

	@Autowired
	ColaboradorDAO colaboradorDAO;

	@Autowired
	ProdutoDAO produtoDAO;

	private Requisicao currentBean;

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
			currentBean.setTipoRequisicao((TipoRequisicao) subView
					.getCmbTipoRequisicao().getValue());
			currentBean.setColaborador((Colaborador) subView
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
				.getAll(TipoRequisicao.class));
		subView.fillCmbRequisitante(colaboradorDAO.getAll(Colaborador.class));
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Requisicao();
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

	public RequisicaoDetalhe novoRequisicaoDetalhe() {
		RequisicaoDetalhe requisicaoDetalhe = new RequisicaoDetalhe();
		currentBean.addRequisicaoDetalhe(requisicaoDetalhe);

		return requisicaoDetalhe;
	}

	public List<Produto> buscarProdutos() {
		return produtoDAO.getAll(Produto.class);
	}

	public void removerRequisicaoDetalhes(
			List<RequisicaoDetalhe> requisicaoDetalhes) {
		for (RequisicaoDetalhe requisicaoDetalhe : requisicaoDetalhes) {
			currentBean.removeRequisicaoDetalhe(requisicaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	@Override
	public String getViewIdentifier() {
		return "requisicaoCompraForm";
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public Requisicao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}