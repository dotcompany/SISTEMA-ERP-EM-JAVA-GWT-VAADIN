package dc.controller.comercial;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.entidade.comercial.Frete;
import dc.entidade.comercial.Venda;
import dc.entidade.geral.pessoal.TransportadoraEntity;
import dc.framework.exception.ErroValidacaoException;
import dc.servicos.dao.comercial.FreteDAO;
import dc.servicos.dao.comercial.VendaDAO;
import dc.servicos.dao.geral.pessoal.TransportadoraDAO;
import dc.servicos.util.Validator;
import dc.visao.comercial.FreteFormView;
import dc.visao.framework.geral.CRUDFormController;

@Controller
@Scope("prototype")
public class FreteFormController extends CRUDFormController<Frete> {

	Frete currentBean;

	FreteFormView subView;

	@Autowired
	FreteDAO dao;

	@Autowired
	TransportadoraDAO transportadoraDAO;

	@Autowired
	VendaDAO vendaDAO;

	@Override
	public String getViewIdentifier() {
		return "FreteForm";
	}

	@Override
	protected boolean validaSalvar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new Frete();

	}

	@Override
	protected void initSubView() {
		subView = new FreteFormView(this);

	}

	@Override
	protected void carregar(Serializable id) {
		currentBean = dao.find(id);

		Integer conhecimento = currentBean.getConhecimento();
		String responsavel = currentBean.getResponsavel();
		String placa = currentBean.getPlaca();
		String ufPlaca = currentBean.getUfPlaca();

		Integer seloFiscal = currentBean.getSeloFiscal();
		BigDecimal quantidadeVolume = currentBean.getQuantidadeVolume();
		BigDecimal pesoBruto = currentBean.getPesoBruto();
		BigDecimal pesoLiquido = currentBean.getPesoLiquido();
		try {

			subView.getCmbTransportadora().setValue(currentBean.getTransportadora());
			subView.getCmbVenda().setValue(currentBean.getVendaCabecalho());
			if (conhecimento != null)
				subView.getTxtConhecimento().setValue(conhecimento.toString());
			if (responsavel != null)
				subView.getTxtResponsavel().setValue(currentBean.getResponsavel().toString());
			if (placa != null)
				subView.getTxtPlaca().setValue(currentBean.getPlaca().toString());
			if (ufPlaca != null)
				subView.getTxtUfPlaca().setValue(currentBean.getUfPlaca().toString());

			if (seloFiscal != null)
				subView.getTxtSeloFiscal().setValue(currentBean.getSeloFiscal().toString());
			if (quantidadeVolume != null)
				subView.getTxtQuantidadeVolumes().setValue(currentBean.getQuantidadeVolume().toString());
			subView.getTxtMarcaVolume().setValue(currentBean.getMarcaVolume());
			subView.getTxtEspecieVolume().setValue(currentBean.getEspecieVolume());
			if (pesoBruto != null)
				subView.getTxtPesoBruto().setValue(currentBean.getPesoBruto().toString());
			if (pesoLiquido != null)
				subView.getTxtPesoLiquido().setValue(currentBean.getPesoLiquido().toString());

		} catch (Exception e) {
			e.printStackTrace();
			mensagemErro("Problema ao carregar");
		}

	}

	@Override
	protected void actionSalvar() {

		try {

			TransportadoraEntity transportadora = (TransportadoraEntity) subView.getCmbTransportadora().getValue();
			Venda venda = (Venda) subView.getCmbVenda().getValue();
			String conhecimento = subView.getTxtConhecimento().getValue();
			String responsavel = subView.getTxtResponsavel().getValue();
			String placa = subView.getTxtPlaca().getValue();
			String ufPlaca = subView.getTxtUfPlaca().getValue();

			String seloFiscal = subView.getTxtSeloFiscal().getValue();
			String qtdeVolumes = subView.getTxtQuantidadeVolumes().getValue();
			String marcaVolume = subView.getTxtMarcaVolume().getValue();
			String especieVolume = subView.getTxtEspecieVolume().getValue();
			String pesoBruto = subView.getTxtPesoBruto().getValue();
			String pesoLiquido = subView.getTxtPesoLiquido().getValue();

			if (!Validator.validateObject(venda)) {
				throw new ErroValidacaoException("Informe o ID da venda");
			}
			currentBean.setTransportadora(transportadora);
			currentBean.setVendaCabecalho(venda);

			if (Validator.validateString(conhecimento)) {
				currentBean.setConhecimento(new Integer(conhecimento));
			}

			currentBean.setResponsavel(responsavel);

			if (Validator.validateString(placa)) {
				placa = placa.replace("-", "").trim();
				currentBean.setPlaca(placa);
			}
			currentBean.setUfPlaca(ufPlaca);

			if (Validator.validateString(seloFiscal)) {
				currentBean.setSeloFiscal(new Integer(seloFiscal));
			}

			if (Validator.validateString(qtdeVolumes)) {
				currentBean.setQuantidadeVolume(new BigDecimal(qtdeVolumes.replace(",", ".")));
			}
			currentBean.setMarcaVolume(marcaVolume);
			currentBean.setEspecieVolume(especieVolume);

			if (Validator.validateString(pesoBruto)) {
				currentBean.setPesoBruto(new BigDecimal(pesoBruto.replace(",", ".")));
			}

			if (Validator.validateString(pesoLiquido)) {
				currentBean.setPesoLiquido(new BigDecimal(pesoLiquido.replace(",", ".")));
			}

			dao.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(currentBean);

		} catch (ErroValidacaoException e) {
			mensagemErro(e.montaMensagemErro());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	@Override
	protected void quandoNovo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Frete";
	}

	@Override
	protected void remover(List<Serializable> ids) {
		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public BeanItemContainer<TransportadoraEntity> carregarTransportadoras() {
		BeanItemContainer<TransportadoraEntity> container = new BeanItemContainer<>(TransportadoraEntity.class);
		for (TransportadoraEntity c : transportadoraDAO.listaTodos()) {
			container.addBean(c);
		}
		return container;
	}

	public BeanItemContainer<Venda> carregarVendas() {
		BeanItemContainer<Venda> container = new BeanItemContainer<>(Venda.class);
		for (Venda v : vendaDAO.listaTodos()) {
			container.addBean(v);
		}
		return container;
	}

	public BigDecimal formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return new BigDecimal(format);
	}

	@Override
	public Frete getModelBean() {
		return currentBean;
	}

}
