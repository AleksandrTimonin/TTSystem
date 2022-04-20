import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GateLauncher extends MainLauncher{
    public GateLauncher(int priority) {
        super(priority);
    }

    @Override
    public void startService(String app) {

        log.warn("in : " + app);
    }

    @Override
    public String toString() {
        return "GateLauncher{" +
                "priority=" + priority +
                '}';
    }
}
