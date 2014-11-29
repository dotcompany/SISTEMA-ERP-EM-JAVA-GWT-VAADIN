package dc.controller.suprimentos.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.FornecedorEntity;
import dc.entidade.suprimentos.compra.Cotacao;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.geral.FornecedorDAO;
import dc.servicos.dao.suprimentos.RequisicaoDetalheDAO;
import dc.servicos.dao.suprimentos.compra.CotacaoDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.compra.CotacaoFormView;

@Controller
@Scope("prototype")
public class CotacaoFormController extends CRUDFormController<Cotacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CotacaoFormView subView;

	@Autowired
	private CotacaoDAO cotacaoDao;

	@Autowired
	private FornecedorDAO fornecedorDao;

	@Autowired
	private RequisicaoDetalheDAO requisicaoDetalheDao;

	private Cotacao currentBean;

	@Override
	protected String getNome() {
		return "cotação de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setDescricao(subView.getTxtDescricao().getValue());
			currentBean.setDataCotacao(subView.getCalDataCotacao().getValue());
			currentBean.setSituacao("A");
			cotacaoDao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = cotacaoDao.find(id);
		subView.getLblId().setValue(String.valueOf(currentBean.getId()));
		subView.getTxtDescricao().setValue(currentBean.getDescricao());
		subView.getCalDataCotacao().setValue(currentBean.getDataCotacao());

		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
		subView.fillReqCotacaoDetalhesSubForm(currentBean
				.getCompraReqCotacaoDetalhes());
	}

	@Override
	protected void initSubView() {
		subView = new CotacaoFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Cotacao();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		cotacaoDao.deleteAllByIds(ids);

		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
		subView.fillCompraFornecedorCotacoesSubForm(currentBean
				.getCompraFornecedorCotacaos());
		subView.fillReqCotacaoDetalhesSubForm(currentBean
				.getCompraReqCotacaoDetalhes());
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		remover(ids);
	}

	public List<RequisicaoDetalheEntity> buscarRequisicaoProdutos() {
		return requisicaoDetalheDao.getAll(RequisicaoDetalheEntity.class);
	}

	public ReqCotacaoDetalheEntity novoRequisicaoCotacaoDetalhe() {
		ReqCotacaoDetalheEntity cotacaoDetalhe = new ReqCotacaoDetalheEntity();
		currentBean.getCompraReqCotacaoDetalhes().add(cotacaoDetalhe);

		return cotacaoDetalhe;
	}

	public void removerRequisicaoCotacaoDetalhes(
			List<ReqCotacaoDetalheEntity> values) {
		for (ReqCotacaoDetalheEntity requisicaoCotacaoDetalhe : values) {
			currentBean.getCompraReqCotacaoDetalhes().remove(
					requisicaoCotacaoDetalhe);
		}

		mensagemRemovidoOK();
	}

	public List<FornecedorEntity> buscarFornecedores() {
		return fornecedorDao.getAll(FornecedorEntity.class);
	}

	public FornecedorCotacaoEntity novoFornecedorCotacao() {
		FornecedorCotacaoEntity fornecedorCotacao = new FornecedorCotacaoEntity();
		currentBean.getCompraFornecedorCotacaos().add(fornecedorCotacao);

		return fornecedorCotacao;
	}

	public void removerFornecedorCotacaos(List<FornecedorCotacaoEntity> values) {
		for (FornecedorCotacaoEntity fornecedorCotacao : values) {
			currentBean.getCompraFornecedorCotacaos().remove(fornecedorCotacao);
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
	public Cotacao getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}