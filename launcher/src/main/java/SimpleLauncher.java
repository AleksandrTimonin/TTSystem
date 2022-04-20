import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleLauncher extends MainLauncher{
    public SimpleLauncher(int priority) {
        super(priority);
    }

    @Override
    public void startService(String app) {
        log.warn("in : " + app);
    }

    @Override
    public String toString() {
        return "SimpleLauncher{" +
                "priority=" + priority +
                '}';
    }
}
