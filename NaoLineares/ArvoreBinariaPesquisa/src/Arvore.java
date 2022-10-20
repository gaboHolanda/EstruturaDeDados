import java.util.LinkedList;

public class Arvore {
    No raiz;

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
            atualizar_fb_insercao(pai, 1);
        } else if (pai.getChave() < k) {
            pai.setFilhoDireito(novo);
            atualizar_fb_insercao(pai, -1);
        }

        novo.setPai(pai);
        return novo;
    }

    public void atualizar_fb_insercao(No no, int lado)
    {
        if (no != null) {
            no.atualizarFator(lado);
            System.out.println(no.getChave() + " " + no.getFatorBalanceamento());
            if (no.getFatorBalanceamento() > 1 || no.getFatorBalanceamento() < -1)
            {
                rebalancearArvore(no);
            }

            if (no.getFatorBalanceamento() != 0 && no.getPai() != null) {
                if(no.éFilhoEsquerdo()){
                    System.out.println("Filho esquerdo "+no.getChave() + " " + no.getFatorBalanceamento());
                    atualizar_fb_insercao(no.getPai(), 1);
                }
                else{
                    System.out.println("Filho direito "+no.getChave() + " " + no.getFatorBalanceamento());
                    System.out.println("Pai "+no.getPai().getChave() + " " + no.getPai().getFatorBalanceamento());
                    atualizar_fb_insercao(no.getPai(), -1);
                }  
            }
        }
    }

    public void atualizar_fb_remocao(No no, int lado)
    {
        if (no != null) {
            no.atualizarFator(lado);

            if (no.getFatorBalanceamento() > 1 || no.getFatorBalanceamento() < -1)
            {
                rebalancearArvore(no);
            }
            else if (no.getFatorBalanceamento() == 0 && no.getPai() != null) {
                if(no.éFilhoEsquerdo()){
                    atualizar_fb_remocao(no.getPai(), -1);
                }
                else{
                    atualizar_fb_remocao(no.getPai(), 1);
                }  
            }
        }  
    }

    public void rebalancearArvore(No no)
    {
        int FB = no.getFatorBalanceamento();
        if (FB < 0){
            if (no.getFilhoDireito().getFatorBalanceamento() <= 0){
                System.out.println("Simples Esquerda");
                System.out.println(FB);
                this.rotacaoEsquerdaSimples(no);
            } else {
                System.out.println("Dupla Esquerda");
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

        int novoFB = (no.getFatorBalanceamento() + 1) - Math.min(sucessor.getFatorBalanceamento(), 0);
        no.setFatorBalanceamento(novoFB);

        novoFB = (sucessor.getFatorBalanceamento() + 1) + Math.max(no.getFatorBalanceamento(), 0);
        sucessor.setFatorBalanceamento(novoFB);
        
    }

    public void rotacaoEsquerdaDupla(No no)
    {
        System.out.println("No desbalanceado: " + no.getChave() + " - " + no.getFatorBalanceamento());
        System.out.println("No filho direito: " + no.getFilhoDireito().getChave() + " - " + no.getFilhoDireito().getFatorBalanceamento());
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

        int novoFB = (no.getFatorBalanceamento() - 1) - Math.max(sucessor.getFatorBalanceamento(), 0);
        no.setFatorBalanceamento(novoFB);

        novoFB = (sucessor.getFatorBalanceamento() - 1) + Math.min(no.getFatorBalanceamento(), 0);
        sucessor.setFatorBalanceamento(novoFB);
        
    }

    public void rotacaoDireitaDupla(No no)
    {
        this.rotacaoEsquerdaSimples(no.getFilhoEsquerdo());
        this.rotacaoDireitaSimples(no);
    }
    

    public No remover(int k) {
        No remover = pesquisar(this.raiz, k);
        boolean eh_filho_esquerdo = remover.éFilhoEsquerdo();
        if (remover.getChave() == k) {
            if (remover.éNoFolha()) {
                No pai = remover.getPai();
                if (pai == null) {
                    this.raiz = null;
                    return null;
                }

                this.removerFilho(pai, remover);
                remover.setPai(null);

                if (eh_filho_esquerdo) {
                    this.atualizar_fb_remocao(pai, -1);
                }
                else {
                    this.atualizar_fb_remocao(pai, 1);
                }

                return remover;
            }

            if (remover.temDoisFilhos()) {
                No sucessor = this.inOrder(remover.getFilhoDireito());
                boolean sucessor_filho_esquerdo = sucessor.éFilhoEsquerdo();
                No pai_remover = remover.getPai();
                No pai_sucessor = sucessor.getPai();

                No filho_direito_sucessor = sucessor.getFilhoDireito();
                if (remover == this.raiz) {
                    this.raiz = sucessor;
                }

                
                
                // Removendo sucessor como filho do seu antigo pai
                if (sucessor != remover.getFilhoDireito()){
                    sucessor.setFilhoDireito(remover.getFilhoDireito());
                    remover.getFilhoDireito().setPai(sucessor);
                    if (filho_direito_sucessor != null){
                        pai_sucessor.setFilhoEsquerdo(filho_direito_sucessor);
                    }
                }
                this.removerFilho(sucessor.getPai(), sucessor);               

                // Atribuindo sucessor como pai do filho esquerdo do removido
                sucessor.setFilhoEsquerdo(remover.getFilhoEsquerdo());
                remover.getFilhoEsquerdo().setPai(sucessor);
                 
                
                if (pai_sucessor == remover) {
                    pai_sucessor = pai_remover;
                    sucessor_filho_esquerdo = eh_filho_esquerdo;
                }
                sucessor.setPai(pai_remover);

                if(pai_remover != null){
                    if (remover.éFilhoEsquerdo()) {
                        pai_remover.setFilhoEsquerdo(sucessor);
                    } else if (remover.éFilhoDireito()) {
                        pai_remover.setFilhoDireito(sucessor);
                    }
                }
                
                remover.setPai(null);
                remover.setFilhoEsquerdo(null);
                remover.setFilhoDireito(null);
                
                if(pai_sucessor != null){
                    if (sucessor_filho_esquerdo) {
                        this.atualizar_fb_remocao(pai_sucessor, -1);
                    }
                    else {
                        this.atualizar_fb_remocao(pai_sucessor, 1);
                    }
                }
                

                return remover;
            }

            else {
                No sucessor = remover.getFilhoDireito() != null ? remover.getFilhoDireito()
                        : remover.getFilhoEsquerdo();

                if (remover == this.raiz) {
                    this.raiz = sucessor;
                }
                this.removerFilho(remover, sucessor);
                
                No pai_remover = remover.getPai();
                sucessor.setPai(pai_remover);

                if(pai_remover != null){
                    if (remover.éFilhoEsquerdo()) {
                        pai_remover.setFilhoEsquerdo(sucessor);
                    } else if (remover.éFilhoDireito()) {
                        pai_remover.setFilhoDireito(sucessor);
                    }
                }
                remover.setPai(null);

                if (eh_filho_esquerdo) {
                    this.atualizar_fb_remocao(pai_remover, -1);
                }
                else {
                    this.atualizar_fb_remocao(pai_remover, 1);
                }

                return remover;
            }
        } else {
            return null;
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

    private void removerFilho(No pai, No filho) {
        if (filho.éFilhoEsquerdo()) {
            pai.setFilhoEsquerdo(null);
        } else if (filho.éFilhoDireito()) {
            pai.setFilhoDireito(null);
        }
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
            System.out.print(x.getChave() + " " + x.getFatorBalanceamento());
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
