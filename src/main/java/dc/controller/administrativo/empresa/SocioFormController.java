package dc.controller.administrativo.empresa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

import dc.controller.geral.diverso.UfListController;
import dc.entidade.administrativo.empresa.DependenteEntity;
import dc.entidade.administrativo.empresa.EmpresaEntity;
import dc.entidade.administrativo.empresa.ParticipacaoSocietariaEntity;
import dc.entidade.administrativo.empresa.QuadroSocietarioEntity;
import dc.entidade.administrativo.empresa.SocioEntity;
import dc.entidade.geral.diverso.UfEntity;
import dc.entidade.geral.pessoal.TipoRelacionamentoEntity;
import dc.servicos.dao.administrativo.empresa.DependenteDAO;
import dc.servicos.dao.administrativo.empresa.ParticipacaoSocietariaDAO;
import dc.servicos.dao.administrativo.empresa.QuadroSocietarioDAO;
import dc.servicos.dao.administrativo.empresa.SocioDAO;
import dc.servicos.dao.geral.UfDAO;
import dc.servicos.dao.geral.pessoal.TipoRelacionamentoDAO;
import dc.servicos.util.Validator;
import dc.visao.administrativo.empresa.SocioFormView;
import dc.visao.administrativo.empresa.SocioFormView.DIRIGENTE;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.spring.SecuritySessionProvider;

@Controller
@Scope("prototype")
@SuppressWarnings("serial")
public class SocioFormController extends CRUDFormController<SocioEntity> {

	SocioFormView subView;

	@Autowired
	SocioDAO dao;

	SocioEntity currentBean;

	@Autowired
	TipoRelacionamentoDAO tipoRelacionamentoDAO;

	@Autowired
	QuadroSocietarioDAO quadroSocietarioDAO;

	@Autowired
	ParticipacaoSocietariaDAO participacaoSocietariaDAO;

	@Autowired
	UfDAO ufDAO;

	@Autowired
	DependenteDAO dependenteDAO;

	@Override
	public String getViewIdentifier() {
		return "socioForm";
	}

	@Override
	protected boolean validaSalvar() {
		boolean valido = true;

		return valido;
	}

	@Override
	protected void criarNovoBean() {
		currentBean = new SocioEntity();
	}

	@Override
	protected void initSubView() {
		subView = new SocioFormView(this);
		
		DefaultManyToOneComboModel<UfEntity> modelUf = new DefaultManyToOneComboModel<UfEntity>(
				UfListController.class, this.ufDAO, super.getMainController());

		this.subView.getCmbUF().setModel(modelUf);
		
		DefaultManyToOneComboModel<QuadroSocietarioEntity> modelQuadroSocietario = new DefaultManyToOneComboModel<QuadroSocietarioEntity>(
				QuadroSocietarioListController.class, this.quadroSocietarioDAO, super.getMainController());

		this.subView.getCmbQuadroSocietario().setModel(modelQuadroSocietario);
	}

	@Override
	protected void carregar(Serializable id) {

		currentBean = dao.find((Integer) id);
		List<DependenteEntity> dependentes = dependenteDAO.findBySocio(currentBean);
		List<ParticipacaoSocietariaEntity> participacoes = participacaoSocietariaDAO.findBySocio(currentBean);

		try {
			carregarCombos();

			if (Validator.validateObject(currentBean.getQuadroSocietario())) {
				subView.getCmbQuadroSocietario().setValue(currentBean.getQuadroSocietario());
			}
			
			subView.getTxtNome().setValue(currentBean.getNome());
			subView.getTxtCpf().setValue(currentBean.getCpf());

			subView.getTxtLogradouro().setValue(currentBean.getLogradouro());
			subView.getTxtNumero().setValue(currentBean.getNumero().toString());
			subView.getTxtComplemento().setValue(currentBean.getComplemento());

			if (Validator.validateObject(currentBean.getBairro())) {
				subView.getTxtBairro().setValue(currentBean.getBairro());
			}
			
			if(currentBean.getUf()!=null){
				subView.getCmbUF().setValue(currentBean.getUf());
			}


			if (Validator.validateObject(currentBean.getMunicipio())) {
				subView.getTxtMunicipio().setValue(currentBean.getMunicipio());
			}

			if (Validator.validateObject(currentBean.getCep())) {
				subView.getTxtCep().setValue(currentBean.getCep());
			}

			if (Validator.validateObject(currentBean.getFone())) {
				subView.getTxtFone().setValue(currentBean.getFone());
			}

			if (Validator.validateObject(currentBean.getCelular())) {
				subView.getTxtCelular().setValue(currentBean.getCelular());
			}

			if (Validator.validateObject(currentBean.getEmail())) {
				subView.getTxtEmail().setValue(currentBean.getEmail());
			}

			if (Validator.validateObject(currentBean.getParticipacao())) {
				subView.getTxtParticipacao().setValue(currentBean.getParticipacao().toString());
			}

			if (Validator.validateObject(currentBean.getQuotas())) {
				subView.getTxtQuotas().setValue(currentBean.getQuotas().toString());
			}

			if (Validator.validateObject(currentBean.getIntegralizar())) {
				subView.getTxtIntegralizar().setValue(currentBean.getIntegralizar().toString());
			}

			if (Validator.validateObject(currentBean.getDataIngresso())) {
				subView.getDataIngresso().setValue(currentBean.getDataIngresso());
			}

			if (Validator.validateObject(currentBean.getDataSaida())) {
				subView.getDataSaida().setValue(currentBean.getDataSaida());
			}

			if (dependentes != null) {
				subView.preencherSubFormDependente(dependentes);
			}

			if (participacoes != null) {

				for (ParticipacaoSocietariaEntity p : participacoes) {

					if (p.getDirigente() != null && !(p.getDirigente().isEmpty()))
						p.setDirigenteEnum(DIRIGENTE.getDirigente(p.getDirigente()));
				}

				subView.preencherSubFormParticipacao(participacoes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void carregarCombos() {
		carregarUFs();
	}

	public EmpresaEntity empresaAtual() {
		return SecuritySessionProvider.getUsuario().getConta().getEmpresa();
	}

	@Override
	protected void actionSalvar() {

		try {
			QuadroSocietarioEntity quadro = (QuadroSocietarioEntity) subView.getCmbQuadroSocietario().getValue();
			
			UfEntity uf = new UfEntity();
			if(subView.getCmbUF().getValue()!=null){
				uf = subView.getCmbUF().getValue();
				this.currentBean.setUf(uf);
			}

			String nome = subView.getTxtNome().getValue();
			String cpf = subView.getTxtCpf().getValue();
			
			String logradouro = subView.getTxtLogradouro().getValue();
			String numero = subView.getTxtNumero().getValue();
			String complemento = subView.getTxtComplemento().getValue();

			String bairro = subView.getTxtBairro().getValue();
			String municipio = subView.getTxtMunicipio().getValue();
			//String uf = (String) subView.getCmbUF().getValue();
			String cep = subView.getTxtCep().getValue();

			String fone = subView.getTxtFone().getValue();
			String celular = subView.getTxtCelular().getValue();
			String email = subView.getTxtEmail().getValue();

			String participacao = subView.getTxtParticipacao().getValue();
			String quotas = subView.getTxtQuotas().getValue();
			String integralizar = subView.getTxtIntegralizar().getValue();
			Date dataIngresso = subView.getDataIngresso().getValue();
			Date dataSaida = subView.getDataSaida().getValue();

			if (Validator.validateObject(quadro)) {
				currentBean.setQuadroSocietario(quadro);
			}
			
			if (Validator.validateObject(nome)) {
				currentBean.setNome(nome);
			}
			
			if (Validator.validateObject(cpf)) {
				currentBean.setCpf(cpf);
			}

			if (Validator.validateObject(logradouro)) {
				currentBean.setLogradouro(logradouro);
			}

			if (Validator.validateObject(numero)) {
				currentBean.setNumero(new Integer(numero));
			}

			if (Validator.validateObject(complemento)) {
				currentBean.setComplemento(complemento);
			}

			if (Validator.validateObject(bairro)) {
				currentBean.setBairro(bairro);
			}

			if (Validator.validateObject(municipio)) {
				currentBean.setMunicipio(municipio);
			}

			if (Validator.validateObject(uf)) {
				currentBean.setUf(uf);
			}

			if (Validator.validateObject(cep)) {
				currentBean.setCep(cep);
			}

			if (Validator.validateObject(fone)) {
				currentBean.setFone(fone);
			}

			if (Validator.validateObject(celular)) {
				currentBean.setCelular(celular);
			}

			if (Validator.validateObject(email)) {
				currentBean.setEmail(email);
			}

			if (Validator.validateObject(participacao)) {
				currentBean.setParticipacao(new BigDecimal(formataValor(participacao)));
			}

			if (Validator.validateObject(quotas)) {
				currentBean.setQuotas(new Integer(quotas));
			}

			if (Validator.validateObject(integralizar)) {
				currentBean.setIntegralizar(new BigDecimal(formataValor(integralizar)));
			}

			if (Validator.validateObject(dataIngresso)) {
				currentBean.setDataIngresso(dataIngresso);
			}

			if (Validator.validateObject(dataSaida)) {
				currentBean.setDataSaida(dataSaida);
			}

			dao.saveOrUpdate(currentBean);

			List<ParticipacaoSocietariaEntity> participacoes = subView.getParticipacoesSubForm().getDados();
			for (ParticipacaoSocietariaEntity p : participacoes) {

				p.setDirigente(p.getDirigenteEnum().getCodigo());
				participacaoSocietariaDAO.saveOrUpdate(p);
			}

			notifiyFrameworkSaveOK(currentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void quandoNovo() {
		try {
			// subView.filEstoqueDetalhesSubForm(currentBean.getDetalhes());
			//novoObjeto(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*private void novoObjeto(Serializable id) {
		try {
			if (id.equals(0) || id == null) {
				this.currentBean = new SocioEntity();

				// this.subView.getCbIndice().setValue(this.pEntity.getIndice());
			} else {
				this.currentBean = this.dao.find(id);

				this.subView.getCmbQuadroSocietario().setValue(this.currentBean.getQuadroSocietario());
			}

			this.subView.getTxtNome().setValue(this.currentBean.getNome());
			this.subView.getTxtCpf().setValue(this.currentBean.getCpf().toString());
			this.subView.getTxtComplemento().setValue(this.currentBean.getComplemento().toString());
			this.subView.getTxtBairro().setValue(this.currentBean.getBairro().toString());
			this.subView.getTxtLogradouro().setValue(this.currentBean.getLogradouro().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	@Override
	protected Component getSubView() {
		return subView;
	}

	@Override
	protected String getNome() {
		return "Sócio";
	}

	@Override
	protected void remover(List<Serializable> ids) {

		dao.deleteAllByIds(ids);

	}

	@Override
	protected void removerEmCascata(List<Serializable> objetos) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isFullSized() {
		return true;
	}

	public List<UfEntity> listarUfs() {
		return ufDAO.listaTodos();
	}
	
	public ParticipacaoSocietariaEntity adicionarParticipacao() {
		ParticipacaoSocietariaEntity participacao = new ParticipacaoSocietariaEntity();
		this.currentBean.addParticipacao(participacao);

		return participacao;
	}

	public void removerParticipacao(List<ParticipacaoSocietariaEntity> values) {
		for (ParticipacaoSocietariaEntity participacao : values) {
			this.currentBean.removeParticipacao(participacao);
		}

		mensagemRemovidoOK();

	}
	
	public DependenteEntity adicionarDependente() {
		DependenteEntity depe = new DependenteEntity();
		this.currentBean.addDependente(depe);

		return depe;
	}

	public void removerDependente(List<DependenteEntity> values) {
		for (DependenteEntity depe : values) {
			this.currentBean.removeDependente(depe);
		}

		mensagemRemovidoOK();

	}

	public BeanItemContainer<String> carregarUFs() {
		BeanItemContainer<String> container = new BeanItemContainer<>(String.class);
		List<UfEntity> ufs = listarUfs();
		for (UfEntity u : ufs) {
			container.addBean(u.getSigla());
		}

		return container;
	}
	
	public List<TipoRelacionamentoEntity> buscarRelacionamentos() {
		return tipoRelacionamentoDAO.getAll(TipoRelacionamentoEntity.class);
	}

	public List<QuadroSocietarioEntity> listarQuadros() {
		return quadroSocietarioDAO.listaTodos();
	}

	public BeanItemContainer<QuadroSocietarioEntity> carregarQuadros() {
		BeanItemContainer<QuadroSocietarioEntity> container = new BeanItemContainer<>(QuadroSocietarioEntity.class);
		for (QuadroSocietarioEntity p : listarQuadros()) {
			container.addItem(p);
		}
		return container;

	}

	public String formataValor(String valor) {
		String format = "";
		format = valor.replace("R$", "").substring(0, valor.indexOf(",")).

		replaceAll(",", "").trim();
		return format;
	}

	@Override
	public SocioEntity getModelBean() {
		return currentBean;
	}

}
