package Nos;
public class NoAVL {
    NoAVL pai;
    NoAVL filhoEsquerdo;
    NoAVL filhoDireito;
    int fatorBalanceamento = 0;
    int chave;

    NoAVL(NoAVL pai, int chave)
    {
        this.pai = pai;
        this.chave = chave;
    }

    public NoAVL getFilhoEsquerdo()
    {
        return this.filhoEsquerdo;
    }
    public NoAVL getFilhoDireito()
    {
        return this.filhoDireito;
    }
    public NoAVL getPai()
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

    public void setFilhoEsquerdo(NoAVL no)
    {
        this.filhoEsquerdo = no;
    }
    public void setFilhoDireito(NoAVL no)
    {
        this.filhoDireito = no;
    }
    public void setPai(NoAVL no)
    {
        this.pai = no;
    }
    public void setFatorBalanceamento(int valor)
    {
        this.fatorBalanceamento = valor;
    }

    public void setChave(int valor)
    {
        this.chave = valor;
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
