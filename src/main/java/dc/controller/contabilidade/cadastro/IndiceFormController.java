package dc.controller.contabilidade.cadastro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.controller.financeiro.IndiceEconomicoListController;
import dc.entidade.contabilidade.cadastro.IndiceEntity;
import dc.entidade.financeiro.IndiceEconomicoEntity;
import dc.servicos.dao.contabilidade.cadastro.IndiceDAO;
import dc.servicos.dao.financeiro.IndiceEconomicoDAO;
import dc.visao.contabilidade.cadastro.IndiceFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 * 
 * @author Gutemberg A. Da Silva
 * 
 */

@Controller
@Scope("prototype")
public class IndiceFormController extends CRUDFormController<IndiceEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IndiceFormView subView;

	/**
	 * DAO'S
	 */

	@Autowired
	private IndiceDAO pDAO;

	@Autowired
	private IndiceEconomicoDAO ieDAO;

	/**
	 * ENTITIES
	 */

	private IndiceEntity pEntity;

	/**
	 * CONSTRUTOR
	 */

	public IndiceFormController() {
		if (this.pEntity == null) {
			this.pEntity = new IndiceEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Índice";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			String periocidade = this.subView.getTfPeriodicidade().getValue();
			Date diarioPartirDe = this.subView.getPdfDiarioPartirDe()
					.getValue();
			String mensalMesAno = this.subView.getTfMensalMesAno().getValue();

			this.pEntity.setPeriodicidade(periocidade);
			this.pEntity.setDiarioPartirDe(diarioPartirDe);
			this.pEntity.setMensalMesAno(mensalMesAno);

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
		this.subView = new IndiceFormView(this);

		popularCombo();
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

	private void popularCombo() {
		try {
			DefaultManyToOneComboModel<IndiceEconomicoEntity> model = new DefaultManyToOneComboModel<IndiceEconomicoEntity>(
					IndiceEconomicoListController.class, this.ieDAO,
					super.getMainController());

			this.subView.getCbIndiceEconomico().setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
				this.pEntity = new IndiceEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfPeriodicidade().setValue(
					this.pEntity.getPeriodicidade());
			this.subView.getPdfDiarioPartirDe().setValue(
					this.pEntity.getDiarioPartirDe());
			this.subView.getTfMensalMesAno().setValue(
					this.pEntity.getMensalMesAno());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}