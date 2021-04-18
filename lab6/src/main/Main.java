package main;

import java.util.Scanner;
import state.Pessoa;

import java.util.ArrayList;

public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
	public static void main(String[] args) {		
		while(true) {
			System.out.println("Sistema para aplicação de vacina contra COVID-19.");
			System.out.println("1 - Cadastrar Usuário.");
			System.out.println("2 - Consultar Usuário");
			System.out.println("3 - Confirmar aplicação da dose.");
			System.out.println("4 - Habilitar vacinação.");
			System.out.println("5 - Alterar dados de um usuário.");
			System.out.println("Aperte qualquer outro número pra sair.");
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
		System.out.println("Endereço:");
		String endereco = scan.next();
		System.out.println("Cartão do SUS:");
		String cartaoSUS = scan.next();
		System.out.println("Email:");
		String email = scan.next();
		System.out.println("Telefone:");
		String telefone = scan.next();
		System.out.println("Profissão:");
		String profissao = scan.next();
		System.out.println("Comorbidade:");
		String comorbidade = scan.next();
		pessoas.add(new Pessoa(nome, idade, cpf, endereco, cartaoSUS, email, telefone, profissao, comorbidade));
		System.out.println("Usuário registrado");
	}	
	
	public static String consultarUsuario() {
		System.out.println("Inserir CPF do usuário:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		for(Pessoa p: pessoas) 
			if (pessoa.equals(p)) {
				if(p.getStatus().getStatus().equals("Primeira dose já aplicada! Na fila para receber a segunda dose!") && p.getStatus().doseDisp())
					p.changeStatus();
				return p.toString();
			}
		return "Usuário não cadastrado";
	}
	
	private static void confirmarVacinacao() {
		System.out.println("Inserir CPF do usuário:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		boolean bol = true;
		for(Pessoa p: pessoas) {
			if (pessoa.equals(p)) {
				if(p.getStatus().doseDisp()) {
					System.out.println("Dose aplicada no usuário:" + p.getNome() + " -  " + p.getCpf());
					p.changeStatus();
					System.out.println("Status atual: " + p.getStatus().getStatus());
					
					bol = false;
				}else {
					System.out.println("Dose não pode ser aplicada no usuário " + p.getNome() + " - CPF: " + p.getCpf());
					System.out.println("Status atual: " + p.getStatus().getStatus());
					bol = false;
				}
			}	
		}
		if(bol) {
			System.out.println("Usuário com o CPF " + cpf + " não encontrado");
		}
	}
	
	public static void alterarUsuario() {
		System.out.println("Inserir CPF do usuário:");
		String cpf = scan.next();
		Pessoa pessoa = new Pessoa(cpf);
		for(Pessoa p: pessoas) {
			if(p.equals(pessoa)) {
				boolean opc = true;
				do {	
				System.out.println("SELECIONE UMA OPÇÃO:");
				System.out.println("1-ALTERAR NOME");
				System.out.println("2-ALTERAR IDADE");
				System.out.println("3-ALTERAR COMORBIDADE");
				System.out.println("4-ALTERAR ENDEREÇO");
				System.out.println("5-ALTERAR CARTÃO DO SUS");
				System.out.println("6-ALTERAR EMAIL");
				System.out.println("7-ALTERAR TELEFONE");
				System.out.println("8-ALTERAR PROFISSÃO");
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
					System.out.println("Endereço:");
				 	p.setEndereco(scan.next());
				 	break;
				}
				if(op == 5) {
					System.out.println("Cartão do SUS:");
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
					System.out.println("Profissão:");
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
		System.out.println("2-Habilitar profissão para a primeira dose.");
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
		System.out.println("Inserir nova comorbidade que estará disponível para a primeira dose.");
		String comorb = scan.next();
		for (Pessoa p: pessoas) {
			if(p.getComorbidade().equals(comorb) && p.getStatus().getStatus().equals("NÃO HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com a comorbidade " + comorb + " agora estão aptas a receber a vacina!");
	}

	private static void habilitarIdade() {
		System.out.println("INSERIR NOVA IDADE PARA A PRIMEIRA DOSE");
		int idade = scan.nextInt();
		for (Pessoa p: pessoas) {
			if(p.getIdade() >= idade && p.getStatus().getStatus().equals("NÃO HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com " + idade + " anos ou mais agora estão aptas a receber a vacina!");
	}
	
	private static void habilitarProfissao() {
		System.out.println("INSERIR NOVA PROFISSÃO PARA A PRIMEIRA DOSE");
		String trab = scan.next();
		for (Pessoa p: pessoas) {
			if(p.getProfissao().equals(trab) && p.getStatus().getStatus().equals("NÃO HABILITADO PARA RECEBER A VACINA")) {
				p.changeStatus();
			}
		}
		
		System.out.println("Pessoas com a profissão " + trab + " agora estão aptos a receber a vacina!");
	}
	
}