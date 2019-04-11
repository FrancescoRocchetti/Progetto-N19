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

        public Builder withNPcie(int nPcie) {
            this.nPcie= nPcie;
            return this;
        }

        public Builder withnBit(int nBit) {
            this.nBit= nBit;
            return this;
        }

        public Builder withNSATA(int nSATA) {
            this.nSATA= nSATA;
            return this;
        }

        public Builder withokCPU(boolean okCPU) {
            this.okCPU= okCPU;
            return this;
        }

        public Builder withokMOBO(boolean okMOBO) {
            this.okMOBO= okMOBO;
            return this;
        }

        public Builder withokRAM(boolean okRAM) {
            this.okRAM= okRAM;
            return this;
        }

        public Builder withokStorage(boolean okStorage) {
            this.okStorage= okStorage;
            return this;
        }

        public Builder withokGPU(boolean okGPU) {
            this.okGPU=okGPU;
            return this;
        }

        public Resource build() {
            Resource risorsa = new Resource();
            risorsa.modulesRAM = this.modulesRam;
            risorsa.power = this.power;
            risorsa.typeSocket = this.typeSocket;
            risorsa.amountRAM=this.amountRAM;
            risorsa.nPci = this.nPci;
            risorsa.nPcie = this.nPcie;
            risorsa.nSATA = this.nSATA;
            risorsa.nBit = this.nBit;
            risorsa.okCPU = this.okCPU;
            risorsa.okMOBO = this.okMOBO;
            risorsa.okRAM = this.okRAM;
            risorsa.okStorage = this.okStorage;
            risorsa.okGPU = this.okGPU;
            return risorsa;
        }
    }

    private Resource() { //costruttore di default reso privato
    }

    public String getTypeSocket() {
        return typeSocket;
    }

    public int getModulesRAM() {
        return modulesRAM;
    }

    public int getPower() {
        return power;
    }

    public int getAmountRAM() {
        return amountRAM;
    }

    public int getnPci() {
        return nPci;
    }

    public int getnPcie() {
        return nPcie;
    }

    public int getnSATA() {
        return nSATA;
    }

    public int getnBit() {
        return nBit;
    }

    public boolean isOkCPU() {
        return okCPU;
    }

    public boolean isOkMOBO() {
        return okMOBO;
    }

    public boolean isOkRAM() {
        return okRAM;
    }

    public boolean isOkStorage() {
        return okStorage;
    }

    public boolean isOkGPU() {
        return okGPU;
    }
}