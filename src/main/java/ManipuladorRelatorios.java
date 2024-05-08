import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ManipuladorRelatorios {

    private Connection conexao;

    public ManipuladorRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirRelatorioTreino (LocalDate data, String cpf, String exercicio, double carga) {
        String sql = "INSERT INTO relatorios(data, cpf, exercicio, carga) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setObject(1, data);
            stmt.setString(2, cpf);
            stmt.setString(3, exercicio);
            stmt.setDouble(4, carga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir entrada no relatorio." + e);
        }
    }

    public void buscarPresencas(String cpfAluno) {
        Scanner entrada = new Scanner(System.in);
        LocalDate dataInicio;
        LocalDate dataFim;
        String dataString;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite a data de inicio para buscar: ");
        dataString = entrada.nextLine();
        try {
            dataInicio = LocalDate.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao fazer o parse da data.");
            return;
        }

        System.out.println("Digite a data de fim para buscar: ");
        dataString = entrada.nextLine();
        try {
            dataFim = LocalDate.parse(dataString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao fazer o parse da data.");
            return;
        }

        String sql = "SELECT * FROM relatorios WHERE cpf = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpfAluno);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                java.sql.Date dataBancoDados = (Date) resultado.getObject("data");
                LocalDate dataBancoDadosConvertida = dataBancoDados.toLocalDate();
                boolean isAfter = (dataBancoDadosConvertida.isEqual(dataInicio) ||
                        dataBancoDadosConvertida.isAfter(dataInicio));

                boolean isBefore = (dataBancoDadosConvertida.isBefore(dataFim) ||
                        dataBancoDadosConvertida.isEqual(dataFim));

                if(isAfter && isBefore) {
                    String nomeExercicio = resultado.getString("exercicio");
                    double carga = resultado.getDouble("carga");
                    System.out.println("Voce treinou no dia " + dataBancoDadosConvertida + " o exercicio "
                    + nomeExercicio + " com carga de " + carga + " kg");
                }
            }
        } catch (SQLException e) {
            System.out.println("Impossivel realizar busca." + e);
        }

    }

    public void evolucaoCargas(String nomeExercicio, String cpfAluno) {
        String sql = "SELECT * FROM relatorios WHERE exercicio = ? AND cpf = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nomeExercicio);
            stmt.setString(2, cpfAluno);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                java.sql.Date dataBancoDados = (Date) resultado.getObject("data");
                LocalDate dataBancoDadosConvertida = dataBancoDados.toLocalDate();
                System.out.println("Voce treinou o exercicio " + nomeExercicio + " em " +
                        dataBancoDadosConvertida + " com carga " + resultado.getDouble("carga")
                + " kg");
                }
            } catch (SQLException e) {
            System.out.println("Impossivel realizar busca." + e);
        }
    }
}
