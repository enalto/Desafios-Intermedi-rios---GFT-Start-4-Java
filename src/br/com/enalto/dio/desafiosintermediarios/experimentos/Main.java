package br.com.enalto.dio.desafiosintermediarios.experimentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static br.com.enalto.dio.desafiosintermediarios.experimentos.CasoDeTeste.Adicionar;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // quantidade de testes
        System.out.println("Informe o total de casos de testes ");
        int N = input.nextInt();


        for (int i = 1; i <= N; i++) {
            System.out.println("Quantidade de cobaias ");
            int quantidade = input.nextInt();
            System.out.println("Tipo ");
            String tipo = input.next();

            CasoDeTeste casoDeTeste = new CasoDeTeste(quantidade, TipoCobaia.getTipo(tipo));
            Adicionar(casoDeTeste);
        }

        int totalDeCobaias = CasoDeTeste.getTotalDeCobaias();
        int totalDeCobaiasCoelho = CasoDeTeste.getTotalDeCobaias(TipoCobaia.COELHO);
        int totalDeCobaiasRato = CasoDeTeste.getTotalDeCobaias(TipoCobaia.RATO);
        int totalDeCobaiasSapo = CasoDeTeste.getTotalDeCobaias(TipoCobaia.SAPO);

        double percentualDeCoelho = (((double) totalDeCobaiasCoelho / totalDeCobaias) * 100);
        double percentualDeRato = (((double) totalDeCobaiasRato / totalDeCobaias) * 100);
        double percentualDeSapo = (((double) totalDeCobaiasSapo / totalDeCobaias) * 100);

        System.out.printf("Total: %d cobaias\n", totalDeCobaias);
        System.out.printf("Total de coelhos: %d\n", totalDeCobaiasCoelho);
        System.out.printf("Total de ratos: %d\n", totalDeCobaiasRato);
        System.out.printf("Total de sapos: %d\n", totalDeCobaiasSapo);
        System.out.println("Percentual de coelhos:");
        System.out.println(String.format("%.2f %%", percentualDeCoelho).replace(",", "."));
        System.out.println("Percentual de ratos:");
        System.out.println(String.format("%.2f %%", percentualDeRato).replace(",", "."));
        System.out.println("Percentual de sapos:");
        System.out.println(String.format("%.2f %%", percentualDeSapo).replace(",", "."));
    }
}

enum TipoCobaia implements Comparable<TipoCobaia> {

    RATO("r"),
    COELHO("c"),
    SAPO("s");

    private String c;

    TipoCobaia(String c) {
        this.c = c;
    }

    public String getC() {
        return c;
    }

    public static TipoCobaia getTipo(String s) {
        for (TipoCobaia value : values()) {
            if (value.getC().equalsIgnoreCase(s)) {
                return value;
            }
        }
        return null;
    }

}

class CasoDeTeste {
    private int quantidade;
    private TipoCobaia tipo;
    public static List<CasoDeTeste> casosDeTesteList = new ArrayList<>();


    public CasoDeTeste(int quantidade, TipoCobaia tipo) {
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoCobaia getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "CasoDeTeste{" +
                "quantidade=" + quantidade +
                ", tipo=" + tipo +
                '}';
    }

    public static int getTotalDeCobaias() {
        return casosDeTesteList.stream().mapToInt(c -> c.getQuantidade()).sum();
    }

    public static int getTotalDeCobaias(TipoCobaia tipo) {
        return casosDeTesteList.stream().filter(new Predicate<CasoDeTeste>() {
            @Override
            public boolean test(CasoDeTeste casoDeTeste) {
                if (casoDeTeste.tipo.getC().equalsIgnoreCase(tipo.getC())) {
                    return true;
                }
                return false;
            }
        }).mapToInt(c -> c.getQuantidade()).sum();
    }

    public static void Adicionar(CasoDeTeste casoDeTeste) {
        casosDeTesteList.add(casoDeTeste);
    }
}