package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Objeto Usuario para ser usado de referencia para populacao do Banco de Dados
 * ou para construcao de JSON para o frontend
 * 
 * Hierarquia de chamada: Aplicacao -> Service -> DAO -> Model
 * Aqui sao feitos os gets e construcao de JSON's
 * 
 * @method construtor de objeto vazio
 * @method construtor de objeto atraves de parse
 * @method construtor de objeto preenchido
 * @method toJson
 */
public class Usuario {
    private int id;
    private String nome;
    private Date nascimento;
    private String nick;
    private String senha;
    private String email;
    private String sexo;
    private String token;
    private SimpleDateFormat nascimentoFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Construtor padrao
     */
    public Usuario() {
        this.id = -1;
        this.nome = "";
        this.nascimento = new Date();
        this.nick = "";
        this.senha = "";
        this.email = "";
        this.sexo = "";
        this.token = "";
    }

    /**
     * Construtor atraves de parse
     * Este construtor nao salva id/senha/token
     * 
     * @param body corpo sem id/senha/token
     */
    public Usuario(String body) {
        // Split do corpo
    	System.out.println(body);
        String tmp[] = body.split(";");

        // Construcao vazia
        this.id = -1;
        this.senha = "";
        this.token = "";

        // Devida atribuicao do parse
        this.email = tmp[0];
        this.nome = tmp[1];
        try {
            this.nascimento = nascimentoFormat.parse(tmp[2]);
        } catch (ParseException e) {
        }
        this.nick = tmp[3];
        this.sexo = tmp[4];
    }

    /**
     * Construtor populado
     * 
     * @param id
     * @param nome
     * @param nascimento
     * @param nick
     * @param senha
     * @param email
     * @param genero
     * @param token
     */
    public Usuario(int id, String nome, Date nascimento, String nick, String senha, String email, String sexo, String token) {
        setId(id);
        setNome(nome);
        setNascimento(nascimento);
        setNick(nick);
        setSenha(senha);
        setEmail(email);
        setSexo(sexo);
        setToken(token);
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
    	 this.id = id;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
   	 this.nome = nome;
   }

    public Date getNascimento() {
        return this.nascimento;
    }
    
    public void setNascimento(Date nascimento) {
   	 	this.nascimento = nascimento;
    }
    public String getNick() {
        return this.nick;
    }
    public void setNick(String nick) {
   	 this.nick = nick;
   }

    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
   	 this.senha = senha;
   }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
   	 this.email = email;
   }

    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
   	 this.sexo = sexo;
   }

    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
   	 this.token = token;
   }
    
    @Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Usuario) obj).getId());
	}

    /**
     * Metodo para geracao de arquivo JSON contendo os dados do usuario
     * 
     * @return JSON sem o objeto pai
     */
    public String toJson() {
        String Json = "";
        Json += "{ ";
        Json += "\"id\":" + this.id + ", \"nome\":\"" + this.nome + "\", \"nascimento\":\"" + this.nascimento
                + "\", \"nick\":\"" + this.nick + "\", \"email\":\"" + this.email + "\", \"genero\":\"" + this.sexo
                + "\"";
        Json += "}";
        return Json;
    }
}
