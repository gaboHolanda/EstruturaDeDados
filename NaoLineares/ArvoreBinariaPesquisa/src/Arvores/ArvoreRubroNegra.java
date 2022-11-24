package Arvores;
import Nos.No;
import Utils.Resultado;

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
        if(no == null || no.chave == null){
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
                    else if(getCorNo(novo.getTio()) == No.NEGRO){
                        InsercaoCaso3(novo);
                    }      
                }
            }
        }    

        this.setCorRaiz();
        
        return novo;
    }

    public void Situacao3_remover(No sucessor)
    {
        if(getCorNo(sucessor.getIrmao()) == No.RUBRO && getCorNo(sucessor.getPai()) == No.NEGRO){
            sucessor.setDuploNegro(true);
            sucessor.getIrmao().setCor(No.NEGRO);
            sucessor.getPai().setCor(No.RUBRO);
            if(sucessor.éFilhoEsquerdo()){
                arvore.rotacaoEsquerdaSimples(sucessor.getPai());
            }
            else{
                arvore.rotacaoDireitaSimples(sucessor.getPai());
            }
            Situacao3_remover(sucessor);
            
        }
        if(getCorNo(sucessor.getIrmao()) == No.NEGRO){
            if (getCorNo(sucessor.getIrmao().getFilhoDireito()) == No.NEGRO && getCorNo(sucessor.getIrmao().getFilhoEsquerdo()) == No.NEGRO){
                if(getCorNo(sucessor.getPai()) == No.NEGRO){
                    sucessor.getIrmao().setCor(No.RUBRO);
                    sucessor.setDuploNegro(false);
                    sucessor.getPai().setDuploNegro(true);
                    Situacao3_remover(sucessor.getPai());
                }
                else{
                    sucessor.getIrmao().setCor(No.RUBRO);
                    sucessor.getPai().setCor(No.NEGRO);
                    sucessor.setDuploNegro(false);
                }
            }

            if (getCorNo(sucessor.getIrmao().getFilhoDireito()) == No.NEGRO && getCorNo(sucessor.getIrmao().getFilhoEsquerdo()) == No.RUBRO){
                sucessor.getIrmao().getFilhoEsquerdo().setCor(No.NEGRO);
                sucessor.getIrmao().setCor(No.RUBRO);
                arvore.rotacaoDireitaSimples(sucessor.getIrmao());   
                Situacao3_remover(sucessor); 
            }
            if (getCorNo(sucessor.getIrmao().getFilhoDireito()) == No.RUBRO){
                sucessor.getIrmao().setCor(getCorNo(sucessor.getPai()));
                sucessor.getPai().setCor(No.NEGRO);
                sucessor.getIrmao().getFilhoDireito().setCor(No.NEGRO);

                arvore.rotacaoEsquerdaSimples(sucessor.getIrmao());
            }
            sucessor.setDuploNegro(false);
            
        }
    }

    public No remover(int k) {
        No raiz_antigo = this.getRaiz();
        No no = arvore.pesquisar(raiz_antigo, k);
        if (no == null){
            return null;
        }
        Resultado resultado = arvore.remover(no);
        No removido = resultado.getRemovido();
        No sucessor = resultado.getSucessor();
        System.out.println(removido.chave);
        System.out.println(getCorNo(removido));

        // SITUAÇÃO 1
        if (getCorNo(removido) == No.RUBRO && (getCorNo(sucessor) == No.RUBRO || sucessor == null)){
            return removido;
        }

        // SITUAÇÃO 2
        if (getCorNo(removido) == No.NEGRO && getCorNo(sucessor) == No.RUBRO){
            sucessor.setCor(No.NEGRO);
            return removido;
        }

        // SITUAÇÃO 3
        if (getCorNo(removido) == No.NEGRO && getCorNo(sucessor) == No.NEGRO){
            this.Situacao3_remover(sucessor);
            return removido;
        }

        // SITUAÇÃO 4
        if (getCorNo(removido) == No.RUBRO && getCorNo(sucessor) == No.NEGRO){
            sucessor.setCor(No.RUBRO);
            No filho_direito = sucessor.getFilhoDireito();

            if (filho_direito == null){
                filho_direito = new No(sucessor, null);
                filho_direito.setCor(No.NEGRO);
                sucessor.setFilhoDireito(filho_direito);
            }
            filho_direito.setDuploNegro(true);
            
            this.Situacao3_remover(filho_direito);
            return removido;
        }


        this.setCorRaiz();
        
        return removido;
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
