package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.tabela.CfopEntity;
import dc.entidade.tributario.OperacaoFiscalEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.geral.tabela.CfopDAO;
import dc.servicos.dao.tributario.OperacaoFiscalDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.OperacaoFiscalFormView;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class OperacaoFiscalFormController extends
		CRUDFormController<OperacaoFiscalEntity> {

	OperacaoFiscalFormView subView;

	@Autowired
	OperacaoFiscalDAO dao;

	OperacaoFiscalEntity currentBean;

	@Autowired
	CfopDAO cfopDAO;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Override
	public String getViewIdentifier() {
		return "operacaoFiscalForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new OperacaoFiscalEntity();
	}

	@Override
	protected void initSubView() {
		subView = new OperacaoFiscalFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.getCfop().setValue(cfopDAO.find(currentBean.getCfop()));
		subView.getDescricao().setValue(currentBean.getDescricao());
		subView.getDescricaoNaNf().setValue(currentBean.getDescricaoNaNf());
		subView.getObservacao().setValue(currentBean.getObservacao());
	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			CfopEntity cfop = (CfopEntity) subView.getCfop().getValue();

			if (cfop == null) {
				throw new ErroValidacaoException("Informe o Campo CFOP!");
			}

			String descricao = subView.getDescricao().getValue();
			if (!(Validator.validateString(descricao)))
				throw new ErroValidacaoException("Informe o Campo Descrição");

			String descricaoNF = subView.getDescricaoNaNf().getValue();
			if (!(Validator.validateString(descricaoNF)))
				throw new ErroValidacaoException(
						"Informe o Campo Descrição na NF");

			currentBean.setDescricao(descricao);
			currentBean.setDescricaoNaNf(descricaoNF);
			currentBean.setObservacao(subView.getObservacao().getValue());
			currentBean.setCfop(cfop.getId());
			currentBean.setEmpresa(empresaAtual());
			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);
		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			// subView.filContagemEstoqueDetalhesSubForm(currentBean.getContagemDetalhes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Operacao Fiscal";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public BeanItemContainer<CfopEntity> carregarCfop() {
		BeanItemContainer<CfopEntity> container = new BeanItemContainer<>(
				CfopEntity.class);
		for (CfopEntity obj : cfopDAO.listaTodos()) {
			container.addBean(obj);
		}
		return container;
	}

	@Override
	public OperacaoFiscalEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}