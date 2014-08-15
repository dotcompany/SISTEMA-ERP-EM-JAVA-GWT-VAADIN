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

import dc.controller.geral.UFListController;
import dc.entidade.framework.Empresa;
import dc.entidade.geral.Endereco;
import dc.entidade.geral.Pessoa;
import dc.entidade.geral.PessoaContato;
import dc.entidade.geral.PessoaFisica;
import dc.entidade.geral.PessoaJuridica;
import dc.entidade.geral.UF;
import dc.entidade.pessoal.EstadoCivil;
import dc.servicos.dao.geral.UFDAO;
import dc.servicos.dao.pessoal.EstadoCivilDAO;
import dc.servicos.dao.pessoal.PessoaDAO;
import dc.servicos.dao.pessoal.PessoaFisicaDAO;
import dc.servicos.dao.pessoal.PessoaJuridicaDAO;
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
import dc.visao.suprimentos.NotaFiscalFormView.CRT;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class PessoaFormController extends CRUDFormController<Pessoa> {

	PessoaFormView subView;

	@Autowired
	private PessoaDAO dao;

	@Autowired
	private PessoaFisicaDAO pessoaFisicaDAO;

	@Autowired
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	Pessoa currentBean;

	@Autowired
	private UFDAO ufDAO;

	@Override
	public String getViewIdentifier() {
		return "PessoaForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Pessoa();
	}

	@Override
	protected void initSubView() {

		try {

			subView = new PessoaFormView(this);

			DefaultManyToOneComboModel<EstadoCivil> estadoCivilModel = new DefaultManyToOneComboModel<EstadoCivil>(EstadoCivilListController.class,
					this.estadoCivilDAO, super.getMainController()) {

				@Override
				public String getCaptionProperty() {
					return "nome";

				}

			};
			this.subView.getCmbEstadoCivil().setModel(estadoCivilModel);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);

		subView.getTxtNome().setValue(currentBean.getNome());
		subView.getCmbTipoPessoa().setValue(TipoPessoa.getTipoPessoa(currentBean.getTipo()));
		subView.getTxtEmail().setValue(currentBean.getEmail());
		subView.getTxtSite().setValue(currentBean.getSite());

		Set<String> selected = new HashSet<String>();

		if (isEnabled(currentBean.getFornecedor())) {
			selected.add("Fornecedor");
		}

		if (isEnabled(currentBean.getCliente())) {
			selected.add("Cliente");
		}
		if (isEnabled(currentBean.getColaborador())) {
			selected.add("Colaborador");
		}
		if (isEnabled(currentBean.getConvenio())) {
			selected.add("Convênio");
		}
		if (isEnabled(currentBean.getTransportadora())) {
			selected.add("Transportadora");
		}

		subView.getGroup().setValue(selected);

		subView.getEnderecosSubForm().fillWith(currentBean.getEnderecos());
		subView.getContatosSubForm().fillWith(currentBean.getContatos());

		if ("F".equals(currentBean.getTipo())) {
			carregarPessoaFisica(id);
		} else if ("J".equals(currentBean.getTipo())) {
			carregarPessoaJuridica(id);
		}
	}

	private void carregarPessoaJuridica(Serializable id) {
		PessoaJuridica pj = dao.getPessoaJuridica((Integer) id);

		subView.getTxtFantasia().setValue(pj.getFantasia());
		subView.getTxtCNPJ().setValue(pj.getCnpj());
		subView.getTxtInscricaoEstadual().setValue(pj.getInscricaoEstadual());
		subView.getTxtInscricaoMunicipal().setValue(pj.getInscricaoMunicipal());
		subView.getDataConstituicao().setValue(pj.getDataConstituicao());
		subView.getTxtSuframa().setValue(pj.getSuframa());
		subView.getCmbTipoRegime().setValue(TipoRegime.getTipoRegime(String.valueOf(pj.getTipoRegime())));
		subView.getCmbCrt().setValue(CRT.getCRT(String.valueOf(pj.getCrt())));

	}

	private void carregarPessoaFisica(Serializable id) {
		PessoaFisica pf = dao.getPessoaFisica((Integer) id);

		subView.getTxtCpf().setValue(pf.getCpf());
		subView.getDtNascimento().setValue(pf.getDataNascimento());
		subView.getTxtNaturalidade().setValue(pf.getNaturalidade());
		subView.getTxtNacionalidade().setValue(pf.getNacionalidade());
		subView.getTxtNomeMae().setValue(pf.getNomeMae());
		subView.getTxtNomePai().setValue(pf.getNomePai());
		subView.getTxtNumeroRG().setValue(pf.getRg());
		subView.getTxtOrgaoEmissor().setValue(pf.getOrgaoRg());
		subView.getDataEmissaoRG().setValue(pf.getDataEmissaoRg());
		subView.getTxtCNH().setValue(pf.getCnhNumero());
		subView.getCmbCategoriaCNH().setValue(CNHCategoria.getCNHCategoria(String.valueOf(pf.getCnhCategoria())));
		subView.getDtCNHEmissao().setValue(pf.getCnhVencimento());
		subView.getCmbEstadoCivil().setValue(pf.getEstadoCivil());
		subView.getCmbRaca().setValue(Raca.getRaca(String.valueOf(pf.getRaca())));
		subView.getCmbTipoSanguineo().setValue(TipoSangue.getTipoSangue(pf.getTipoSangue()));
		subView.getTxtNumeroReservista().setValue(pf.getReservistaNumero());
		subView.getCmbCategoriaReservista().setValue(CategoriaReservista.getCategoriaReservista(String.valueOf(pf.getReservistaCategoria())));
		subView.getGrpSexo().setValue("F".equals(pf.getSexo()) ? "Feminino" : "Masculino");
		subView.getTxtTituloEleitor().setValue(pf.getTituloEleitoralNumero());
		subView.getTxtTituloSecao().setConvertedValue(pf.getTituloEleitoralSecao());
		subView.getTxtTituloZona().setConvertedValue(pf.getTituloEleitoralZona());
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
			TipoPessoa tpPessoa = (TipoPessoa) subView.getCmbTipoPessoa().getValue();
			currentBean.setNome(subView.getTxtNome().getValue());
			currentBean.setTipo(tpPessoa.getCodigo());
			currentBean.setEmail(subView.getTxtEmail().getValue());
			currentBean.setSite(subView.getTxtSite().getValue());

			List<String> values = new ArrayList<String>((Collection<String>) subView.getGroup().getValue());

			for (String value : values) {
				if ("Fornecedor".equals(value)) {
					currentBean.setFornecedor('1');
				} else if ("Cliente".equals(value)) {
					currentBean.setCliente('1');
				} else if ("Colaborador".equals(value)) {
					currentBean.setColaborador('1');
				} else if ("Convênio".equals(value)) {
					currentBean.setConvenio('1');
				} else if ("Transportadora".equals(value)) {
					currentBean.setTransportadora('1');
				}

			}

			currentBean.setEmpresa(SecuritySessionProvider.getUsuario().getEmpresa());

			if ("F".equals(tpPessoa.getCodigo())) {
				salvarPessoaFisica();
			} else if ("J".equals(tpPessoa.getCodigo())) {
				salvarPessoaJuridica();
			}

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro("Erro!!");
			e.printStackTrace();
		}
	}

	private void salvarPessoaJuridica() {
		PessoaJuridica pj = dao.getPessoaJuridica(currentBean.getId());
		if (pj == null) {
			pj = new PessoaJuridica();
		}

		pj.setFantasia(subView.getTxtFantasia().getValue());
		pj.setCnpj(subView.getTxtCNPJ().getValue());
		pj.setInscricaoEstadual(subView.getTxtInscricaoEstadual().getValue());
		pj.setInscricaoMunicipal(subView.getTxtInscricaoMunicipal().getValue());
		pj.setDataConstituicao(subView.getDataConstituicao().getValue());
		pj.setPessoa(currentBean);
		pj.setSuframa(subView.getTxtSuframa().getValue());
		pj.setTipoRegime(subView.getCmbTipoRegime().getValue().toString().charAt(0));
		pj.setCrt(subView.getCmbCrt().getValue().toString().charAt(0));
		dao.saveOrUpdate(currentBean);
		pessoaJuridicaDAO.saveOrUpdate(pj);

	}

	private void salvarPessoaFisica() {
		PessoaFisica pf = dao.getPessoaFisica(currentBean.getId());

		if (pf == null) {
			pf = new PessoaFisica();
		}

		pf.setPessoa(currentBean);
		pf.setCpf(subView.getTxtCpf().getValue());
		pf.setDataNascimento(subView.getDtNascimento().getValue());
		pf.setNaturalidade(subView.getTxtNaturalidade().getValue());
		pf.setNacionalidade(subView.getTxtNacionalidade().getValue());
		pf.setNomeMae(subView.getTxtNomeMae().getValue());
		pf.setNomePai(subView.getTxtNomePai().getValue());
		pf.setRg(subView.getTxtNumeroRG().getValue());
		pf.setOrgaoRg(subView.getTxtOrgaoEmissor().getValue());
		pf.setDataEmissaoRg(subView.getDataEmissaoRG().getValue());
		pf.setCnhNumero(subView.getTxtCNH().getValue());
		pf.setCnhCategoria(((CNHCategoria) subView.getCmbCategoriaCNH().getValue()).getCodigo().charAt(0));
		pf.setCnhVencimento(subView.getDtCNHEmissao().getValue());
		pf.setEstadoCivil((EstadoCivil) subView.getCmbEstadoCivil().getValue());
		pf.setRaca(((Raca) subView.getCmbRaca().getValue()).getCodigo().charAt(0));
		pf.setTipoSangue(((TipoSangue) subView.getCmbTipoSanguineo().getValue()).getCodigo());
		pf.setReservistaNumero(subView.getTxtNumeroReservista().getValue());
		pf.setReservistaCategoria(Integer.parseInt(((CategoriaReservista) subView.getCmbCategoriaReservista().getValue()).getCodigo()));
		pf.setSexo(subView.getGrpSexo().getValue().toString().charAt(0));
		pf.setTituloEleitoralNumero(subView.getTxtTituloEleitor().getValue());
		pf.setTituloEleitoralSecao((Integer) subView.getTxtTituloSecao().getConvertedValue());
		pf.setTituloEleitoralZona((Integer) subView.getTxtTituloZona().getConvertedValue());
		dao.saveOrUpdate(currentBean);
		pessoaFisicaDAO.saveOrUpdate(pf);
	}

	@Override
	protected void quandoNovo() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
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
		dao.deleteAllByIds(ids);
	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		System.out.println("");
	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public PessoaContato novoContato() {
		PessoaContato c = new PessoaContato();
		currentBean.adicionarContato(c);
		return c;
	}

	public Endereco novoEndereco() {
		Endereco end = new Endereco();
		currentBean.adicionarEndereco(end);

		return end;
	}

	public ManyToOneComboModel<UF> getUfModel() {
		ManyToOneComboModel<UF> model = new DefaultManyToOneComboModel<UF>(UFListController.class, ufDAO, this.getMainController());
		return model;
	}

	public List<UF> getUfs() {
		return ufDAO.listaTodos();
	}

	@Override
	public Pessoa getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}