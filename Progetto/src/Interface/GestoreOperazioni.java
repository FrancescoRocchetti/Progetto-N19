package Interface;

public class GestoreOperazioni {
    private boolean loggedin;
    private boolean modified;
    private Piattaforma p;

    public GestoreOperazioni(Piattaforma p){
        loggedin = false;
        modified = false;
        this.p = p;
        p.setEnabled(false);
        p.setDefaultCloseOperation(0);
    }


}
