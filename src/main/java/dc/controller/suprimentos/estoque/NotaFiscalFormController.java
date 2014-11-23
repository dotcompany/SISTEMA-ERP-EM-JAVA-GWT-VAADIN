package dc.controller.suprimentos.estoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.util.ClasseUtil;
import dc.entidade.produto.Produto;
import dc.entidade.suprimentos.CupomVinculado;
import dc.entidade.suprimentos.NFeTransporte;
import dc.entidade.suprimentos.NfeDuplicata;
import dc.entidade.suprimentos.NfeFatura;
import dc.entidade.suprimentos.NfeLocalEntrega;
import dc.entidade.suprimentos.NfeLocalRetirada;
import dc.entidade.suprimentos.NotaFiscal;
import dc.entidade.suprimentos.NotaFiscalEmitente;
import dc.entidade.suprimentos.NotaReferenciada;
import dc.servicos.dao.suprimentos.CupomVinculadoDAO;
import dc.servicos.dao.suprimentos.DuplicataDAO;
import dc.servicos.dao.suprimentos.NFEmitenteDAO;
import dc.servicos.dao.suprimentos.NFeFaturaDAO;
import dc.servicos.dao.suprimentos.NFeLocalEntregaDAO;
import dc.servicos.dao.suprimentos.NFeLocalRetiradaDAO;
import dc.servicos.dao.suprimentos.NFeTransporteDAO;
import dc.servicos.dao.suprimentos.NotaFiscalDAO;
import dc.servicos.dao.suprimentos.NotaReferenciadaDAO;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.suprimentos.estoque.NotaFiscalFormView;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.CRT;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.FINALIDADE_EMISSAO;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.FORMA_EMISSAO;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.FORMA_PAGAMENTO;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.TIPO_DANFE;
import dc.visao.suprimentos.estoque.NotaFiscalFormView.TIPO_OPERACAO;

@Controller
@Scope("prototype")
public class NotaFiscalFormController extends CRUDFormController<NotaFiscal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	NotaFiscalDAO dao;

	@Autowired
	NFEmitenteDAO emitenteDAO;

	@Autowired
	CupomVinculadoDAO cupomDAO;

	@Autowired
	NotaReferenciadaDAO notaReferenciadaDAO;

	@Autowired
	NFeLocalEntregaDAO localEntregaDAO;

	@Autowired
	NFeLocalRetiradaDAO localRetiradaDAO;

	@Autowired
	NFeTransporteDAO transporteDAO;

	@Autowired
	NFeFaturaDAO faturaDAO;

	@Autowired
	DuplicataDAO duplicataDAO;

	private NotaFiscal currentBean;

	NotaFiscalFormView subView;

	@Override
	protected String getNome() {
		return "Entrada de NF-E";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected void actionSalvar() {
		try {
			currentBean.setStatus((String) subView.getCmbStatus().getValue());
			currentBean.setChaveAcesso(subView.getTxtChaveAcesso().getValue());
			currentBean.setNumero(subView.getTxtNumero().getValue());

			currentBean.setModelo(subView.getTxtModelo().getValue());
			currentBean.setSerie(subView.getTxtSerie().getValue());
			currentBean.setDataEmissao(subView.getDataEmissao().getValue());

			currentBean.setDataEntradaSaida(subView.getDataEntradaSaida()
					.getValue());
			currentBean.setHoraEntradaSaida(subView.getHoraEntradaSaida()
					.getValue());

			currentBean.setTipoOperacao(((TIPO_OPERACAO) subView
					.getTipoOperacao().getValue()).getCodigo());

			currentBean.setFormaPagamento(((FORMA_PAGAMENTO) subView
					.getFormaPagamento().getValue()).getCodigo());

			currentBean.setFormaEmissao(((FORMA_EMISSAO) subView
					.getFormaEmissao().getValue()).getCodigo());

			currentBean.setFinalidadeEmissao(((FINALIDADE_EMISSAO) subView
					.getFinalidadeEmissao().getValue()).getCodigo());

			currentBean.setTipoImpressaoDanfe(((TIPO_DANFE) subView
					.getTipoDanfe().getValue()).getCodigo());

			currentBean.setNaturezaOperacao(subView.getNatureza().getValue());

			currentBean.setBaseCalculoIcms(new BigDecimal(subView.getBaseICMS()
					.getValue()));
			currentBean.setValorIcms(new BigDecimal(subView.getValorICMS()
					.getValue()));
			currentBean.setBaseCalculoIcmsSt(new BigDecimal(subView
					.getBaseICMSST().getValue()));
			currentBean.setValorIcmsSt(new BigDecimal(subView.getValorICMSST()
					.getValue()));
			currentBean.setValorCofins(new BigDecimal(subView.getTotalCofins()
					.getValue()));
			currentBean.setValorTotalProdutos(new BigDecimal(subView
					.getTotalProdutos().getValue()));

			currentBean.setValorFrete(new BigDecimal(subView.getValorFrete()
					.getValue()));
			currentBean.setValorSeguro(new BigDecimal(subView.getValorSeguro()
					.getValue()));
			currentBean.setOutrasDespesasAcessorias(new BigDecimal(subView
					.getOutrasDespesas().getValue()));
			currentBean.setValorPis(new BigDecimal(subView.getValorTotalPIS()
					.getValue()));
			currentBean.setValorDescontos(new BigDecimal(subView.getDescontos()
					.getValue()));
			currentBean.setValorTotalNota(new BigDecimal(subView.getTotalNota()
					.getValue()));

			currentBean.setInformacoesContribuinte(subView.getInfoContrib()
					.getValue());
			currentBean.setInformacoesFisco(subView.getInfoFisco().getValue());

			dao.saveOrUpdate(currentBean);

			salvarEmitente();
			salvarLocalEntrega();
			salvarLocalRetirada();
			salvarTransporte();
			salvarFatura();

			notifiyFrameworkSaveOK(this.currentBean);
		} catch (Exception e) {
			mensagemErro(e.getMessage());
			e.printStackTrace();
		}

	}

	public void salvarFatura() {
		NfeFatura fatura = faturaDAO.buscaFaturaPorNota(currentBean);

		if (fatura == null) {
			fatura = new NfeFatura();
		}

		if (subView.getNumeroFatura() != null
				&& !(subView.getNumeroFatura().getValue().isEmpty())) {
			fatura.setNumero(subView.getNumeroFatura().getValue());

			if (subView.getValorOriginalFatura() != null) {
				fatura.setValorOriginal(new BigDecimal(subView
						.getValorOriginalFatura().getValue()));
			}

			if (subView.getValorDescontoFatura() != null) {
				fatura.setValorDesconto(new BigDecimal(subView
						.getValorDescontoFatura().getValue()));
			}

			if (subView.getValorLiquidoFatura() != null) {
				fatura.setValorLiquido(new BigDecimal(subView
						.getValorLiquidoFatura().getValue()));
			}

			fatura.setNotaFiscal(currentBean);
			faturaDAO.saveOrUpdate(fatura);
		}

	}

	public void salvarTransporte() {
		NFeTransporte transporte = transporteDAO
				.buscaTransportePorNota(currentBean);

		if (transporte == null) {
			transporte = new NFeTransporte();
		}

		if (subView.getCpfTransp() != null
				&& !(subView.getCpfTransp().getValue().isEmpty())) {
			transporte.setCpfCnpj(subView.getCpfTransp().getValue());
		}

		if (subView.getRazaoSocialTransp() != null) {
			transporte
					.setRazaoSocial(subView.getRazaoSocialTransp().getValue());
		}

		if (subView.getInscricaoEstadualTransp() != null) {
			transporte.setInscricaoEstadual(subView
					.getInscricaoEstadualTransp().getValue());
		}

		if (subView.getLogradouroTransp() != null) {
			transporte.setLogradouro(subView.getLogradouroTransp().getValue());
		}

		if (subView.getCidadeTransp() != null) {
			transporte.setCidade(subView.getCidadeTransp().getValue());
		}

		if (subView.getCodigoMunicipioTransp() != null
				&& !(subView.getCodigoMunicipioTransp().getValue().isEmpty())) {
			transporte.setCodigoIBGE(new Integer(subView
					.getCodigoMunicipioTransp().getValue()));
		}

		if (subView.getUfTransp() != null) {
			transporte.setUf(subView.getUfTransp().getValue());
		}

		if (subView.getCfopTransp() != null
				&& !(subView.getCfopTransp().getValue().isEmpty())) {
			transporte.setCfop(new Integer(subView.getCfopTransp().getValue()));
		}

		if (subView.getBaseCalculoTransp() != null
				&& !(subView.getBaseCalculoTransp().getValue().isEmpty())) {
			transporte.setBaseCalculo(new BigDecimal(subView
					.getBaseCalculoTransp().getValue()));
		}

		if (subView.getAliquotaTransp() != null
				&& !(subView.getAliquotaTransp().getValue().isEmpty())) {
			transporte.setAliquota(new BigDecimal(subView.getAliquotaTransp()
					.getValue()));
		}

		if (subView.getValorServicoTransp() != null
				&& !(subView.getValorServicoTransp().getValue().isEmpty())) {
			transporte.setValorServico(new BigDecimal(subView
					.getValorServicoTransp().getValue()));
		}

		if (subView.getIcmsRetidoTransp() != null
				&& !(subView.getIcmsRetidoTransp().getValue().isEmpty())) {
			transporte.setValorIcmsRetido(new BigDecimal(subView
					.getIcmsRetidoTransp().getValue()));
		}

		if (subView.getUfVeiculo() != null) {
			transporte.setUfVeiculo(subView.getUfVeiculo().getValue());
		}

		if (subView.getPlacaVeiculo() != null) {
			transporte.setPlacaVeiculo(subView.getPlacaVeiculo().getValue());
		}

		if (subView.getRntcVeiculo() != null) {
			transporte.setRntcVeiculo(subView.getRntcVeiculo().getValue());
		}

		transporte.setNotaFiscal(currentBean);
		transporteDAO.saveOrUpdate(transporte);

	}

	public void salvarEmitente() {
		NotaFiscalEmitente emitente = emitenteDAO.findByNota(currentBean);

		if (emitente == null) {
			emitente = new NotaFiscalEmitente();
		}

		if (subView.getCpfCnpjEm() != null
				&& !(subView.getCpfCnpjEm().getValue().isEmpty())) {
			emitente.setCpfCnpj(subView.getCpfCnpjEm().getValue());

			if (subView.getRazaoEm() != null) {
				emitente.setRazaoSocial(subView.getRazaoEm().getValue());
			}

			if (subView.getFantasiaEm() != null) {
				emitente.setNomeFantasia(subView.getFantasiaEm().getValue());
			}

			if (subView.getCep() != null) {
				emitente.setCep(subView.getCep().getValue());
			}

			if (subView.getLogradouro() != null) {
				emitente.setLogradouro(subView.getLogradouro().getValue());
			}

			if (subView.getNumero() != null) {
				emitente.setNumero(subView.getNumero().getValue());
			}

			if (subView.getComplemento() != null) {
				emitente.setComplemento(subView.getComplemento().getValue());
			}

			if (subView.getBairro() != null) {
				emitente.setBairro(subView.getBairro().getValue());
			}

			if (subView.getCodigoMunicipio() != null
					&& !(subView.getCodigoMunicipio().getValue().isEmpty())) {
				emitente.setCodigoIBGE(new Integer(subView.getCodigoMunicipio()
						.getValue()));
			}

			if (subView.getCidade() != null) {
				emitente.setCidade(subView.getCidade().getValue());
			}

			if (subView.getUf() != null) {
				emitente.setUf(subView.getUf().getValue());
			}

			if (subView.getInscricao() != null) {
				emitente.setInscricaoEstadual(subView.getInscricao().getValue());
			}

			if (subView.getTelefone() != null) {
				emitente.setTelefone(subView.getTelefone().getValue());
			}

			if (subView.getCrt() != null) {
				emitente.setCrt(((CRT) subView.getCrt().getValue()).getCodigo());
			}

			// String codigoMunicipio = subView.getCodigoMunicipio().getValue();

			emitente.setNota(currentBean);
			emitenteDAO.saveOrUpdate(emitente);
		}
	}

	public void salvarLocalEntrega() {
		NfeLocalEntrega entrega = localEntregaDAO
				.buscaEntregaPorNota(currentBean);

		if (entrega == null) {
			entrega = new NfeLocalEntrega();
		}

		if (subView.getCnpjEnt() != null
				&& !(subView.getCnpjEnt().getValue().isEmpty())) {
			entrega.setCpfCnpj(subView.getCnpjEnt().getValue());
		}

		if (subView.getLogradouroEnt() != null
				&& !(subView.getLogradouroEnt().getValue().isEmpty())) {
			entrega.setLogradouro(subView.getLogradouroEnt().getValue());
		}

		if (subView.getNumeroEnt() != null
				&& !(subView.getNumeroEnt().getValue().isEmpty())) {
			entrega.setNumero(subView.getNumeroEnt().getValue());
		}

		if (subView.getComplementoEnt() != null
				&& !(subView.getComplementoEnt().getValue().isEmpty())) {
			entrega.setComplemento(subView.getComplementoEnt().getValue());
		}

		if (subView.getBairroEnt() != null
				&& !(subView.getBairroEnt().getValue().isEmpty())) {
			entrega.setBairro(subView.getBairroEnt().getValue());
		}

		if (subView.getBairroEnt() != null
				&& !(subView.getBairroEnt().getValue().isEmpty())) {
			entrega.setBairro(subView.getBairroEnt().getValue());
		}

		if (subView.getIbgeEnt() != null
				&& !(subView.getIbgeEnt().getValue().isEmpty())) {
			entrega.setCodigoMunicipio(new Integer(subView.getIbgeEnt()
					.getValue()));
		}

		if (subView.getCidadeEnt() != null
				&& !(subView.getCidadeEnt().getValue().isEmpty())) {
			entrega.setCidade(subView.getCidadeEnt().getValue());
		}

		if (subView.getUfEnt() != null
				&& !(subView.getUfEnt().getValue().isEmpty())) {
			entrega.setUf(subView.getUfEnt().getValue());
		}

		entrega.setNotaFiscal(currentBean);
		localEntregaDAO.saveOrUpdate(entrega);
	}

	public void salvarLocalRetirada() {
		NfeLocalRetirada retirada = localRetiradaDAO
				.buscaRetiradaPorNota(currentBean);

		if (retirada == null) {
			retirada = new NfeLocalRetirada();
		}

		if (subView.getCnpjRet() != null
				&& !(subView.getCnpjRet().getValue().isEmpty())) {
			retirada.setCpfCnpj(subView.getCnpjRet().getValue());
		}

		if (subView.getLogradouroRet() != null
				&& !(subView.getLogradouroRet().getValue().isEmpty())) {
			retirada.setLogradouro(subView.getLogradouroRet().getValue());
		}

		if (subView.getNumeroRet() != null
				&& !(subView.getNumeroRet().getValue().isEmpty())) {
			retirada.setNumero(subView.getNumeroRet().getValue());
		}

		if (subView.getComplementoRet() != null
				&& !(subView.getComplementoRet().getValue().isEmpty())) {
			retirada.setComplemento(subView.getComplementoRet().getValue());
		}

		if (subView.getBairroRet() != null
				&& !(subView.getBairroRet().getValue().isEmpty())) {
			retirada.setBairro(subView.getBairroRet().getValue());
		}

		if (subView.getBairroRet() != null
				&& !(subView.getBairroRet().getValue().isEmpty())) {
			retirada.setBairro(subView.getBairroRet().getValue());
		}

		if (subView.getIbgeRet() != null
				&& !(subView.getIbgeRet().getValue().isEmpty())) {
			retirada.setCodigoMunicipio(new Integer(subView.getIbgeRet()
					.getValue()));
		}

		if (subView.getCidadeRet() != null
				&& !(subView.getCidadeRet().getValue().isEmpty())) {
			retirada.setCidade(subView.getCidadeRet().getValue());
		}

		if (subView.getUfRet() != null
				&& !(subView.getUfRet().getValue().isEmpty())) {
			retirada.setUf(subView.getUfRet().getValue());
		}

		retirada.setNotaFiscal(currentBean);
		localRetiradaDAO.saveOrUpdate(retirada);
	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find((Integer) id);
		subView.getTxtChaveAcesso().setValue(currentBean.getChaveAcesso());
		subView.getTxtModelo().setValue(currentBean.getModelo());
		subView.getDataEmissao().setValue(currentBean.getDataEmissao());
		subView.getDataEntradaSaida().setValue(
				currentBean.getDataEntradaSaida());
		subView.getHoraEntradaSaida().setValue(
				currentBean.getHoraEntradaSaida());
		subView.getNatureza().setValue(currentBean.getNaturezaOperacao());
		subView.getBaseICMS().setValue(
				currentBean.getBaseCalculoIcms().toString());
		subView.getValorICMS().setValue(currentBean.getValorIcms().toString());
		subView.getBaseICMSST().setValue(
				currentBean.getBaseCalculoIcmsSt().toString());
		subView.getValorICMSST().setValue(
				currentBean.getValorIcmsSt().toString());
		subView.getTotalCofins().setValue(
				currentBean.getValorCofins().toString());
		subView.getTotalProdutos().setValue(
				currentBean.getValorTotalProdutos().toString());
		subView.getValorFrete()
				.setValue(currentBean.getValorFrete().toString());
		subView.getValorSeguro().setValue(
				currentBean.getValorSeguro().toString());
		subView.getOutrasDespesas().setValue(
				currentBean.getOutrasDespesasAcessorias().toString());
		subView.getValorTotalPIS().setValue(
				currentBean.getValorPis().toString());
		subView.getDescontos().setValue(
				currentBean.getValorDescontos().toString());
		subView.getTotalNota().setValue(
				currentBean.getValorTotalNota().toString());
		subView.getInfoFisco().setValue(currentBean.getInformacoesFisco());
		subView.getInfoContrib().setValue(
				currentBean.getInformacoesContribuinte());
		subView.getTxtSerie().setValue(currentBean.getSerie());
		subView.getTxtNumero().setValue(currentBean.getNumero());

		subView.carregarFormaPagamento();
		subView.carregarFormaEmissao();
		subView.carregarTipoOperacao();
		subView.carregarFinalidadeEmissao();
		subView.carregarTipoDANFE();
		subView.carregarCRT();
		subView.carregarView(currentBean);

		carregarEmitente();
		carregarCuponsVinculados();
		carregarNotasReferenciadas();
		carregarLocalEntrega();
		carregarLocalRetirada();
		carregarTransporte();
		carregarFatura();
		carregarDuplicatas();

	}

	public void carregarFatura() {
		NfeFatura fatura = faturaDAO.buscaFaturaPorNota(currentBean);

		if (fatura != null) {
			subView.getNumeroFatura().setValue(fatura.getNumero());
			subView.getValorOriginalFatura().setValue(
					fatura.getValorOriginal().toString());
			subView.getValorDescontoFatura().setValue(
					fatura.getValorDesconto().toString());
			subView.getValorLiquidoFatura().setValue(
					fatura.getValorLiquido().toString());
		}
	}

	public void carregarTransporte() {
		try {
			NFeTransporte transporte = transporteDAO
					.buscaTransportePorNota(currentBean);
			if (transporte != null) {
				subView.getCpfTransp().setValue(transporte.getCpfCnpj());
				subView.getRazaoSocialTransp().setValue(
						transporte.getRazaoSocial());
				subView.getInscricaoEstadualTransp().setValue(
						transporte.getInscricaoEstadual());
				subView.getLogradouroTransp().setValue(
						transporte.getLogradouro());
				subView.getCidadeTransp().setValue(transporte.getCidade());
				subView.getUfTransp().setValue(transporte.getUf());
				if (transporte.getCodigoIBGE() != null)
					subView.getCodigoMunicipioTransp().setValue(
							transporte.getCodigoIBGE().toString());
				if (transporte.getCfop() != null)
					subView.getCfopTransp().setValue(
							transporte.getCfop().toString());
				if (transporte.getBaseCalculo() != null)
					subView.getBaseCalculoTransp().setValue(
							transporte.getBaseCalculo().toString());
				if (transporte.getAliquota() != null)
					subView.getAliquotaTransp().setValue(
							transporte.getAliquota().toString());
				if (transporte.getValorServico() != null)
					subView.getValorServicoTransp().setValue(
							transporte.getValorServico().toString());
				if (transporte.getValorIcmsRetido() != null)
					subView.getIcmsRetidoTransp().setValue(
							transporte.getValorIcmsRetido().toString());
				if (transporte.getUfVeiculo() != null)
					subView.getUfVeiculo().setValue(
							transporte.getUfVeiculo().toString());
				subView.getPlacaVeiculo()
						.setValue(transporte.getPlacaVeiculo());
				subView.getRntcVeiculo().setValue(transporte.getRntcVeiculo());
			}

		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR TRANSPORTE");
			e.printStackTrace();
		}
	}

	public void carregarEmitente() {

		try {
			NotaFiscalEmitente emitente = emitenteDAO.findByNota(currentBean);
			subView.carregarViewCRT(emitente);
			if (emitente != null) {

				subView.getCpfCnpjEm().setValue(emitente.getCpfCnpj());
				subView.getRazaoEm().setValue(emitente.getRazaoSocial());
				subView.getFantasiaEm().setValue(emitente.getNomeFantasia());
				subView.getCep().setValue(emitente.getCep());
				subView.getLogradouro().setValue(emitente.getLogradouro());
				subView.getNumero().setValue(emitente.getNumero());
				subView.getComplemento().setValue(emitente.getComplemento());

				subView.getBairro().setValue(emitente.getBairro());
				subView.getCodigoMunicipio().setValue(
						emitente.getCodigoIBGE().toString());
				subView.getCidade().setValue(emitente.getCidade());
				subView.getUf().setValue(emitente.getUf());
				subView.getInscricao()
						.setValue(emitente.getInscricaoEstadual());

				subView.getTelefone().setValue(emitente.getTelefone());
			}

		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR EMITENTE");
			e.printStackTrace();
		}

	}

	public void carregarLocalEntrega() {
		NfeLocalEntrega entrega = localEntregaDAO
				.buscaEntregaPorNota(currentBean);

		try {
			if (entrega != null) {
				subView.getCnpjEnt().setValue(entrega.getCpfCnpj());
				subView.getLogradouroEnt().setValue(entrega.getLogradouro());
				subView.getNumeroEnt().setValue(entrega.getNumero());
				subView.getComplementoEnt().setValue(entrega.getComplemento());
				subView.getBairroEnt().setValue(entrega.getBairro());
				if (entrega.getCodigoMunicipio() != null)
					subView.getIbgeEnt().setValue(
							entrega.getCodigoMunicipio().toString());
				subView.getCidadeEnt().setValue(entrega.getCidade());
				subView.getUfEnt().setValue(entrega.getUf());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void carregarLocalRetirada() {
		NfeLocalRetirada retirada = localRetiradaDAO
				.buscaRetiradaPorNota(currentBean);

		try {
			if (retirada != null) {
				subView.getCnpjRet().setValue(retirada.getCpfCnpj());
				subView.getLogradouroRet().setValue(retirada.getLogradouro());
				subView.getNumeroRet().setValue(retirada.getNumero());
				subView.getComplementoRet().setValue(retirada.getComplemento());
				subView.getBairroRet().setValue(retirada.getBairro());
				if (retirada.getCodigoMunicipio() != null)
					subView.getIbgeRet().setValue(
							retirada.getCodigoMunicipio().toString());
				subView.getCidadeRet().setValue(retirada.getCidade());
				subView.getUfRet().setValue(retirada.getUf());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void carregarCuponsVinculados() {
		try {
			currentBean.setCuponsVinculados(cupomDAO
					.buscaCuponsPorNota(currentBean));
			subView.preencheCupomSubForm(currentBean.getCuponsVinculados());
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR CUPONS VINCULADOS");
			e.printStackTrace();
		}

	}

	public void carregarNotasReferenciadas() {
		try {
			currentBean.setNotasReferenciados(notaReferenciadaDAO
					.buscaNotasReferenciadas(currentBean));
			subView.preencherNotasSubForm(currentBean.getNotasReferenciadas());
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR NOTAS REFERENCIADAS");
			e.printStackTrace();
		}

	}

	public void carregarDuplicatas() {
		try {
			currentBean.setDuplicatas(duplicataDAO
					.buscarDuplicatasPorNota(currentBean));
			subView.preencherDuplicatasSubForm(currentBean.getDuplicatas());
		} catch (Exception e) {
			System.out.println("PROBLEMA AO CARREGAR NOTAS DUPLICATAS");
			e.printStackTrace();
		}

	}

	@Override
	protected void initSubView() {
		subView = new NotaFiscalFormView(this);
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new NotaFiscal();

	}

	@Override
	public String getViewIdentifier() {
		return ClasseUtil.getUrl(this);
	}

	@Override
	protected boolean validaSalvar() {
		return true;
	}

	@Override
	protected void quandoNovo() {
	}

	@Override
	protected void remover(List<Serializable> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public NfeDuplicata novaDuplicata() {
		NfeDuplicata d = new NfeDuplicata();
		currentBean.adicionarDuplicata(d);
		return d;
	}

	public CupomVinculado novoCupom() {
		CupomVinculado c = new CupomVinculado();
		currentBean.adicionarCupom(c);
		return c;
	}

	public Produto novoProduto(Produto p) {
		currentBean.adicionarProduto(p);
		return p;
	}

	public NotaReferenciada novaNota() {
		NotaReferenciada n = new NotaReferenciada();
		currentBean.adicionarNota(n);
		return n;
	}

	@Override
	public NotaFiscal getModelBean() {
		// TODO Auto-generated method stub
		return currentBean;
	}

}