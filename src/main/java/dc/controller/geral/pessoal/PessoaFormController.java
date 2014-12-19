package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

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
import dc.control.util.StringUtils;
import dc.control.validator.DotErpException;
import dc.control.validator.classe.PessoaValidator;
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

			this.subView.getMocEstadoCivil().setModel(model);

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
			// carregarCategoriaPessoa();

			// Valores iniciais

			this.subView.getCbTipoPessoa().setValue(TipoPessoaEn.F);
			this.subView.getOgSexo().setValue(SexoEn.F);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.currentBean = this.pessoaDAO.find(id);

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.pessoaContatoDAO
					.getPessoaContatoList(this.currentBean);

			this.currentBean.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.pessoaEnderecoDAO
					.getPessoaEnderecoList(this.currentBean);

			this.currentBean.setPessoaEnderecoList(auxLista2);

			this.currentBean.setPessoaFisica(this.pessoaFisicaDAO
					.getEntity(this.currentBean));
			this.currentBean.setPessoaJuridica(this.pessoaJuridicaDAO
					.getEntity(this.currentBean));

			this.subView.getTxtNome().setValue(this.currentBean.getNome());

			this.subView.getCbTipoPessoa().setValue(
					this.currentBean.getTipoPessoa());

			this.subView.getTxtEmail().setValue(this.currentBean.getEmail());
			this.subView.getTxtSite().setValue(this.currentBean.getSite());

			if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.F)) {
				this.pessoaFisicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaFisica(carregarPessoaFisica());
			} else if (this.currentBean.getTipoPessoa().equals(TipoPessoaEn.J)) {
				this.pessoaJuridicaDAO.getEntity(this.currentBean);

				this.currentBean.setPessoaJuridica(carregarPessoaJuridica());
			}

			this.subView.getCkCliente().setValue(
					this.currentBean.getCliente().equals("0") ? Boolean.FALSE
							: Boolean.TRUE);
			this.subView.getCkColaborador().setValue(
					this.currentBean.getColaborador());
			this.subView.getCkFornecedor().setValue(
					this.currentBean.getFornecedor());
			this.subView.getCkTransportadora().setValue(
					this.currentBean.getTransportadora());

			// PessoaContato

			this.subView.getSfPessoaContato().fillWith(
					this.currentBean.getPessoaContatoList());

			// PessoaEndereco

			for (PessoaEnderecoEntity ent : this.currentBean
					.getPessoaEnderecoList()) {
				if (StringUtils.isNotBlank(ent.getSiglaUf())) {
					UfEntity uf = this.ufDAO.find(ent.getIdUf());

					ent.setUf(uf);
				}
			}

			this.subView.getSfPessoaEndereco().fillWith(
					this.currentBean.getPessoaEnderecoList());
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
			this.subView.getCbTipoRegime().setValue(tipoRegimeEn);
		}

		CrtEn crtEn = pj.getCrt();

		if (ObjectUtils.isNotBlank(crtEn)) {
			this.subView.getCbCrt().setValue(crtEn);
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
			this.subView.getCbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getMocEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTxtNumeroReservista()
				.setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.currentBean.getPessoaFisica().getSexo();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			this.subView.getOgSexo().setValue(sexoEn);
		}

		this.subView.getTxtTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTxtTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTxtTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	@Override
	protected void actionSalvar() {
		try {
			TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) this.subView
					.getCbTipoPessoa().getValue();

			this.currentBean.setTipoPessoa(tipoPessoaEn);

			this.currentBean.setNome(this.subView.getTxtNome().getValue());
			this.currentBean.setEmail(this.subView.getTxtEmail().getValue());
			this.currentBean.setSite(this.subView.getTxtSite().getValue());

			//

			// Object[] auxLista = this.subView.getOgCategoriaPessoa()
			// .getItemIds().toArray();

			// for (Object obj : auxLista) {
			// CategoriaPessoaEn en = (CategoriaPessoaEn) obj;
			//
			// if (en.equals(CategoriaPessoaEn.C)) {
			// this.currentBean
			// .setCliente(this.subView.getOgCategoriaPessoa()
			// .isSelected(en) == Boolean.TRUE ? "1" : "0");
			// } else if (en.equals(CategoriaPessoaEn.F)) {
			// this.currentBean.setFornecedor(this.subView
			// .getOgCategoriaPessoa().isSelected(en));
			// } else if (en.equals(CategoriaPessoaEn.O)) {
			// this.currentBean.setColaborador(this.subView
			// .getOgCategoriaPessoa().isSelected(en));
			// } else if (en.equals(CategoriaPessoaEn.T)) {
			// this.currentBean.setTransportadora(this.subView
			// .getOgCategoriaPessoa().isSelected(en));
			// }
			// }

			this.currentBean.setCliente(this.subView.getCkCliente().getValue()
					.equals(Boolean.TRUE) ? "1" : "0");
			this.currentBean.setColaborador(this.subView.getCkColaborador()
					.getValue());
			this.currentBean.setFornecedor(this.subView.getCkFornecedor()
					.getValue());
			this.currentBean.setTransportadora(this.subView
					.getCkTransportadora().getValue());

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
					.getCbTipoRegime().getValue();

			if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
				pj.setTipoRegime(tipoRegimeEn);
			}

			CrtEn crtEn = (CrtEn) this.subView.getCbCrt().getValue();

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

			CnhEn cnhEn = (CnhEn) this.subView.getCbCategoriaCnh().getValue();

			if (ObjectUtils.isNotBlank(cnhEn)) {
				pf.setCnh(cnhEn);
			}

			pf.setCnhVencimento(this.subView.getDataCnhEmissao().getValue());

			EstadoCivilEntity estadoCivil = this.subView.getMocEstadoCivil()
					.getValue();

			if (ObjectUtils.isNotBlank(estadoCivil)) {
				pf.setEstadoCivil(estadoCivil);
			}

			RacaEn racaEn = (RacaEn) this.subView.getCbRaca().getValue();

			if (ObjectUtils.isNotBlank(racaEn)) {
				pf.setRaca(racaEn);
			}

			TipoSanguineoEn tipoSanguineoEn = (TipoSanguineoEn) this.subView
					.getCbTipoSanguineo().getValue();

			if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
				pf.setTipoSangue(tipoSanguineoEn);
			}

			pf.setReservistaNumero(this.subView.getTxtNumeroReservista()
					.getValue());

			CategoriaReservistaEn categoriaReservistaEn = (CategoriaReservistaEn) this.subView
					.getCbCategoriaReservista().getValue();

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
			this.subView.getCbTipoRegime().addItem(en);
		}
	}

	public void carregarCnh() {
		for (CnhEn en : CnhEn.values()) {
			this.subView.getCbCategoriaCnh().addItem(en);
		}
	}

	public void carregarRaca() {
		for (RacaEn en : RacaEn.values()) {
			this.subView.getCbRaca().addItem(en);
		}
	}

	public void carregarCategoriaReservista() {
		for (CategoriaReservistaEn en : CategoriaReservistaEn.values()) {
			this.subView.getCbCategoriaReservista().addItem(en);
		}
	}

	public void carregarCrt() {
		for (CrtEn en : CrtEn.values()) {
			this.subView.getCbCrt().addItem(en);
		}
	}

	public void carregarTipoSanguineo() {
		for (TipoSanguineoEn en : TipoSanguineoEn.values()) {
			this.subView.getCbTipoSanguineo().addItem(en);
		}
	}

	public void carregarTipoPessoa() {
		for (TipoPessoaEn en : TipoPessoaEn.values()) {
			this.subView.getCbTipoPessoa().addItem(en);
		}
	}

	public void carregarSexo() {
		for (SexoEn en : SexoEn.values()) {
			this.subView.getOgSexo().addItem(en);
		}
	}

	// public void carregarCategoriaPessoa() {
	// for (CategoriaPessoaEn en : CategoriaPessoaEn.values()) {
	// this.subView.getOgCategoriaPessoa().addItem(en);
	// }
	// }

	public BeanItemContainer<UfEntity> getUfBic() {
		try {
			List<UfEntity> auxLista = this.ufDAO.listaTodos();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			return bic;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

}