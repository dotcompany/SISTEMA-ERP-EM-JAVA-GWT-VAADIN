package dc.controller.suprimento.compra;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.geral.pessoal.FornecedorEntity;
import dc.entidade.suprimentos.compra.CotacaoEntity;
import dc.entidade.suprimentos.compra.FornecedorCotacaoEntity;
import dc.entidade.suprimentos.compra.ReqCotacaoDetalheEntity;
import dc.entidade.suprimentos.compra.RequisicaoDetalheEntity;
import dc.servicos.dao.geral.IFornecedorDAO;
import dc.servicos.dao.suprimentos.compra.ICotacaoDAO;
import dc.servicos.dao.suprimentos.compra.IRequisicaoDetalheDAO;
import dc.visao.framework.DCFieldGroup;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimento.compra.CotacaoFormView;

@Controller
@Scope("prototype")
public class CotacaoFormController extends CRUDFormController<CotacaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CotacaoFormView subView;

	@Autowired
	private ICotacaoDAO cotacaoDao;

	@Autowired
	private IFornecedorDAO fornecedorDao;

	@Autowired
	private IRequisicaoDetalheDAO requisicaoDetalheDao;

	private CotacaoEntity currentBean;

	@Override
	protected String getNome() {
		return "Cotação de Compra";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			cotacaoDao.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		
		try {
			this.currentBean = this.cotacaoDao.find(id);
			
			subView.fillCompraFornecedorCotacoesSubForm(currentBean.getCompraFornecedorCotacaos());
			subView.fillReqCotacaoDetalhesSubForm(currentBean.getCompraReqCotacaoDetalhes());
			fieldGroup.setItemDataSource(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			subView = new CotacaoFormView(this);
			this.fieldGroup = new DCFieldGroup<>(CotacaoEntity.class);
		
     	    fieldGroup.bind(this.subView.getCalDataCotacao(), "dataCotacao");
     	   fieldGroup.bind(this.subView.getTxtDescricao(), "descricao");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			currentBean = new CotacaoEntity();
			fieldGroup.setItemDataSource(this.currentBean);
		}catch(Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.cotacaoDao.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {

			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		for (Serializable id : ids) {
			CotacaoEntity cotacao = (CotacaoEntity) id;

			try {
				cotacaoDao.delete(cotacao);
			} catch (Exception e) {
				e.printStackTrace();
				mensagemErro(e.getMessage());
			}
		}
		
		mensagemRemovidoOK();
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
	public CotacaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}