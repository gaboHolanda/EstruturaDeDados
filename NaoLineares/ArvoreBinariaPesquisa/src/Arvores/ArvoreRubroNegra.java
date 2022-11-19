package Arvores;
import Nos.No;

public class ArvoreRubroNegra {
    private ArvoreBinariaPesquisa arvore = new ArvoreBinariaPesquisa();

    // private boolean Regra1(No no)
    // {
    //     if (no.éNoFolha() && no.cor == No.NEGRO){
    //         return true;
    //     }
    //     return false;
    // }

    // private boolean Regra2()
    // {
    //     if (this.arvore.raiz.cor == No.NEGRO){
    //         return true;
    //     }
    //     return false;
    // }

    // private boolean Regra3(No no)
    // {
    //     if (no.cor == No.RUBRO){
    //         if(no.getFilhoDireito().cor == No.NEGRO && no.getFilhoEsquerdo().cor == No.NEGRO){
    //             return true;
    //         }
    //         return false;
    //     }
    //     return true;
    // }

    private void InsercaoCaso2(No novo){
        novo.getAvo().setCor(No.RUBRO);
        novo.getPai().setCor(No.NEGRO);
        novo.getTio().setCor(No.NEGRO);
        
        if(getCorNo(novo.getAvo().getPai()) == No.RUBRO){
            InsercaoCaso2(novo.getAvo());
        }
    }

    private void InsercaoCaso3(No novo){
        if(novo.éFilhoEsquerdo()){
            if (novo.getPai().éFilhoEsquerdo()){
                arvore.rotacaoDireitaSimples(novo.getAvo());
                novo.getPai().setCor(No.NEGRO);
                novo.setCor(No.RUBRO); 
                novo.getIrmao().setCor(No.RUBRO);
            }
            else{
                arvore.rotacaoEsquerdaDupla(novo.getAvo());
                novo.setCor(No.NEGRO); 
                novo.getFilhoDireito().setCor(No.RUBRO);
                novo.getFilhoEsquerdo().setCor(No.RUBRO);
            }
        }
        else{
            if (novo.getPai().éFilhoDireito()){
                arvore.rotacaoEsquerdaSimples(novo.getAvo());
                novo.getPai().setCor(No.NEGRO);
                novo.setCor(No.RUBRO); 
                novo.getIrmao().setCor(No.RUBRO);
            }
            else{
                arvore.rotacaoDireitaDupla(novo.getAvo());
                novo.setCor(No.NEGRO); 
                novo.getFilhoDireito().setCor(No.RUBRO);
                novo.getFilhoEsquerdo().setCor(No.RUBRO);
            }
        }
    }

    private String getCorNo(No no)
    {
        if(no == null){
            return No.NEGRO;
        }
        return no.cor;
    }

    public No inserir(int k) {
        No raiz_antigo = this.getRaiz();
        No novo = arvore.inserir(k);

        if(raiz_antigo != null){
            if(getCorNo(novo.getPai()) == No.NEGRO){
                return novo;
            }
    
            if(getCorNo(novo.getPai()) == No.RUBRO){
                if(getCorNo(novo.getAvo()) == No.NEGRO){
                    if(getCorNo(novo.getTio()) == No.RUBRO){
                        InsercaoCaso2(novo);
                    }
                    else{
                        InsercaoCaso3(novo);
                    }      
                }
            }
        }    

        this.setCorRaiz();
        
        return novo;
    }


    public No getRaiz()
    {
        return arvore.getRaiz();
    }
    
    public void setCorRaiz()
    {
        this.getRaiz().setCor(No.NEGRO);
    }

    public void mostrarArvore()
    {
        this.arvore.mostrarArvore();
    }
}
