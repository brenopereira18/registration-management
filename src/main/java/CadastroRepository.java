import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroRepository {
    private Connection connection;

    public CadastroRepository() {
        this.connection = FabricaConexao.getConnection();
    }

    public void toRegister(Cadastro registration) {
        String instrucaoSql = "INSERT INTO public.tab_cadastro (nome, idade) VALUES(?, ?);";
        try {
            PreparedStatement pst = connection.prepareStatement(instrucaoSql);
            pst.setString(1, registration.getName());
            pst.setInt(2, registration.getAge());
            pst.execute();
            System.out.println("Cadastro realizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toUpdateRegister(Cadastro registration) {
        String instrucaoSql = "UPDATE public.tab_cadastro SET nome=?, idade=? WHERE id=?;";
        try {
            PreparedStatement pst = connection.prepareStatement(instrucaoSql);
            pst.setString(1, registration.getName());
            pst.setInt(2, registration.getAge());
            pst.setInt(3, registration.getId());
            pst.execute();
            System.out.println("Update realizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toDeleteRegister(Integer id) {
        String instrucaoSql = "DELETE FROM public.tab_cadastro WHERE id=?;";
        try {
            PreparedStatement pst = connection.prepareStatement(instrucaoSql);
            pst.setInt(1, id);
            pst.execute();
            System.out.println("Cadastro excluido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cadastro> toListRegisters() {
        List<Cadastro> registers = new ArrayList<>();
        String instrucaoSql = "SELECT id, nome, idade FROM public.tab_cadastro;";
        try {
            PreparedStatement pst = connection.prepareStatement(instrucaoSql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                Integer id = result.getInt("id");
                String name = result.getString("nome");
                Integer age = result.getInt("idade");

                Cadastro registration = new Cadastro(name, age);
                registration.setId(id);

                registers.add(registration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registers;
    }

    public Cadastro toFilterRegister(Integer id) {
        Cadastro registration = null;
        String instrucaoSql = "SELECT id, nome, idade FROM public.tab_cadastro WHERE id = ?;";
        try {
            PreparedStatement pst = connection.prepareStatement(instrucaoSql);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                String name = result.getString("nome");
                Integer  age = result.getInt("idade");
                registration = new Cadastro(name, age);
                registration.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registration;
    }
}
