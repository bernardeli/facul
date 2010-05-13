
public class Cliente extends Login implements Cadastro{
	public Endereco enderecos[];
	public String nome;
	public String cpf;
	public String email;
	public String rg;
	public String emissor;
	public String telefone;
	public String celular;
	public Cliente(){}
	public int cadastrar(Endereco enderecos[],
			String nome,
			String cpf,
			String email,
			String rg,
			String emissor,
			String telefone,
			String celular){return 1;}
}
