package dc.controller.geral.produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import dc.servicos.dao.geral.produto.*;
import dc.visao.framework.DCFieldGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vaadin.ui.Component;

import dc.control.enums.ClasseEn;
import dc.control.enums.IatEn;
import dc.control.enums.SimNaoEn;
import dc.control.enums.TipoSpedEn;
import dc.control.enums.VendaTipoVendaEn;
import dc.control.util.ClassUtils;
import dc.control.util.NumberUtils;
import dc.control.util.classes.ProdutoUtils;
import dc.control.validator.DotErpException;
import dc.controller.geral.diverso.AlmoxarifadoListController;
import dc.controller.tributario.GrupoTributarioListController;
import dc.controller.tributario.IcmsCustomizadoListController;
import dc.entidade.geral.diverso.AlmoxarifadoEntity;
import dc.entidade.geral.produto.GrupoEntity;
import dc.entidade.geral.produto.MarcaEntity;
import dc.entidade.geral.produto.NcmEntity;
import dc.entidade.geral.produto.ProdutoEntity;
import dc.entidade.tributario.GrupoTributarioEntity;
import dc.entidade.tributario.IcmsCustomizadoCabecalhoEntity;
import dc.model.business.geral.produto.ProdutoBusiness;
import dc.servicos.dao.geral.diverso.AlmoxarifadoDAO;
import dc.servicos.dao.tributario.GrupoTributarioDAO;
import dc.servicos.dao.tributario.IcmsCustomizadoDAO;
import dc.servicos.util.Validator;
import dc.visao.framework.component.manytoonecombo.DefaultManyToOneComboModel;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.geral.produto.ProdutoFormView;

@Controller
@Scope("prototype")
public class ProdutoFormController extends CRUDFormController<ProdutoEntity> {

    private static final long serialVersionUID = 1L;

    private ProdutoFormView subView;

    private ProdutoEntity entity;

    @Autowired
    private ProdutoBusiness<ProdutoEntity> business;

    /**
     * DAO
     */

    @Autowired
    private SubGrupoDAO subGrupoDAO;

    @Autowired
    private AlmoxarifadoDAO almoxarifadoDAO;

    @Autowired
    private MarcaDAO marcaDAO;

    @Autowired
    private IcmsCustomizadoDAO icmsCustomizadoDAO;

    @Autowired
    private GrupoTributarioDAO grupoTributarioDAO;

    @Autowired
    private GrupoDAO grupoDAO;

    @Autowired
    private NcmDAO ncmDAO;

    @Autowired
    private ProdutoDAO produtoDAO;

    @Autowired
    private UnidadeProdutoDAO unidadeProdutoDAO;

    private GrupoEntity grupo = new GrupoEntity();

    public ProdutoFormController() {
    }

    @Override
    public String getViewIdentifier() {
        return ClassUtils.getUrl(this);
    }

    @Override
    protected boolean validaSalvar() {
        try {
            fieldGroup.commit();

            ProdutoUtils.validateRequiredFields(this.subView);

            return true;
        } catch (DotErpException dee) {
            adicionarErroDeValidacao(dee.getComponent(), dee.getMessage());

            return false;
        } catch (FieldGroup.CommitException ce) {

            return false;
        }

    }

    @Override
    protected void criarNovoBean() {
        try {
            this.entity = new ProdutoEntity();

            fieldGroup.setItemDataSource(this.entity);

        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }

    }

    @Override
    protected void initSubView() {
        try {
            this.subView = new ProdutoFormView(this);

            configuraBinding();

            preencheCombos();


            DefaultManyToOneComboModel<AlmoxarifadoEntity> modelAlmoxarifado = new DefaultManyToOneComboModel<AlmoxarifadoEntity>(
                    AlmoxarifadoListController.class, this.almoxarifadoDAO,
                    super.getMainController()) {
                @Override
                public String getCaptionProperty() {
                    return "nome";
                }
            };
            this.subView.getMocAlmoxarifado().setModel(modelAlmoxarifado);

            DefaultManyToOneComboModel<MarcaEntity> modelMarca = new DefaultManyToOneComboModel<MarcaEntity>(
                    MarcaListController.class, this.marcaDAO,
                    super.getMainController()) {
                @Override
                public String getCaptionProperty() {
                    return "nome";
                }
            };
            this.subView.getMocMarca().setModel(modelMarca);

            DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity> modelIcmsCustomizado = new DefaultManyToOneComboModel<IcmsCustomizadoCabecalhoEntity>(
                    IcmsCustomizadoListController.class, this.icmsCustomizadoDAO,
                    super.getMainController()) {
                @Override
                public String getCaptionProperty() {
                    return "nome";
                }
            };
            this.subView.getMocIcmsCustomizado().setModel(modelIcmsCustomizado);

            this.subView.getCbTemIcmsCustomizado().setValue(SimNaoEn.N);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void configuraBinding() {
        this.fieldGroup = new DCFieldGroup<>(ProdutoEntity.class);

        fieldGroup.bind(this.subView.getTfNome(), "nome");
        fieldGroup.bind(this.subView.getMocUnidadeProduto(), "unidadeProduto");
        fieldGroup.bind(this.subView.getMocSubGrupo(), "subGrupo");
        fieldGroup.bind(this.subView.getCbIppt(), "ippt");

        // Configura os ManyToOneComboFields
        this.subView.getMocUnidadeProduto().configuraCombo(
                "descricao", UnidadeProdutoListController.class, this.unidadeProdutoDAO, this.getMainController());

        this.subView.getMocSubGrupo().configuraCombo(
                "nome", SubGrupoListController.class, this.subGrupoDAO, this.getMainController());


    }

    private void preencheCombos() {

        DefaultManyToOneComboModel<GrupoEntity> modelGrupo = new DefaultManyToOneComboModel<GrupoEntity>(
                GrupoListController.class, this.grupoDAO,
                super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "nome";
            }
        };
        this.subView.getMocGrupo().setModel(modelGrupo);

        DefaultManyToOneComboModel<NcmEntity> modelNcm = new DefaultManyToOneComboModel<NcmEntity>(
                NcmListController.class, this.ncmDAO, super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "descricao";
            }
        };
        this.subView.getMocNcm().setModel(modelNcm);

        DefaultManyToOneComboModel<GrupoTributarioEntity> modelGrupoTributario = new DefaultManyToOneComboModel<GrupoTributarioEntity>(
                GrupoTributarioListController.class, this.grupoTributarioDAO,
                super.getMainController()) {
            @Override
            public String getCaptionProperty() {
                return "descricao";
            }
        };
        this.subView.getMocGrupoTributario().setModel(modelGrupoTributario);

    }

    @Override
    protected void carregar(Serializable id) {

        try {
            this.entity = this.business.find(id);

            fieldGroup.setItemDataSource(this.entity);

            if (entity.getMarca() != null) {
                subView.getMocMarca().setValue(entity.getMarca());
            }

            if (entity.getAlmoxarifado() != null) {
                subView.getMocAlmoxarifado().setValue(entity.getAlmoxarifado());
            }

            if (entity.getIcmsCustomizado() != null) {
                subView.getMocIcmsCustomizado().setValue(entity.getIcmsCustomizado());
            }

            if (entity.getGrupo() != null) {
                subView.getMocGrupo().setValue(entity.getGrupo());
            }

            if (entity.getGrupoTributario() != null) {
                subView.getMocGrupoTributario().setValue(entity.getGrupoTributario());
            }

            if (entity.getNcm() != null) {
                subView.getMocNcm().setValue(entity.getNcm());
            }

            this.subView.getTfGtin().setValue(this.entity.getGtin());
            this.subView.getTfCodigoInterno().setValue(this.entity.getGtin());
            this.subView.getTfDescricao().setValue(this.entity.getDescricao());
            this.subView.getTfDescricaoPdv().setValue(this.entity.getDescricaoPdv());
            this.subView.getCbInativo().setValue(this.entity.getInativo());
            this.subView.getCbClasse().setValue(this.entity.getClasse());

            if (this.entity.getValorCompra() != null) {
                subView.getCfValorCompra().setConvertedValue(this.entity.getValorCompra());
            }
            if (this.entity.getValorVenda() != null) {
                subView.getCfValorVenda().setConvertedValue(this.entity.getValorVenda());
            }
            if (this.entity.getPrecoVendaMinimo() != null) {
                subView.getCfValorVendaMinimo().setConvertedValue(this.entity.getPrecoVendaMinimo());
            }
            if (this.entity.getPrecoSugerido() != null) {
                subView.getCfValorSugerido().setConvertedValue(this.entity.getPrecoSugerido());
            }
            if (this.entity.getCustoMedioLiquido() != null) {
                subView.getCfCustoMedioLiquido().setConvertedValue(this.entity.getPrecoSugerido());
            }
            if (this.entity.getCustoMedioLiquido() != null) {
                subView.getCfPrecoLucroZero().setConvertedValue(this.entity.getCustoMedioLiquido());
            }
            if (this.entity.getPrecoLucroMinimo() != null) {
                subView.getCfPrecoLucroMinimo().setConvertedValue(this.entity.getPrecoSugerido());
            }
            if (this.entity.getPrecoLucroMaximo() != null) {
                subView.getCfPrecoLucroMaximo().setConvertedValue(this.entity.getPrecoLucroMaximo());
            }
            if (this.entity.getMarkup() != null) {
                subView.getCfMarkup().setConvertedValue(this.entity.getMarkup());
            }

            this.subView.getTfLst().setValue(this.entity.getCodigoLst());
            this.subView.getTfExtipi().setValue(this.entity.getExTipi());
            this.subView.getCbTipoVenda().setValue(this.entity.getTipoVenda());
            this.subView.getCbIat().setValue(this.entity.getIat());
            this.subView.getCbTipoItemSped().setValue(this.entity.getTipoSped());
            this.subView.getTfTotalizadorParcial().setValue(this.entity.getTotalizadorParcial());

            this.subView.getTfCodigoBalanca().setConvertedValue(this.entity.getCodigoBalanca());
            this.subView.getTfPeso().setConvertedValue(this.entity.getPeso());

            if (this.entity.getQuantidadeEstoque() != null) {
                subView.getTfQuantidadeEstoque().setConvertedValue(this.entity.getQuantidadeEstoque());
            }

            this.subView.getTfQuantidadeEstoque().setConvertedValue(this.entity.getQuantidadeEstoque());
            this.subView.getTfQuantidadeEstoqueAnterior().setConvertedValue(this.entity.getQuantidadeEstoqueAnterior());
            this.subView.getTfEstoqueIdeal().setConvertedValue(this.entity.getEstoqueIdeal());
            this.subView.getTfEstoqueMinimo().setConvertedValue(this.entity.getEstoqueMinimo());
            this.subView.getTfEstoqueMaximo().setConvertedValue(this.entity.getEstoqueMaximo());
            this.subView.getTfTaxaComissao().setConvertedValue(this.entity.getTaxaComissao());
            this.subView.getTfPontoPedido().setConvertedValue(this.entity.getPontoPedido());
            this.subView.getTfLoteEconomicoCompra().setConvertedValue(this.entity.getLoteEconomicoCompra());
            this.subView.getTfAliquotaIcms().setConvertedValue(this.entity.getAliquotaIcms());
            this.subView.getTfAliquotaIssqn().setConvertedValue(this.entity.getAliquotaIssqn());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void actionSalvar() {

        try {


            MarcaEntity marca = this.subView.getMocMarca().getValue();
            this.entity.setMarca(marca);

            AlmoxarifadoEntity almoxarifado = this.subView.getMocAlmoxarifado()
                    .getValue();
            this.entity.setAlmoxarifado(almoxarifado);

            GrupoEntity modelGrupo = new GrupoEntity();
            if (subView.getMocGrupo().getValue() != null) {
                modelGrupo = subView.getMocGrupo().getValue();
                this.entity.setGrupo(modelGrupo);
            }

            GrupoEntity grupo = this.subView.getMocGrupo().getValue();
            this.entity.setGrupo(grupo);

            GrupoTributarioEntity grupoTributario = this.subView
                    .getMocGrupoTributario().getValue();
            this.entity.setGrupoTributario(grupoTributario);

            NcmEntity ncm = this.subView.getMocNcm().getValue();
            this.entity.setNcm(ncm);

            this.entity.setGtin(this.subView.getTfGtin().getValue());
            this.entity.setCodigoInterno(this.subView.getTfCodigoInterno()
                    .getValue());

            SimNaoEn inativoEn = (SimNaoEn) (this.subView.getCbInativo()
                    .getValue());
            this.entity.setInativo(inativoEn);

            ClasseEn classeEn = (ClasseEn) this.subView.getCbClasse()
                    .getValue();
            this.entity.setClasse(classeEn);

            this.entity.setDescricao(this.subView.getTfDescricao().getValue());
            this.entity.setDescricaoPdv(this.subView.getTfDescricaoPdv()
                    .getValue());

            this.entity.setValorVenda(NumberUtils.createBigDecimal(this.subView
                    .getCfValorVenda().getConvertedValue()));
            this.entity.setValorCompra(NumberUtils
                    .createBigDecimal(this.subView.getCfValorCompra()
                            .getConvertedValue()));
            this.entity.setPrecoVendaMinimo(NumberUtils
                    .createBigDecimal(this.subView.getCfValorVendaMinimo()
                            .getConvertedValue()));
            this.entity.setPrecoSugerido(NumberUtils
                    .createBigDecimal(this.subView.getCfValorSugerido()
                            .getConvertedValue()));
            this.entity.setCustoMedioLiquido(NumberUtils
                    .createBigDecimal(this.subView.getCfCustoMedioLiquido()
                            .getConvertedValue()));
            this.entity.setPrecoLucroZero(NumberUtils
                    .createBigDecimal(this.subView.getCfPrecoLucroZero()
                            .getConvertedValue()));
            this.entity.setPrecoLucroMaximo(NumberUtils
                    .createBigDecimal(this.subView.getCfPrecoLucroMaximo()
                            .getConvertedValue()));
            this.entity.setPrecoLucroMinimo(NumberUtils
                    .createBigDecimal(this.subView.getCfPrecoLucroMinimo()
                            .getConvertedValue()));
            this.entity.setMarkup(NumberUtils.createBigDecimal(this.subView
                    .getCfMarkup().getConvertedValue()));

            this.entity.setCodigoLst(this.subView.getTfLst().getValue());
            this.entity.setExTipi(this.subView.getTfExtipi().getValue());

            VendaTipoVendaEn tipoVendaEn = (VendaTipoVendaEn) this.subView
                    .getCbTipoVenda().getValue();
            this.entity.setTipoVenda(tipoVendaEn);

            IatEn iatEn = (IatEn) this.subView.getCbIat().getValue();
            this.entity.setIat(iatEn);


            TipoSpedEn tipoSpedEn = (TipoSpedEn) this.subView
                    .getCbTipoItemSped().getValue();
            this.entity.setTipoSped(tipoSpedEn);

            this.entity.setTotalizadorParcial(this.subView
                    .getTfTotalizadorParcial().getValue());
            this.entity.setCodigoBalanca(NumberUtils.toInt(this.subView
                    .getTfCodigoBalanca().getValue()));

            // this.entity.setQuantidadeEstoque((Integer)
            // this.subView.getTfQuantidadeEstoque().getConvertedValue());
            // this.entity.setQuantidadeEstoqueAnterior((Integer)
            // this.subView.getTfQuantidadeEstoqueAnterior().getConvertedValue());
            // this.entity.setQuantidadeEstoque(NumberUtils.createBigDecimal(this.subView.getTfQuantidadeEstoque().getValue()));
            // this.entity.setQuantidadeEstoqueAnterior(NumberUtils.createBigDecimal(this.subView.getTfQuantidadeEstoqueAnterior().getConvertedValue()));

            if (subView.getTfQuantidadeEstoque() != null) {
                String quantidadeEstoque = subView.getTfQuantidadeEstoque()
                        .getValue();
                if (Validator.validateString(quantidadeEstoque)) {
                    quantidadeEstoque = formataBigDecimal(quantidadeEstoque);
                    this.entity.setQuantidadeEstoque(new BigDecimal(
                            quantidadeEstoque));
                }
            }

            if (subView.getTfQuantidadeEstoqueAnterior() != null) {
                String quantidadeEstoqueAnterior = subView
                        .getTfQuantidadeEstoqueAnterior().getValue();
                if (Validator.validateString(quantidadeEstoqueAnterior)) {
                    quantidadeEstoqueAnterior = formataBigDecimal(quantidadeEstoqueAnterior);
                    this.entity.setQuantidadeEstoqueAnterior(new BigDecimal(
                            quantidadeEstoqueAnterior));
                }
            }

            if (subView.getTfEstoqueIdeal() != null) {
                String estoqueIdeal = subView.getTfEstoqueIdeal().getValue();
                if (Validator.validateString(estoqueIdeal)) {
                    estoqueIdeal = formataBigDecimal(estoqueIdeal);
                    this.entity.setEstoqueIdeal(new BigDecimal(estoqueIdeal));
                }
            }

            if (subView.getTfEstoqueMinimo() != null) {
                String estoqueMinimo = subView.getTfEstoqueMinimo().getValue();
                if (Validator.validateString(estoqueMinimo)) {
                    estoqueMinimo = formataBigDecimal(estoqueMinimo);
                    this.entity.setEstoqueMinimo(new BigDecimal(estoqueMinimo));
                }
            }

            if (subView.getTfEstoqueMaximo() != null) {
                String estoqueMaximo = subView.getTfEstoqueMaximo().getValue();
                if (Validator.validateString(estoqueMaximo)) {
                    estoqueMaximo = formataBigDecimal(estoqueMaximo);
                    this.entity.setEstoqueMaximo(new BigDecimal(estoqueMaximo));
                }
            }

            if (subView.getTfPeso() != null) {
                String peso = subView.getTfPeso().getValue();
                if (Validator.validateString(peso)) {
                    peso = formataBigDecimal(peso);
                    this.entity.setPeso(new BigDecimal(peso));
                }
            }

            // this.entity.setEstoqueIdeal(NumberUtils.createBigDecimal(this.subView.getTfEstoqueIdeal().getConvertedValue()));
            // this.entity.setEstoqueMinimo(NumberUtils.createBigDecimal(this.subView.getTfEstoqueMinimo().getConvertedValue()));
            // this.entity.setEstoqueMaximo(NumberUtils.createBigDecimal(this.subView.getTfEstoqueMaximo().getConvertedValue()));
            // this.entity.setPeso(NumberUtils.createBigDecimal(this.subView.getTfPeso().getConvertedValue()));
            this.entity.setTaxaComissao(NumberUtils
                    .createBigDecimal(this.subView.getTfTaxaComissao()
                            .getConvertedValue()));
            this.entity.setPontoPedido(NumberUtils
                    .createBigDecimal(this.subView.getTfPontoPedido()
                            .getConvertedValue()));
            this.entity.setLoteEconomicoCompra(NumberUtils
                    .createBigDecimal(this.subView.getTfLoteEconomicoCompra()
                            .getConvertedValue()));
            this.entity.setAliquotaIcms(NumberUtils
                    .createBigDecimal(this.subView.getTfAliquotaIcms()
                            .getConvertedValue()));
            this.entity.setAliquotaIssqn(NumberUtils
                    .createBigDecimal(this.subView.getTfAliquotaIssqn()
                            .getConvertedValue()));

            this.business.saveOrUpdate(this.entity);

            notifiyFrameworkSaveOK(this.entity);
        } catch (Exception e) {
            e.printStackTrace();

            mensagemErro(e.getMessage());
        }
    }

    public String formataBigDecimal(String valor) {
        String format = "";
        format = valor.replace(".", "").replace(",", ".");
        return format;
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
        return "Produto";
    }

    @Override
    public boolean isFullSized() {
        return true;
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

    @Override
    public ProdutoEntity getModelBean() {
        // TODO Auto-generated method stub
        return entity;
    }

    /**
     * COMBO
     */

    public List<String> getIcmsCustomizado() {
        try {
            List<String> siLista = new ArrayList<String>();

            for (SimNaoEn en : SimNaoEn.values()) {
                siLista.add(en.ordinal(), en.toString());

            }

            return siLista;

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public ProdutoBusiness<ProdutoEntity> getBusiness() {
        return business;
    }

    public List<ProdutoEntity> buscarProdutos() {
        return produtoDAO.getAll(ProdutoEntity.class);
    }

}
