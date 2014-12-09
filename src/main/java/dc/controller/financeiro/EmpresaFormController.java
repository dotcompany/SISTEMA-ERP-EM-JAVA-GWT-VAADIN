package dc.controller.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.control.enums.CrtEn;
import dc.control.enums.TipoEmpresaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.util.ClassUtils;
import dc.controller.sistema.SeguimentoListController;
import dc.entidade.empresa.EmpresaCnaeEntity;
import dc.entidade.financeiro.SindicatoEntity;
import dc.entidade.framework.EmpresaEntity;
import dc.entidade.framework.EmpresaSeguimento;
import dc.entidade.framework.Fpas;
import dc.entidade.framework.SeguimentoEntity;
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
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class EmpresaFormController extends CRUDFormController<EmpresaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmpresaFormView subView;

	private EmpresaEntity currentBean;

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

	@Override
	protected String getNome() {
		return "Empresa";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void actionSalvar() {
		String razaoSocial = subView.getTfRazaoSocial().getValue();
		String nomeFantasia = subView.getTfNomeFantasia().getValue();

		ContadorEntity contador = (ContadorEntity) subView.getCbContador()
				.getValue();
		SindicatoEntity sindicato = (SindicatoEntity) subView.getCbSindicato()
				.getValue();
		Fpas fpas = (Fpas) subView.getCbFpas().getValue();

		Date dataInicioAtividades = subView.getPdfDataInicioAtividades()
				.getValue();
		String cnpj = subView.getMtfCnpj().getValue();
		String inscricaoEstadual = subView.getTfInscricaoEstadual().getValue();
		String inscricaoEstadualSt = subView.getTfInscricaoEstadualSt()
				.getValue();
		String inscricaoMunicipal = subView.getTfInscricaoMunicipal()
				.getValue();
		String inscricaoJuntaComercial = subView.getTfInscricaoJuntaComercial()
				.getValue();
		//
		Date dataInscricaoJuntaComercial = subView
				.getPdfDataInscricaoJuntaComercial().getValue();
		String suframa = subView.getTfSuframa().getValue();
		String contato = subView.getTfContato().getValue();
		String codigoTerceiros = subView.getTfCodigoTerceiros().getValue();
		String cei = subView.getTfCei().getValue();
		//
		String aliquotaPis = subView.getTfAliquotaPis().getValue();
		String aliquotaCofins = subView.getTfAliquotaCofins().getValue();
		String aliquotaSat = subView.getTfAliquotaSat().getValue();
		//
		String codigoGps = subView.getTfCodigoGps().getValue();
		//
		String codigoMunicipio = subView.getTfMunicipio().getValue();
		String codigoUf = subView.getTfUf().getValue();

		CnaeEntity cnaePrincipal = (CnaeEntity) subView.getCbCnaePrincipal()
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

			if (Validator.validateObject(subView.getCbMatriz().getValue())) {
				EmpresaEntity e = (EmpresaEntity) subView.getCbMatriz()
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

	@Override
	protected void carregar(Serializable id) {
		// carregaCombos();
		currentBean = empresaDAO.find(id);

		subView.getTfRazaoSocial().setValue(currentBean.getRazaoSocial());
		subView.getTfNomeFantasia().setValue(currentBean.getNomeFantasia());

		if (currentBean.getSindicatoPatronal() != null) {
			SindicatoEntity sindicato = sindicatoDAO.find(currentBean
					.getSindicatoPatronal());
			subView.getCbSindicato().setValue(sindicato);
		}

		if (currentBean.getContador() != null) {
			try {
				Integer idC = new Integer(currentBean.getContador());
				ContadorEntity contador = contadorDAO.find(idC);
				subView.getCbContador().setValue(contador);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (currentBean.getCnpj() != null) {
			subView.getMtfCnpj().setValue(currentBean.getCnpj());
		}

		if (currentBean.getFpas() != null) {
			Fpas fpas = fpasDAO.find(currentBean.getFpas());
			subView.getCbFpas().setValue(fpas);
		}

		if (Validator.validateObject(currentBean.getDataInicioAtividades())) {
			subView.getPdfDataInicioAtividades().setValue(
					currentBean.getDataInicioAtividades());
		}

		if (Validator.validateObject(currentBean.getInscricaoEstadual())) {
			subView.getTfInscricaoEstadual().setValue(
					currentBean.getInscricaoEstadual());
		}

		if (Validator.validateObject(currentBean.getInscricaoEstadualSt())) {
			subView.getTfInscricaoEstadualSt().setValue(
					currentBean.getInscricaoEstadualSt());
		}

		if (Validator.validateObject(currentBean.getInscricaoMunicipal())) {
			subView.getTfInscricaoMunicipal().setValue(
					currentBean.getInscricaoEstadualSt());
		}

		if (Validator.validateObject(currentBean.getInscricaoJuntaComercial())) {
			subView.getTfInscricaoJuntaComercial().setValue(
					currentBean.getInscricaoJuntaComercial());
		}

		if (Validator.validateObject(currentBean.getDataInscJuntaComercial())) {
			subView.getPdfDataInscricaoJuntaComercial().setValue(
					currentBean.getDataInscJuntaComercial());
		}

		if (Validator.validateObject(currentBean.getSuframa())) {
			subView.getTfSuframa().setValue(currentBean.getSuframa());
		}

		if (Validator.validateObject(currentBean.getContato())) {
			subView.getTfContato().setValue(currentBean.getContato());
		}

		if (Validator.validateObject(currentBean.getCodigoTerceiros())) {
			subView.getTfCodigoTerceiros().setValue(
					currentBean.getCodigoTerceiros().toString());
		}

		if (Validator.validateObject(currentBean.getCei())) {
			subView.getTfCei().setValue(currentBean.getCei());
		}

		if (Validator.validateObject(currentBean.getAliquotaPis())) {
			subView.getTfAliquotaPis().setValue(
					currentBean.getAliquotaPis().toString());
		}

		if (Validator.validateObject(currentBean.getAliquotaCofins())) {
			subView.getTfAliquotaCofins().setValue(
					currentBean.getAliquotaCofins().toString());
		}

		if (Validator.validateObject(currentBean.getAliquotaSat())) {
			subView.getTfAliquotaSat().setValue(
					currentBean.getAliquotaSat().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoGps())) {
			subView.getTfCodigoGps().setValue(
					currentBean.getCodigoGps().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoIbgeCidade())) {
			subView.getTfMunicipio().setValue(
					currentBean.getCodigoIbgeCidade().toString());
		}

		if (Validator.validateObject(currentBean.getCodigoIbgeUf())) {
			subView.getTfUf()
					.setValue(currentBean.getCodigoIbgeUf().toString());
		}

		if (Validator.validateObject(currentBean.getCnaePrincipal())) {
			Integer idCnae = new Integer(currentBean.getCnaePrincipal());
			CnaeEntity cnae = cnaeDAO.find(idCnae);

			subView.getCbCnaePrincipal().setValue(cnae);
		}

		if (Validator.validateObject(currentBean.getMatriz())) {
			Integer idMatriz = currentBean.getMatriz();
			EmpresaEntity matriz = empresaDAO.find(idMatriz);
			subView.getCbMatriz().setValue(matriz);
		}

		try {
			List<PessoaEnderecoEntity> enderecos = enderecoDAO
					.listaPorEmpresa(currentBean);
			currentBean.setEnderecos(enderecos);
			// carregarSeguimentos();
			subView.fillEnderecoSubForm(enderecos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new EmpresaFormView(this);

			DefaultManyToOneComboModel<SeguimentoEntity> seguimentos = new DefaultManyToOneComboModel<SeguimentoEntity>(
					SeguimentoListController.class, this.seguimentoDAO,
					super.getMainController());

			this.subView.getMocSeguimentos().setModel(seguimentos);

			// carregarContador();
			carregarCrt();
			carregarTipoRegime();
			carregarTipoEmpresa();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new EmpresaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
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
		try {
			for (PessoaEnderecoEntity endereco : values) {
				this.currentBean.removeEndereco(endereco);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.empresaDAO.deleteAllByIds(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> ids) {

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
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
		List<EmpresaCnaeEntity> lista = empresaCnaeDAO.listarPrincipais();

		for (EmpresaCnaeEntity obj : lista) {
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

	@Override
	protected boolean isFullSized() {
		return true;
	}

	@Override
	public EmpresaEntity getModelBean() {
		return currentBean;
	}

	/**
	 * COMBOS
	 */

	public void carregarContador() {
		this.subView.getCbContador().addItems(this.contadorDAO.listaTodos());
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCbCrt().addItem(en);
		}
	}

	public void carregarTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCbTipoRegime().addItem(en);
		}
	}

	public void carregarTipoEmpresa() {
		for (TipoEmpresaEn en : TipoEmpresaEn.values()) {
			this.subView.getCbTipoEmpresa().addItem(en);
		}
	}

}