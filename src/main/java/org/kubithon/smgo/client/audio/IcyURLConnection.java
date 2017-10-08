package org.kubithon.smgo.client.audio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcyURLConnection extends HttpURLConnection
{
    protected Socket                        socket;
    protected OutputStream                  outputStream;
    protected InputStream                   inputStream;
    protected HashMap<String, List<String>> requestProps;
    protected HashMap<String, List<String>> headers;
    protected String                        responseLine;

    /**
     * Creates new instance for the given URL.
     */
    public IcyURLConnection(URL url) {
        super(url);
    }

    /**
     * Opens a communications link to the resource referenced by this URL, if
     * such a connection has not already been established.
     */
    @Override
    public synchronized void connect() throws IOException {
        // according to specification:
        if (this.connected)
            return;

        this.socket = this.createSocket();

        this.socket.connect(new InetSocketAddress(this.url.getHost(), this.url.getPort() != -1 ? this.url.getPort() : this.url.getDefaultPort()),
                this.getConnectTimeout());

        Map<String, List<String>> requestProps = this.getRequestProperties();

        this.connected = true;

        this.headers = new HashMap<String, List<String>>();

        this.outputStream = this.socket.getOutputStream();
        this.inputStream = this.socket.getInputStream();

        this.writeLine("GET " + ("".equals(this.url.getPath()) ? "/" : this.url.getPath()) + " HTTP/1.1");
        this.writeLine("Host: " + this.url.getHost());

        if (requestProps != null)
            for (Map.Entry<String, List<String>> entry : requestProps.entrySet())
                for (String val : entry.getValue())
                    this.writeLine(entry.getKey() + ": " + val);

        this.writeLine("");

        this.responseLine = this.readResponseLine();

        for (String line = this.readLine(); line != null && line.length() != 0;) {
            this.parseHeaderLine(line);
            line = this.readLine();
        }
    }

    @Override
    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override
    public String getHeaderField(String name) {
        HashMap<String, List<String>> lmap = this.headers;

        if (lmap != null) {
            List<String> list = lmap.get(name);

            if (list != null && !list.isEmpty())
                return list.get(0);
        }

        return null;
    }

    @Override
    public String getHeaderField(int n) {
        return n == 0 ? this.responseLine : null;
    }

    @Override
    public Map<String, List<String>> getHeaderFields() {
        return this.headers;
    }

    @Override
    public synchronized void setRequestProperty(String key, String value) {
        if (this.requestProps == null)
            this.requestProps = new HashMap<String, List<String>>();

        List<String> list = new ArrayList<String>();
        list.add(value);
        this.requestProps.put(key, list);
    }

    @Override
    public synchronized void addRequestProperty(String key, String value) {
        if (this.requestProps == null)
            this.requestProps = new HashMap<String, List<String>>();

        List<String> list = this.requestProps.get(key);
        if (list == null)
            list = new ArrayList<String>();

        list.add(value);
        this.requestProps.put(key, list);
    }

    @Override
    public Map<String, List<String>> getRequestProperties() {
        return this.requestProps;
    }

    @Override
    public synchronized void disconnect() {
        if (!this.connected)
            return;

        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException e) {}
            this.socket = null;
        }

        this.inputStream = null;
        this.outputStream = null;
        this.headers = null;
        this.responseLine = null;
    }

    @Override
    public boolean usingProxy() {
        return false;
    }

    /**
     * Creates a new unconnected Socket instance. Subclasses may use this method
     * to override the default socket implementation.
     */
    protected Socket createSocket() {
        return new Socket();
    }

    /**
     * Reads one response header line and adds it to the headers map.
     */
    protected void parseHeaderLine(String line) throws IOException {
        int len = 2;
        int n = line.indexOf(": ");

        if (n == -1) {
            len = 1;
            n = line.indexOf(':');
            if (n == -1)
                return;
        }

        String key = line.substring(0, n);
        String val = line.substring(n + len);

        List<String> list = this.headers.get(key);

        if (list != null)
            list.add(val);
        else {
            list = new ArrayList<String>();
            list.add(val);
            this.headers.put(key, list);
        }
    }

    /**
     * Reads the first response line.
     */
    protected String readResponseLine() throws IOException {
        String line = this.readLine();

        if (line != null) {
            int n = line.indexOf(' ');

            if (n != -1)
                line = "HTTP/1.0" + line.substring(n);
        }

        return line;
    }

    /**
     * Reads one response line.
     *
     * @return the line without any new-line character.
     */
    protected String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();

        int c;
        while ((c = this.inputStream.read()) != -1) {
            if (c == '\r')
                continue;
            if (c == '\n')
                break;
            sb.append((char) c);
        }

        return sb.toString();
    }

    /**
     * Writes one request line.
     */
    protected void writeLine(String line) throws IOException {
        line += '\r';
        line += '\n';
        this.outputStream.write(line.getBytes("UTF-8"));
    }

}
