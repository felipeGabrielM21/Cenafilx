package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    private static Connection conn;
    private static PreparedStatement st;
    private static ResultSet rs;
//-------------------------------------Conectando--------------------------------------------------
    private static final String URL = "jdbc:mysql://localhost:3306/atividade2";
    private static final String USUARIO = "root";
    private static final String SENHA = "fefe21@";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection conectar() throws SQLException {
        Connection conexao = null;
        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conn = conexao; // Atribuindo a conexão à variável estática
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao registrar o Driver: " + e.getMessage());
        }
        return conexao;
    }
//-----------------------------------Cadastrar-------------------------------------------------

    public int Cadastrar(Filme filme) throws ParseException {
        int idInserido = -1; // Valor padrão caso não seja possível recuperar o ID
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dataLancamento = formatter.parse(filme.getDataLancamento());
            st = conn.prepareStatement("INSERT INTO filmes (NomeFilme, DataLancamento, Categoria) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            // Estabeleça os valores dos parâmetros corretamente
            st.setString(1, filme.getNomeFilme());
            st.setDate(2, new java.sql.Date(dataLancamento.getTime())); // Use 2 para a data
            st.setString(3, filme.getCategoria()); // Use 3 para a categoria

            // Execute a inserção
            st.executeUpdate();

            // Recupere as chaves geradas (no caso, o ID do filme)
            ResultSet generatedKeys = st.getGeneratedKeys();
            if (generatedKeys.next()) {
                idInserido = generatedKeys.getInt(1); // Obtém o ID gerado
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            ex.printStackTrace();
        }
        return idInserido;
    }

//----------------------------------ConsultaTodos---------------------------------------------------
    public List<Filme> consultarTodos() throws SQLException {
        Connection conn = conectar();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Filme> filmes = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM filmes");
            rs = st.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme(
                        rs.getInt("id"),
                        rs.getString("NomeFilme"),
                        rs.getString("Categoria"),
                        rs.getString("DataLancamento")
                );
                filmes.add(filme);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            close(conn);
        }

        return filmes;
    }

//---------------------------------Consulta por Categoria------------------------------------
    public List<Filme> ConsultaPorCategoria(String categoria) throws SQLException {
        Connection conn = conectar();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Filme> filmes = new ArrayList<>();

        try {
            st = conn.prepareStatement("SELECT * FROM filmes WHERE categoria LIKE ?");
            st.setString(1, "%" + categoria + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme(
                        rs.getString("NomeFilme"),
                        rs.getString("Categoria"),
                        rs.getString("DataLancamento")
                );
                filmes.add(filme);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            close(conn);
        }

        return filmes;
    }
//-----------------------------------Atualizar-------------------------------------------------

    public String atualizar(Filme filme) throws SQLException, ParseException {
        Connection conn = conectar();
        PreparedStatement st = null;
        int rowsAffected = 0;

        try {
            st = conn.prepareStatement("UPDATE filmes SET NomeFilme = ?, Categoria = ?, DataLancamento = ? WHERE id = ?");
            st.setString(1, filme.getNomeFilme());
            st.setString(2, filme.getCategoria());

            // Convertendo a string de data para o formato adequado
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatter.parse(filme.getDataLancamento());
            java.sql.Date dataLancamento = new java.sql.Date(dataUtil.getTime());
            st.setDate(3, dataLancamento);
            st.setInt(4, filme.getId());

            // Adicione o código para definir o id
            // st.setInt(4, filme.getId()); // Exemplo: supondo que o id seja um atributo da classe Filme
            rowsAffected = st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
            close(conn);
        }
        return null;
    }

//-------------------------------Excluir---------------------------------------------------
    public boolean excluir(String nome) throws SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        boolean sucesso = false;

        try {
            conn = conectar();
            String sql = "DELETE FROM filmes WHERE NomeFilme = ?"; // Corrigido para o nome da coluna correta
            st = conn.prepareStatement(sql);
            st.setString(1, nome.trim()); // Use trim() para remover espaços em branco extras
            int rowsAffected = st.executeUpdate();
            sucesso = rowsAffected > 0;
        } finally {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return sucesso;
    }
//-----------------------------------Desconctando-----------------------------------------------

    public static void close(Connection conexao) throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
    }
}
