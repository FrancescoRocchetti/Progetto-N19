package Resources;

import java.util.ArrayList;

public class SommatoreRes {

    private static Resource loop(ArrayList<Resource> ar){
        Resource temp = new Resource.Builder().build();

        for(Resource r : ar){
            temp = new Resource.Builder()
                    .withokPSU(temp.isOkPSU() || r.isOkPSU())
                    .withokMOBO(temp.isOkMOBO() || r.isOkMOBO())
                    .withokCPU(temp.isOkCPU() || r.isOkMOBO())
                    .withokCase(temp.isOkCase() || r.isOkCase())

                    .withNSATA(temp.getnSATA() + r.getnSATA())
                    .withNSlot(temp.getnSlot325() + r.getnSlot325())
                    .withNPci(temp.getnPci() + r.getnPci())
                    .withNPcie(temp.getnPcie() + r.getnPcie())
                    .withModules(temp.getModulesRAM() + r.getModulesRAM())
                    .build();
        }

        return temp;
    }

    public static Resource sum(ArrayList<Resource> ar){
        return loop(ar);
    }
}
