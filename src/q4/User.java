package q4;

public class User {
	
	private String id;
	private String nome;
	private String data_de_nascimento;
	private String naturalidade;
	private String nacionalidade;
	
	public User(String id, String nome, String data_de_nascimento, 
			String naturalidade, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_de_nascimento = data_de_nascimento;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
	}

	public String getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getData_de_nascimento() {
		return data_de_nascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}
	
	public String getNacionalidade() {
		return nacionalidade;
	}
}