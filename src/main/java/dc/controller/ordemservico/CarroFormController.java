package dc.controller.ordemservico;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.controller.geral.pessoal.ClienteListController;
import dc.entidade.ordemservico.CarroEntity;
import dc.entidade.ordemservico.CombustivelEntity;
import dc.entidade.ordemservico.CorEntity;
import dc.entidade.ordemservico.MarcaOsEntity;
import dc.entidade.ordemservico.ModeloEntity;
import dc.entidade.geral.pessoal.ClienteEntity;
import dc.servicos.dao.ordemservico.CarroDAO;
import dc.servicos.dao.ordemservico.CombustivelDAO;
import dc.servicos.dao.ordemservico.CorDAO;
import dc.servicos.dao.ordemservico.MarcaDAO;
import dc.servicos.dao.ordemservico.ModeloDAO;
import dc.servicos.dao.geral.pessoal.ClienteDAO;
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

	CarroFormView subView;

	@Autowired
	CarroDAO carroDAO;

	@Autowired
	ClienteDAO clienteDAO;

	@Autowired
	MarcaDAO marcaDAO;

	@Autowired
	CorDAO corDAO;

	@Autowired
	ModeloDAO modeloDAO;

	@Autowired
	CombustivelDAO combustivelDAO;

	private CarroEntity currentBean;

	@Override
	protected String getNome() {
		return "Carro";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		String placa = subView.getTfPlaca().getValue();
		if (Validator.validateString(placa)) {
			placa = placa.replace("-", "").trim();
			currentBean.setPlaca(placa);
		}
		currentBean.setCliente(subView.getCbCliente().getValue());
		currentBean.setMarca(subView.getCbMarca().getValue());
		currentBean.setCor(subView.getCbCor().getValue());
		currentBean.setModelo(subView.getCbModelo().getValue());
		currentBean.setCombustivel(subView.getCbCombustivel().getValue());
		currentBean.setMotorizacao(subView.getTfMotorizacao().getValue());
		currentBean.setAno(Integer.parseInt(subView.getTfAno().getValue().toString()));
		currentBean.setChassi(subView.getTfChassi().getValue());
		currentBean.setObservacao(subView.getTaObservacao().getValue());
		try {
			carroDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = carroDAO.find(id);
		subView.getTfPlaca().setValue(currentBean.getPlaca());
		subView.getCbCliente().setValue(currentBean.getCliente());
		subView.getCbMarca().setValue(currentBean.getMarca());
		subView.getCbCor().setValue(currentBean.getCor());
		subView.getCbModelo().setValue(currentBean.getModelo());
		subView.getCbCombustivel().setValue(currentBean.getCombustivel());
		subView.getTfMotorizacao().setValue(currentBean.getMotorizacao());
		subView.getTfAno().setValue(currentBean.getAno().toString());
		subView.getTfChassi().setValue(currentBean.getChassi());
		subView.getTaObservacao().setValue(currentBean.getObservacao());

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

		DefaultManyToOneComboModel<ClienteEntity> cliente = new DefaultManyToOneComboModel<ClienteEntity>(ClienteListController.class, this.clienteDAO,
				super.getMainController()) {
			@Override
			public String getCaptionProperty() {
				return "pessoa.nome";
			}
		};
		this.subView.getCbCliente().setModel(cliente);

		DefaultManyToOneComboModel<MarcaOsEntity> marca = new DefaultManyToOneComboModel<MarcaOsEntity>(MarcaListController.class, this.marcaDAO,
				super.getMainController());

		this.subView.getCbMarca().setModel(marca);

		DefaultManyToOneComboModel<CorEntity> cor = new DefaultManyToOneComboModel<CorEntity>(CorListController.class, this.corDAO, super.getMainController());

		this.subView.getCbCor().setModel(cor);

		DefaultManyToOneComboModel<CombustivelEntity> combustivel = new DefaultManyToOneComboModel<CombustivelEntity>(CombustivelListController.class,
				this.combustivelDAO, super.getMainController());

		this.subView.getCbCombustivel().setModel(combustivel);

	}

	public void getModelo(String classePesquisa, Integer idSelecionado) {
		DefaultManyToOneComboModelSelect<ModeloEntity> modelo = new DefaultManyToOneComboModelSelect<ModeloEntity>(ModeloListController.class, this.modeloDAO,
				super.getMainController(), classePesquisa, idSelecionado);

		this.subView.getCbModelo().setModel(modelo);
	}

	@Override
	protected void remover(List<Serializable> ids) {
		carroDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */
	@Override
	protected boolean validaSalvar() {

		boolean valido = true;

		if (!Validator.validateString(subView.getTfPlaca().getValue())) {
			adicionarErroDeValidacao(subView.getTfPlaca(), "Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "carroForm";
	}

	@Override
	public CarroEntity getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}
}
