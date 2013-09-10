package dc.controller.folhapagamento.inss;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.entidade.folhapagamento.inss.InssEntity;
import dc.entidade.framework.Empresa;
import dc.servicos.dao.folhapagamento.inss.InssDAO;
import dc.visao.folhapagamento.inss.InssFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class InssFormController extends CRUDFormController<InssEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InssFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private InssDAO pDAO;

	/**
	 * ENTITIES
	 */

	private InssEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public InssFormController() {
		if (this.pEntity == null) {
			this.pEntity = new InssEntity();
		}
	}

	@Override
	protected String getNome() {
		return "INSS";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String competencia = this.subView.getTfCompetencia().getValue();

			this.pEntity.setCompetencia(competencia);

			/**
			 * Empresa vinda da conta do usuário logado
			 */

			Empresa empresa = SecuritySessionProvider.getUsuario().getConta()
					.getEmpresa();

			this.pEntity.setEmpresa(empresa);

			/**
			 * **************************************
			 */

			this.pDAO.saveOrUpdate(this.pEntity);

			mensagemSalvoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			this.pEntity = new InssEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.pEntity = this.pDAO.find(id);

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa a��o aqui. Ou então deixar em branco, para comportamento padr�o
	 */
	@Override
	protected void quandoNovo() {
		try {
			this.pEntity = new InssEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new InssFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			this.pEntity = new InssEntity();

			this.subView.getTfCompetencia().setValue(
					this.pEntity.getCompetencia());
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_inss_fc";
	}

	/**
	 * COMBOS
	 */

	/**
	 * **************************************
	 */

	@Override
	protected boolean isFullSized() {
		return true;
	}

}