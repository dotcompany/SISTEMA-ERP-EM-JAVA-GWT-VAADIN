package dc.controller.folhapagamento.inss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.validator.ObjectValidator;
import dc.entidade.folhapagamento.inss.InssEntity;
import dc.entidade.folhapagamento.inss.RetencaoEntity;
import dc.entidade.folhapagamento.inss.ServicoEntity;
import dc.servicos.dao.folhapagamento.inss.InssDAO;
import dc.servicos.dao.folhapagamento.inss.RetencaoDAO;
import dc.servicos.dao.folhapagamento.inss.ServicoDAO;
import dc.visao.folhapagamento.inss.RetencaoFormView;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainController;

/** @author Gutemberg A. Da Silva */

@Controller
@Scope("prototype")
public class RetencaoFormController extends CRUDFormController<RetencaoEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RetencaoFormView subView;

	/** DAO'S */

	@Autowired
	private RetencaoDAO pDAO;

	@Autowired
	private InssDAO iDAO;

	@Autowired
	private ServicoDAO sDAO;

	/** ENTITIES */

	private RetencaoEntity pEntity;

	/** CONSTRUTOR */

	public RetencaoFormController() {
		if (this.pEntity == null) {
			this.pEntity = new RetencaoEntity();
		}
	}

	@Override
	protected String getNome() {
		return "Retenção";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			Double valor13 = Double.parseDouble(this.subView.getTfValor13().getValue());
			Double valorMensal = Double.parseDouble(this.subView.getTfValorMensal().getValue());

			InssEntity inss = (InssEntity) this.subView.getCbInss().getValue();
			ServicoEntity servico = (ServicoEntity) this.subView.getCbServico().getValue();

			this.pEntity.setValor13(valor13);
			this.pEntity.setValorMensal(valorMensal);
			this.pEntity.setInss(inss);
			this.pEntity.setServico(servico);

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

			mensagemErro(e.getMessage());
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar Programação customizada
	 * para essa ação aqui. Ou então deixar em branco, para comportamento padrão
	 */
	@Override
	protected void quandoNovo() {
		try {
			if (this.iDAO == null) {
				this.iDAO = new InssDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new ServicoDAO();
			}

			novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Autowired
	private MainController mainController;

	@Override
	protected void initSubView() {
		this.subView = new RetencaoFormView(this);

		this.subView.getCbInss().setData(this.inssListarTodos());
		this.subView.getCbServico().setData(this.servicoListarTodos());

		// this.subView.getCbInss().setData(carregarCbInss());
		// this.subView.getCbServico().setData(carregarCbServico());
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		try {
			if (this.iDAO == null) {
				this.iDAO = new InssDAO();
			}

			if (this.sDAO == null) {
				this.sDAO = new ServicoDAO();
			}

			novoObjeto(0);
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
		String valor13 = this.subView.getTfValor13().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valor13)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValor13(), msg);

			return false;
		}

		String valorMensal = this.subView.getTfValorMensal().getValue();

		if (!ObjectValidator.validateNotRequiredNumber(valorMensal)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getTfValorMensal(), msg);

			return false;
		}

		InssEntity inss = (InssEntity) this.subView.getCbInss().getValue();

		if (!ObjectValidator.validateObject(inss)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbInss(), msg);

			return false;
		}

		ServicoEntity servico = (ServicoEntity) this.subView.getCbServico().getValue();

		if (!ObjectValidator.validateObject(servico)) {
			String msg = "Não pode ficar em branco.";

			adicionarErroDeValidacao(this.subView.getCbServico(), msg);

			return false;
		}

		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		return "folhapagamento_inss_retencao_fc";
	}

	/** COMBOS */

	public List<InssEntity> inssListarTodos() {
		List<InssEntity> auxLista = new ArrayList<InssEntity>();

		auxLista = this.iDAO.listarTodos();

		return auxLista;
	}

	public List<ServicoEntity> servicoListarTodos() {
		List<ServicoEntity> auxLista = new ArrayList<ServicoEntity>();

		auxLista = this.sDAO.listarTodos();

		return auxLista;
	}

	private DefaultManyToOneComboModel<InssEntity> carregarCbInss() {
		DefaultManyToOneComboModel<InssEntity> model = new DefaultManyToOneComboModel<InssEntity>(InssListController.class, this.iDAO,
				super.getMainController());

		return model;
	}

	private DefaultManyToOneComboModel<ServicoEntity> carregarCbServico() {
		DefaultManyToOneComboModel<ServicoEntity> model = new DefaultManyToOneComboModel<ServicoEntity>(ServicoListController.class, this.sDAO,
				super.getMainController());

		return model;
	}

	/** ************************************** */

	@Override
	protected boolean isFullSized() {
		return true;
	}

	/** ************************************** */

	private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.pEntity = new RetencaoEntity();
			} else {
				this.pEntity = this.pDAO.find(id);
			}

			this.subView.getTfValor13().setValue(String.valueOf(this.pEntity.getValor13()));
			this.subView.getTfValorMensal().setValue(String.valueOf(this.pEntity.getValorMensal()));

			// this.subView.carregarCmbInss(this.inssListarTodos());
			// this.subView.carregarCmbServico(this.servicoListarTodos());

			this.subView.getCbInss().setData(this.inssListarTodos());
			this.subView.getCbServico().setData(this.servicoListarTodos());

			this.subView.getCbInss().setValue(this.pEntity.getInss());
			this.subView.getCbServico().setValue(this.pEntity.getServico());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RetencaoEntity getModelBean() {
		// TODO Auto-generated method stub
		return pEntity;
	}

}