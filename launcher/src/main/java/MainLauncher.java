import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MainLauncher {
    int priority;
    private MainLauncher next;

    public MainLauncher(int priority) {
        this.priority = priority;
    }

    public void setNext(MainLauncher next) {
        this.next = next;
    }
    public void launchManager(String[] apps, int level){
        if(level>=priority){
            startService(apps[priority]);
        }
        if(next!=null){

            next.launchManager(apps, level);
        }
    }

    public abstract void startService(String app);

}
