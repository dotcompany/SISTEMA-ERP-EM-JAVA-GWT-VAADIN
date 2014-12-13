package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.ui.Component;

import dc.control.enums.CategoriaPessoaEn;
import dc.control.enums.CategoriaReservistaEn;
import dc.control.enums.CnhEn;
import dc.control.enums.CrtEn;
import dc.control.enums.RacaEn;
import dc.control.enums.SexoEn;
import dc.control.enums.TipoPessoaEn;
import dc.control.enums.TipoRegimeEn;
import dc.control.enums.TipoSanguineoEn;
import dc.control.util.ClassUtils;
import dc.control.util.ObjectUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.PessoaValidator;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.geral.PessoaContatoEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.servicos.dao.geral.PessoaContatoDAO;
import dc.servicos.dao.geral.PessoaEnderecoDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.pessoal.EstadoCivilDAO;
import dc.servicos.dao.geral.pessoal.PessoaDAO;
import dc.servicos.dao.geral.pessoal.PessoaFisicaDAO;
import dc.servicos.dao.geral.pessoal.PessoaJuridicaDAO;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.pessoal.PessoaFormView;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
public class PessoaFormController extends CRUDFormController<PessoaEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PessoaFormView subView;

	private PessoaEntity currentBean;

	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	@Autowired
	private PessoaContatoDAO pessoaContatoDAO;

	@Autowired
	private PessoaEnderecoDAO pessoaEnderecoDAO;

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	@Autowired
	private UfDAO ufDAO;

	@Override
	protected String getNome() {
		return "Pessoa";
	}

	@Override
	public String getViewIdentifier() {
		// TODO Auto-generated method stub
		return ClassUtils.getUrl(this);
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	@Override
	public PessoaEntity getModelBean() {
		return currentBean;
	}

	// public PessoaEntity getCurrentBean() {
	// return currentBean;
	// }

	// public void setCurrentBean(PessoaEntity currentBean) {
	// this.currentBean = currentBean;
	// }

	protected boolean validaSalvar() {
		try {
			PessoaValidator.validaSalvar(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.currentBean = new PessoaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaFormView(this);

			DefaultManyToOneComboModel<EstadoCivilEntity> model = new DefaultManyToOneComboModel<EstadoCivilEntity>(
					EstadoCivilListController.class, this.estadoCivilDAO,
					super.getMainController());

			this.subView.getCmbEstadoCivil().setModel(model);

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

			carregarTipoRegime();
			carregarCnh();
			carregarRaca();
			carregarCategoriaReservista();
			carregarCrt();
			carregarTipoSanguineo();
			carregarTipoPessoa();
			carregarSexo();
			carregarCategoriaPessoa();

			this.subView.getCmbTipoPessoa().setValue(TipoPessoaEn.F);
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

			this.subView.getOgCategoriaPessoa().setValue(selected);

			// PessoaContato

			List<PessoaContatoEntity> auxLista = this.pessoaContatoDAO
					.getPessoaContatoList(this.currentBean);

			this.currentBean.setPessoaContatoList(auxLista);

			this.subView.getSfPessoaContato().fillWith(
					this.currentBean.getPessoaContatoList());

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista1 = this.pessoaEnderecoDAO
					.getPessoaEnderecoList(this.currentBean);

			this.currentBean.setPessoaEnderecoList(auxLista1);

			this.subView.getSfPessoaEndereco().fillWith(
					this.currentBean.getPessoaEnderecoList());

			if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.F)) {
				this.pessoaFisicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaFisica(carregarPessoaFisica());
			} else if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.J)) {
				this.pessoaJuridicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaJuridica(carregarPessoaJuridica());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaJuridicaEntity carregarPessoaJuridica() {
		PessoaJuridicaEntity pj = this.currentBean.getPessoaJuridica();

		this.subView.getTxtFantasia().setValue(pj.getFantasia());
		this.subView.getTxtCnpj().setValue(pj.getCnpj());
		this.subView.getTxtInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTxtInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getDataConstituicao().setValue(pj.getDataConstituicao());
		this.subView.getTxtSuframa().setValue(pj.getSuframa());

		TipoRegimeEn tipoRegimeEn = pj.getTipoRegime();

		if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
			this.subView.getCmbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (ObjectUtils.isNotBlank(crtEn)) {
			this.subView.getCmbCrt().setValue(crtEn);
		}

		return pj;
	}

	private PessoaFisicaEntity carregarPessoaFisica() {
		PessoaFisicaEntity pf = this.currentBean.getPessoaFisica();

		this.subView.getTxtCpf().setValue(pf.getCpf());
		this.subView.getDataNascimento().setValue(pf.getDataNascimento());
		this.subView.getTxtNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTxtNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTxtNomeMae().setValue(pf.getNomeMae());
		this.subView.getTxtNomePai().setValue(pf.getNomePai());
		this.subView.getTxtNumeroRg().setValue(pf.getRg());
		this.subView.getTxtOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getDataEmissaoRg().setValue(pf.getDataEmissaoRg());
		this.subView.getTxtCnh().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			this.subView.getCmbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getCmbEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCmbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCmbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTxtNumeroReservista()
				.setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCmbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		this.subView.getOgSexo().setValue(
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

	public EmpresaEntity empresaAtual() {
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

			// List<CategoriaPessoaEn> auxLista = new
			// ArrayList<CategoriaPessoaEn>(
			// (Collection<CategoriaPessoaEn>) this.subView
			// .getOgCategoriaPessoa().getValue());

			// for (CategoriaPessoaEn en : auxLista) {
			// if ("Fornecedor".equals(value)) {
			// this.currentBean.setFornecedor('1');
			// } else if ("Cliente".equals(value)) {
			// this.currentBean.setCliente('1');
			// } else if ("Colaborador".equals(value)) {
			// this.currentBean.setColaborador('1');
			// } else if ("Transportadora".equals(value)) {
			// this.currentBean.setTransportadora('1');
			// }
			// }

			this.currentBean.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.F)) {
				salvarPessoaFisica();
			} else if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.J)) {
				salvarPessoaJuridica();
			}

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.subView
					.getSfPessoaContato().getDados();

			this.currentBean.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.subView
					.getSfPessoaEndereco().getDados();

			this.currentBean.setPessoaEnderecoList(auxLista2);

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

			pj.setPessoa(this.currentBean);

			pj.setFantasia(this.subView.getTxtFantasia().getValue());
			pj.setCnpj(this.subView.getTxtCnpj().getValue());
			pj.setInscricaoEstadual(this.subView.getTxtInscricaoEstadual()
					.getValue());
			pj.setInscricaoMunicipal(this.subView.getTxtInscricaoMunicipal()
					.getValue());
			pj.setDataConstituicao(this.subView.getDataConstituicao()
					.getValue());
			pj.setSuframa(this.subView.getTxtSuframa().getValue());

			TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
					.getCmbTipoRegime().getValue();

			if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
				pj.setTipoRegime(tipoRegimeEn);
			}

			CrtEn crtEn = (CrtEn) this.subView.getCmbCrt().getValue();

			if (ObjectUtils.isNotBlank(crtEn)) {
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
			pf.setDataNascimento(this.subView.getDataNascimento().getValue());
			pf.setNaturalidade(this.subView.getTxtNaturalidade().getValue());
			pf.setNacionalidade(this.subView.getTxtNacionalidade().getValue());
			pf.setNomeMae(this.subView.getTxtNomeMae().getValue());
			pf.setNomePai(this.subView.getTxtNomePai().getValue());
			pf.setRg(this.subView.getTxtNumeroRg().getValue());
			pf.setOrgaoRg(this.subView.getTxtOrgaoEmissor().getValue());
			pf.setDataEmissaoRg(this.subView.getDataEmissaoRg().getValue());
			pf.setCnhNumero(this.subView.getTxtCnh().getValue());

			CnhEn cnhEn = (CnhEn) this.subView.getCmbCategoriaCnh().getValue();

			if (ObjectUtils.isNotBlank(cnhEn)) {
				pf.setCnh(cnhEn);
			}

			pf.setCnhVencimento(this.subView.getDataCnhEmissao().getValue());

			EstadoCivilEntity estadoCivil = this.subView.getCmbEstadoCivil()
					.getValue();

			if (ObjectUtils.isNotBlank(estadoCivil)) {
				pf.setEstadoCivil(estadoCivil);
			}

			RacaEn racaEn = (RacaEn) this.subView.getCmbRaca().getValue();

			if (ObjectUtils.isNotBlank(racaEn)) {
				pf.setRaca(racaEn);
			}

			TipoSanguineoEn tipoSanguineoEn = (TipoSanguineoEn) this.subView
					.getCmbTipoSanguineo().getValue();

			if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
				pf.setTipoSangue(tipoSanguineoEn);
			}

			pf.setReservistaNumero(this.subView.getTxtNumeroReservista()
					.getValue());

			CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
					.getCmbCategoriaReservista().getValue();

			if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
				pf.setReservistaCategoria(categoriaReservistaEn);
			}

			SexoEn sexoEn = (SexoEn) this.subView.getOgSexo().getValue();

			if (ObjectUtils.isNotBlank(sexoEn)) {
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
	protected void remover(List<Serializable> ids) {
		try {
			this.pessoaDAO.deletePessoa(ids);

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {

	}

	public PessoaContatoEntity aderirPessoaContato() {
		try {
			PessoaContatoEntity ent = new PessoaContatoEntity();
			ent.setPessoa(this.currentBean);

			this.currentBean.getPessoaContatoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaContato(List<PessoaContatoEntity> values) {
		try {
			for (PessoaContatoEntity ent : values) {
				this.pessoaContatoDAO.delete(ent);
				this.currentBean.getPessoaContatoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	public PessoaEnderecoEntity aderirPessoaEndereco() {
		try {
			PessoaEnderecoEntity ent = new PessoaEnderecoEntity();
			ent.setPessoa(this.currentBean);

			this.currentBean.getPessoaEnderecoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaEndereco(List<PessoaEnderecoEntity> values) {
		try {
			for (PessoaEnderecoEntity ent : values) {
				this.pessoaEnderecoDAO.delete(ent);
				this.currentBean.getPessoaEnderecoList().remove(ent);
			}

			mensagemRemovidoOK();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * COMBOS
	 */

	public void carregarTipoRegime() {
		for (TipoRegimeEn en : TipoRegimeEn.values()) {
			this.subView.getCmbTipoRegime().addItem(en);
		}
	}

	public void carregarCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCmbCategoriaCnh().addItem(en);
		}
	}

	public void carregarRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCmbRaca().addItem(en);
		}
	}

	public void carregarCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCmbCategoriaReservista().addItem(en);
		}
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCmbCrt().addItem(en);
		}
	}

	public void carregarTipoSanguineo() {
		for (TipoSanguineoEn en : TipoSanguineoEn.values()) {
			this.subView.getCmbTipoSanguineo().addItem(en);
		}
	}

	public void carregarTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCmbTipoPessoa().addItem(en);
		}
	}

	public void carregarSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getOgSexo().addItem(en);
		}
	}

	public void carregarCategoriaPessoa() {
		for (CategoriaPessoaEn en : CategoriaPessoaEn.values()) {
			this.subView.getOgCategoriaPessoa().addItem(en);
		}
	}

	public List<UfEntity> getUfList() {
		try {
			List<UfEntity> auxLista = this.ufDAO.listaTodos();

			return auxLista;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}