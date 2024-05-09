import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ManipuladorTreinoEspecificacao {
    private Connection conexao;

    public ManipuladorTreinoEspecificacao(Connection conexao) {
        this.conexao = conexao;
    }

    public List<TreinoEspecificacao> buscarListaTreinos() {
        String sql = "SELECT * FROM treinosespecificacoes";
        List<TreinoEspecificacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TreinoEspecificacao treinoEspecificacao = new TreinoEspecificacao();
                treinoEspecificacao.setCodigo(resultado.getInt("codigo"));
                treinoEspecificacao.setCodigoExercicio(resultado.getInt("codigoexercicio"));
                treinoEspecificacao.setSeries(resultado.getInt("series"));
                treinoEspecificacao.setRepMin(resultado.getInt("repmin"));
                treinoEspecificacao.setRepMax(resultado.getInt("repmax"));
                treinoEspecificacao.setCarga(resultado.getDouble("carga"));
                treinoEspecificacao.setDescanso(resultado.getInt("descanso"));
                retorno.add(treinoEspecificacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao tentar listar os Treinos especificacoes." + e);
        }
        return retorno;
    }
    public void inserirTreinoEspecificacao(TreinoEspecificacao treinoEspecificacao) {
        String sql = "INSERT INTO treinosespecificacoes (codigo, codigoexercicio, series, " +
                "repmin, repmax, carga, descanso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, treinoEspecificacao.getCodigo());
            stmt.setInt(2, treinoEspecificacao.getCodigoExercicio());
            stmt.setInt(3, treinoEspecificacao.getSeries());
            stmt.setInt(4, treinoEspecificacao.getRepMin());
            stmt.setInt(5, treinoEspecificacao.getRepMax());
            stmt.setDouble(6, treinoEspecificacao.getCarga());
            stmt.setInt(7, treinoEspecificacao.getDescanso());
            stmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Impossivel inserir treino.");
        }
    }

    public boolean treinando(Integer codTreino, List<Exercicio> exercicioList, String cpfLocal,
                             ManipuladorRelatorios manipuladorRelatorios) {
        Scanner entrada = new Scanner(System.in);
        LocalDate data = LocalDate.now();
        String sql = "SELECT * FROM treinosespecificacoes WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codTreino);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer codexe = rs.getInt("codigoexercicio");
                double cargaExe = rs.getDouble("carga");
                Integer seriesExe = rs.getInt("series");
                Integer descansoExe = rs.getInt("descanso");
                String nomeExercicio = "ERRO";
                double carga = 0;
                for(Exercicio exercicio : exercicioList) {
                    if(exercicio.getCodigo().equals(codexe)) {
                        nomeExercicio = exercicio.getNome();
                        carga = rs.getDouble("carga");
                    }
                }
                System.out.println("Você realizou o exercício " + nomeExercicio + " com carga " + cargaExe
                        + " kg em " + seriesExe + " series com descanso de " + descansoExe +
                        " segundos? 1 (Sim) 0 (Não)");
                if(entrada.nextInt() == 1){
                    manipuladorRelatorios.inserirRelatorioTreino(data, cpfLocal, nomeExercicio, carga);
                    System.out.println("Deseja alterar a carga? 1 (Sim) 0 (Não)");
                    if(entrada.nextInt() == 1){
                        atualizarCarga(entrada, codTreino, codexe);
                    }
                }

            }

        } catch(SQLException e) {
            System.out.println("Não foi possível iniciar o treino." + e);
            return false;
        }

        return true;
    }

    public void atualizarCarga(Scanner entrada, Integer codTreino, Integer codexe) {
        double carga;
        System.out.println("Digite a nova carga : ");
        carga = entrada.nextDouble();

        String sql2 = "UPDATE treinosespecificacoes SET carga = ? WHERE codigo = ? AND codigoexercicio = ?";
        try {
            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
            stmt2.setDouble(1, carga);
            stmt2.setInt(2, codTreino);
            stmt2.setInt(3, codexe);
            stmt2.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Não foi atualizar a carga." + e);
        }
    }

    public void removerTreino(Integer codigo) {
        String sql = "DELETE FROM treinosespecificacoes WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0)
            System.out.println("Exercicios do treino removidos com sucesso.");
            else
            System.out.println("Não existem exercicios a serem removidos.");
        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir os exercicios do treino.");
        }
    }

    public void alterarExerciciosTreino(Integer codigo, TreinoEspecificacao treinoEspecificacaoLocal) {
        String sql = "UPDATE treinosespecificacoes SET series=?, repmin=?, repmax=?, carga=?, descanso=? WHERE codigo =? AND codigoexercicio = ?";
        try {

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, treinoEspecificacaoLocal.getSeries());
            stmt.setInt(2, treinoEspecificacaoLocal.getRepMin());
            stmt.setInt(3, treinoEspecificacaoLocal.getRepMax());
            stmt.setDouble(4, treinoEspecificacaoLocal.getCarga());
            stmt.setInt(5, treinoEspecificacaoLocal.getDescanso());
            stmt.setInt(6, codigo);
            stmt.setInt(7, treinoEspecificacaoLocal.getCodigoExercicio());

            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao tentar alterar o Exercicio do treino.");
        }
    }
}
