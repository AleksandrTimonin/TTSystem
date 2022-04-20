import com.sanjati.api.MyApp;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthLauncher extends MainLauncher{
    public AuthLauncher(int priority) {
        super(priority);
    }

    @Override
    public void startService(String app) {
        log.warn("in : " + app);
    }

    @Override
    public String toString() {
        return "AuthLauncher{" +
                "priority=" + priority +
                '}';
    }
}
