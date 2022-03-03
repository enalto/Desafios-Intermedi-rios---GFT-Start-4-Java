package br.com.enalto.dio.desafiosintermediarios.matriz;

public class Main {

    public static void main(String[] args) {

        double d1 = 1.0;
        double d2 = 330.0;
        double d3 = -3.5;
        double d4 = 2.5;
        double d5 = 4.1;

        double [][] M = new double[12][12];

        double count = 1;
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                M[i][j] = count++;
            }
        }

        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                System.out.print(M[i][j]+" ");
            }
            System.out.print("\n");
        }

        double soma=0d;

        int limiteInferior=1;
        int limiteSuperior=11;
        for(int j=11; j>6; j--){
            for(int i=limiteInferior; i<limiteSuperior; i++){
                System.out.print(M[i][j]+" ");
                soma += M[i][j];
            }
            limiteInferior++;
            limiteSuperior--;
            System.out.print("\n");
        }

        System.out.println("Soma= "+soma);
        System.out.println("Media= "+(soma/30));

    }
}
