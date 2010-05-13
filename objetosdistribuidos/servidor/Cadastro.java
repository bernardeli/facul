import java.rmi.*;
public interface Cadastro extends java.rmi.Remote{
	  public int cadastrar(Endereco enderecos[],
				String nome,
				String cpf,
				String email,
				String rg,
				String emissor,
				String telefone,
				String celular) throws java.rmi.RemoteException;
}
