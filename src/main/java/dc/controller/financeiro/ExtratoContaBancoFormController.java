package dc.controller.financeiro;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.UploadField;

import com.sun.mail.iap.Response;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

import dc.control.util.ClassUtils;
import dc.entidade.financeiro.ContaCaixa;
import dc.entidade.financeiro.ExtratoContaBancoEntity;
import dc.entidade.financeiro.ImportaOFX;
import dc.model.business.financeiro.ExtratoContaBancoBusiness;
import dc.servicos.dao.financeiro.ExtratoContaBancoDAO;
import dc.visao.financeiro.ExtratoContaBancoFormView;
import dc.visao.framework.geral.CRUDFormController;
import dc.visao.framework.geral.MainUI;

@Controller
@Scope("prototype")
public class ExtratoContaBancoFormController extends CRUDFormController<ExtratoContaBancoEntity> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ExtratoContaBancoEntity entity;
	
	ExtratoContaBancoFormView subView;
	
	@Autowired
	private ExtratoContaBancoDAO extratoContaBancoDAO;
	
	private ContaCaixa contaCaixa;
    private String mes;
    private String ano;
	
	/**
	 * BUSINESS
	 *
	@Autowired
	private ExtratoContaBancoBusiness<ExtratoContaBancoEntity> business;
	
	public ExtratoContaBancoBusiness<ExtratoContaBancoEntity> getBusiness() {
		 return business;
	}*/

	@Override
	public String getViewIdentifier() {
		return ClassUtils.getUrl(this);
	}

	@Override
	protected boolean isFullSized() {

		return true;
	}

	@Override
	protected boolean validaSalvar() {
		try {
			 fieldGroup.commit();
			return true;
		} catch (FieldGroup.CommitException ce) {
			return false;
		}
	}

	@Override
	protected void criarNovoBean() {
		try {
			entity = new ExtratoContaBancoEntity();
			//fieldGroup.setItemDataSource(this.entity);
		}catch (Exception e) {
			e.printStackTrace();
			mensagemErro(e.getMessage());
	    }
		
	}

	@Override
	protected void initSubView() {
		try {
			
			subView = new ExtratoContaBancoFormView(this);
			
			subView.getBtnImportar().addListener(new ValueChangeListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void valueChange(ValueChangeEvent event) {
					UploadField upload = (UploadField) event.getProperty();
					//importaOFX((File) upload.getValue());
					importaOFX();
					atualizaSaldos();
				}
			});

		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}

	@Override
	protected void carregar(Serializable id) {
		try {
			entity = this.extratoContaBancoDAO.find(id);
			//entity = this.business.find((Integer) id);
			
			//fieldGroup.setItemDataSource(this.entity);
			
			List<ExtratoContaBancoEntity> itens = extratoContaBancoDAO.findByExtratoContaBanco(entity);
			subView.preencheSubForm(itens);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

@Override
protected void actionSalvar() {
	try {
		//this.business.saveOrUpdate(this.entity);
		this.extratoContaBancoDAO.saveOrUpdate(this.entity);

		notifiyFrameworkSaveOK(this.entity);
    } catch (Exception e) {
	    e.printStackTrace();
    }
}

@Override
protected Component getSubView() {
	// TODO Auto-generated method stub
	return subView;
}

@Override
protected String getNome() {
	// TODO Auto-generated method stub
	return "Extrato Conta Banco";
}

@Override
protected void remover(List<Serializable> ids) {
	try {
		//this.business.deleteAll(ids);
		this.extratoContaBancoDAO.deleteAll(ids);

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
public ExtratoContaBancoEntity getModelBean() {
	// TODO Auto-generated method stub
	return entity;
}

/*public ExtratoContaBancoEntity novoExtratoContaBancoItem() {
	ExtratoContaBancoEntity item = new ExtratoContaBancoEntity();
	entity.addExtratoContaBancoItem(item);
	return item;
}

public void removerExtratoContaBancoItem(List<ExtratoContaBancoEntity> extratoItem) {
	for (ExtratoContaBancoEntity extratoContaItem : extratoItem) {
		entity.removeExtratoContaBancoItem(extratoContaItem);
	}
	mensagemRemovidoOK();
}*/

private void atualizaSaldos() {
    List<ExtratoContaBancoEntity> listaExtrato = (List<ExtratoContaBancoEntity>) subView.getExtratoContaBancoSubForm().getData();
    BigDecimal creditos = BigDecimal.ZERO;
    BigDecimal debitos = BigDecimal.ZERO;
    BigDecimal saldo = BigDecimal.ZERO;
    for (int i = 0; i < listaExtrato.size(); i++) {
        if (listaExtrato.get(i).getValor().compareTo(BigDecimal.ZERO) == -1) {
            debitos = debitos.add(listaExtrato.get(i).getValor());
        } else {
            creditos = creditos.add(listaExtrato.get(i).getValor());
        }
    }
    saldo = creditos.add(debitos);

    subView.getCredito().setConvertedValue(creditos);
    subView.getDebito().setConvertedValue(debitos);
    subView.getSaldo().setConvertedValue(saldo);
}

public void importaOFX() {
    FileFilter filter = new FileFilter() {

        @Override
        public boolean accept(File f) {
            String arquivo = f.getName().toLowerCase();
            return f.isDirectory() || arquivo.endsWith(".ofx");
        }

        @Override
        public String getDescription() {
            return "*.ofx";
        }
    };

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(filter);
    fileChooser.showOpenDialog(fileChooser);
    File file = fileChooser.getSelectedFile();

    if (file != null) {
        ImportaOFX importa = new ImportaOFX();
        List<ExtratoContaBancoEntity> listaExtrato = importa.importaArquivoOFX(file);
        //seta os dados do extrato
        for (int i = 0; i < listaExtrato.size(); i++) {
            listaExtrato.get(i).setAno(ano);
            listaExtrato.get(i).setMes(mes);
            listaExtrato.get(i).setContaCaixa(contaCaixa);

            //subView.getExtratoContaBancoSubForm().equals(listaExtrato.get(i));
            subView.getExtratoContaBancoSubForm().fillWith(listaExtrato);
            subView.getExtratoContaBancoSubForm().markAsDirtyRecursive();
        }
        try {
        	ConfirmDialog
			.show(MainUI.getCurrent(),
					"Erro!", new ConfirmDialog.Listener() {

						/**
				 * 
				 */
						private static final long serialVersionUID = 1L;

						public void onClose(ConfirmDialog dialog) {
                             if (dialog.isConfirmed()) {
            	                   adicionarErroDeValidacao(subView.getExtratoContaBancoSubForm(),"Erro ao salvar os dados! ");
                                   mensagemErro("Erro ao salvar os dados! ");
                         } else {
            	                  subView.getExtratoContaBancoSubForm().getDados();
                         }
                             
						}
						
			});
        	
        	subView.getExtratoContaBancoSubForm().fillWith(listaExtrato);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public void efetuaConciliacao(String tipoConciliacao) throws Exception {
    //define os parametros da grid
    Map otherGridParams = new HashMap();
    otherGridParams.put("tipoConciliacao", tipoConciliacao);
    otherGridParams.put("contaCaixa", contaCaixa);
    otherGridParams.put("extrato", subView.getExtratoContaBancoSubForm().getData());

    //seta os parametros da grid

    Response res = (Response) subView.getExtratoContaBancoSubForm().getData();
    if (res != null) {
        adicionarErroDeValidacao(subView.getExtratoContaBancoSubForm(),"Erro ao salvar os dados! Aviso do Sistema");
        mensagemErro("Erro ao salvar os dados! Aviso do Sistema");
    } else {
        subView.getExtratoContaBancoSubForm();
    }
}


	

}
