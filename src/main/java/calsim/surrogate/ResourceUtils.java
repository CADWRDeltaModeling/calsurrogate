package calsim.surrogate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceUtils {

    /**
     * Extracts a resource folder (e.g. "/models/my_model") to a temporary folder.
     * If the resource is directly on the file system (in a dev environment),
     * simply returns that File.
     *
     * @param resourceFolderPath the path to the resource folder (should start with a "/")
     * @return a File representing the folder on disk.
     * @throws IOException if extraction fails.
     */
    public static File extractResourceFolder(String resourceFolderPath) throws IOException {
        // Get the URL of the resource folder.
    	URL url = ResourceUtils.class.getResource(resourceFolderPath);
        //URL url = ResourceUtils.class.getResource(resourceFolderPath);
        if (url == null) {
            throw new IOException("Resource not found: " + resourceFolderPath);
        }
        // If running from file system (e.g., in Eclipse), simply return the file.
        if (url.getProtocol().equals("file")) {
            return new File(url.getPath());
        }
        // If running from a jar, extract the folder contents.
        if (url.getProtocol().equals("jar")) {
            // Get the jar file path (removing "file:" and "!/..." parts)
            String jarPath = url.getPath().substring(5, url.getPath().indexOf("!"));
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries();
            // Create a temporary directory to copy resources into.
            File tempDir = Files.createTempDirectory("tempModel").toFile();
            tempDir.deleteOnExit();
            // The resource folder in the jar will not have the leading slash.
            String folderInJar = resourceFolderPath.startsWith("/") ? resourceFolderPath.substring(1) : resourceFolderPath;
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(folderInJar + "/")) {
                    File f = new File(tempDir, entryName.substring(folderInJar.length()));
                    if (entry.isDirectory()) {
                        f.mkdirs();
                    } else {
                        f.getParentFile().mkdirs();
                        try (InputStream is = jar.getInputStream(entry)) {
                            Files.copy(is, f.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }
            }
            jar.close();
            return tempDir;
        }
        throw new IOException("Unsupported protocol: " + url.getProtocol());
    }
}
