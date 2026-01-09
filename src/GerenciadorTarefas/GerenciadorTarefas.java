package GerenciadorTarefas;

import java.util.Scanner;

public class GerenciadorTarefas {

	public static void main(String[] args) {
		
		
		
		 Scanner scanner = new Scanner(System.in);

	        String[] tarefas = new String[10];
	        int totalTarefas = 0;
	        int opcao;

	        do {
	            System.out.println("\n===== GERENCIADOR DE TAREFAS =====");
	            System.out.println("1 - Adicionar tarefa");
	            System.out.println("2 - Listar tarefas");
	            System.out.println("3 - Remover tarefa");
	            System.out.println("4 - Sair");
	            System.out.print("Escolha uma opção: ");

	            opcao = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (opcao) {

	                case 1:
	                    if (totalTarefas < tarefas.length) {
	                        System.out.print("Digite a tarefa: ");
	                        String tarefa = scanner.nextLine();
	                        tarefas[totalTarefas] = tarefa;
	                        totalTarefas++;
	                        System.out.println("Tarefa adicionada!");
	                    } else {
	                        System.out.println("Lista cheia!");
	                    }
	                    break;

	                case 2:
	                    if (totalTarefas == 0) {
	                        System.out.println("Nenhuma tarefa cadastrada.");
	                    } else {
	                        System.out.println("📋 Tarefas:");
	                        for (int i = 0; i < totalTarefas; i++) {
	                            System.out.println((i + 1) + " - " + tarefas[i]);
	                        }
	                    }
	                    break;

	                case 3:
	                    if (totalTarefas == 0) {
	                        System.out.println("Não há tarefas para remover.");
	                    } else {
	                        System.out.print("Número da tarefa para remover: ");
	                        int remover = scanner.nextInt();

	                        if (remover > 0 && remover <= totalTarefas) {
	                            for (int i = remover - 1; i < totalTarefas - 1; i++) {
	                                tarefas[i] = tarefas[i + 1];
	                            }
	                            tarefas[totalTarefas - 1] = null;
	                            totalTarefas--;
	                            System.out.println("Tarefa removida!");
	                        } else {
	                            System.out.println("Número inválido!");
	                        }
	                    }
	                    break;

	                case 4:
	                    System.out.println("Encerrando...");
	                    break;

	                default:
	                    System.out.println("Opção inválida!");
	            }

	        } while (opcao != 4);

	        scanner.close();
	    }
	}