package Arvores;
import java.util.LinkedList;
import Nos.No;
import Utils.Resultado;

public class ArvoreBinariaPesquisa {
    public No raiz = null;

    public No pesquisar(No v, int k) {
        if (v.éNoFolha()) {
            return v;
        }
        if (k < v.getChave()) {
            if (v.getFilhoEsquerdo() != null){
                return pesquisar(v.getFilhoEsquerdo(), k);
            }
            else{
                return v;
            }
        } else if (k == v.getChave()) {
            return v;
        } else if (k > v.getChave()) {
            if (v.getFilhoDireito() != null){
                return pesquisar(v.getFilhoDireito(), k);
            }
            else{
                return v;
            }
        }

        return null;
    }

    public No inserir(int k) {
        No novo = new No(null, k);

        if (this.raiz == null) {
            this.raiz = novo;
            return novo;
        }

        No pai = pesquisar(this.raiz, k);

        if (pai.getChave() == k) {
            return pai;
        } else if (pai.getChave() > k) {
            pai.setFilhoEsquerdo(novo);
            novo.setPai(pai);
        } else if (pai.getChave() < k) {
            pai.setFilhoDireito(novo);
            novo.setPai(pai);
        }
        
        return novo;
    }


    public void rebalancearArvore(No no)
    {
    }

    public void rotacaoEsquerdaSimples(No no)
    {
        No pai_desbalanceado = no.getPai();
        No sucessor = no.getFilhoDireito();
        
        if (pai_desbalanceado != null){
            if (no.éFilhoDireito()){
                pai_desbalanceado.setFilhoDireito(sucessor);
            }
            else {
                pai_desbalanceado.setFilhoEsquerdo(sucessor);
            }
        }
        else{
            this.setRaiz(sucessor);
        }
        
        sucessor.setPai(pai_desbalanceado);

        no.setFilhoDireito(sucessor.getFilhoEsquerdo()); 
        if (sucessor.getFilhoEsquerdo() != null){
            sucessor.getFilhoEsquerdo().setPai(no);
        }
        no.setPai(sucessor);

        sucessor.setFilhoEsquerdo(no);
    }

    public void rotacaoEsquerdaDupla(No no)
    {
        this.rotacaoDireitaSimples(no.getFilhoDireito());
        
        this.rotacaoEsquerdaSimples(no);
    }

    public void rotacaoDireitaSimples(No no)
    {
        No pai_desbalanceado = no.getPai();
        No sucessor = no.getFilhoEsquerdo();
        
        if (pai_desbalanceado != null){
            if (no.éFilhoDireito()){
                pai_desbalanceado.setFilhoDireito(sucessor);
            }
            else {
                pai_desbalanceado.setFilhoEsquerdo(sucessor);
            }
        }
        else{
            this.setRaiz(sucessor);
        }
        
        sucessor.setPai(pai_desbalanceado);

        no.setFilhoEsquerdo(sucessor.getFilhoDireito()); 
        if (sucessor.getFilhoDireito() != null){
            sucessor.getFilhoDireito().setPai(no);
        }
        no.setPai(sucessor);

        sucessor.setFilhoDireito(no);
    }

    public void rotacaoDireitaDupla(No no)
    {
        this.rotacaoEsquerdaSimples(no.getFilhoEsquerdo());
        this.rotacaoDireitaSimples(no);
    }

    public Resultado remover(No remover) {
        if (remover.éNoFolha()) {
            No pai = remover.getPai();
            if (pai == null) {
                this.raiz = null;
                return null;
            }

            if (remover.éFilhoEsquerdo()) {
                pai.setFilhoEsquerdo(null);
            } else if (remover.éFilhoDireito()) {
                pai.setFilhoDireito(null);
            }

            return new Resultado(remover, null);
        }

        else if (!remover.temDoisFilhos()){
            No sucessor = remover.getFilhoDireito() != null ? remover.getFilhoDireito()
                    : remover.getFilhoEsquerdo();

            if (remover == this.raiz) {
                this.raiz = sucessor;
            }                
            No pai_remover = remover.getPai();     

            if(pai_remover != null){
                if (remover.éFilhoEsquerdo()) {
                    pai_remover.setFilhoEsquerdo(sucessor);
                } else if (remover.éFilhoDireito()) {
                    pai_remover.setFilhoDireito(sucessor);
                }
            }
            sucessor.setPai(pai_remover);

            return new Resultado(remover, sucessor);
        }

        else {
            int chave_remover = remover.getChave();
            No sucessor = this.inOrder(remover.getFilhoDireito());
            int chave_sucessor = sucessor.getChave();

            Resultado resultado = remover(sucessor);
            
            No retorno = resultado.getRemovido();
            remover.setChave(chave_sucessor);
            retorno.setChave(chave_remover);
            
            String cor_remover = remover.getCor();
            remover.setCor(retorno.getCor());
            retorno.setCor(cor_remover);
            return new Resultado(retorno, remover);
        }   
        
    }

    public No inOrder(No v) {
        if (v.getFilhoEsquerdo() != null) {
            return inOrder(v.getFilhoEsquerdo());
        }
        return v;
        // if (v.getFilhoDireito() != null){
        // return inOrder(v.getFilhoDireito());
        // }
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public No getRaiz(){
        return this.raiz;
    }

    public void mostrarArvore() {
        No raiz = this.raiz;

        LinkedList<No> nivelArvore = new LinkedList<No>();
        nivelArvore.add(raiz);
        LinkedList<No> temp = new LinkedList<No>();

        int contador = 0;
        int altura = altura(this.raiz);
        double numberOfElements = (Math.pow(2, (altura)) - 1);

        while (contador <= (altura+1)) {
            No removido = nivelArvore.removeFirst();
            if (temp.isEmpty()) {
                imprimirEspaco(numberOfElements
                        / Math.pow(2, contador + 1),
                        removido);
            } else {
                imprimirEspaco(numberOfElements
                        / Math.pow(2, contador),
                        removido);
            }
            if (removido == null) {
                temp.add(null);
                temp.add(null);
            } else {
                temp.add(removido.getFilhoEsquerdo());
                temp.add(removido.getFilhoDireito());
            }

            if (nivelArvore.isEmpty()) {
                System.out.println("");
                System.out.println("");
                nivelArvore = temp;
                temp = new LinkedList<>();
                contador++;
            }
        }
    }

    public static void imprimirEspaco(double n, No x) {
        for (; n > 0; n--) {
            System.out.print("\t");
        }
        if (x == null) {
            System.out.print(" ");
        } else {
            System.out.print(x.getChave() + " [" + x.cor + "]");
        }
    }

    public static int altura(No raiz) {
        if (raiz == null || raiz.éNoFolha()) {
            return 0;
        }

        return 1 + Math.max(altura(raiz.getFilhoEsquerdo()), altura(raiz.getFilhoDireito()));
    }

    public static int profundidade(No raiz) {
        if (raiz == null || raiz.getPai() == null) {
            return 0;
        }

        return 1 + Math.max(altura(raiz.getFilhoEsquerdo()), altura(raiz.getFilhoDireito()));
    }

}
