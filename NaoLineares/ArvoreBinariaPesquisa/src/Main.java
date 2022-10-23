import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Arvore arvore = new Arvore();
        int choice = 0;
        arvore.inserir(35);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(25);   
        arvore.inserir(38);
        arvore.inserir(45);
        arvore.inserir(39);
        arvore.mostrarArvore();
        
        Scanner sc = new Scanner(System.in);
        while(choice != 7){
            System.out.println("Qual ação?:");  
            System.out.println("1 - Inserir:");  
            System.out.println("2 - Remover:");  
            System.out.println("3 - Mostrar árvore:");  
            System.out.println("4 - Mostrar pai:");  
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
                arvore.remover_avl(numero);
                arvore.mostrarArvore();
            }
            if(choice == 3){
                arvore.mostrarArvore();
            }
            if(choice == 4){
                System.out.print("Qual pai mostrar?:");  
                int numero = sc.nextInt();
                int pai = arvore.get_pai(numero);
                System.out.println(pai);
            }
            if(choice == 9){
                choice = 7;
                break;
            }
        }

    }
}
