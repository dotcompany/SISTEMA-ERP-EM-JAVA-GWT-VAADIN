package dc.controller.ordemservico;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModelSelect;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.ordemservico.ServicoOsFormView;
import dc.entidade.ordemservico.Grupo;
import dc.entidade.ordemservico.ServicoOs;
import dc.entidade.ordemservico.SubGrupo; 
import dc.servicos.dao.ordemservico.GrupoDAO;
import dc.servicos.dao.ordemservico.ServicoOsDAO;
import dc.servicos.dao.ordemservico.SubGrupoDAO;

/**
*
* @author Paulo Sérgio
*/ 

@Controller
@Scope("prototype")
public class ServicoOsFormController extends CRUDFormController<ServicoOs> {

	private static final long serialVersionUID = 1L;

	ServicoOsFormView subView;
	
	@Autowired
	ServicoOsDAO servicoDAO;
	
	@Autowired
	GrupoDAO grupoDAO;

	@Autowired
	SubGrupoDAO subGrupoDAO;

	private ServicoOs currentBean;
	
	@Override
	protected String getNome() {
		return "Servico";
	}

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override  
	protected void actionSalvar() {
	    currentBean.setNome(subView.getTfNome().getValue());
		currentBean.setGrupo(subView.getCbGrupo().getValue());
		currentBean.setSubGrupo(subView.getCbSubGrupo().getValue()); 
		String aliquota = subView.getTfAliquotaIssqn().getValue();
		if(Validator.validateString(aliquota)){
			aliquota = formataBigDecimal(aliquota);
			currentBean.setAliquotaIssqn(new BigDecimal(aliquota));
		}
		
		String horaGasta = subView.getTfHoraGasta().getValue();
		if(Validator.validateString(horaGasta)){
			horaGasta = formataBigDecimal(horaGasta);
			currentBean.setHoraGasta(new BigDecimal(horaGasta));
		}
		
		String valorComissaoTecnico = subView.getTfValorComissaoTecnico().getValue();
		if(Validator.validateString(valorComissaoTecnico)){
			valorComissaoTecnico = formataBigDecimal(valorComissaoTecnico);
			currentBean.setValorComissaoTecnico(new BigDecimal(valorComissaoTecnico));
		}
		String valorComissaoVendedor = subView.getTfValorComissaoVendedor().getValue();  
		if(Validator.validateString(valorComissaoVendedor)){
			valorComissaoVendedor = formataBigDecimal(valorComissaoVendedor);
			currentBean.setValorComissaoVendedor(new BigDecimal(valorComissaoVendedor));
		}
		String valorServico = subView.getTfValorServico().getValue();
		if(Validator.validateString(valorServico)){
			valorServico = formataMoeda(valorServico);
			currentBean.setValorServico(new BigDecimal(valorServico));
		}
		String valorMinimoServico = subView.getTfValorMinimoServico().getValue();
		if(Validator.validateString(valorMinimoServico)){
			valorMinimoServico = formataMoeda(valorMinimoServico);
			currentBean.setValorMinimoServico(new BigDecimal(valorMinimoServico));
		}
		if(subView.getTfGarantiaDia()!=null){
			currentBean.setGarantiaDia(Integer.parseInt(subView.getTfGarantiaDia().getValue()));
		}
		
		String retorno = subView.getTfRetorno().getValue();
		if(Validator.validateString(retorno)){
			currentBean.setRetorno(Integer.parseInt(subView.getTfRetorno().getValue()));
		}

		currentBean.setTipoComissaoTecnico((String) subView.getOptTipoComissaoTecnico().getValue());
		currentBean.setTipoComissaoVendedor((String) subView.getOptTipoComissaoVendedor().getValue());
		
		String valorPromocional = subView.getTfValorPromocional().getValue();
		if(Validator.validateString(valorPromocional)){
			valorPromocional = formataMoeda(valorPromocional);
			currentBean.setValorPromocional(new BigDecimal(valorPromocional));
		}
		currentBean.setVencimentoPromocao(subView.getDtVencimentoPromocao().getValue());
		currentBean.setObservacao(subView.getTaObservacao().getValue());
		try{
			servicoDAO.saveOrUpdate(currentBean);
			notifiyFrameworkSaveOK(this.currentBean);	
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void carregar(Serializable id) {
		currentBean = servicoDAO.find(id);
		
		subView.getTfNome().setValue(currentBean.getNome());
		subView.getCbGrupo().setValue(currentBean.getGrupo());
		subView.getCbSubGrupo().setValue(currentBean.getSubGrupo());
		BigDecimal aliquota = currentBean.getAliquotaIssqn();
		if (aliquota != null) {
			subView.getTfAliquotaIssqn().setConvertedValue(aliquota);;
		}
		
		BigDecimal horaGasta = currentBean.getHoraGasta();
		if (horaGasta != null) {
			subView.getTfHoraGasta().setConvertedValue(horaGasta);
		}
		BigDecimal comissaoTecnico= currentBean.getValorComissaoTecnico();
		if (comissaoTecnico != null) {
			subView.getTfValorComissaoTecnico().setConvertedValue(comissaoTecnico);
		}
		subView.getOptTipoComissaoTecnico().setValue(currentBean.getTipoComissaoTecnico());
		BigDecimal comissaoVendedor = currentBean.getValorComissaoVendedor();
		if (comissaoVendedor != null) {
			subView.getTfValorComissaoVendedor().setConvertedValue(comissaoVendedor);
		}
		subView.getOptTipoComissaoVendedor().setValue(currentBean.getTipoComissaoVendedor());
		BigDecimal valorServico = currentBean.getValorServico();
		if (valorServico != null) {
			subView.getTfValorServico().setConvertedValue(valorServico);
		}
		BigDecimal valorMinimoServico = currentBean.getValorMinimoServico();
		if (valorMinimoServico != null) {
			subView.getTfValorMinimoServico().setConvertedValue(valorMinimoServico);
		}
		subView.getTfGarantiaDia().setValue(currentBean.getGarantiaDia().toString());
		subView.getTfRetorno().setValue(currentBean.getRetorno().toString());
		BigDecimal valorPromocional = currentBean.getValorPromocional();
		if (valorPromocional != null) {
			subView.getTfValorPromocional().setConvertedValue(valorPromocional);
		}
		subView.getTaObservacao().setValue(currentBean.getObservacao());
	}
	
	/* Callback para quando novo foi acionado. Colocar ProgramaÃ§Ã£o customizada para essa aÃ§Ã£o aqui. Ou entÃ£o deixar em branco, para comportamento padrÃ£o */
	@Override
	protected void quandoNovo() {
		
	}

	@Override
	protected void initSubView() {
		subView = new ServicoOsFormView(this);
		
		preencheCombos();
	}

	/* Deve sempre atribuir a current Bean uma nova instancia do bean do formulario.*/
	@Override
	protected void criarNovoBean() {
		currentBean = new ServicoOs();
	}
	
	private void preencheCombos() {

		DefaultManyToOneComboModel<Grupo> grupo = new DefaultManyToOneComboModel<Grupo>(GrupoListController.class,
				this.grupoDAO, super.getMainController());

		this.subView.getCbGrupo().setModel(grupo);
		
	}
	
	public void getSubgrupo(String classePesquisa, Integer idSelecionado){
		DefaultManyToOneComboModelSelect<SubGrupo> subGrupo = new DefaultManyToOneComboModelSelect<SubGrupo>(SubGrupoListController.class,
				this.subGrupoDAO, super.getMainController(),classePesquisa,idSelecionado);
		
		this.subView.getCbSubGrupo().setModel(subGrupo);
	}
	
	@Override
	protected void remover(List<Serializable> ids) {
		 servicoDAO.deleteAllByIds(ids);
		 mensagemRemovidoOK();
	}

	/* Implementar validacao de campos antes de salvar. */ 
	@Override
	protected boolean validaSalvar() {
		
		boolean valido = true;

		if (!Validator.validateString(subView.getTfNome().getValue())) {
			adicionarErroDeValidacao(subView.getTfNome(), "Não pode ficar em branco");
			valido = false;
		}
		
		return valido;
	}
 
	@Override 
	protected void removerEmCascata(List<Serializable> ids) {
	}

	@Override
	public String getViewIdentifier() {
		return "servicoForm";
	}
	public String formataMoeda(String valor){
		String format = "";
		format = valor.replace("R$","").
				substring(0,valor.indexOf(",")).replaceAll( ",","." ).trim();
		return format;
	}
	
	public String formataBigDecimal(String valor){
		String format = "";
		format = valor.replace(",",".");
		return format;
	}
} 
