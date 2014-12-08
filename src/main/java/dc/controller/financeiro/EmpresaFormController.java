package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.controller.sistema.SeguimentoListController;
import dc.entidade.empresa.EmpresaCnae;
import dc.entidade.financeiro.SindicatoEntity;
import dc.entidade.framework.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.framework.Fpas;
import dc.entidade.framework.Seguimento;
import dc.entidade.geral.CnaeEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.pessoal.ContadorEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.empresa.EmpresaCnaeDAO;
import dc.servicos.dao.financeiro.SindicatoDAO;
import dc.servicos.dao.framework.geral.EmpresaDAO;
import dc.servicos.dao.framework.geral.EmpresaSeguimentoDAO;
import dc.servicos.dao.framework.geral.FpasDAO;
import dc.servicos.dao.framework.geral.SeguimentoDAO;
import dc.servicos.dao.geral.CnaeDAO;
import dc.servicos.dao.geral.PessoaEnderecoDAO;
import dc.servicos.dao.geral.pessoal.ContadorDAO;
import dc.servicos.util.Validator;
import dc.visao.financeiro.EmpresaFormView;
import dc.visao.financeiro.enums.CrtType;
import dc.visao.financeiro.enums.TipoRegimeType;
import dc.visao.financeiro.enums.TipoType;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

/**
 *
 * @author Wesley Jr
 /*
 * Nessa classe ela pega a classe principal que é o CRUD, que tem todos os controllers
 * da Tela, onde quando extendemos herdamos os métodos que temos na tela principal.
 * Temos o botão Novo que é para Criar uma nova Tela, para adicionar informações
 * novas, e dentro temos o Button Salvar que é para salvar as informações no Banco de Dados
 * Temos o carregar também que é para pegar as informações que desejarmos quando
 * formos pesquisar na Tela.
 *
 */

/**
 * @author Wesley Jr /* Nessa classe ela pega a classe principal que é o CRUD,
 *         que tem todos os controllers da Tela, onde quando extendemos herdamos
 *         os métodos que temos na tela principal. Temos o botão Novo que é para
 *         Criar uma nova Tela, para adicionar informações novas, e dentro temos
 *         o Button Salvar que é para salvar as informações no Banco de Dados
 *         Temos o carregar também que é para pegar as informações que
 *         desejarmos quando formos pesquisar na Tela.
 */

@Controller
@Scope("prototype")
public class EmpresaFormController extends CRUDFormController<EmpresaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmpresaFormView subView;

	@Autowired
	private EmpresaDAO empresaDAO;

	/*
	 * @Autowired private MatrizDAO matrizDAO;
	 */

	@Autowired
	private ContadorDAO contadorDAO;

	@Autowired
	private SindicatoDAO sindicatoDAO;

	@Autowired
	private FpasDAO fpasDAO;

	@Autowired
	private EmpresaCnaeDAO empresaCnaeDAO;

	@Autowired
	private PessoaEnderecoDAO enderecoDAO;

	@Autowired
	private CnaeDAO cnaeDAO;

	@Autowired
	private SeguimentoDAO seguimentoDAO;

	@Autowired
	private EmpresaSeguimentoDAO empresaSeguimentoDAO;

	private EmpresaEntity currentBean;

	@Override
	protected String getNome() {
		return "Empresa";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		String razaoSocial = subView.getTxtRazaoSocial().getValue();
		String nomeFantasia = subView.getTxtNomeFantasia().getValue();

		ContadorEntity contador = (ContadorEntity) subView.getCmbContador()
				.getValue();
		SindicatoEntity sindicato = (SindicatoEntity) subView.getCmbSindicato()
				.getValue();
		Fpas fpas = (Fpas) subView.getCmbFpas().getValue();

		Date dataInicioAtividades = subView.getDtInicioAtividades().getValue();
		String cnpj = subView.getTxtCnpj().getValue();
		String inscricaoEstadual = subView.getTxtInscricaoEstadual().getValue();
		String inscricaoEstadualSt = subView.getTxtInscricaoEstadualSt()
				.getValue();
		String inscricaoMunicipal = subView.getTxtInscricaoMunicipal()
				.getValue();
		String inscricaoJuntaComercial = subView
				.getTxtInscricaoJuntaComercial().getValue();
		//
		Date dataInscricaoJuntaComercial = subView
				.getDtInscricaoJuntaComercial().getValue();
		String suframa = subView.getTxtSuframa().getValue();
		String contato = subView.getTxtContato().getValue();
		String codigoTerceiros = subView.getTxtCodigoTerceiros().getValue();
		String cei = subView.getTxtCei().getValue();
		//
		String aliquotaPis = subView.getTxtAliquotaPis().getValue();
		String aliquotaCofins = subView.getTxtAliquotaCofins().getValue();
		String aliquotaSat = subView.getTxtAliquotaSat().getValue();
		//
		String codigoGps = subView.getTxtCodigoGps().getValue();
		//
		String codigoMunicipio = subView.getTxtMunicipio().getValue();
		String codigoUf = subView.getTxtUf().getValue();

		CnaeEntity cnaePrincipal = (CnaeEntity) subView.getCmbCnaePrincipal()
				.getValue();
		//
		try {
			if (!(Validator.validateString(razaoSocial))) {
				throw new ErroValidacaoException("Informe a Razão Social");
			}
			if (!(Validator.validateString(nomeFantasia))) {
				throw new ErroValidacaoException("Informe o Nome de Fantasia");
			}

			if (Validator.validateObject(sindicato)) {
				currentBean.setSindicatoPatronal(sindicato.getId());
			}

			if (Validator.validateObject(contador)) {
				currentBean.setContador(contador.getId());
			}

			if (Validator.validateObject(fpas)) {
				currentBean.setFpas(fpas.getId());
			}

			currentBean.setRazaoSocial(razaoSocial);
			currentBean.setNomeFantasia(nomeFantasia);

			if (sindicato != null) {
				currentBean.setSindicatoPatronal(sindicato.getId());
			}

			if (fpas != null) {
				currentBean.setFpas(fpas.getId());
			}

			if (contador != null) {
				currentBean.setContador(contador.getId());
			}

			if (Validator.validateObject(dataInicioAtividades)) {
				currentBean.setDataInicioAtividades(dataInicioAtividades);
			}

			if (Validator.validateObject(cnpj)) {
				currentBean.setCnpj(cnpj);
			}

			if (Validator.validateObject(inscricaoEstadual)) {
				currentBean.setInscricaoEstadual(inscricaoEstadual);
			}

			if (Validator.validateObject(inscricaoEstadualSt)) {
				currentBean.setInscricaoEstadualSt(inscricaoEstadualSt);
			}

			if (Validator.validateObject(inscricaoMunicipal)) {
				currentBean.setInscricaoMunicipal(inscricaoMunicipal);
			}

			if (Validator.validateObject(inscricaoJuntaComercial)) {
				currentBean.setInscricaoJuntaComercial(inscricaoJuntaComercial);
			}

			if (Validator.validateObject(dataInscricaoJuntaComercial)) {
				currentBean
						.setDataInscJuntaComercial(dataInscricaoJuntaComercial);
			}

			if (Validator.validateObject(suframa)) {
				currentBean.setSuframa(suframa);
			}

			if (Validator.validateObject(contato)) {
				currentBean.setContato(contato);
			}

			if (Validator.validateObject(codigoTerceiros)) {
				currentBean.setCodigoTerceiros(new Integer(codigoTerceiros));
			}

			if (Validator.validateObject(cei)) {
				currentBean.setCei(cei);
			}

			if (Validator.validateObject(aliquotaPis)) {
				currentBean.setAliquotaPis(new BigDecimal(aliquotaPis));
			}

			if (Validator.validateObject(aliquotaCofins)) {
				currentBean.setAliquotaCofins(new BigDecimal(aliquotaCofins));
			}

			if (Validator.validateObject(aliquotaSat)) {
				currentBean.setAliquotaSat(new BigDecimal(aliquotaSat));
			}

			if (Validator.validateObject(codigoGps)) {
				currentBean.setCodigoGps(new Integer(codigoGps));
			}

			if (Validator.validateObject(codigoMunicipio)) {
				currentBean.setCodigoIbgeCidade(new Integer(codigoMunicipio));
			}

			if (Validator.validateObject(codigoUf)) {
				currentBean.setCodigoIbgeUf(new Integer(codigoUf));
			}

			if (Validator.validateObject(cnaePrincipal)) {

				currentBean.setCnaePrincipal(cnaePrincipal.getId().toString());
			}

			if (Validator.validateObject(subView.getCmbMatriz().getValue())) {

				EmpresaEntity e = (EmpresaEntity) subView.getCmbMatriz()
						.getValue();
				currentBean.setMatriz(e.getId());

			}
			List<EmpresaSeguimento> empresaSeguimentos = subView
					.getSeguimentos();
			for (EmpresaSeguimento empresaSeguimento : empresaSeguimentos) {
				empresaSeguimento.setEmpresa(currentBean);
			}

			currentBean.setEmpresaSeguimentos(empresaSeguimentos);

			empresaDAO.saveOrUpdate(currentBean);

			for (PessoaEnderecoEntity e : currentBean.getEnderecos()) {
				e.setEmpresa(currentBean);
				String cep = e.getCep().replace(".", "").replace("-", "")
						.trim();
				e.setCep(cep);
				enderecoDAO.saveOrUpdate(e);
			}
			currentBean.setEmpresa(currentBean.getId());
			empresaDAO.saveOrUpdate(currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (ErroValidacaoException e) {
			e.montaMensagemErro();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private void carregarCombos() { subView.carregarCRT();
	 * subView.carregarTipoRegime(); subView.carregarTipo(); carregarMatrizes();
	 * /* subView.carregaComboMatrix(matrizDAO .getAll(Matriz.class));
	 */
	// subView.carregaComboContador(contadorDAO
	// .getAll(Contador.class));
	// subView.carregaComboSindicato(sindicatoDAO
	// .getAll(Sindicato.class));
	/*
	 * subView.carregaComboFpas(FpasDAO .getAll(Fpas.class));
	 * 
	 * }
	 */

	@Override
	protected void carregar(Serializable id) {
		carregaCombos();
		currentBean = empresaDAO.find(id);

		subView.getTxtRazaoSocial().setValue(currentBean.getRazaoSocial());
		subView.getTxtNomeFantasia().setValue(currentBean.getNomeFantasia());

		if (currentBean.getSindicatoPatronal() != null) {
			SindicatoEntity sindicato = sindicatoDAO.find(currentBean
					.getSindicatoPatronal());
			subView.getCmbSindicato().setValue(sindicato);
		}

		if (currentBean.getContador() != null) {
			try {
				Integer idC = new Integer(currentBean.getContador());
				ContadorEntity contador = contadorDAO.find(idC);
				subView.getCmbContador().setValue(contador);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (currentBean.getCnpj() != null) {
			subView.getTxtCnpj().setValue(currentBean.getCnpj());
		}

		if (currentBean.getFpas() != null) {
			Fpas fpas = fpasDAO.find(currentBean.getFpas());
			subView.getCmbFpas().setValue(fpas);
		}

		if (Validator.validateObject(currentBean.getDataInicioAtividades())) {
			subView.getDtInicioAtividades().setValue(
					currentBean.getDataInicioAtividades());
		}

		if (Validator.validateObject(currentBean.getInscricaoEstadual())) {
			subView.getTxtInscricaoEstadual().setValue(
					currentBean.getInscricaoEstadual());
		}

		if (Validator.validateObject(currentBean.getInscricaoEstadualSt())) {
			subView.getTxtInscricaoEstadualSt().setValue(
					currentBean.getInscricaoEstadualSt());
		}

		if (Validator.validateObject(currentBean.getInscricaoMunicipal())) {
			subView.getTxtInscricaoMunicipal().setValue(
					currentBean.getInscricaoEstadualSt());
		}

		if (Validator.validateObject(currentBean.getInscricaoJuntaComercial())) {
			subView.getTxtInscricaoJuntaComercial().setValue(
					currentBean.getInscricaoJuntaComercial());
		}

		if (Validator.validateObject(currentBean.getDataInscJuntaComercial())) {
			subView.getDtInscricaoJuntaComercial().setValue(
					currentBean.getDataInscJuntaComercial());
		}

		if (Validator.validateObject(currentBean.getSuframa())) {
			subView.getTxtSuframa().setValue(currentBean.getSuframa());
		}

		if (Validator.validateObject(currentBean.getContato())) {
			subView.getTxtContato().setValue(currentBean.getContato());
		}

		if (Validator.validateObject(currentBean.getCodigoTerceiros())) {
			subView.getTxtCodigoTerceiros().setValue(
					currentBean.getCodigoTerceiros().toString());
		}

		if (Validator.validateObject(currentBean.getCei())) {
			subView.getTxtCei().setValue(currentBean.getCei());
		}

		if (Validator.validateObject(currentBean.getAliquotaPis())) {
			subView.getTxtAliquotaPis().setValue(
					currentBean.getAliquotaPis().toString());
		}

		if (Validator.validateObject(currentBean.getAliquotaCofins())) {
			subView.getTxtAliquotaCofins().setValue(
					currentBean.getAliquotaCofins().toString());
		}

		if (Validator.validateObject(currentBean.getAliquotaSat())) {
			subView.getTxtAliquotaSat().setValue(
					currentBean.getAliquotaSat().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoGps())) {
			subView.getTxtCodigoGps().setValue(
					currentBean.getCodigoGps().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoIbgeCidade())) {
			subView.getTxtMunicipio().setValue(
					currentBean.getCodigoIbgeCidade().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoIbgeUf())) {
			subView.getTxtUf().setValue(
					currentBean.getCodigoIbgeUf().toString());
		}

		if (Validator.validateObject(currentBean.getCnaePrincipal())) {

			Integer idCnae = new Integer(currentBean.getCnaePrincipal());
			CnaeEntity cnae = cnaeDAO.find(idCnae);

			subView.getCmbCnaePrincipal().setValue(cnae);
		}

		if (Validator.validateObject(currentBean.getMatriz())) {
			Integer idMatriz = currentBean.getMatriz();
			EmpresaEntity matriz = empresaDAO.find(idMatriz);
			subView.getCmbMatriz().setValue(matriz);
		}

		try {
			List<PessoaEnderecoEntity> enderecos = enderecoDAO
					.listaPorEmpresa(currentBean);
			currentBean.setEnderecos(enderecos);
			carregarSeguimentos();
			subView.fillEnderecoSubForm(enderecos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void carregarSeguimentos() {
		currentBean.setEmpresaSeguimentos(empresaSeguimentoDAO
				.listaPorEmpresa(currentBean));

		subView.setEmpresaSeguimentos(currentBean.getEmpresaSeguimentos());
	}

	@Override
	protected void initSubView() {
		subView = new EmpresaFormView(this);

		carregaCombos();
	}

	private void carregaCombos() {
		this.subView.InitCbs(getEmpresaCrtType(), getEmpresaTipoRegimeType(),
				getEmpresaTipoType());

		DefaultManyToOneComboModel<Seguimento> seguimentos = new DefaultManyToOneComboModel<Seguimento>(
				SeguimentoListController.class, this.seguimentoDAO,
				super.getMainController());

		this.subView.getComboSeguimentos().setModel(seguimentos);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new EmpresaEntity();
		carregaCombos();
	}

	@Override
	@Transactional
	protected void quandoNovo() {
		try {
			Hibernate.initialize(currentBean.getTipoAquisicaoList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PessoaEnderecoEntity novoEndereco() {
		PessoaEnderecoEntity endereco = new PessoaEnderecoEntity();
		this.currentBean.addEndereco(endereco);

		return endereco;
	}

	public void removerEndereco(List<PessoaEnderecoEntity> values) {
		for (PessoaEnderecoEntity endereco : values) {
			this.currentBean.removeEndereco(endereco);
		}

		mensagemRemovidoOK();
	}

	@Override
	protected void remover(List<Serializable> ids) {
		empresaDAO.deleteAllByIds(ids);
		mensagemRemovidoOK();
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return "empresaForm";
	}

	public BeanItemContainer<EmpresaEntity> carregarMatrizes() {
		BeanItemContainer<EmpresaEntity> container = new BeanItemContainer<>(
				EmpresaEntity.class);

		for (EmpresaEntity obj : empresaDAO.buscaMatrizes()) {
			container.addBean(obj);
		}

		return container;
	}

	public BeanItemContainer<SindicatoEntity> carregarSindicatos() {
		BeanItemContainer<SindicatoEntity> container = new BeanItemContainer<>(
				SindicatoEntity.class);

		for (SindicatoEntity obj : sindicatoDAO.listaTodos()) {
			container.addBean(obj);
		}

		return container;
	}

	public BeanItemContainer<CnaeEntity> carregarEmpresaCnae() {
		BeanItemContainer<CnaeEntity> container = new BeanItemContainer<>(
				CnaeEntity.class);
		List<EmpresaCnae> lista = empresaCnaeDAO.listarPrincipais();

		for (EmpresaCnae obj : lista) {
			container.addBean(obj.getCnae());
		}

		return container;
	}

	public BeanItemContainer<ContadorEntity> carregarContadores() {
		BeanItemContainer<ContadorEntity> container = new BeanItemContainer<>(
				ContadorEntity.class);

		for (ContadorEntity obj : contadorDAO.listaTodos()) {
			container.addBean(obj);
		}

		return container;
	}

	public BeanItemContainer<Fpas> carregarFpas() {
		BeanItemContainer<Fpas> container = new BeanItemContainer<>(Fpas.class);

		for (Fpas obj : fpasDAO.listaTodos()) {
			container.addBean(obj);
		}

		return container;
	}

	/** COMBO */
	public List<String> getEmpresaCrtType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (CrtType en : CrtType.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getEmpresaTipoRegimeType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (TipoRegimeType en : TipoRegimeType.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	public List<String> getEmpresaTipoType() {
		try {
			List<String> siLista = new ArrayList<String>();

			for (TipoType en : TipoType.values()) {
				siLista.add(en.ordinal(), en.toString());

			}

			return siLista;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public EmpresaEntity getModelBean() {
		return currentBean;
	}

}