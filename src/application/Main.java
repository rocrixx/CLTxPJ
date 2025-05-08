package application;

import java.util.Scanner;
import entities.Funcionario;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome do funcionário: ");
        String nome = sc.nextLine();

        System.out.print("Salário bruto: ");
        double salarioBruto = sc.nextDouble();

        System.out.print("Benefício refeição: ");
        double refeicao = sc.nextDouble();

        System.out.print("Benefício transporte: ");
        double transporte = sc.nextDouble();

        System.out.print("Bônus: ");
        double bonus = sc.nextDouble();

        Funcionario funcionario = new Funcionario(nome, salarioBruto, refeicao, transporte, bonus);
        funcionario.mostrarResumo();

        sc.close();
    }
}

