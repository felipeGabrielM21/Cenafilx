package data;

public class Filme {

    private String NomeFilme;
    private String DataLancamento;
    private String Categoria;
    private int id;

    public Filme(String NomeFilme, String DataLancamento, String Categoria) {
        this.NomeFilme = NomeFilme;
        this.DataLancamento = DataLancamento;
        this.Categoria = Categoria;
    }

   public Filme(int id, String nomeFilme, String categoria, String dataLancamento) {
    this.id = id;
    this.NomeFilme = nomeFilme;
    this.Categoria = categoria;
    this.DataLancamento = dataLancamento;
}


    public String getNomeFilme() {
        return NomeFilme;
    }

    public void setNomeFilme(String NomeFilme) {
        this.NomeFilme = NomeFilme;
    }

    public String getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(String DataLancamento) {
        this.DataLancamento = DataLancamento;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
