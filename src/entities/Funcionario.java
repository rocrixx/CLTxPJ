package entities;

public class Funcionario {
    private final String nome;
    private final double salarioBruto;
    private final double refeicao;
    private final double transporte;
    private final double bonus;

    public Funcionario(String nome, double salarioBruto, double refeicao, double transporte, double bonus) {
        this.nome = nome;
        this.salarioBruto = salarioBruto;
        this.refeicao = refeicao;
        this.transporte = transporte;
        this.bonus = bonus;
    }

    public double calcularINSS() {
        double inss;
        double base = Math.min(salarioBruto, 7786.02);

        if (base <= 1412.00) {
            inss = base * 0.075;
        } else if (base <= 2666.68) {
            inss = (1412.00 * 0.075) + ((base - 1412.00) * 0.09);
        } else if (base <= 4000.03) {
            inss = (1412.00 * 0.075) + ((2666.68 - 1412.00) * 0.09) + ((base - 2666.68) * 0.12);
        } else {
            inss = (1412.00 * 0.075) + ((2666.68 - 1412.00) * 0.09) +
                    ((4000.03 - 2666.68) * 0.12) + ((base - 4000.03) * 0.14);
        }

        return inss;
    }

    public double calcularIRRF(double base) {
        double baseIR = base - 528.00;

        if (baseIR <= 2112.00) return 0.0;
        else if (baseIR <= 2826.65) return baseIR * 0.075 - 158.40;
        else if (baseIR <= 3751.05) return baseIR * 0.15 - 370.40;
        else if (baseIR <= 4664.68) return baseIR * 0.225 - 651.73;
        else return baseIR * 0.275 - 884.96;
    }

    public double calcularFGTS() {
        return salarioBruto * 0.08;
    }

    public double calcularTotalAnualCLT() {
        double inss = calcularINSS();
        double salarioLiquidoBase = salarioBruto - inss;
        double irrf = calcularIRRF(salarioLiquidoBase);
        double salarioLiquidoMensal = salarioBruto - inss - irrf + refeicao + transporte + bonus;
        double ferias = salarioBruto + (salarioBruto / 3.0);
        double fgtsAnual = calcularFGTS() * 12;

        return (salarioLiquidoMensal * 12) + ferias + salarioBruto + fgtsAnual;
    }

    public double calcularSalarioPjEquivalente() {
        return calcularTotalAnualCLT() / 12.0;
    }

    public void mostrarResumo() {
        double inss = calcularINSS();
        double baseIR = salarioBruto - inss;
        double irrf = calcularIRRF(baseIR);
        double fgts = calcularFGTS();

        double salarioLiquidoMensal = salarioBruto - inss - irrf + refeicao + transporte + bonus;
        double salarioPjEquivalente = calcularSalarioPjEquivalente();

        System.out.println("\n--- RESUMO SALARIAL ---");
        System.out.println("Funcionário: " + nome);
        System.out.printf("Salário Bruto: R$ %.2f\n", salarioBruto);
        System.out.printf("Desconto INSS: R$ %.2f\n", inss);
        System.out.printf("Base de cálculo IRRF: R$ %.2f\n", baseIR);
        System.out.printf("Desconto IRRF: R$ %.2f\n", irrf);
        System.out.printf("FGTS (depósito): R$ %.2f\n", fgts);
        System.out.printf("Benefícios: R$ %.2f\n", refeicao + transporte + bonus);
        System.out.printf("Salário Líquido Mensal: R$ %.2f\n", salarioLiquidoMensal);

        System.out.println("\n--- EQUIVALÊNCIA PJ ---");
        System.out.printf("Total líquido anual CLT (com férias + 13 + FGTSº): R$ %.2f\n", calcularTotalAnualCLT());
        System.out.printf("Salário PJ equivalente (mensal): R$ %.2f\n", salarioPjEquivalente);
    }
}
