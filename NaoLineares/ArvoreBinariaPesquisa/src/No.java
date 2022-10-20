public class No {
    No pai;
    No filhoEsquerdo;
    No filhoDireito;
    int fatorBalanceamento = 0;
    int chave;

    No(No pai, int chave)
    {
        this.pai = pai;
        this.chave = chave;
    }

    public No getFilhoEsquerdo()
    {
        return this.filhoEsquerdo;
    }
    public No getFilhoDireito()
    {
        return this.filhoDireito;
    }
    public No getPai()
    {
        return this.pai;
    }
    public int getChave()
    {
        return this.chave;
    }

    public int getFatorBalanceamento()
    {
        return this.fatorBalanceamento;
    }

    public void atualizarFator(int valor)
    {
        this.fatorBalanceamento += (1 * valor);
    }

    public void setFilhoEsquerdo(No no)
    {
        this.filhoEsquerdo = no;
    }
    public void setFilhoDireito(No no)
    {
        this.filhoDireito = no;
    }
    public void setPai(No no)
    {
        this.pai = no;
    }
    public void setFatorBalanceamento(int valor)
    {
        this.fatorBalanceamento = valor;
    }

    public boolean éFilhoEsquerdo()
    {
        if (this.getPai() == null){
            return false;
        }
        return this.pai.getFilhoEsquerdo() == this;
    }

    public boolean éFilhoDireito()
    {
        if (this.getPai() == null){
            return false;
        }
        return this.pai.getFilhoDireito() == this;
    }
    public boolean éNoFolha()
    {
        return (this.getFilhoDireito() == null && this.getFilhoEsquerdo() == null);
    }

    public boolean temDoisFilhos()
    {
        return (this.getFilhoDireito() != null && this.getFilhoEsquerdo() != null);
    }
}
