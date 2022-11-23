import java.util.Scanner;

import Arvores.ArvoreRubroNegra;

public class Main {
    public static void main(String[] args) throws Exception {

        ArvoreRubroNegra arvore = new ArvoreRubroNegra();
        int choice = 0;
        
        Scanner sc = new Scanner(System.in);
        while(choice != 7){
            System.out.println("Qual ação?:");  
            System.out.println("1 - Inserir:");  
            System.out.println("2 - Remover:");  
            System.out.println("3 - Mostrar árvore:");  
            System.out.println("9 - Sair:");  
            choice = sc.nextInt();
            if(choice == 1){
                System.out.print("Qual numero inserir?:");  
                int numero = sc.nextInt();
                arvore.inserir(numero);
                arvore.mostrarArvore();
            }
            if(choice == 2){
                System.out.print("Remover:");  
                int numero = sc.nextInt();
                arvore.remover(numero);
                arvore.mostrarArvore();
            }
            if(choice == 3){
                arvore.mostrarArvore();
            }
            if(choice == 9){
                choice = 7;
                break;
            }
        }

    }
}
