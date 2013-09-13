package dc.controller.ponto;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.framework.Empresa;
import dc.entidade.geral.Usuario;
import dc.entidade.ponto.PontoClassificacaoJornada;
import dc.servicos.dao.ponto.PontoClassificacaoJornadaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ponto.PontoClassificacaoJornadaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PontoClassificacaoJornadaFormController extends CRUDFormController<PontoClassificacaoJornada> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PontoClassificacaoJornadaFormView subView;

	@Autowired
	private PontoClassificacaoJornadaDAO pontoClassificacaoJornadaDAO;

	private PontoClassificacaoJornada currentBean;

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateString(subView.getTxNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxNome(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateNumber(subView.getTxCodigo().getValue())) {
			adicionarErroDeValidacao(subView.getTxCodigo(), "Número inválido");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getCmbDescontarHoras().getValue())) {
			adicionarErroDeValidacao(subView.getCmbDescontarHoras(), "Não pode ficar em branco");
			valido = false;
		}
		
		if (!Validator.validateObject(subView.getCmbPadrao().getValue())) {
			adicionarErroDeValidacao(subView.getCmbPadrao(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PontoClassificacaoJornada();
	}

	@Override
	protected void initSubView() {
		subView = new PontoClassificacaoJornadaFormView();
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = pontoClassificacaoJornadaDAO.find(id);
		subView.preencheForm(currentBean);
	}

	@Override
	protected void actionSalvar() {
		subView.preencheBean(currentBean);
		try {
			Usuario usuario = SecuritySessionProvider.getUsuario();
			Empresa empresa = usuario.getConta().getEmpresa();
			currentBean.setEmpresa(empresa);
			pontoClassificacaoJornadaDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Ponto Classificação Jornada";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		pontoClassificacaoJornadaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();

	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "pontoClassificacaoJornadaFormController";
	}

}
