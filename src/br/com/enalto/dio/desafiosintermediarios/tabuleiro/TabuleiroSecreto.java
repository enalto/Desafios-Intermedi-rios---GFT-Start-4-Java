package br.com.enalto.dio.desafiosintermediarios.tabuleiro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TabuleiroSecreto {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        // dimensão do tabuleiro
        int N = Integer.parseInt(s[0]);
        // quantidade de operações
        int Q = Integer.parseInt(s[1]);

        Tabuleiro tabuleiro = new Tabuleiro(N);

//        System.out.println();

//        tabuleiro.setLinha(3, 3);

//        tabuleiro.print();

//        tabuleiro.setColuna(2, 7);
//        tabuleiro.print();

        int operacao;
        int valor;
        int linhaColuna;

        String[] s1;

        for (int i = 0; i < Q; i++) {
            s1 = br.readLine().split(" ");
            operacao = Integer.parseInt(s1[0]);
            linhaColuna = Integer.parseInt(s1[1]);
            switch (operacao) {
                // 1 X R: Atribuir o valor R a todos os números da linha X;
                case 1:
                    valor = Integer.parseInt(s1[2]);
                    tabuleiro.setLinha(linhaColuna, valor);
                    break;
                // 2 X R: Atribuir o valor R a todos os números da coluna X;
                case 2:
                    valor = Integer.parseInt(s1[2]);
                    tabuleiro.setColuna(linhaColuna, valor);
                    break;
                // 3 X: Imprimir o valor mais frequente na linha X;
                case 3:
                    tabuleiro.printValorMaisFrequenteNaLinha(linhaColuna);
                    break;
                // 4 X: Imprimir o valor mais frequente da coluna X.
                case 4:
                    tabuleiro.printValorMaisFrequenteNaColuna(linhaColuna);
                    break;
            }
        }

    }

}

class Tabuleiro {
    private int tabuleiro[][];

    public Tabuleiro(int N) {
        this.tabuleiro = new int[N][N];
        init();
        //print();
    }


    private void init() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiro[i][j] = 0;
            }
        }
    }

    public void print() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setLinha(int row, int valor) {
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[row - 1][i] = valor;
        }
        //print();
    }

    public void setColuna(int col, int valor) {
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i][col - 1] = valor;
        }
        //print();
    }

    public void printValorMaisFrequenteNaLinha(int nlinha) {
        int elementos[] = new int[tabuleiro.length];
        for (int i = 0; i < tabuleiro.length; i++) {
            elementos[i] = tabuleiro[nlinha - 1][i];
        }
        Map<Integer, Long> mapIntegerCount =
                Arrays.stream(elementos).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        Optional<Map.Entry<Integer, Long>> max = mapIntegerCount.entrySet().stream().max((o1, o2) -> Long.compare(o1.getValue(), o2.getValue()));
        Stream<Map.Entry<Integer, Long>> entryStream;
        entryStream = mapIntegerCount.entrySet().stream().filter(integerLongEntry -> {
            return integerLongEntry.getValue().equals(max.get().getValue());
        });
        Optional<Map.Entry<Integer, Long>> max1 = entryStream.max((o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
        System.out.println(max1.get().getKey());
    }

    public void printValorMaisFrequenteNaColuna(int Coluna) {
        int elementos[] = new int[tabuleiro.length];
        for (int i = 0; i < tabuleiro.length; i++) {
            elementos[i] = tabuleiro[i][Coluna - 1];
        }
        Map<Integer, Long> mapIntegerCount =
                Arrays.stream(elementos).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        Optional<Map.Entry<Integer, Long>> max = mapIntegerCount.entrySet()
                .stream().max((o1, o2) -> Long.compare(o1.getValue(), o2.getValue()));
        Stream<Map.Entry<Integer, Long>> entryStream = mapIntegerCount.entrySet()
                .stream().filter(integerLongEntry -> integerLongEntry.getValue().equals(max.get().getValue()));
        Optional<Map.Entry<Integer, Long>> max1 = entryStream.max((o1, o2) -> Integer.compare(o1.getKey(), o2.getKey()));
        System.out.println(max1.get().getKey());
    }
}