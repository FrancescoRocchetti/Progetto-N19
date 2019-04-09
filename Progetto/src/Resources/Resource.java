package Resources;

public class Resource {
    private String typeSocket;
    private int RAMmodules;
    private int power;

    /**
     * Bisogna inizializzare tutti gli attributi a 0/NULL/Valore standard.
     * Ciò perchè quando aggiungo o tolgo una componente (ad esempio la CPU)
     * non tolgo ne aggiungo alcuni attributi di una risorsa (ad esempio banchi di RAM).
     */

    public static class Builder {
        private int RAMmodules;
        private int power;
        private String typeSocket;

        public Builder withSocket(String typeSocket) {
            this.typeSocket = typeSocket;
            return this;
        }

        public Builder withBanchi(int RAMmodules) {
            this.RAMmodules = RAMmodules;
            return this;
        }

        public Builder withPotenza(int power) {
            this.power = power;
            return this;
        }

        public Resource build() {
            Resource risorsa = new Resource();
            risorsa.RAMmodules = this.RAMmodules;
            risorsa.power = this.power;
            risorsa.typeSocket = this.typeSocket;
            return risorsa;
        }
    }

    private Resource() { //costruttore di default reso privato
    }
}