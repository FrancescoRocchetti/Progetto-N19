package Resources;

import java.util.ArrayList;

public class SommatoreRes {

    //somma le risorse di una build in una risorsa unica

    private static Resource loop(ArrayList<Resource> ar) {
        Resource temp = new Resource.Builder().build();

        for (Resource r : ar) {
            temp = new Resource.Builder()
                    .withokPSU(temp.isOkPSU() || r.isOkPSU())
                    .withokMOBO(temp.isOkMOBO() || r.isOkMOBO())
                    .withokCPU(temp.isOkCPU() || r.isOkCPU())
                    .withokCase(temp.isOkCase() || r.isOkCase())
                    .withokRAM(temp.isOkRAM() || r.isOkRAM())
                    .withokGPU(temp.isOkGPU() || r.isOkGPU())
                    .withokCooler(temp.isOkCooler() || r.isOkCooler())
                    .withokStorage(temp.isOkStorage() || r.isOkStorage())
                    .withokCoolerI(temp.isOkCoolerI() || r.isOkCoolerI())


                    .withPower(temp.getPower() + r.getPower())
                    .withNSATA(temp.getnSATA() + r.getnSATA())
                    .withNSlot350(temp.getnSlot350() + r.getnSlot350())
                    .withNSlot250(temp.getnSlot250() + r.getnSlot250())
                    .withNPci(temp.getnPci() + r.getnPci())
                    .withNPcie(temp.getnPcie() + r.getnPcie())
                    .withModules(temp.getModulesRAM() + r.getModulesRAM())
                    .build();
        }

        return temp;
    }

    public static Resource sum(ArrayList<Resource> ar) {
        return loop(ar);
    }
}
