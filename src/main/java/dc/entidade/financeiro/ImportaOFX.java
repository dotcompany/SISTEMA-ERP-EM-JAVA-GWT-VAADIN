package dc.entidade.financeiro;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

public class ImportaOFX {
	
	 public List<ExtratoContaBancoEntity> importaArquivoOFX(File arquivo) {
	       /*try {
	            AggregateUnmarshaller a = new AggregateUnmarshaller(ResponseEnvelope.class);
	            ResponseEnvelope re = (ResponseEnvelope) a.unmarshal(new FileInputStream(arquivo));

	            //define o tipo de mensagem
	            MessageSetType type = MessageSetType.banking;
	            ResponseMessageSet message = re.getMessageSet(type);

	            if (message != null) {
	                //busca a lista de contas no arquivo
	            	ExtratoContaBancoEntity extrato;
	                List<ExtratoContaBancoEntity> listaExtrato = new ArrayList<ExtratoContaBancoEntity>();
	                List bank = ((BankingResponseMessageSet) message).getStatementResponses();
	                for (int i = 0; i < bank.size(); i++) {
	                    //objeto que contÃ©m os dados das contas
	                    BankStatementResponseTransaction conta = (BankStatementResponseTransaction) bank.get(i);

	                    //busca os dados das transaÃ§Ãµes
	                    List transacoes = conta.getMessage().getTransactionList().getTransactions();
	                    for (int j = 0; j < transacoes.size(); j++) {
	                        Transaction transaction = (Transaction) transacoes.get(j);

	                        extrato = new ExtratoContaBancoEntity();
	                        extrato.setDataMovimento(transaction.getDate());
	                        extrato.setDataBalancete(transaction.getDatePosted());
	                        extrato.setDocumento(transaction.getCheckNumber());
	                        extrato.setHistorico(transaction.getMemo());
	                        extrato.setValor(transaction.getBigDecimalAmount());

	                        listaExtrato.add(extrato);
	                    }
	                }
	                return listaExtrato;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
	        return null;
	    }


}
