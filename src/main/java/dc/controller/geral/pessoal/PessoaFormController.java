package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.ui.Component;

import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.CrtEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.TipoPessoaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.enums.TipoSangueEn;
import dc.control.util.ClassUtils;
import dc.controller.geral.UfListController;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.PessoaContatoEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.geral.pessoal.EstadoCivilDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.PessoaFisicaDAO;
import dc.servicos.dao.geral.pessoal.PessoaJuridicaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.PessoaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PessoaFormController extends CRUDFormController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PessoaFormView subView;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	PessoaEntity currentBean;

	@Autowired
	private UFDAO ufDAO;

	public PessoaFormController() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		if (!Validator.validateObject(subView.getCmbTipoPessoa().getValue())) {
			adicionarErroDeValidacao(subView.getCmbTipoPessoa(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtEmail().getValue())) {
			adicionarErroDeValidacao(subView.getTxtEmail(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtNome().getValue())) {
			adicionarErroDeValidacao(subView.getTxtNome(),
					"Não pode ficar em branco");
			valido = false;
		}

		if (!Validator.validateString(subView.getTxtSite().getValue())) {
			adicionarErroDeValidacao(subView.getTxtSite(),
					"Não pode ficar em branco");
			valido = false;
		}

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new PessoaEntity();
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaFormView(this);

			DefaultManyToOneComboModel<EstadoCivilEntity> model = new DefaultManyToOneComboModel<EstadoCivilEntity>(
					EstadoCivilListController.class, this.estadoCivilDAO,
					super.getMainController());

			/*
			 * DefaultManyToOneComboModel<EstadoCivilEntity> model = new
			 * DefaultManyToOneComboModel<EstadoCivilEntity>(
			 * EstadoCivilListController.class, this.estadoCivilDAO,
			 * super.getMainController()) {
			 * 
			 * @Override public String getCaptionProperty() { return "nome"; }
			 * 
			 * };
			 */

			this.subView.getCmbEstadoCivil().setModel(model);

			comboTipoRegime();
			comboCnh();
			comboRaca();
			comboCategoriaReservista();
			comboCrt();
			comboTipoSangue();
			comboTipoPessoa();
			comboSexo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.pessoaDAO.find(id);

			this.currentBean.setPessoaFisica(this.pessoaFisicaDAO
					.getEntity(this.currentBean));
			this.currentBean.setPessoaJuridica(this.pessoaJuridicaDAO
					.getEntity(this.currentBean));

			this.subView.getTxtNome().setValue(this.currentBean.getNome());

			this.subView.getCmbTipoPessoa().setValue(
					this.currentBean.getTipoPessoa());

			this.subView.getTxtEmail().setValue(this.currentBean.getEmail());
			this.subView.getTxtSite().setValue(this.currentBean.getSite());

			Set<String> selected = new HashSet<String>();

			if (isEnabled(this.currentBean.getFornecedor())) {
				selected.add("Fornecedor");
			}

			if (isEnabled(this.currentBean.getCliente())) {
				selected.add("Cliente");
			}

			if (isEnabled(this.currentBean.getColaborador())) {
				selected.add("Colaborador");
			}

			if (isEnabled(this.currentBean.getTransportadora())) {
				selected.add("Transportadora");
			}

			this.subView.getGroup().setValue(selected);

			this.subView.getEnderecosSubForm().fillWith(
					this.currentBean.getEnderecoList());
			this.subView.getContatosSubForm().fillWith(
					this.currentBean.getContatoList());

			if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.F)) {
				this.pessoaFisicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaFisica(carregarPessoaFisica());
			} else if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.J)) {
				this.pessoaJuridicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaJuridica(carregarPessoaJuridica());
			}

			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaJuridicaEntity carregarPessoaJuridica() {
		PessoaJuridicaEntity pj = this.currentBean.getPessoaJuridica();

		this.subView.getTxtFantasia().setValue(pj.getFantasia());
		this.subView.getTxtCNPJ().setValue(pj.getCnpj());
		this.subView.getTxtInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTxtInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getDataConstituicao().setValue(pj.getDataConstituicao());
		this.subView.getTxtSuframa().setValue(pj.getSuframa());

		TipoRegimeEn tipoRegimeEn = pj.getTipoRegime();

		if (tipoRegimeEn != null) {
			this.subView.getCmbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (crtEn != null) {
			this.subView.getCmbCrt().setValue(crtEn);
		}

		return pj;
	}

	private PessoaFisicaEntity carregarPessoaFisica() {
		PessoaFisicaEntity pf = this.currentBean.getPessoaFisica();

		this.subView.getTxtCpf().setValue(pf.getCpf());
		this.subView.getDtNascimento().setValue(pf.getDataNascimento());
		this.subView.getTxtNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTxtNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTxtNomeMae().setValue(pf.getNomeMae());
		this.subView.getTxtNomePai().setValue(pf.getNomePai());
		this.subView.getTxtNumeroRG().setValue(pf.getRg());
		this.subView.getTxtOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getDataEmissaoRG().setValue(pf.getDataEmissaoRg());
		this.subView.getTxtCNH().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (cnhEn != null) {
			this.subView.getCmbCategoriaCNH().setValue(cnhEn);
		}

		this.subView.getDtCNHEmissao().setValue(pf.getCnhVencimento());
		this.subView.getCmbEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (racaEn != null) {
			this.subView.getCmbRaca().setValue(racaEn);
		}

		TipoSangueEn tipoSangueEn = pf.getTipoSangue();

		if (pf.getTipoSangue() != null && !pf.getTipoSangue().equals("")) {
			this.subView.getCmbTipoSanguineo().setValue(tipoSangueEn);
		}

		this.subView.getTxtNumeroReservista()
				.setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (pf.getReservistaCategoria() != null
				&& !pf.getReservistaCategoria().equals("")) {
			this.subView.getCmbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		this.subView.getGrpSexo().setValue(
				"F".equals(pf.getSexo()) ? "Feminino" : "Masculino");
		this.subView.getTxtTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTxtTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTxtTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	private boolean isEnabled(Character enabled) {
		return enabled != null && '1' == enabled;
	}

	public Empresa empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Transactional
	@Override
	protected void actionSalvar() {
		try {
			this.currentBean.setNome(this.subView.getTxtNome().getValue());
			this.currentBean.setTipoPessoa((TipoPessoaEn) this.subView
					.getCmbTipoPessoa().getValue());
			this.currentBean.setEmail(this.subView.getTxtEmail().getValue());
			this.currentBean.setSite(this.subView.getTxtSite().getValue());

			List<String> values = new ArrayList<String>(
					(Collection<String>) subView.getGroup().getValue());

			for (String value : values) {
				if ("Fornecedor".equals(value)) {
					this.currentBean.setFornecedor('1');
				} else if ("Cliente".equals(value)) {
					this.currentBean.setCliente('1');
				} else if ("Colaborador".equals(value)) {
					this.currentBean.setColaborador('1');
				} else if ("Transportadora".equals(value)) {
					this.currentBean.setTransportadora('1');
				}
			}

			this.currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.F)) {
				salvarPessoaFisica();
			} else if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.J)) {
				salvarPessoaJuridica();
			}

			if (this.currentBean.getContatoList() == null
					|| this.currentBean.getContatoList().isEmpty()
					|| this.currentBean.getContatoList().size() == 0) {
				this.currentBean
						.setContatoList(new ArrayList<PessoaContatoEntity>());
			}

			if (this.currentBean.getEnderecoList() == null
					|| this.currentBean.getEnderecoList().isEmpty()
					|| this.currentBean.getEnderecoList().size() == 0) {
				this.currentBean
						.setEnderecoList(new ArrayList<PessoaEnderecoEntity>());
			}

			this.pessoaDAO.saveOrUpdatePessoa(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	private void salvarPessoaJuridica() {
		try {
			PessoaJuridicaEntity pj = this.currentBean.getPessoaJuridica();

			if (pj == null) {
				pj = new PessoaJuridicaEntity();
			}

			pj.setFantasia(this.subView.getTxtFantasia().getValue());
			pj.setCnpj(this.subView.getTxtCNPJ().getValue());
			pj.setInscricaoEstadual(this.subView.getTxtInscricaoEstadual()
					.getValue());
			pj.setInscricaoMunicipal(this.subView.getTxtInscricaoMunicipal()
					.getValue());
			pj.setDataConstituicao(this.subView.getDataConstituicao()
					.getValue());
			pj.setPessoa(this.currentBean);
			pj.setSuframa(this.subView.getTxtSuframa().getValue());

			TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
					.getCmbTipoRegime().getValue();

			if (tipoRegimeEn != null) {
				pj.setTipoRegime(tipoRegimeEn);
			}

			CrtEn crtEn = (CrtEn) this.subView.getCmbCrt().getValue();

			if (crtEn != null) {
				pj.setCrt(crtEn);
			}

			this.currentBean.setPessoaJuridica(pj);
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvarPessoaFisica() {
		try {
			PessoaFisicaEntity pf = this.currentBean.getPessoaFisica();

			if (pf == null) {
				pf = new PessoaFisicaEntity();
			}

			pf.setPessoa(this.currentBean);
			pf.setCpf(this.subView.getTxtCpf().getValue());
			pf.setDataNascimento(this.subView.getDtNascimento().getValue());
			pf.setNaturalidade(this.subView.getTxtNaturalidade().getValue());
			pf.setNacionalidade(this.subView.getTxtNacionalidade().getValue());
			pf.setNomeMae(this.subView.getTxtNomeMae().getValue());
			pf.setNomePai(this.subView.getTxtNomePai().getValue());
			pf.setRg(this.subView.getTxtNumeroRG().getValue());
			pf.setOrgaoRg(this.subView.getTxtOrgaoEmissor().getValue());
			pf.setDataEmissaoRg(this.subView.getDataEmissaoRG().getValue());
			pf.setCnhNumero(this.subView.getTxtCNH().getValue());

			CnhEn cnhEn = (CnhEn) this.subView.getCmbCategoriaCNH().getValue();

			if (cnhEn != null) {
				pf.setCnh(cnhEn);
			}

			pf.setCnhVencimento(this.subView.getDtCNHEmissao().getValue());

			if (this.subView.getCmbEstadoCivil().getValue() != null
					&& !this.subView.getCmbEstadoCivil().getValue().equals("")) {
				pf.setEstadoCivil((EstadoCivilEntity) this.subView
						.getCmbEstadoCivil().getValue());
			}

			RacaEn racaEn = (RacaEn) this.subView.getCmbRaca().getValue();

			if (racaEn != null) {
				pf.setRaca(racaEn);
			}

			TipoSangueEn tipoSangueEn = (TipoSangueEn) this.subView
					.getCmbTipoSanguineo().getValue();

			if (tipoSangueEn != null) {
				pf.setTipoSangue(tipoSangueEn);
			}

			pf.setReservistaNumero(this.subView.getTxtNumeroReservista()
					.getValue());

			CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
					.getCmbCategoriaReservista().getValue();

			if (categoriaReservistaEn != null) {
				pf.setReservistaCategoria(categoriaReservistaEn);
			}

			SexoEn sexoEn = (SexoEn) this.subView.getGrpSexo().getValue();

			if (sexoEn != null) {
				pf.setSexo(sexoEn);
			}

			pf.setTituloEleitoralNumero(this.subView.getTxtTituloEleitor()
					.getValue());
			pf.setTituloEleitoralSecao((Integer) this.subView
					.getTxtTituloSecao().getConvertedValue());
			pf.setTituloEleitoralZona((Integer) this.subView.getTxtTituloZona()
					.getConvertedValue());

			this.currentBean.setPessoaFisica(pf);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	protected void quandoNovo() {

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		try {
			this.pessoaDAO.deletePessoa(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public PessoaEntity getCurrentBean() {
		return currentBean;
	}

	public void setCurrentBean(PessoaEntity currentBean) {
		this.currentBean = currentBean;
	}

	public PessoaContatoEntity novoContato() {
		PessoaContatoEntity c = new PessoaContatoEntity();
		this.currentBean.getContatoList().add(c);

		return c;
	}

	public PessoaEnderecoEntity novoEndereco() {
		PessoaEnderecoEntity end = new PessoaEnderecoEntity();
		this.currentBean.getEnderecoList().add(end);

		return end;
	}

	public ManyToOneComboModel<UfEntity> getUfModel() {
		ManyToOneComboModel<UfEntity> model = new DefaultManyToOneComboModel<UfEntity>(
				UfListController.class, this.ufDAO, this.getMainController());

		return model;
	}

	public List<UfEntity> getUfs() {
		return ufDAO.listaTodos();
	}

	@Override
	public PessoaEntity getModelBean() {
		return currentBean;
	}

	/**
	 * COMBOS
	 */

	public void comboTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCmbTipoRegime().addItem(en);
		}
	}

	public void comboCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCmbCategoriaCNH().addItem(en);
		}
	}

	public void comboRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCmbRaca().addItem(en);
		}
	}

	public void comboCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCmbCategoriaReservista().addItem(en);
		}
	}

	public void comboCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCmbCrt().addItem(en);
		}
	}

	public void comboTipoSangue() {
		for (TipoSangueEn en : TipoSangueEn.values()) {
			this.subView.getCmbTipoSanguineo().addItem(en);
		}
	}

	public void comboTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCmbTipoPessoa().addItem(en);
		}
	}

	public void comboSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getGrpSexo().addItem(en);
		}
	}

}