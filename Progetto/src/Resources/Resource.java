package Resources;

//LA CLASSE E' ANCORA INCOMPLETA
public class Resource {
    private String typeSocket;
    private int RAMmodules;
    private int power;

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

    private Resource() {
    }
}