package dc.controller.tributario;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.tributario.GrupoTributario;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.tributario.GrupoTributarioFormView;
import dc.visao.tributario.GrupoTributarioFormView.ORIGEM_MERCADORIA;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class GrupoTributarioFormController extends
		CRUDFormController<GrupoTributario> {

	GrupoTributarioFormView subView;

	@Autowired
	GrupoTributarioDAO dao;

	GrupoTributario currentBean;

	String CAMPO_EM_BRANCO = "Não pode ficar em branco";

	@Override
	public String getViewIdentifier() {
		return "grupoTributarioForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new GrupoTributario();
	}

	@Override
	protected void initSubView() {
		subView = new GrupoTributarioFormView(this);
	}

	@Override
	protected void carregar(Serializable id) {
		// TODO Auto-generated method stub
		currentBean = dao.find((Integer) id);
		subView.preencherForm(currentBean);
	}

	public Empresa empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {
		try {
			String descricao = subView.getDescricao().getValue();
			String origem = "";
			String obs = subView.getObservacao().getValue();

			if (!(Validator.validateString(descricao)))
				throw new ErroValidacaoException("Informe o Campo Descrição");

			if (!(Validator.validateObject(subView.getCmbOrigemMercadoria()
					.getValue()))) {
				throw new ErroValidacaoException(
						"Informe o Campo Origem da Mercadoria");
			} else {
				origem = ((ORIGEM_MERCADORIA) (subView.getCmbOrigemMercadoria()
						.getValue())).getCodigo();
			}

			currentBean.setEmpresa(empresaAtual());
			currentBean.setNome(descricao);
			currentBean.setOrigemMercadoria(origem);
			currentBean.setObservacao(obs);
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
		return "Grupo Tributário";
	}

	@Override
	protected void remover(List<Serializable> ids) {

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<GrupoTributario> trazerTodos() {
		return dao.listaTodos();
	}

}