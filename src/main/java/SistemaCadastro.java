import java.util.List;
import java.util.Scanner;

public class SistemaCadastro {
    public static void displayRegistrations(CadastroRepository repository) {
        List<Cadastro> repositories = repository.toListRegisters();
        if (repositories.isEmpty()) {
            System.out.println("Nenhum cadastro encontrado.");
            return;
        }

        for (Cadastro registration : repositories) {
            String text = "Id: %d - Nome: %s - Idade: %d";
            System.out.println(String.format(
                    text, registration.getId(), registration.getName(), registration.getAge()
            ));
        }
    }

    public static void main(String[] args) {
        FabricaConexao.toConnect();
        Scanner scanner = new Scanner(System.in);
        CadastroRepository repository = new CadastroRepository();

        while (true) {
            System.out.println("SERVIÇOS:");
            System.out.println("1-Cadastrar pessoa // 2-Atualizar cadastro // 3-Deletar cadastro" +
                    "// 4-Listar cadastros // 5-Sair ");
            System.out.println("Informe o número do serviço desejado.");
            Integer response = Integer.parseInt(scanner.nextLine());

            if (response == 1) {
                System.out.println("Informe o nome: ");
                String name = scanner.nextLine();

                System.out.println("Informe a idade: ");
                Integer age = Integer.parseInt(scanner.nextLine());

                Cadastro registration = new Cadastro(name, age);
                repository.toRegister(registration);
            } else if (response == 2) {
                displayRegistrations(repository);

                System.out.println("Informe o id do cadastro a atualizar:");
                try {
                    Integer id = Integer.parseInt(scanner.nextLine());
                    Cadastro registrationToBeUpdate = repository.toFilterRegister(id);

                    if (registrationToBeUpdate == null) {
                        System.out.println("Cadastro com o ID informado não encontrado.");
                    } else {
                        System.out.println("Informe o novo nome: ");
                        String newName = scanner.nextLine();
                        System.out.println("Informe a nova idade: ");
                        Integer newAge = Integer.parseInt(scanner.nextLine());

                        registrationToBeUpdate.setName(newName);
                        registrationToBeUpdate.setAge(newAge);
                        repository.toUpdateRegister(registrationToBeUpdate);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido. Por favor, insira um número inteiro.");
                }
            } else if (response == 3) {
                displayRegistrations(repository);

                System.out.println("Informe o id do cadastro a deletar:");
                try {
                    Integer id = Integer.parseInt(scanner.nextLine());
                    Cadastro registrationToBeDelete = repository.toFilterRegister(id);

                    if (registrationToBeDelete == null) {
                        System.out.println("Cadastro com o ID informado não encontrado.");
                    } else {
                        repository.toDeleteRegister(registrationToBeDelete.getId());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido. Por favor, insira um número inteiro.");
                }
            } else if (response == 4){
                displayRegistrations(repository);
            } else {
                break;
            }
        }
        scanner.close();
    }
}
