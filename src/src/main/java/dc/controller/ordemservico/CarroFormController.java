package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.control.util.classes.ordemservico.CarroUtils;
import dc.control.validator.DotErpException;
import dc.controller.geral.pessoal.ClienteListController;
import dc.entidade.ordemservico.CarroEntity;
import dc.entidade.ordemservico.CombustivelEntity;
import dc.entidade.ordemservico.CorEntity;
import dc.entidade.ordemservico.MarcaOsEntity;
import dc.entidade.ordemservico.ModeloOsEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.model.business.geral.pessoal.ClienteBusiness;
import dc.model.business.ordemservico.CarroBusiness;
import dc.model.business.ordemservico.CombustivelBusiness;
import dc.model.business.ordemservico.CorBusiness;
import dc.model.business.ordemservico.MarcaOsBusiness;
import dc.model.business.ordemservico.ModeloOsBusiness;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModelSelect;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.CarroFormView;

/** @author Paulo Sérgio */

@Controller
@Scope("prototype")
public class CarroFormController extends CRUDFormController<CarroEntity> {

	private static final long serialVersionUID = 1L;

	private CarroFormView subView;

	private CarroEntity currentBean;

	@Autowired
	private CarroBusiness<CarroEntity> business;

	@Autowired
	private CorBusiness<CorEntity> businessCor;

	@Autowired
	private CombustivelBusiness<CombustivelEntity> businessCombustivel;

	@Autowired
	private MarcaOsBusiness<MarcaOsEntity> businessMarcaOs;

	@Autowired
	private ModeloOsBusiness<ModeloOsEntity> businessModeloOs;

	@Autowired
	private ClienteBusiness<ClienteEntity> businessCliente;

	/**
	 * CONSTRUTOR
	 */
	public CarroFormController() {
	}

	public CarroBusiness<CarroEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Carro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected void actionSalvar() {
		String placa = subView.getTfPlaca().getValue();
		if (Validator.validateString(placa)) {
			placa = placa.replace("-", "").trim();
			this.currentBean.setPlaca(placa);
		}
		this.currentBean.setCliente(subView.getCbCliente().getValue());
		this.currentBean.setMarca(subView.getCbMarca().getValue());
		this.currentBean.setCor(subView.getCbCor().getValue());
		this.currentBean.setModelo(subView.getCbModelo().getValue());
		this.currentBean.setCombustivel(subView.getCbCombustivel().getValue());
		this.currentBean.setMotorizacao(subView.getTfMotorizacao().getValue());
		if(subView.getTfAno()!=null && !subView.getTfAno().getValue().equals("")){
			this.currentBean.setAno(Integer.parseInt(subView.getTfAno().getValue().toString()));
		}
		if(subView.getTfChassi()!=null && !subView.getTfChassi().getValue().equals("")){
			this.currentBean.setChassi(subView.getTfChassi().getValue());
		}
		if(subView.getTaObservacao()!=null && !subView.getTaObservacao().getValue().equals("")){
			this.currentBean.setObservacao(subView.getTaObservacao().getValue());
		}
		try {
			this.business.saveOrUpdate(this.currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.business.find(id);
			subView.getTfPlaca().setValue(this.currentBean.getPlaca());
			subView.getCbCliente().setValue(this.currentBean.getCliente());
			subView.getCbMarca().setValue(this.currentBean.getMarca());
			subView.getCbCor().setValue(this.currentBean.getCor());
			subView.getCbModelo().setValue(this.currentBean.getModelo());
			subView.getCbCombustivel().setValue(this.currentBean.getCombustivel());
			subView.getTfMotorizacao().setValue(this.currentBean.getMotorizacao());
			subView.getTfAno().setValue(this.currentBean.getAno().toString());
			subView.getTfChassi().setValue(this.currentBean.getChassi());
			subView.getTaObservacao().setValue(this.currentBean.getObservacao());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada
	 * para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento
	 * padrÃ£o
	 */
	@Override
	protected void quandoNovo() {

	}

	@Override
	protected void initSubView() {
		subView = new CarroFormView(this);

		preencheCombos();
	}

	/*
	 * Deve sempre atribuir a current Bean uma nova instancia do bean do
	 * formulario.
	 */
	@Override
	protected void criarNovoBean() {
		currentBean = new CarroEntity();
	}

	private void preencheCombos() {
		DefaultManyToOneComboModel<ClienteEntity> cliente = new DefaultManyToOneComboModel<ClienteEntity>(ClienteListController.class,super.getMainController(),false,this.businessCliente);

		this.subView.getCbCliente().setModel(cliente);

		
		DefaultManyToOneComboModel<MarcaOsEntity> marca = new DefaultManyToOneComboModel<MarcaOsEntity>(MarcaListController.class,super.getMainController(),false,this.businessMarcaOs);

		this.subView.getCbMarca().setModel(marca);

		DefaultManyToOneComboModel<CorEntity> cor = new DefaultManyToOneComboModel<CorEntity>(CorListController.class, super.getMainController(), false, this.businessCor);

		this.subView.getCbCor().setModel(cor);

		DefaultManyToOneComboModel<CombustivelEntity> combustivel = new DefaultManyToOneComboModel<CombustivelEntity>(CombustivelListController.class,
				super.getMainController(),false,this.businessCombustivel);

		this.subView.getCbCombustivel().setModel(combustivel);

	}

	
	public void getModelo(String classePesquisa, Integer idSelecionado) {
		DefaultManyToOneComboModelSelect<ModeloOsEntity> modelo = new DefaultManyToOneComboModelSelect<ModeloOsEntity>(ModeloListController.class, this.businessModeloOs,
				super.getMainController(), classePesquisa, idSelecionado, false);

		this.subView.getCbModelo().setModel(modelo);
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.business.deleteAll(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected boolean validaSalvar() {
		try {
			CarroUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public CarroEntity getModelBean() {
		return currentBean;
	}
}
