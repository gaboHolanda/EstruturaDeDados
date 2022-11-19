package Arvores;
import java.util.LinkedList;

import Nos.NoAVL;

public class ArvoreAVL {
    NoAVL raiz;

    public NoAVL pesquisar(NoAVL v, int k) {
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

    public NoAVL inserir(int k) {
        NoAVL novo = new NoAVL(null, k);

        if (this.raiz == null) {
            this.raiz = novo;
            return novo;
        }

        NoAVL pai = pesquisar(this.raiz, k);

        if (pai.getChave() == k) {
            return pai;
        } else if (pai.getChave() > k) {
            pai.setFilhoEsquerdo(novo);
            novo.setPai(pai);
            atualizar_fb_insercao(pai, 1);
        } else if (pai.getChave() < k) {
            pai.setFilhoDireito(novo);
            novo.setPai(pai);
            atualizar_fb_insercao(pai, -1);
        }
        
        return novo;
    }

    public void atualizar_fb_insercao(NoAVL no, int lado)
    {
        System.out.println("Atualizar fator de balanceamento: " + no.getChave());
        if(no.getPai() != null){
            System.out.println("Pai: " + no.getPai().getChave());
        }
        
        if (no != null) {
            no.atualizarFator(lado);
            NoAVL pai = no.getPai();
            if (no.getFatorBalanceamento() > 1 || no.getFatorBalanceamento() < -1)
            {
                rebalancearArvore(no);
            }

            if (no.getFatorBalanceamento() != 0 && no.getPai() != null) {
                if(pai.getChave() > no.getChave()){
                    atualizar_fb_insercao(pai, 1);
                }
                else{
                    atualizar_fb_insercao(pai, -1);
                }  
            }
        }
    }

    public void atualizar_fb_remocao(NoAVL no, int lado)
    {
        if (no != null) {
            no.atualizarFator(lado);
            NoAVL pai = no.getPai();

            if (no.getFatorBalanceamento() > 1 || no.getFatorBalanceamento() < -1)
            {
                rebalancearArvore(no);
            }
            if (no.getFatorBalanceamento() == 0 && pai != null) {
                if(pai.getChave() > no.getChave()){
                    atualizar_fb_remocao(pai, -1);
                }
                else{
                    atualizar_fb_remocao(pai, 1);
                }  
            }
        }  
    }

    public void rebalancearArvore(NoAVL no)
    {
        int FB = no.getFatorBalanceamento();
        if (FB < 0){
            if (no.getFilhoDireito().getFatorBalanceamento() <= 0){
                this.rotacaoEsquerdaSimples(no);
            } else {
                this.rotacaoEsquerdaDupla(no);
            }
        }
        if (FB > 0) {
            if (no.getFilhoEsquerdo().getFatorBalanceamento() >= 0){
                this.rotacaoDireitaSimples(no);
            } else {
                this.rotacaoDireitaDupla(no);
            }
        }
    }

    public void rotacaoEsquerdaSimples(NoAVL no)
    {
        NoAVL pai_desbalanceado = no.getPai();
        NoAVL sucessor = no.getFilhoDireito();
        
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

        int novoFB = (no.getFatorBalanceamento() + 1) - Math.min(sucessor.getFatorBalanceamento(), 0);
        no.setFatorBalanceamento(novoFB);

        novoFB = (sucessor.getFatorBalanceamento() + 1) + Math.max(no.getFatorBalanceamento(), 0);
        sucessor.setFatorBalanceamento(novoFB);
        
    }

    public void rotacaoEsquerdaDupla(NoAVL no)
    {
        this.rotacaoDireitaSimples(no.getFilhoDireito());
        
        this.rotacaoEsquerdaSimples(no);
    }

    public void rotacaoDireitaSimples(NoAVL no)
    {
        NoAVL pai_desbalanceado = no.getPai();
        NoAVL sucessor = no.getFilhoEsquerdo();
        
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

        int novoFB = (no.getFatorBalanceamento() - 1) - Math.max(sucessor.getFatorBalanceamento(), 0);
        no.setFatorBalanceamento(novoFB);

        novoFB = (sucessor.getFatorBalanceamento() - 1) + Math.min(no.getFatorBalanceamento(), 0);
        sucessor.setFatorBalanceamento(novoFB);
        
    }

    public void rotacaoDireitaDupla(NoAVL no)
    {
        this.rotacaoEsquerdaSimples(no.getFilhoEsquerdo());
        this.rotacaoDireitaSimples(no);
    }
    
    public void remover_avl(int k)
    {
        NoAVL remover = pesquisar(this.raiz, k);
        NoAVL removido = remover(remover);

        if(removido != null && removido.getPai() != null){
            if (removido.getPai().getChave() > removido.getChave()) {
                this.atualizar_fb_remocao(removido.getPai(), -1);
            }
            else {
                this.atualizar_fb_remocao(removido.getPai(), 1);
            }
        }
    }

    public NoAVL remover(NoAVL remover) {
        if (remover.éNoFolha()) {
            System.out.println("folha");
            NoAVL pai = remover.getPai();
            if (pai == null) {
                this.raiz = null;
                return null;
            }

            if (remover.éFilhoEsquerdo()) {
                pai.setFilhoEsquerdo(null);
            } else if (remover.éFilhoDireito()) {
                pai.setFilhoDireito(null);
            }

            return remover;
        }

        else if (!remover.temDoisFilhos()){
            System.out.println("um filho");
            NoAVL sucessor = remover.getFilhoDireito() != null ? remover.getFilhoDireito()
                    : remover.getFilhoEsquerdo();

            if (remover == this.raiz) {
                this.raiz = sucessor;
            }                
            NoAVL pai_remover = remover.getPai();     

            if(pai_remover != null){
                if (remover.éFilhoEsquerdo()) {
                    pai_remover.setFilhoEsquerdo(sucessor);
                } else if (remover.éFilhoDireito()) {
                    pai_remover.setFilhoDireito(sucessor);
                }
            }
            sucessor.setPai(pai_remover);

            return remover;
        }

        else {
            System.out.println("dois filhos");
            NoAVL sucessor = this.inOrder(remover.getFilhoDireito());
            int chave_sucessor = sucessor.getChave();
            System.out.println(chave_sucessor);

            NoAVL retorno = remover(sucessor);
            remover.setChave(chave_sucessor);          
            
            return retorno;
        }   
        
    }

    public NoAVL inOrder(NoAVL v) {
        if (v.getFilhoEsquerdo() != null) {
            return inOrder(v.getFilhoEsquerdo());
        }
        return v;
        // if (v.getFilhoDireito() != null){
        // return inOrder(v.getFilhoDireito());
        // }
    }

    public void setRaiz(NoAVL raiz) {
        this.raiz = raiz;
    }

    public NoAVL getRaiz(){
        return this.raiz;
    }

    public void mostrarArvore() {
        NoAVL raiz = this.raiz;

        LinkedList<NoAVL> nivelArvore = new LinkedList<NoAVL>();
        nivelArvore.add(raiz);
        LinkedList<NoAVL> temp = new LinkedList<NoAVL>();

        int contador = 0;
        int altura = altura(this.raiz);
        double numberOfElements = (Math.pow(2, (altura)) - 1);

        while (contador <= (altura+1)) {
            NoAVL removido = nivelArvore.removeFirst();
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

    public static void imprimirEspaco(double n, NoAVL x) {
        for (; n > 0; n--) {
            System.out.print("\t");
        }
        if (x == null) {
            System.out.print(" ");
        } else {
            System.out.print(x.getChave() + " [" + x.getFatorBalanceamento() + "]");
        }
    }

    public static int altura(NoAVL raiz) {
        if (raiz == null || raiz.éNoFolha()) {
            return 0;
        }

        return 1 + Math.max(altura(raiz.getFilhoEsquerdo()), altura(raiz.getFilhoDireito()));
    }

    public static int profundidade(NoAVL raiz) {
        if (raiz == null || raiz.getPai() == null) {
            return 0;
        }

        return 1 + Math.max(altura(raiz.getFilhoEsquerdo()), altura(raiz.getFilhoDireito()));
    }

}
