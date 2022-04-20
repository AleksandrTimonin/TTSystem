import com.sanjati.StatisticsApp;
import com.sanjati.api.MyApp;
import com.sanjati.auth.WebAuthServiceApplication;
import com.sanjati.core.CoreApp;
import com.sanjati.front.FrontApp;
import com.sanjati.gateway.GatewayApp;


public class launcher {
    public static void main(String[] args) {
        // во время выполнения задания у меня не получилось таким способом запустить все мои микросервисы
        //  времени менять идею уже нет.. так что оставлю так, а запустить эту штуку попробую позже
        // подскажите как правильно запускать приложения из дугих классов
        // тут реализован паттерн "цепочка отвественности"
        String[] apps = new String[]{"new GatewayApp()","new WebAuthServiceApplication()","new CoreApp()","new FrontApp()","new StatisticsApp()"};
        GateLauncher gateLauncher = new GateLauncher(Priority.G);
        AuthLauncher authLauncher = new AuthLauncher(Priority.GA);
        CoreLauncher coreLauncher = new CoreLauncher(Priority.GAC);
        SimpleLauncher simpleLauncher = new SimpleLauncher(Priority.GACF);
        StatisticsLauncher statisticsLauncher = new StatisticsLauncher(Priority.GACFS);

        gateLauncher.setNext(authLauncher);
        authLauncher.setNext(coreLauncher);
        coreLauncher.setNext(simpleLauncher);
        simpleLauncher.setNext(statisticsLauncher);

        gateLauncher.launchManager(apps, Priority.GACF);

    }
}
