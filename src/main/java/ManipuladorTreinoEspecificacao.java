import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManipuladorTreinoEspecificacao {
    private Connection conexao;

    public ManipuladorTreinoEspecificacao(Connection conexao) {
        this.conexao = conexao;
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

    public boolean treinando(Integer codTreino) {
        Scanner entrada = new Scanner(System.in);
        String sql = "SELECT * FROM treinosespecificacoes WHERE codigo = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codTreino);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer codexe = rs.getInt("codigoexercicio");
                System.out.println("Você realizou o exercício " + codexe + "(aqui tem que por o nome do exe)? 1 (Sim) 0 (Não)");
                if(entrada.nextInt() == 1){
                    System.out.println("Deseja alterar a carga? 1 (Sim) 0 (Não)");
                    if(entrada.nextInt() == 1){
                        System.out.println("Digite a nova carga : ");
                        double carga = entrada.nextDouble();

                        String sql2 = "UPDATE treinosespecificacoes SET carga = ? WHERE codigo = ? AND codigoexercicio = ?";
                        try {
                            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
                            stmt2.setDouble(1, carga);
                            stmt2.setInt(2, codTreino);
                            stmt2.setInt(3, codexe);
                            int rowsUpdated = stmt2.executeUpdate();
                        } catch(SQLException e) {
                            System.out.println("Não foi atualizar a carga." + e);
                            return false;
                        }
                    }
                }

            }

        } catch(SQLException e) {
            System.out.println("Não foi possível iniciar o treino." + e);
            return false;
        }

        return true;
    }
}
