package dc.controller.geral.pessoal;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.Property.ValueChangeEvent;
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
import dc.control.util.classes.PessoaUtils;
import dc.control.validator.DotErpException;
import dc.entidade.geral.PessoaContatoEntity;
import dc.entidade.geral.PessoaEnderecoEntity;
import dc.entidade.geral.PessoaEntity;
import dc.entidade.geral.PessoaFisicaEntity;
import dc.entidade.geral.PessoaJuridicaEntity;
import dc.entidade.geral.UfEntity;
import dc.entidade.geral.pessoal.EstadoCivilEntity;
import dc.model.business.geral.diverso.UfBusiness;
import dc.model.business.geral.pessoal.PessoaBusiness;
import dc.model.business.geral.pessoal.PessoaContatoBusiness;
import dc.model.business.geral.pessoal.PessoaEnderecoBusiness;
import dc.servicos.dao.geral.pessoal.EstadoCivilDAO;
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

	/**
	 * ENTITY
	 */

	private PessoaEntity entity;

	/**
	 * BUSINESS
	 */

	@Autowired
	private PessoaBusiness<PessoaEntity> business;

	@Autowired
	private PessoaContatoBusiness<PessoaContatoEntity> pessoaContatoBusiness;

	@Autowired
	private PessoaEnderecoBusiness<PessoaEnderecoEntity> pessoaEnderecoBusiness;

	@Autowired
	private UfBusiness<UfEntity> ufBusiness;

	/**
	 * DAO
	 */

	@Autowired
	private EstadoCivilDAO estadoCivilDAO;

	/**
	 * CONSTRUTOR
	 */

	public PessoaFormController() {
		// TODO Auto-generated constructor stub
	}

	public PessoaBusiness<PessoaEntity> getBusiness() {
		return business;
	}

	@Override
	protected String getNome() {
		return "Pessoa";
	}

	@Override
	protected Component getSubView() {
		return subView;
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
		return entity;
	}

	@Override
	protected void initSubView() {
		try {
			this.subView = new PessoaFormView(this);

			DefaultManyToOneComboModel<EstadoCivilEntity> model = new DefaultManyToOneComboModel<EstadoCivilEntity>(
					EstadoCivilListController.class, this.estadoCivilDAO,
					super.getMainController());

			this.subView.getMocEstadoCivil().setModel(model);

			carregarTipoRegime();
			carregarCnh();
			carregarRaca();
			carregarCategoriaReservista();
			carregarCrt();
			carregarTipoSanguineo();
			carregarTipoPessoa();
			carregarSexo();

			// Valores iniciais

			this.subView.getCbTipoPessoa().setValue(TipoPessoaEn.F);
			this.subView.getOgSexo().setValue(SexoEn.F);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean validaSalvar() {
		try {
			PessoaUtils.validateRequiredFields(this.subView);

			return true;
		} catch (DotErpException dee) {
			adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

			return false;
		}
	}

	@Override
	protected void actionSalvar() {
		try {
			TipoPessoaEn tipoPessoaEn = (TipoPessoaEn) this.subView
					.getCbTipoPessoa().getValue();

			this.entity.setTipoPessoa(tipoPessoaEn);

			this.entity.setNome(this.subView.getTfNome().getValue());
			this.entity.setEmail(this.subView.getTfEmail().getValue());
			this.entity.setSite(this.subView.getTfSite().getValue());

			this.entity.setCliente(this.subView.getCkCliente().getValue()
					.equals(Boolean.TRUE) ? "1" : "0");
			this.entity.setColaborador(this.subView.getCkColaborador()
					.getValue());
			this.entity
					.setFornecedor(this.subView.getCkFornecedor().getValue());
			this.entity.setTransportadora(this.subView.getCkTransportadora()
					.getValue());

			this.entity.setEmpresa(SecuritySessionProvider.getUsuario()
					.getEmpresa());

			if (this.entity.getTipoPessoa().equals(TipoPessoaEn.F)) {
				salvarPessoaFisica();
			} else if (this.entity.getTipoPessoa().equals(TipoPessoaEn.J)) {
				salvarPessoaJuridica();
			}

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.subView
					.getSfPessoaContato().getDados();

			this.entity.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.subView
					.getSfPessoaEndereco().getDados();

			this.entity.setPessoaEnderecoList(auxLista2);

			this.business.saveOrUpdate(this.entity);

			notifiyFrameworkSaveOK(this.entity);
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	private void salvarPessoaJuridica() {
		try {
			PessoaJuridicaEntity pj = this.entity.getPessoaJuridica();

			if (pj == null) {
				pj = new PessoaJuridicaEntity();
			}

			pj.setPessoa(this.entity);

			pj.setFantasia(this.subView.getTfFantasia().getValue());
			pj.setCnpj(this.subView.getMtfCnpj().getValue());
			pj.setInscricaoEstadual(this.subView.getTfInscricaoEstadual()
					.getValue());
			pj.setInscricaoMunicipal(this.subView.getTfInscricaoMunicipal()
					.getValue());
			pj.setDataConstituicao(this.subView.getPdfDataConstituicao()
					.getValue());
			pj.setSuframa(this.subView.getTfSuframa().getValue());

			TipoRegimeEn tipoRegimeEn = (TipoRegimeEn) this.subView
					.getCbTipoRegime().getValue();

			if (ObjectUtils.isNotBlank(tipoRegimeEn)) {
				pj.setTipoRegime(tipoRegimeEn);
			}

			CrtEn crtEn = (CrtEn) this.subView.getCbCrt().getValue();

			if (ObjectUtils.isNotBlank(crtEn)) {
				pj.setCrt(crtEn);
			}

			this.entity.setPessoaJuridica(pj);
		} catch (Exception e) {
			throw e;
		}
	}

	private void salvarPessoaFisica() {
		try {
			PessoaFisicaEntity pf = this.entity.getPessoaFisica();

			if (pf == null) {
				pf = new PessoaFisicaEntity();
			}

			pf.setPessoa(this.entity);

			pf.setCpf(this.subView.getMtfCpf().getValue());
			pf.setDataNascimento(this.subView.getPdfDataNascimento().getValue());
			pf.setNaturalidade(this.subView.getTfNaturalidade().getValue());
			pf.setNacionalidade(this.subView.getTfNacionalidade().getValue());
			pf.setNomeMae(this.subView.getTfNomeMae().getValue());
			pf.setNomePai(this.subView.getTfNomePai().getValue());
			pf.setRg(this.subView.getTfNumeroRg().getValue());
			pf.setOrgaoRg(this.subView.getTfOrgaoEmissor().getValue());
			pf.setDataEmissaoRg(this.subView.getPdfDataEmissaoRg().getValue());
			pf.setCnhNumero(this.subView.getTfCnh().getValue());

			CnhEn cnhEn = (CnhEn) this.subView.getCbCategoriaCnh().getValue();

			if (ObjectUtils.isNotBlank(cnhEn)) {
				pf.setCnh(cnhEn);
			}

			pf.setCnhVencimento(this.subView.getPdfDataCnhEmissao().getValue());

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

			pf.setReservistaNumero(this.subView.getTfNumeroReservista()
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

			pf.setTituloEleitoralNumero(this.subView.getTfTituloEleitor()
					.getValue());
			pf.setTituloEleitoralSecao((Integer) this.subView
					.getTfTituloSecao().getConvertedValue());
			pf.setTituloEleitoralZona((Integer) this.subView.getTfTituloZona()
					.getConvertedValue());

			this.entity.setPessoaFisica(pf);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			this.entity = this.business.find(id);

			// PessoaContato

			List<PessoaContatoEntity> auxLista1 = this.pessoaContatoBusiness
					.list(this.entity);

			this.entity.setPessoaContatoList(auxLista1);

			// PessoaEndereco

			List<PessoaEnderecoEntity> auxLista2 = this.pessoaEnderecoBusiness
					.list(this.entity);

			this.entity.setPessoaEnderecoList(auxLista2);

			this.subView.getTfNome().setValue(this.entity.getNome());

			this.subView.getCbTipoPessoa()
					.setValue(this.entity.getTipoPessoa());

			this.subView.getTfEmail().setValue(this.entity.getEmail());
			this.subView.getTfSite().setValue(this.entity.getSite());

			if (this.entity.getTipoPessoa().equals(TipoPessoaEn.F)) {
				this.entity.setPessoaFisica(carregarPessoaFisica());
			} else if (this.entity.getTipoPessoa().equals(TipoPessoaEn.J)) {
				this.entity.setPessoaJuridica(carregarPessoaJuridica());
			}

			this.subView.getCkCliente().setValue(
					this.entity.getCliente().equals("0") ? Boolean.FALSE
							: Boolean.TRUE);

			this.subView.getCkColaborador().setValue(
					this.entity.getColaborador());

			this.subView.getCkFornecedor()
					.setValue(this.entity.getFornecedor());

			this.subView.getCkTransportadora().setValue(
					this.entity.getTransportadora());

			// PessoaContato

			this.subView.getSfPessoaContato().fillWith(
					this.entity.getPessoaContatoList());

			// PessoaEndereco

			for (PessoaEnderecoEntity ent : this.entity.getPessoaEnderecoList()) {
				if (StringUtils.isNotBlank(ent.getSiglaUf())) {
					UfEntity uf = this.ufBusiness.find(ent.getIdUf());

					ent.setUf(uf);
				}
			}

			this.subView.getSfPessoaEndereco().fillWith(
					this.entity.getPessoaEnderecoList());

			visualizarAba();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PessoaJuridicaEntity carregarPessoaJuridica() {
		PessoaJuridicaEntity pj = this.entity.getPessoaJuridica();

		this.subView.getTfFantasia().setValue(pj.getFantasia());
		this.subView.getMtfCnpj().setValue(pj.getCnpj());
		this.subView.getTfInscricaoEstadual().setValue(
				pj.getInscricaoEstadual());
		this.subView.getTfInscricaoMunicipal().setValue(
				pj.getInscricaoMunicipal());
		this.subView.getPdfDataConstituicao()
				.setValue(pj.getDataConstituicao());
		this.subView.getTfSuframa().setValue(pj.getSuframa());

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
		PessoaFisicaEntity pf = this.entity.getPessoaFisica();

		this.subView.getMtfCpf().setValue(pf.getCpf());
		this.subView.getPdfDataNascimento().setValue(pf.getDataNascimento());
		this.subView.getTfNaturalidade().setValue(pf.getNaturalidade());
		this.subView.getTfNacionalidade().setValue(pf.getNacionalidade());
		this.subView.getTfNomeMae().setValue(pf.getNomeMae());
		this.subView.getTfNomePai().setValue(pf.getNomePai());
		this.subView.getTfNumeroRg().setValue(pf.getRg());
		this.subView.getTfOrgaoEmissor().setValue(pf.getOrgaoRg());
		this.subView.getPdfDataEmissaoRg().setValue(pf.getDataEmissaoRg());
		this.subView.getTfCnh().setValue(pf.getCnhNumero());

		CnhEn cnhEn = pf.getCnh();

		if (ObjectUtils.isNotBlank(cnhEn)) {
			this.subView.getCbCategoriaCnh().setValue(cnhEn);
		}

		this.subView.getPdfDataCnhEmissao().setValue(pf.getCnhVencimento());
		this.subView.getMocEstadoCivil().setValue(pf.getEstadoCivil());

		RacaEn racaEn = pf.getRaca();

		if (ObjectUtils.isNotBlank(racaEn)) {
			this.subView.getCbRaca().setValue(racaEn);
		}

		TipoSanguineoEn tipoSanguineoEn = pf.getTipoSangue();

		if (ObjectUtils.isNotBlank(tipoSanguineoEn)) {
			this.subView.getCbTipoSanguineo().setValue(tipoSanguineoEn);
		}

		this.subView.getTfNumeroReservista().setValue(pf.getReservistaNumero());

		CategoriaReservistaEn categoriaReservistaEn = pf
				.getReservistaCategoria();

		if (ObjectUtils.isNotBlank(categoriaReservistaEn)) {
			this.subView.getCbCategoriaReservista().setValue(
					categoriaReservistaEn);
		}

		SexoEn sexoEn = (SexoEn) this.entity.getPessoaFisica().getSexo();

		if (ObjectUtils.isNotBlank(sexoEn)) {
			this.subView.getOgSexo().setValue(sexoEn);
		}

		this.subView.getTfTituloEleitor().setValue(
				pf.getTituloEleitoralNumero());
		this.subView.getTfTituloSecao().setConvertedValue(
				pf.getTituloEleitoralSecao());
		this.subView.getTfTituloZona().setConvertedValue(
				pf.getTituloEleitoralZona());

		return pf;
	}

	@Override
	protected void criarNovoBean() {
		try {
			this.entity = new PessoaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visualizarAba();
		}
	}

	@Override
	protected void quandoNovo() {
		try {
			this.entity = new PessoaEntity();
		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		} finally {
			visualizarAba();
		}
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
	protected void removerEmCascata(List<Serializable> objetos) {
		try {

		} catch (Exception e) {
			e.printStackTrace();

			mensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 */

	public PessoaContatoEntity aderirPessoaContato() {
		try {
			PessoaContatoEntity ent = new PessoaContatoEntity();
			ent.setPessoa(this.entity);

			this.entity.getPessoaContatoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaContato(List<PessoaContatoEntity> values) {
		try {
			for (PessoaContatoEntity ent : values) {
				this.pessoaContatoBusiness.delete(ent);
				this.entity.getPessoaContatoList().remove(ent);
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
			ent.setPessoa(this.entity);

			this.entity.getPessoaEnderecoList().add(ent);

			return ent;
		} catch (Exception e) {
			e.printStackTrace();

			throw e;
		}
	}

	public void removerPessoaEndereco(List<PessoaEnderecoEntity> values) {
		try {
			for (PessoaEnderecoEntity ent : values) {
				this.pessoaEnderecoBusiness.delete(ent);
				this.entity.getPessoaEnderecoList().remove(ent);
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

	/**
	 * 
	 */

	public void visualizarAba() {
		this.subView
				.getTsGeral()
				.getTab(4)
				.setVisible(
						this.entity.getCliente().equals("0") ? Boolean.FALSE
								: Boolean.TRUE);

		this.subView.getTsGeral().getTab(5)
				.setVisible(this.entity.getColaborador());
		this.subView.getTsGeral().getTab(6)
				.setVisible(this.entity.getFornecedor());
		this.subView.getTsGeral().getTab(7)
				.setVisible(this.entity.getTransportadora());
	}

	/**
	 * 
	 */

	public BeanItemContainer<UfEntity> getUfBic() {
		try {
			List<UfEntity> auxLista = this.ufBusiness.findAll();

			BeanItemContainer<UfEntity> bic = new BeanItemContainer<UfEntity>(
					UfEntity.class, auxLista);

			return bic;
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 */

	public void vceVisibleTabSheet(ValueChangeEvent event, Integer indexTab) {
		Object obj = event.getProperty().getValue();

		if (obj instanceof TipoPessoaEn) {
			TipoPessoaEn en = (TipoPessoaEn) obj;

			this.subView
					.getTsGeral()
					.getTab(0)
					.setVisible(
							en.equals(TipoPessoaEn.F) ? Boolean.TRUE
									: Boolean.FALSE);
			this.subView
					.getTsGeral()
					.getTab(1)
					.setVisible(
							en.equals(TipoPessoaEn.J) ? Boolean.TRUE
									: Boolean.FALSE);

			this.subView.getTsGeral().setSelectedTab(
					en.equals(TipoPessoaEn.F) ? 0 : 1);
		} else if (obj instanceof Boolean) {
			Boolean b = (Boolean) obj;

			this.subView.getTsGeral().getTab(indexTab).setVisible(b);
		} else {
			System.out.println(":: [instanceof] no type for " + obj.toString());
		}
	}

}