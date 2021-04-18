package main;

import java.util.Scanner;
import state.Pessoa;

import java.util.ArrayList;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	public static void main(String[] args) {		
		while(true) {
			System.out.println("Sistema para aplica��o de vacina contra COVID-19.");
			System.out.println("1 - Cadastrar Usu�rio.");
			System.out.println("2 - Consultar Usu�rio");
			System.out.println("3 - Confirmar aplica��o da dose.");
			System.out.println("4 - Habilitar vacina��o.");
			System.out.println("5 - Alterar dados de um usu�rio.");
			System.out.println("Aperte qualquer outro n�mero pra sair.");
			int op = scan.nextInt();
			if(op == 1)
				registrarUsuario();
			if(op == 2)
				System.out.println(consultarUsuario());
			if(op == 3)
				confirmarVacinacao();
			if(op == 4)
				habilitarVacinacao();
			if(op == 5)
				alterarUsuario();
			if(op <= 0 || op > 5)
				break;
			System.out.println();
			System.out.println("\n");
		}
	}
	
	private static void registrarUsuario() {
		System.out.println("Nome:");
		String nome = scan.next();
		System.out.println("Idade:");
		int idade = scan.nextInt();
		System.out.println("CPF:");
		String cpf = scan.next();
		System.out.println("Endere�o:");
		String endereco = scan.next();
		System.out.println("Cart�o do SUS:");
		String cartaoSUS = scan.next();
		System.out.println("Email:");
		String email = scan.next();
		System.out.println("Telefone:");
		String telefone = scan.next();
		System.out.println("Profiss�o:");
		String profissao = scan.next();
		System.out.println("Comorbidade:");
		String comorbidade = scan.next();
		pessoas.add(new Pessoa(nome, idade, cpf, endereco, cartaoSUS, email, telefone, profissao, comorbidade));
		System.out.println("Usu�rio registrado");
	}	
	
	public static String consultarUsuario() {
		System.out.println("Inserir CPF do usu�rio:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		for(Pessoa p: pessoas) 
			if (pessoa.equals(p)) {
				if(p.getStatus().getStatus().equals("Primeira dose j� aplicada! Na fila para receber a segunda dose!") && p.getStatus().doseDisp())
					p.changeStatus();
				return p.toString();
			}
		return "Usu�rio n�o cadastrado";
	}
	
	private static void confirmarVacinacao() {
		System.out.println("Inserir CPF do usu�rio:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		boolean bol = true;
		for(Pessoa p: pessoas) {
			if (pessoa.equals(p)) {
				if(p.getStatus().doseDisp()) {
					System.out.println("Dose aplicada no usu�rio:" + p.getNome() + " -  " + p.getCpf());
					p.changeStatus();
					System.out.println("Status atual: " + p.getStatus().getStatus());
					
					bol = false;
				}else {
					System.out.println("Dose n�o pode ser aplicada no usu�rio " + p.getNome() + " - CPF: " + p.getCpf());
					System.out.println("Status atual: " + p.getStatus().getStatus());
					bol = false;
				}
			}	
		}
		if(bol) {
			System.out.println("Usu�rio com o CPF " + cpf + " n�o encontrado");
		}
	}
	
	public static void alterarUsuario() {
		System.out.println("Inserir CPF do usu�rio:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		for(Pessoa p: pessoas) {
			if(p.equals(pessoa)) {
				boolean opc = true;
				do {	
				System.out.println("SELECIONE UMA OP��O:");
				System.out.println("1-ALTERAR NOME");
				System.out.println("2-ALTERAR IDADE");
				System.out.println("3-ALTERAR COMORBIDADE");
				System.out.println("4-ALTERAR ENDERE�O");
				System.out.println("5-ALTERAR CART�O DO SUS");
				System.out.println("6-ALTERAR EMAIL");
				System.out.println("7-ALTERAR TELEFONE");
				System.out.println("8-ALTERAR PROFISS�O");
				System.out.println("0-RETORNAR A PAGINA INICIAL");
				int op = scan.nextInt();
				if(op == 1) {
					System.out.println("Nome:");
					p.setNome(scan.next());
					break;	
				}
				if(op == 2) {
					System.out.println("Idade:");
					p.setIdade(scan.nextInt());
					break;
				}
				if(op == 3) {
					System.out.println("Comorbidade:");
					p.setComorbidades(scan.next());
					break;
				}
				if(op == 4) {
					System.out.println("Endere�o:");
				 	p.setEndereco(scan.next());
				 	break;
				}
				if(op == 5) {
					System.out.println("Cart�o do SUS:");
				 	p.setCartaoSUS(scan.next());
				 	break;
				}
				if(op == 6) {
					System.out.println("Email:");
					p.setEmail(scan.next());
					break;
				}
				if(op == 7) {
					System.out.println("Telefone:");
					p.setTelefone(scan.next());
					break;
				}
				if(op == 8) {
					System.out.println("Profiss�o:");
					p.setProfissao(scan.next());
					break;
				}
				if(op == 0)
					opc = false;
				}while(opc);
			}
		}
	}

	public static void habilitarVacinacao() {
		System.out.println("1-Habilitar idade para primeira dose.");
		System.out.println("2-Habilitar profiss�o para a primeira dose.");
		System.out.println("3-Habilitar comorbidade para a primeira dose.");
		int op = scan.nextInt();
		if(op == 1)
			habilitarIdade();
		if(op == 2)
			habilitarProfissao();
		if(op == 3)
			habilitarComorbidade();
	}
	
	private static void habilitarComorbidade() {
		System.out.println("Inserir nova comorbidade que estar� dispon�vel para a primeira dose.");
		String comorb = scan.next();
		for (Pessoa p: pessoas) {
			if(p.getComorbidade().equals(comorb) && p.getStatus().getStatus().equals("N�O HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com a comorbidade " + comorb + " agora est�o aptas a receber a vacina!");
	}

	private static void habilitarIdade() {
		System.out.println("INSERIR NOVA IDADE PARA A PRIMEIRA DOSE");
		int idade = scan.nextInt();
		for (Pessoa p: pessoas) {
			if(p.getIdade() >= idade && p.getStatus().getStatus().equals("N�O HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com " + idade + " anos ou mais agora est�o aptas a receber a vacina!");
	}
	
	private static void habilitarProfissao() {
		System.out.println("INSERIR NOVA PROFISS�O PARA A PRIMEIRA DOSE");
		String trab = scan.next();
		for (Pessoa p: pessoas) {
			if(p.getProfissao().equals(trab) && p.getStatus().getStatus().equals("N�O HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com a profiss�o " + trab + " agora est�o aptos a receber a vacina!");
	}
	
}