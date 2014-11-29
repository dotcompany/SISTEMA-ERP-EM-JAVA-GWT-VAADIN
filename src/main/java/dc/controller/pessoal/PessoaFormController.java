package dc.controller.pessoal;

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

import dc.control.util.ClasseUtil;
import dc.controller.geral.UFListController;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.PessoaContato;
import dc.entidade.geral.PessoaEndereco;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.EstadoCivilEntity;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.EstadoCivilDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.PessoaFisicaDAO;
import dc.servicos.dao.pessoal.PessoaJuridicaDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.ManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.pessoal.PessoaFormView;
import dc.visao.pessoal.PessoaFormView.CNHCategoria;
import dc.visao.pessoal.PessoaFormView.CategoriaReservista;
import dc.visao.pessoal.PessoaFormView.Raca;
import dc.visao.pessoal.PessoaFormView.TipoRegime;
import dc.visao.pessoal.PessoaFormView.TipoSangue;
import dc.visao.pessoal.TipoPessoa;
import dc.visao.spring.SecuritySessionProvider;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.CRT;

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
		return ClasseUtil.getUrl(this);
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
					TipoPessoa.getTipoPessoa(this.currentBean.getTipo()));
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
					this.currentBean.getEnderecos());
			this.subView.getContatosSubForm().fillWith(
					this.currentBean.getContatos());

			if ("F".equals(this.currentBean.getTipo())) {
				carregarPessoaFisica(id);
			} else if ("J".equals(this.currentBean.getTipo())) {
				carregarPessoaJuridica(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void carregarPessoaJuridica(Serializable id) {
		PessoaJuridicaEntity pj = this.currentBean.getPessoaJuridica();

		this.subView.getTxtFantasia().setValue(pj.getFantasia());
		this.subView.getTxtCNPJ().setValue(pj.getCnpj());
		this.subView.getTxtInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTxtInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getDataConstituicao().setValue(pj.getDataConstituicao());
		this.subView.getTxtSuframa().setValue(pj.getSuframa());

		if (pj.getTipoRegime() != null
				&& !pj.getTipoRegime().toString().equals("")) {
			this.subView.getCmbTipoRegime()
					.setValue(
							TipoRegime.getTipoRegime(String.valueOf(pj
									.getTipoRegime())));
		}

		if (pj.getCrt() != null && !pj.getCrt().toString().equals("")) {
			this.subView.getCmbCrt().setValue(
					CRT.getCRT(String.valueOf(pj.getCrt())));
		}
	}

	private void carregarPessoaFisica(Serializable id) {
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

		if (pf.getCnhCategoria() != null
				&& !pf.getCnhCategoria().toString().equals("")) {
			this.subView.getCmbCategoriaCNH().setValue(
					CNHCategoria.getCNHCategoria(String.valueOf(pf
							.getCnhCategoria())));
		}

		this.subView.getDtCNHEmissao().setValue(pf.getCnhVencimento());
		this.subView.getCmbEstadoCivil().setValue(pf.getEstadoCivil());

		if (pf.getRaca() != null && !pf.getRaca().toString().equals("")) {
			this.subView.getCmbRaca().setValue(
					Raca.getRaca(String.valueOf(pf.getRaca())));
		}

		if (pf.getTipoSangue() != null && !pf.getTipoSangue().equals("")) {
			this.subView.getCmbTipoSanguineo().setValue(
					TipoSangue.getTipoSangue(pf.getTipoSangue()));
		}

		this.subView.getTxtNumeroReservista()
				.setValue(pf.getReservistaNumero());

		if (pf.getReservistaCategoria() != null
				&& !pf.getReservistaCategoria().equals("")) {
			this.subView.getCmbCategoriaReservista().setValue(
					CategoriaReservista.getCategoriaReservista(String
							.valueOf(pf.getReservistaCategoria())));
		}

		this.subView.getGrpSexo().setValue(
				"F".equals(pf.getSexo()) ? "Feminino" : "Masculino");
		this.subView.getTxtTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTxtTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTxtTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());
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
			TipoPessoa tipoPessoa = (TipoPessoa) this.subView
					.getCmbTipoPessoa().getValue();

			this.currentBean.setNome(this.subView.getTxtNome().getValue());
			this.currentBean.setTipo(tipoPessoa.getCodigo());
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

			if ("F".equals(tipoPessoa.getCodigo())) {
				salvarPessoaFisica();

				this.currentBean.setPessoaJuridica(null);
			} else if ("J".equals(tipoPessoa.getCodigo())) {
				salvarPessoaJuridica();

				this.currentBean.setPessoaFisica(null);
			}

			if (this.currentBean.getContatos() == null
					|| this.currentBean.getContatos().isEmpty()
					|| this.currentBean.getContatos().size() == 0) {
				this.currentBean.setContatos(new ArrayList<PessoaContato>());
			}

			if (this.currentBean.getEnderecos() == null
					|| this.currentBean.getEnderecos().isEmpty()
					|| this.currentBean.getEnderecos().size() == 0) {
				this.currentBean.setEnderecos(new ArrayList<PessoaEndereco>());
			}

			this.pessoaDAO.saveOrUpdatePessoa(this.currentBean);

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			// mensagemErro("Erro!!");
			e.printStackTrace();
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

			if (this.subView.getCmbTipoRegime().getValue() != null
					&& !this.subView.getCmbTipoRegime().getValue().equals("")) {
				pj.setTipoRegime(this.subView.getCmbTipoRegime().getValue()
						.toString().charAt(0));
			}

			if (this.subView.getCmbCrt().getValue() != null
					&& !this.subView.getCmbCrt().getValue().equals("")) {
				pj.setCrt(this.subView.getCmbCrt().getValue().toString()
						.charAt(0));
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

			if (this.subView.getCmbCategoriaCNH().getValue() != null
					&& !this.subView.getCmbCategoriaCNH().getValue().equals("")) {
				pf.setCnhCategoria(((CNHCategoria) this.subView
						.getCmbCategoriaCNH().getValue()).getCodigo().charAt(0));
			}

			pf.setCnhVencimento(this.subView.getDtCNHEmissao().getValue());

			if (this.subView.getCmbEstadoCivil().getValue() != null
					&& !this.subView.getCmbEstadoCivil().getValue().equals("")) {
				pf.setEstadoCivil((EstadoCivilEntity) this.subView
						.getCmbEstadoCivil().getValue());
			}

			if (this.subView.getCmbRaca().getValue() != null
					&& !this.subView.getCmbRaca().getValue().equals("")) {
				pf.setRaca(((Raca) this.subView.getCmbRaca().getValue())
						.getCodigo().charAt(0));
			}

			if (this.subView.getCmbTipoSanguineo().getValue() != null
					&& !this.subView.getCmbTipoSanguineo().getValue()
							.equals("")) {
				pf.setTipoSangue(((TipoSangue) this.subView
						.getCmbTipoSanguineo().getValue()).getCodigo());
			}

			pf.setReservistaNumero(this.subView.getTxtNumeroReservista()
					.getValue());

			if (this.subView.getCmbCategoriaReservista().getValue() != null
					&& !this.subView.getCmbCategoriaReservista().getValue()
							.equals("")) {
				pf.setReservistaCategoria(Integer
						.parseInt(((CategoriaReservista) this.subView
								.getCmbCategoriaReservista().getValue())
								.getCodigo()));
			}

			if (this.subView.getGrpSexo().getValue() != null
					&& !this.subView.getGrpSexo().getValue().equals("")) {
				pf.setSexo(this.subView.getGrpSexo().getValue().toString()
						.charAt(0));
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
		// dao.deleteAllByIds(ids);

		mensagemRemovidoOK();
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

	public PessoaContato novoContato() {
		PessoaContato c = new PessoaContato();
		currentBean.adicionarContato(c);

		return c;
	}

	public PessoaEndereco novoEndereco() {
		PessoaEndereco end = new PessoaEndereco();
		currentBean.adicionarEndereco(end);

		return end;
	}

	public ManyToOneComboModel<UF> getUfModel() {
		ManyToOneComboModel<UF> model = new DefaultManyToOneComboModel<UF>(
				UFListController.class, ufDAO, this.getMainController());

		return model;
	}

	public List<UF> getUfs() {
		return ufDAO.listaTodos();
	}

	@Override
	public PessoaEntity getModelBean() {
		return currentBean;
	}

}