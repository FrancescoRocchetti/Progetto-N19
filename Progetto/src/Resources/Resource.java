package Resources;

//LA CLASSE E' ANCORA INCOMPLETA
public class Resource {
    private String typeSocket;
    private int modulesRAM;
    private int power;
    private int amountRAM;
    private int nPci;
    private int nPcie;
    private int nSATA;
    private int nBit;
    private boolean okCPU; //attributi ok valutano se ci sono i pezzi fondamentali
    private boolean okMOBO;
    private boolean okRAM;
    private boolean okStorage;
    private boolean okGPU; //controlla che GPU sia integrata, se non la è se c'è GPU dedicata

    /**
     * Bisogna inizializzare tutti gli attributi a 0/NULL/Valore standard.
     * Ciò perchè quando aggiungo o tolgo una componente (ad esempio la CPU)
     * non tolgo ne aggiungo alcuni attributi di una risorsa (ad esempio banchi di RAM).
     */

    public static class Builder {
        private int modulesRam;
        private int power;
        private String typeSocket;
        private int amountRAM;
        private int nPci;
        private int nPcie;
        private int nSATA;
        private int nBit;
        private boolean okCPU;
        private boolean okMOBO;
        private boolean okRAM;
        private boolean okStorage;
        private boolean okGPU;

        public Builder withSocket(String typeSocket) {
            this.typeSocket = typeSocket;
            return this;
        }

        public Builder withModules(int modulesRam) {
            this.modulesRam = modulesRam;
            return this;
        }

        public Builder withPower(int power) {
            this.power = power;
            return this;
        }

        public Builder withAmountRAM(int amountRAM) {
            this.amountRAM= amountRAM;
            return this;
        }

        public Builder withNPci(int nPci) {
            this.nPci= nPci;
            return this;
        }



        public Resource build() {
            Resource risorsa = new Resource();
            risorsa.modulesRAM = this.modulesRam;
            risorsa.power = this.power;
            risorsa.typeSocket = this.typeSocket;
            risorsa.amountRAM=this.amountRAM;
            return risorsa;
        }
    }

    private Resource() { //costruttore di default reso privato
    }
}