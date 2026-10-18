import java.util.Locale;

public class ColaboradorComissionado extends Colaborador {

    private double valorVendas;
    private double percentualComissao;

    public ColaboradorComissionado(int matricula, String nome, double valorVendas, double percentualComissao) {
        super(matricula, nome);
        this.valorVendas = valorVendas;
        this.percentualComissao = percentualComissao;
    }

    public double getValorVendas() {
        return valorVendas;
    }

    public void setValorVendas(double valorVendas) {
        this.valorVendas = valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public double calcularComissao() {
        return valorVendas * percentualComissao / 100;
    }

    @Override
    public String getTipo() {
        return "Comissionado";
    }

    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularComissao();
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Valor de vendas...: " + formatarMoeda(valorVendas));
        System.out.println("Percentual........: "
                + String.format(Locale.forLanguageTag("pt-BR"), "%.2f%%", percentualComissao));
        System.out.println("Comissão..........: " + formatarMoeda(calcularComissao()));
    }
}
