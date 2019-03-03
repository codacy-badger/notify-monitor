package pl.tscript3r.notify.monitor.parsers;

import org.springframework.stereotype.Component;
import pl.tscript3r.notify.monitor.exceptions.IncompatibleHostnameException;

@Component
public class ParserFactory {

    public Boolean isCompatible(String hostname) {
        return hostname.equals("olx.pl");
    }

    public Parser getParser(String hostname) {
        if (!hostname.equals("olx.pl"))
            throw new IncompatibleHostnameException(hostname);
        return new OLXParser();
    }

}
