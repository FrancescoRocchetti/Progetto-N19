package Resources;

//LA CLASSE E' ANCORA INCOMPLETA
public class Resource {
    private String typeSocket;
    private String dimensionCase;
    private String dimensionMOBO;
    private int modulesRAM;
    private int power;
    private int amountRAM;
    private int nPci;
    private int nPcie;
    private int nSATA;
    private int nBit;
    private int nSlot350;
    private int nSlot250;
    private String typeRAM;
    private boolean okCPU; //attributi ok valutano se ci sono i pezzi fondamentali
    private boolean okMOBO;
    private boolean okRAM;
    private boolean okStorage;
    private boolean okGPU; //controlla che GPU sia integrata, se non la è se c'è GPU dedicata
    private boolean okCase;
    private boolean okCooler;
    private boolean okPSU;
    private boolean okCoolerI;
    private String[] supportedSocketC;

    /**
     * Bisogna inizializzare tutti gli attributi a 0/NULL/Valore standard.
     * Ciò perchè quando aggiungo o tolgo una componente (ad esempio la CPU)
     * non tolgo ne aggiungo alcuni attributi di una risorsa (ad esempio banchi di RAM).
     */

    public static class Builder {
        private int modulesRam;
        private int power;
        private String typeSocket;
        private String dimensionCase;
        private String dimensionMOBO;
        private int amountRAM;
        private int nPci;
        private int nPcie;
        private int nSATA;
        private int nBit;
        private int nSlot350;
        private int nSlot250;
        private String typeRAM;
        private boolean okCPU;
        private boolean okMOBO;
        private boolean okRAM;
        private boolean okStorage;
        private boolean okGPU;
        private boolean okCase;
        private boolean okCooler;
        private boolean okPSU;
        private boolean okCoolerI;
        private String[] supportedSocketC;

        public Builder withSpportedSocketC(String[] supportedSocketC){
            this.supportedSocketC = supportedSocketC;
            return this;
        }

        public Builder withTypeRAM(String typeRAM) {
            this.typeRAM = typeRAM;
            return this;
        }

        public Builder withDimensionCase(String dimensionCase) {
            this.dimensionCase = dimensionCase;
            return this;
        }

        public Builder withDimensionMOBO(String dimensionMOBO) {
            this.dimensionMOBO = dimensionMOBO;
            return this;
        }

        public Builder withNSlot350(int nSlot350) {
            this.nSlot350 = nSlot350;
            return this;
        }

        public Builder withNSlot250(int nSlot250) {
            this.nSlot250 = nSlot250;
            return this;
        }

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
            this.amountRAM = amountRAM;
            return this;
        }

        public Builder withNPci(int nPci) {
            this.nPci = nPci;
            return this;
        }

        public Builder withNPcie(int nPcie) {
            this.nPcie = nPcie;
            return this;
        }

        public Builder withnBit(int nBit) {
            this.nBit = nBit;
            return this;
        }

        public Builder withNSATA(int nSATA) {
            this.nSATA = nSATA;
            return this;
        }

        public Builder withokCPU(boolean okCPU) {
            this.okCPU = okCPU;
            return this;
        }

        public Builder withokMOBO(boolean okMOBO) {
            this.okMOBO = okMOBO;
            return this;
        }

        public Builder withokRAM(boolean okRAM) {
            this.okRAM = okRAM;
            return this;
        }

        public Builder withokStorage(boolean okStorage) {
            this.okStorage = okStorage;
            return this;
        }

        public Builder withokGPU(boolean okGPU) {
            this.okGPU = okGPU;
            return this;
        }

        public Builder withokCase(boolean okCase) {
            this.okCase = okCase;
            return this;
        }

        public Builder withokCooler(boolean okCooler) {
            this.okCooler = okCooler;
            return this;
        }

        public Builder withokCoolerI(boolean okCoolerI) {
            this.okCoolerI = okCoolerI;
            return this;
        }

        public Builder withokPSU(boolean okPSU) {
            this.okPSU = okPSU;
            return this;
        }

        public Resource build() {
            Resource risorsa = new Resource();
            risorsa.okCase = false;
            risorsa.okCooler = false;
            risorsa.okCPU = false;
            risorsa.okGPU = false;
            risorsa.okRAM = false;
            risorsa.okPSU = false;
            risorsa.modulesRAM = this.modulesRam;
            risorsa.power = this.power;
            risorsa.typeSocket = this.typeSocket;
            risorsa.amountRAM = this.amountRAM;
            risorsa.nPci = this.nPci;
            risorsa.nPcie = this.nPcie;
            risorsa.nSATA = this.nSATA;
            risorsa.nBit = this.nBit;
            risorsa.typeRAM = this.typeRAM;
            risorsa.dimensionCase = this.dimensionCase;
            risorsa.dimensionMOBO = this.dimensionMOBO;
            risorsa.nSlot350 = this.nSlot350;
            risorsa.nSlot250 = this.nSlot250;
            risorsa.okCPU = this.okCPU;
            risorsa.okMOBO = this.okMOBO;
            risorsa.okRAM = this.okRAM;
            risorsa.okStorage = this.okStorage;
            risorsa.okGPU = this.okGPU;
            risorsa.okCase = this.okCase;
            risorsa.okCooler = this.okCooler;
            risorsa.okCoolerI = this.okCoolerI;
            risorsa.okPSU = this.okPSU;
            risorsa.supportedSocketC = this.supportedSocketC;
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

    public int getnSlot350() {
        return nSlot350;
    }

    public int getnSlot250() {
        return nSlot250;
    }

    public String getDimensionCase() {
        return dimensionCase;
    }

    public String getDimensionMOBO() {
        return dimensionMOBO;
    }

    public String getTypeRAM() {
        return typeRAM;
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

    public boolean isOkCase() {
        return okCase;
    }

    public boolean isOkCooler() {
        return okCooler;
    }

    public boolean isOkCoolerI() {
        return okCoolerI;
    }

    public boolean isOkPSU() {
        return okPSU;
    }

    public String[] getSupportedSocketC() {
        return supportedSocketC;
    }

    @Override
    //toString aggiunto solo per test
    public String toString() {
        return "Resource{" +
                "okCPU=" + okCPU +
                ", okMOBO=" + okMOBO +
                ", okRAM=" + okRAM +
                ", okStorage=" + okStorage +
                ", okGPU=" + okGPU +
                ", okCase=" + okCase +
                ", okCooler=" + okCooler +
                ", okPSU=" + okPSU +
                ", npci=" + nPci +
                '}';
    }
}