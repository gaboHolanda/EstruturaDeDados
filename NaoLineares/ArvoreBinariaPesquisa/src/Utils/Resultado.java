package Utils;
import Nos.No;

public class Resultado {
    private final No removido;
    private final No sucessor;

    public Resultado(No removido, No sucessor) {
        this.removido = removido;
        this.sucessor = sucessor;
    }

    public No getRemovido() {
        return removido;
    }

    public No getSucessor() {
        return sucessor;
    }
}
