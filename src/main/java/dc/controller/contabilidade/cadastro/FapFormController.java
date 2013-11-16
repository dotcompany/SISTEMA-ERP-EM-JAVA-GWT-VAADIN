package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.contabilidade.cadastro.FapEntity;
import dc.servicos.dao.contabilidade.cadastro.FapDAO;
import dc.visao.contabilidade.cadastro.FapFormView;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class FapFormController extends CRUDFormController<FapEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FapFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private FapDAO pDAO;

	/**
	 * ENTITIES
	 */

	private FapEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public FapFormController() {
		if (this.pEntity == null) {
			this.pEntity = new FapEntity();
		}
	}

	@Override
	protected String getNome() {
		return "FAP";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			BigDecimal fap = new BigDecimal(this.subView.getTfFap().getValue());
			Date dataInicial = this.subView.getPdfDataInicial().getValue();
			Date dataFinal = this.subView.getPdfDataFinal().getValue();

			this.pEntity.setFap(fap);
			this.pEntity.setDataInicial(dataInicial);
			this.pEntity.setDataFinal(dataFinal);

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
		this.subView = new FapFormView(this);
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
				this.pEntity = new FapEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfFap().setValue(this.pEntity.getFap().toString());
			this.subView.getPdfDataInicial().setValue(
					this.pEntity.getDataInicial());
			this.subView.getPdfDataFinal()
					.setValue(this.pEntity.getDataFinal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}