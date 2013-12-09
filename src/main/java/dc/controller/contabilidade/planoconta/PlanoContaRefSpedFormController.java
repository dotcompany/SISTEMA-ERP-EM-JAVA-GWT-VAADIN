package dc.controller.contabilidade.planoconta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.control.validator.Validator;
import dc.entidade.contabilidade.planoconta.PlanoContaRefSpedEntity;
import dc.servicos.dao.contabilidade.planoconta.PlanoContaRefSpedDAO;
import dc.visao.contabilidade.planoconta.PlanoContaRefSpedFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller(value = "contabilidadePlanoContaRefSpedFormController")
@Scope("prototype")
public class PlanoContaRefSpedFormController extends
		CRUDFormController<PlanoContaRefSpedEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PlanoContaRefSpedFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private PlanoContaRefSpedDAO pDAO;

	/**
	 * ENTITIES
	 */

	private PlanoContaRefSpedEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public PlanoContaRefSpedFormController() {
		if (this.pEntity == null) {
			this.pEntity = new PlanoContaRefSpedEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Plano conta - SPED";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String codCtaRef = this.subView.getTfCodCtaRef().getValue();
			String descricao = this.subView.getTfDescricao().getValue();
			String orientacoes = this.subView.getTfOrientacoes().getValue();
			Date inicioValidade = this.subView.getPdfInicioValidade()
					.getValue();
			Date fimValidade = this.subView.getPdfFimValidade().getValue();
			String tipo = this.subView.getTfTipo().getValue();

			this.pEntity.setCodCtaRef(codCtaRef);
			this.pEntity.setDescricao(descricao);
			this.pEntity.setOrientacoes(orientacoes);
			this.pEntity.setInicioValidade(inicioValidade);
			this.pEntity.setFimValidade(fimValidade);
			this.pEntity.setTipo(tipo);

			this.pDAO.saveOrUpdate(this.pEntity);

			notifiyFrameworkSaveOK(this.pEntity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			novoObjeto(0);
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			novoObjeto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		this.subView = new PlanoContaRefSpedFormView(this);
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {
		Object inicioValidade = this.subView.getPdfInicioValidade().getValue();

		if (!Validator.validateNotRequiredDate(inicioValidade)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfInicioValidade(), msg);

			return false;
		}

		Object fimValidade = this.subView.getPdfFimValidade().getValue();

		if (!Validator.validateNotRequiredDate(fimValidade)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getPdfFimValidade(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getViewIdentifier() {
		String sUrl = ClasseUtil.getUrl(this);

		return sUrl;
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

	/**
	 * **************************************
	 */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new PlanoContaRefSpedEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfCodCtaRef().setValue(this.pEntity.getCodCtaRef());
			this.subView.getTfDescricao().setValue(this.pEntity.getDescricao());
			this.subView.getTfOrientacoes().setValue(
					this.pEntity.getOrientacoes());
			this.subView.getPdfInicioValidade().setValue(
					this.pEntity.getInicioValidade());
			this.subView.getPdfFimValidade().setValue(
					this.pEntity.getFimValidade());
			this.subView.getTfTipo().setValue(this.pEntity.getTipo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}