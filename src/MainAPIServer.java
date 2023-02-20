import geo.services.GeoServices;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import tools.MainUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Properties;

import static java.lang.Thread.sleep;

public class MainAPIServer {

    private static boolean isHttps = false;
    private static File keyStoreFile;
    private static String keyStoreFileName;
    private static String keyStorePass = "rj45mlk64";

    public static void main(String[] args) throws Exception {
        // Читаем настройки из файла
        Properties properties = new Properties();
        String curDir = new File("").getAbsolutePath();
        FileInputStream fis = new FileInputStream(curDir + "/server.properties");
        properties.load(fis);
        int serverPort = Integer.parseInt(properties.getProperty("server.port"));


        int httpsServerPort = 0;
        if (properties.containsKey("https")) {
            isHttps = Boolean.parseBoolean(properties.getProperty("https", "false"));
            keyStoreFileName = properties.getProperty("https.keyStoreFileName", "");
            keyStorePass = properties.getProperty("https.keyStorePass", "");
            httpsServerPort = Integer.parseInt(properties.getProperty("https.port"));
        }

        MainUtils.getInstance().printLog("Properties loaded");

        Locale.setDefault(new Locale("en", "US"));



        if (properties.containsKey("service.geo")) {
            if (Boolean.parseBoolean(properties.getProperty("service.geo", "false"))) {
                GeoServices geoServices = new GeoServices();
                geoServices.start();
            }
        }



        MainUtils.getInstance().printLog("Starting server at port " + serverPort);
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("server");
        Server server = new Server(threadPool);
        server.setHandler(new MainAPIHandler());

        Connector httpConnector = createHttpConnector(server, serverPort, isHttps, httpsServerPort);
        if (isHttps) {
            loadKeyStores(curDir + "/" + keyStoreFileName);
            Connector httpsConnector = createHttpsConnector(server, httpsServerPort, keyStoreFile, keyStorePass);
            server.setConnectors(new Connector[]{httpConnector, httpsConnector});
        } else {
            server.addConnector(httpConnector);
        }

        while (!server.isStarted()) {
            try {
                server.start();
                MainUtils.getInstance().printLog("Started  server at port " + serverPort + " success");
                if (isHttps) {
                    MainUtils.getInstance().printLog("Started  https server at port " + httpsServerPort + " success");
                }
                server.join();
            } catch (Exception e) {
                MainUtils.getInstance().printLog(e.getLocalizedMessage());
                sleep(5 * 1000);
            }
        }
    }

    private static Connector createHttpConnector(Server server, int httpPort, boolean isHttps, int httpsPort) {
        HttpConfiguration httpConf = new HttpConfiguration();
        httpConf.setSendServerVersion(false);
        if (isHttps) httpConf.setSecurePort(httpsPort);
        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(httpConf));
        connector.setPort(httpPort);
        return connector;
    }

    private static Connector createHttpsConnector(Server server, int httpsPort, File keyStoreFile, String keyStorePass) {
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(keyStoreFile.getAbsolutePath());
        sslContextFactory.setKeyStorePassword(keyStorePass);
        sslContextFactory.setNeedClientAuth(false);

        // Setup HTTPS Configuration
        HttpConfiguration httpsConf = new HttpConfiguration();
        httpsConf.setSendServerVersion(false);
        httpsConf.setSecureScheme("https");
        httpsConf.setSecurePort(httpsPort);
        httpsConf.setOutputBufferSize(32768);
        httpsConf.setRequestHeaderSize(8192);
        httpsConf.setResponseHeaderSize(8192);
        SecureRequestCustomizer secureRequestCustomizer = new SecureRequestCustomizer();
        secureRequestCustomizer.setSniHostCheck(false);
        httpsConf.addCustomizer(secureRequestCustomizer); // adds ssl info to request object

        // Establish the HTTPS ServerConnector
        ServerConnector httpsConnector = new ServerConnector(server, new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()), new HttpConnectionFactory(httpsConf));
        httpsConnector.setPort(httpsPort);

        return httpsConnector;
    }

    private static void loadKeyStores(String keyStorePath) {
        keyStoreFile = new File(keyStorePath);
        if (!keyStoreFile.exists()) {
            throw new RuntimeException("Key store file does not exist on path '" + keyStoreFile.getAbsolutePath() + "'");
        }
    }
}
