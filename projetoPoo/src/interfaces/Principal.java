package interfaces;

import java.util.Scanner;

import classes.Cliente;
import conexao.Conexao;
import conexao.DadosConexao;
import dao.ClienteDAO;
import dao.CreateDAO;
import files.ArquivoTxt;

public class Principal {
	public static Scanner input = new Scanner(System.in);
	public static final String BD = "projeto_poo";
	public static final String SCHEMA = "serra_tec";
	public static final String PATH = "C:\\temp\\";
	public static final String SFILE = "DadosConexao.ini";

	public static void main(String[] args) {

		ArquivoTxt conexaoIni = new ArquivoTxt(SFILE);
		DadosConexao dadoCon = new DadosConexao();
		boolean abrirSistema = false;

		if (conexaoIni.criarArquivo()) {
			if (conexaoIni.alimentaDadosConexao()) {
				dadoCon = conexaoIni.getData();
				abrirSistema = true;
			} else {
				conexaoIni.apagarArquivo();
				System.out.println("Arquivo de configuração de conexão:\n");
				System.out.println("Local: ");
				String local = input.nextLine();
				System.out.println("Porta: ");
				String porta = input.nextLine();
				System.out.println("Usuário: ");
				String usuario = input.nextLine();
				System.out.println("Senha: ");
				String senha = input.nextLine();
				System.out.println("Database: ");
				String banco = input.nextLine();

				if (conexaoIni.criarArquivo()) {
					conexaoIni.escreverArquivo("bd=PostgreSql");
					conexaoIni.escreverArquivo("local=" + local);
					conexaoIni.escreverArquivo("porta=" + porta);
					conexaoIni.escreverArquivo("usuario=" + usuario);
					conexaoIni.escreverArquivo("senha=" + senha);
					conexaoIni.escreverArquivo("banco=" + banco);

					if (conexaoIni.alimentaDadosConexao()) {
						dadoCon = conexaoIni.getData();
						abrirSistema = true;
					} else
						System.out.println("Não foi possível efetuar a configuração.\nVerifique");
				}
			}
		} else
			System.out.println("Houve um problema na criação do arquivo de configuração.");

		if (abrirSistema) {
			if (CreateDAO.createBD(BD, SCHEMA, dadoCon)) {
				Conexao con = new Conexao(dadoCon);
				con.conect();
				menuPrincipal(con);
			} else {
				System.err.println("Houve um problema na criação do banco de dados.");
			}
		}

	}

	public static void menuPrincipal(Conexao con) {
		int opcao;

		do {
			System.out.println("\nMENU PRINCIPAL");
			System.out.println("---------------------");
			System.out.println("0- Sair");
			System.out.println("1- Clientes");
			System.out.println("2- Produtos");
			System.out.println("3- Pedido");

			opcao = informeOpcao("Informe uma opção: ");

			switch (opcao) {
			case 0:
				System.out.println("Sistema encerrado.");
				break;
			case 1:
				menuCliente(con);
				break;
			case 2:
				menuProdutos(con);
				break;
			case 3:
				menuPedido(con);
				break;

			default:
				System.out.println("Opçãoo inválida.");
			}

		} while (opcao != 0);

	}

	public static int informeOpcao(String msg) {
		System.out.print("\n" + msg);
		String resposta = input.nextLine();
		int opcao;

		try {
			opcao = Integer.parseInt(resposta);
		} catch (Exception e) {
			opcao = 0;
		}

		return opcao;
	}

	public static void menuCRUD() {
		System.out.println("1- Localizar");
		System.out.println("2- Listar");
		System.out.println("3- Voltar");
	}

	public static void menuCliente(Conexao con) {
		int opcao;

		do {
			menuCRUD();
			opcao = informeOpcao("Informe uma opção: ");
			switch (opcao) {
			case 1:
				localizarCliente(con);
				break;
			case 2:
				listarCliente(con);
				break;
			case 3:
				break;
			default:
				System.out.println("Opção inválida.");
			}
		} while (opcao != 6);
	}

	public static void menuProdutos(Conexao con) {
		
	}

	public static void menuPedido(Conexao con) {

	}
//	public static void excluirCliente(Conexao con) {
//		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
//
//		int codigo = informeOpcao("\nInforme o código: ");
//		Cliente cliente = clienteDAO.localizarCliente(null, codigo);
//
//		if (cliente != null) {
//			clienteDAO.apagarCliente(cliente.getIdCliente());
//		}
//	}

	public static void localizarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);

		int codigo = informeOpcao("\nInforme o código: ");
		Cliente cliente = clienteDAO.localizarCliente(null, codigo);

		if (cliente != null) {
			System.out.println("Localização do cliente");
			System.out.printf("Nome: %s", cliente.getNome());
			System.out.printf("Endereço: %s", cliente.getEndereco());
		}
	}

//	public static void alterarCliente(Conexao con) {
//		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
//
//		int codigo = informeOpcao("\nInforme o código: ");
//		Cliente cliente = clienteDAO.localizarCliente(null, codigo);
//
//		if (cliente != null) {
//			clienteDAO.alterarCliente(cliente);
//		}
//	}

	public static void listarCliente(Conexao con) {
		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
		clienteDAO.listarClientes();
	}

//	public static void incluirCliente(Conexao con) {
//		ClienteDAO clienteDAO = new ClienteDAO(con, SCHEMA);
//		Cliente cliente = solicitarDadosCliente();
//		clienteDAO.incluirCliente(cliente);
//	}

//	public static Cliente solicitarDadosCliente() {
//		Cliente cliente = new Cliente();
//
//		System.out.println("\nDados do cliente");
//		System.out.println("Informe o nome: ");
//		String nome = input.nextLine();
//
//		System.out.println("Informe o CPF: ");
//		String cpf = input.nextLine();
//
//		System.out.println("Informe o endereço: ");
//		String endereco = input.nextLine();
//
//		cliente.setNome(nome);
//		cliente.setCpf(cpf);
//		cliente.setEndereco(endereco);
//
//		return cliente;
//	}

	public static boolean isNumeric(String str) {
		return str != null && str.matches("[0-9.]+");
	}

	public static Cliente localizarClientePor(Conexao con) {
		ClienteDAO clienteDao = new ClienteDAO(con, SCHEMA);
		Cliente cliente = null;
		int opcao;

		do {
			opcao = informeOpcao("Escolha como localizar");

			System.out.println("1- Nome");
			System.out.println("2- Código");
			System.out.println("3- Voltar");

			switch (opcao) {
			case 1:
				System.out.println("Informe o nome: ");
				String nome = input.nextLine();
				cliente = clienteDao.localizarCliente(nome, 0);
				if (cliente == null) {
					System.out.println("Cliente não encontrado");
				} else
					opcao = 3;
				break;
			case 2:
				System.out.println("Informe o código: ");
				int codigo = informeOpcao("");
				cliente = clienteDao.localizarCliente(null, codigo);
				if (cliente == null) {
					System.out.println("Cliente não encontrado");
				} else
					opcao = 3;
				break;
			default:
				System.out.println("Código inválido");
			}
		} while (opcao != 3);

		return cliente;
	}
}
