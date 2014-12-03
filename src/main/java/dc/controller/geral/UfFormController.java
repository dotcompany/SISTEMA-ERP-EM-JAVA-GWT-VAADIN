package dc.controller.geral;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.StringUtils;
import dc.controller.geral.diverso.PaisListController;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.diverso.PaisEntity;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.diverso.PaisDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.UfFormView;

@Controller
@Scope("prototype")
public class UfFormController extends CRUDFormController<UfEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UfFormView subView;

	private UfEntity currentBean;

	@Autowired
	private UfDAO ufDAO;

	@Autowired
	private PaisDAO paisDAO;

	@Override
	protected String getNome() {
		return "UF";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	protected boolean validaSalvar() {
		boolean valido = true;

		PaisEntity pais = this.subView.getMocPais().getValue();

		if (!Validator.validateObject(pais)) {
			adicionarErroDeValidacao(this.subView.getMocPais(),
					"Não pode ficar em branco!");

			valido = false;
		}

		String nome = this.subView.getTfNome().getValue();

		if (StringUtils.isBlank(nome)) {
			adicionarErroDeValidacao(this.subView.getTfNome(),
					"Não pode ficar em branco!");

			valido = false;
		}

		String sigla = this.subView.getTfSigla().getValue();

		if (StringUtils.isBlank(sigla)) {
			adicionarErroDeValidacao(this.subView.getTfSigla(),
					"Não pode ficar em branco!");

			valido = false;
		}

		return valido;
	}

	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTfNome().getValue());
			this.currentBean.setSigla(this.subView.getTfSigla().getValue());

			boolean bCodigoIbge = NumberUtils.isNumber(this.subView
					.getTfCodigoIbge().getValue());

			if (bCodigoIbge) {
				this.currentBean.setCodigoIbge(NumberUtils.toInt(this.subView
						.getTfCodigoIbge().getValue()));
			}

			PaisEntity pais = this.subView.getMocPais().getValue();

			if (pais != null) {
				this.currentBean.setPais(pais);
			}

			this.ufDAO.saveOrUpdate(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.ufDAO.find(id);

			this.subView.getTfNome().setValue(this.currentBean.getNome());
			this.subView.getTfSigla().setValue(this.currentBean.getSigla());
			this.subView.getTfCodigoIbge().setValue(
					this.currentBean.getCodigoIbge().toString());

			PaisEntity pais = this.currentBean.getPais();

			if (pais != null) {
				this.subView.getMocPais().setValue(pais);
			}
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

	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new UfFormView(this);

			DefaultManyToOneComboModel<PaisEntity> paisModel = new DefaultManyToOneComboModel<PaisEntity>(
					PaisListController.class, this.paisDAO,
					super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nomePtbr";
				}

			};

			this.subView.getMocPais().setModel(paisModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		this.currentBean = new UfEntity();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.ufDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public UfEntity getModelBean() {
		return currentBean;
	}

}