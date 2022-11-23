package Nos;

public class No {
    public No pai;
    public No filhoEsquerdo;
    public No filhoDireito;
    public static final String RUBRO = "RUBRO";
    public static final String NEGRO = "NEGRO";
    public String cor = null;
    public Integer chave = null;
    public boolean duplo_negro = false;

    public No(No pai, Integer chave)
    {
        this.pai = pai;
        this.chave = chave;
        this.cor = RUBRO;
    }


    // GETTERS
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
    public No getIrmao()
    {
        if(this.éFilhoEsquerdo()){
            return this.getPai().getFilhoDireito();
        }
        else{
            return this.getPai().getFilhoEsquerdo();
        }
    }
    public No getAvo()
    {
        return this.pai.getPai();
    }
    public No getTio()
    {
        return this.pai.getIrmao();
    }
    public int getChave()
    {
        return this.chave;
    }
    public String getCor()
    {
        return this.cor;
    }
    public boolean getDuploNegro()
    {
        return this.duplo_negro;
    }

    // SETTERS
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

    public void setChave(int valor)
    {
        this.chave = valor;
    }
    public void setCor(String valor)
    {
        this.cor = valor;
    }
    public void setDuploNegro(Boolean valor)
    {
        this.duplo_negro = valor;
    }

    // METHODS
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
