import javafx.beans.property.*;

public class DataSerialObject {

    private final StringProperty id_atleta;
    private final StringProperty nome;
    private final StringProperty sexo;
    private final StringProperty nascimento;
    private final StringProperty id_nacao;
    private final StringProperty cpf;
    private final StringProperty altura;
    private final StringProperty peso;
    private final StringProperty punicoes;
    private final StringProperty impedido;


    public DataSerialObject()
    {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    public DataSerialObject(String atleta, String nome, String sexo, String nascimento,
                            String nacao, String cpf, String altura, String peso, String punicoes,
                            String impedido)
    {
        this.id_atleta = new SimpleStringProperty(atleta);
        this.nome = new SimpleStringProperty(nome);
        this.sexo = new SimpleStringProperty(sexo);
        this.nascimento = new SimpleStringProperty(nascimento);
        this.id_nacao = new SimpleStringProperty(nacao);
        this.cpf = new SimpleStringProperty(cpf);
        this.altura = new SimpleStringProperty(altura);
        this.peso = new SimpleStringProperty(peso);
        this.punicoes = new SimpleStringProperty(punicoes);
        this.impedido = new SimpleStringProperty(impedido);
    }

    public String getId_atleta() {
        return id_atleta.get();
    }

    public StringProperty id_atletaProperty() {
        return id_atleta;
    }

    public void setId_atleta(String id_atleta) {
        this.id_atleta.set(id_atleta);
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public String getNascimento() {
        return nascimento.get();
    }

    public StringProperty nascimentoProperty() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento.set(nascimento);
    }

    public String getId_nacao() {
        return id_nacao.get();
    }

    public StringProperty id_nacaoProperty() {
        return id_nacao;
    }

    public void setId_nacao(String id_nacao) {
        this.id_nacao.set(id_nacao);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getAltura() {
        return altura.get();
    }

    public StringProperty alturaProperty() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura.set(altura);
    }

    public String getPeso() {
        return peso.get();
    }

    public StringProperty pesoProperty() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso.set(peso);
    }

    public String getPunicoes() {
        return punicoes.get();
    }

    public StringProperty punicoesProperty() {
        return punicoes;
    }

    public void setPunicoes(String punicoes) {
        this.punicoes.set(punicoes);
    }

    public String getImpedido() {
        return impedido.get();
    }

    public StringProperty impedidoProperty() {
        return impedido;
    }

    public void setImpedido(String impedido) {
        this.impedido.set(impedido);
    }
}
